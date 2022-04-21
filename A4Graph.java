import java.util.*; // for Scanner
import java.io.*;   // for File, PrintWriter
/**
 * 
 * uses recursive backtracking to find a path through a graph or indicate that no path exists
 * 
 * @author Lengfan Yan
 * @version Assignment 3  2/12
 */
public class A4Graph{
  
  /**
   * read a file and take that file's infor to a Matrix if it is right file
   * @param filename the name of file that needs to get infor
   * @return give a 2d array for the file reading
   */ 
  public static int[][] getMatrix(String filename) {
//    if(filename == null){//throw new IllegalArgumentException("param cannot be null.");
//      System.err.println("something go wrong here");
//    return null;
//    }
    
    
    int row = 0;
    int colum = 0;
    int size = 0;
    String line = "";
    int[][] mat;
    
    try{
      
      //if(filename == null){throw new IllegalArgumentException("param cannot be null.");}
      
      
      File file = new File(filename);
      Scanner infile = new Scanner(file);
      
      if (infile.hasNextLine()){                   //get size of matrix
        line = infile.nextLine();
        
        size = Integer.parseInt(line);}
      mat = new int[size][size];
      
      while(infile.hasNext()){
        if(infile.hasNextInt()){
          mat[row][colum] = infile.nextInt();
          colum++;
          if(colum == size){colum = 0; row++;}     //reset colum if reach size and go to next row
        }
        infile.next();
      }
      infile.close();
      return mat; 
      // }
    }
    
    catch(FileNotFoundException e) {
      System.err.println("File: " + filename +"   not found.")  ;
      return null; }
//    catch(IllegalArgumentException e) {
//      System.err.println("something go wrong here");
//      return null; }
    
    catch(RuntimeException e) {
      System.err.println("something go wrong here");
      return null; }
    
    //return null; 
    
  }
  
  
  
  /**
   * starting method for recursive method to getpath of graph
   * @param adjacencyMatrix  the 2d array used for infor of graph
   * @param startNode the place start the path
   * @param endNode the ending place of path
   * @return give the path from startNode to endNode
   */ 
  public static String getPath(int[][] adjacencyMatrix, int startNode, int endNode){
    
   // try{
      if (adjacencyMatrix == null) {throw new IllegalArgumentException();}
      if (startNode < 0 || startNode >= adjacencyMatrix[0].length || endNode <0 || endNode >= adjacencyMatrix[0].length){
        throw new IllegalArgumentException();}
      
    
    ArrayList<Integer> pass = new ArrayList<Integer>();
    pass.add(startNode);
    ArrayList<Integer> curpass = new ArrayList<Integer>();
    curpass.add(startNode);
    ArrayList<Integer> stand = new ArrayList<Integer>();
    
    if (startNode == endNode){ return Integer.toString(endNode);}
    else{
      //int[] p = new int[adjacencyMatrix[0].length];
      stand = getPath(adjacencyMatrix, pass , curpass, startNode, endNode);
      
      if(stand == null || stand.size() == 0){return "no path";}
      
      String ans =Integer.toString(startNode);
      for(int i = 1; i<stand.size()-1; i++){
        ans += "->" + Integer.toString(stand.get(i));}
      
      return  ans;}
        //startNode + getPath(adjacencyMatrix, pass , curpass, startNode, endNode);}
    
//    }
//    catch(IllegalArgumentException e) {
//      System.err.println("something go wrong here");
//      return null; }
  }
  
  
  
  
  
  /**
   * recursive method to find path of one node to another from a 2d array
   * @param m  the 2d array used for inofor
   * @param cur the Node now at
   * @param end the target node.
   * @return give next node that is ok to move
   */ 
  private static ArrayList<Integer> getPath(int[][] m,ArrayList<Integer> pass,ArrayList<Integer> curpass, int cur, int end){
    if(cur == end){return curpass;}
      //return Integer.toString(end);}                      //if at endNode, done
    //if(pass.size() == m[cur].length){return "no path";}
    
    else{
      if (!pass.contains(cur)){
      pass.add(cur);                                           //set current pass can't access
      }
      if (!curpass.contains(cur)){
      curpass.add(cur);  
      }
      if(pass.size() == m[cur].length){return null;}
      int temp = 0;
      
      for(int i = 0; i<m[cur].length-1; i++){                 //loop all potential pass of this node
        for(int s = 0; s<pass.size()-1; s++){                 //loop all passed node
          if (i == pass.get(s)){ i++; }}                     //if i == any value in pass, skip
        
        if(m[cur][i] != 0){                                  // able to move if not 0
          temp = i;
          //pass.add(i);
          //return ("->" + 
                  getPath(m, pass ,curpass, temp, end);              // storeing a array print onece all
        }
      }
      
      //for(int i = 0;i<m[cur].length-1; i++){}
      curpass.remove(curpass.size()-1);
      temp = curpass.get(curpass.size()-1);
      getPath(m, pass ,curpass,temp, end);
      
      pass.remove(pass.size()-1);                                       //
    }
    return null; 
  }
}