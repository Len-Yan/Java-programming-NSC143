import java.io.*;
import javax.swing.JFileChooser;
import java.util.*;
import javax.swing.JButton;
import java.io.File;
/**
 * read text in a file and take symbols inside and put them in a Huffman tree base on the frequence of the character.
 * encodes those symbles in the tree's leaf to numerical values of 1 and 0.
 * then print show things that related to that Huffman tree and text
 * @author Lengfan Yan
 * @version 2015/12/7
 */ 
public class Huffman{
  //ini
  private HNode root;
  private int fre;
  private String codeNum = "";
  private Map<Character, Integer> store;
  private HashMap<Character, String> code = new HashMap<Character, String>();
  private String original; 
  
  /**
   * creates a JFile chooser dialog let user choose a file and read it. 
   * store text from file and make a huffman tree, also encode it
   * then show the encode code and origianl text. with some other things related with that.
   * 
   */ 
  public Huffman(){
    //fileChooser
    File selectedFile;
    JButton open = new JButton();
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(""));
    if (fileChooser.showOpenDialog(open) != JFileChooser.APPROVE_OPTION) {
      System.out.print("the path or target file error, please try again.");
      return;
      //throw new IllegalArgumentException("the path or target file error, please try again.");
    }
    //if (fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
    selectedFile = fileChooser.getSelectedFile();
    
    //read file......
    String temp;
    store = new HashMap<Character, Integer>();
    try{
    File file = new File(selectedFile.getAbsolutePath());
    Scanner infile = new Scanner(file); 
    
    // put everything to a Map
    infile.useDelimiter("");                                                  //read text one by one
    original = "";
    Character read;
    while(infile.hasNext() == true){
      temp = infile.next();
      read = temp.charAt(0);
      original += read;
      if(store.containsKey(read) == false){                                   //if char not in map, put it in
        store.put(read, 1);
      }
      else{                                                                   //if char in map freq +1
        fre = store.get(read);
        store.put(read, (fre+1));
      }
    }
    infile.close();                                                           //end reading
    
    if(original == ""){System.out.print("no readble text in file, or bad file type \n "); return;}
    System.out.print("Original message: " + original + "\n");
    
    // add everything to PriorityQueue
    PriorityQueue<HNode> hn = new PriorityQueue<HNode>();     
    for(Map.Entry<Character, Integer> entry : store.entrySet()){
      hn.add(new HNode(entry.getKey(), entry.getValue(), null, null));
    }
    while(hn.size() > 1){
      HNode one = hn.poll();                                                  //take the low freq 2 HNode out
      HNode two = hn.poll();                                                  //make new HNode put back to Queue
      HNode com = new HNode( null, (one.frequn + two.frequn), one, two);
      hn.add(com);
    }
    root = hn.peek();                                                         //store root reference
    
    //encoding
    code = encode();
    System.out.println("\n" + "Huffman Tree: ");
    tree(root,0);
    System.out.println("");
    printTable(store, code);
    
    String em = "";                                                           //code after encode will sotre
    temp ="";
    Character co;
    infile = new Scanner(file);                                               //reopen file and code message
    infile.useDelimiter("");
    while(infile.hasNext() == true){
      temp = infile.next();
      co = temp.charAt(0);
      if(code.containsKey(co) == true){                                       //if original text match encoding system
        em += code.get(co);
      }
      else{ infile.close();                                                   //throw exception if don'e match
        throw new RuntimeException("something wrong with encoding or the text file");
      }
    }
    //printing part of result
    System.out.println("Encoded message: \n" + em + "\n");
    infile.close();
    
    String dec = decode(em, code);
    System.out.println("Decoded message: \n" + dec + "\n");
    
    double avg = (double)em.length()/original.length();
    System.out.println("Average # of bits/symbol: " + avg);
    }
    catch(FileNotFoundException e){//throw new IllegalArgumentException("the path or target file error, please try again.");}
      System.out.print("the path or target file not found, please try again.");}
  }
//=======================================================================================================
  
  /**
   * kick off method for recursive method that encode the huffman tree
   * @return return a hashmap that contains the character infor and it's encode infor
   */
  public HashMap<Character, String> encode(){                                //doesn't need return since Map is filed
    encode(root);
    return code;
  }
  // recursive method of encode()
  private void encode(HNode thisroot){                                       //put all char and its code to Map
    HNode temp = thisroot;
    if(thisroot == null){return;}
    
    if(temp.left != null){
      codeNum = codeNum + "0";                                               //code change when go deep 
      encode(temp.left);
      codeNum = codeNum.substring(0, (codeNum.length()-1));                  //reduce code when backoff
    }
    if(temp.right != null){
      codeNum = codeNum + "1";
      encode(temp.right);
      codeNum = codeNum.substring(0, (codeNum.length()-1));
    }
    if(temp.left == null && temp.right == null){
//      //newline -- \n
//      if((int)temp.chara == 10){ System.out.println("\\n" + "  " + codeNum);
//      }
//      //space -- [ ]
//      else if(temp.chara == ' '){ System.out.println("[ ]" + "  " + codeNum);
//      }
//      //empty == []
//       else if((int)temp.chara == 13){ System.out.println("[]" + "  " + codeNum);
//      }
//      else{
//        System.out.println(temp.chara + "   " + codeNum);}
      if(codeNum == ""){codeNum = "0";}
      code.put(temp.chara, codeNum);    ////////////
    }
  }
  /**
   * print a Table of Symbol / Frequency / Code
   *  use "\n" for newline character, "[ ]" for space, "[]" for potential null character(int 13)
   * @param data1 the map of stores Character and frequnency infor
   * @param data2 the map of stores Character and coding infor
   */ 
  public void printTable(Map<Character, Integer> data1, HashMap<Character, String> data2){
    Character temp;
    String out = "";
    System.out.println("Symbol / Frequency / Code  Table: ");
    for(Map.Entry<Character, Integer> entry : data1.entrySet()){
      out = "";
      temp = entry.getKey();
      out += temp;
      //newline -- \n
      if((int)temp == 10){ out = ""; out = "\\n"; }
      //space -- [ ]
      if(temp == ' '){ out = ""; out = "[ ]"; }
      //empty(null?) -- []
      if((int)temp == 13){ out = ""; out = "[]"; }
      //tab -- \t
      if((int)temp == 9){ out = ""; out = "\\t"; }
      System.out.println(String.format("%-11s%s", out , data1.get(temp) + "         " + data2.get(temp)));
    }
  }
  /**
   * method to decode the coded message.
   * @param cod  the coded message that passing to decode it
   * @param data the HashMap<Character,String> that contains the coding infor
   * @return give back the String of decoded message
   */ 
  public String decode(String cod, HashMap<Character, String> data){
    String ans = "";
    String temp = "";
    HashMap<String, Character> redata = new HashMap<String, Character>();         //create a Map that for decoding
    for(Map.Entry<Character, String> entry : data.entrySet()){
      redata.put(entry.getValue(), entry.getKey());
    }
    
    for(int i = 0; i< cod.length(); i++){
      temp += cod.charAt(i); 
      if(redata.containsKey(temp) == true){
        ans += redata.get(temp);
        temp = "";
      }
    }
    return ans;
  }
  
  
  /**
   * this method use the top root of Tree to print out Huffman Tree
   * @param rot the root of HNode
   * @param deep deep is the depth of each HNode, it helpe to draw the Tree
   */ 
  public void tree(HNode rot, int deep){
    if(rot == null){return;}
    deep++;
    System.out.print("node (" + rot.frequn + ")" );
    if(rot.chara != null){
      //special case(\n = newline; [] = empty(null); [ ] = space}; \t = tab)
      if((int)rot.chara == 10){System.out.print(" '" + "\\n" + "' ");}
      else if((int)rot.chara == 13){System.out.print(" '" + "[]" + "' ");}
      else if(rot.chara == ' '){System.out.print(" '" + "[ ]" + "' ");}
      else if((int)rot.chara == 9){System.out.print(" '" + "\\t" + "' ");}
      else{
        System.out.print(" '" + rot.chara + "' ");}
    }
    System.out.println("");                                                           
    if(rot.left != null){
      for(int i = 1; i<= deep; i++){
        System.out.print("     ");
      }
      tree(rot.left,deep); 
    }
    if(rot.right != null){
      for(int i = 1; i<= deep; i++){
        System.out.print("     ");
      }
      tree(rot.right,deep); 
    }
    deep--;
  }
  
  //run the huffman
  public static void main(String[] args) throws IOException{
    Huffman a = new Huffman();
  }
  
//======================================================================================================================
  /**
   * class that use HNODE as linked tree strcuture in Huffman to create a Huffman tree
   * it store character value and frequence of that character with 2 able child nodes position.
   * also implements CompareTo() method for ablity to compare the frequn in HNode
   */ 
  class HNode implements Comparable<HNode> {
    Character chara;
    int frequn;
    HNode left;
    HNode right;
    
    /**
     * constructor of HNode
     * @param chara character that store in this node
     * @param frequn  int value of times it appear of this chara
     * @param left link tree structure of node that is after it(1)
     * @param right link tree structure of node that is after it(2)
     */ 
    public HNode(Character chara, int frequn, HNode left, HNode right){
      this.frequn = frequn;
      this.chara = chara;
      this.left = left;
      this.right = right;
    }
    
    /**
     * compare 2 HNode's character
     * @param a the HNode that wish to campare with
     * @return give back the int value that this 2 HNode.character differ
     */ 
    public int compareTo(HNode a){
      return this.frequn - a.frequn;
    }
  }
}