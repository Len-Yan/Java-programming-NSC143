import java.util.*; // for Scanner
import java.io.*;   // for File, PrintWriter
/**
 * Assignment 3
 * user can open a txt file with data of path graph to find the path through nodes.
 * uses recursive backtracking to find a path through a graph or indicate that no path exists.
 *       there is a big error that the recursive method does not stop even it reaches return call.
 * @author Lengfan Yan
 * @version Assignment 3  2/12
 */
public class A4Graph{
  
  //testing
//  public static void main (String[] args) { 
//    int[][] m = 
//    { {0,0,5,4,0,0,0},
//      {3,0,0,9,0,0,0},
//      {2,0,0,0,0,4,0},
//      {0,0,0,0,0,8,2},
//      {1,0,2,0,0,7,0},
//      {0,0,0,0,0,0,0},
//      {0,2,0,0,0,0,0},
//    };
//    A4Graph.getPath(m,1, 5);
//  }
  
  
  
  /**
   * read a file and take that file's infor to a Matrix if it is right file
   * @param filename the name of file that needs to get infor
   * @return give a 2d array for the file reading
   */ 
  public static int[][] getMatrix(String filename) {
    int row = 0;
    int colum = 0;
    int size = 0;
    String line = "";
    int[][] mat;
    
    try{
      File file = new File(filename);
      Scanner infile = new Scanner(file);
      
      if (infile.hasNextLine()){                              //get size of matrix
        line = infile.nextLine();
        size = Integer.parseInt(line);
      }
      
      mat = new int[size][size];
      
      while(infile.hasNext()){                                 //reading data
        if(infile.hasNextInt()){
          mat[row][colum] = infile.nextInt();
          colum++;
          if(colum == size){colum = 0; row++;}                 //reset colum if reach size and go to next row
        }
        infile.next();
      }
      infile.close();
      return mat; 
    }
    
    catch(FileNotFoundException e) {
      System.err.println("File: " + filename +"   not found.")  ;
      return null; }
    catch(RuntimeException e) {
      System.err.println("something with file goes wrong here");
      return null; }
    catch(Exception e) {
      System.err.println("not sure what wrong here");
      return null; }
  }
  
  //--------------------------------------------------------------------------------------------------------------------------
  
  /**
   * starting method for recursive method to getpath of graph
   * @param adjacencyMatrix  the 2d array used for infor of graph
   * @param startNode the place start the path
   * @param endNode the ending place of path
   * @return give the path from startNode to endNode
   */ 
  public static String getPath(int[][] adjacencyMatrix, int startNode, int endNode){
    //try{
    if (adjacencyMatrix == null) {throw new IllegalArgumentException();}
    if (startNode < 0 || startNode >= adjacencyMatrix[0].length || endNode <0 || endNode >= adjacencyMatrix[0].length){
      throw new IllegalArgumentException();}
    // }
//    catch(IllegalArgumentException e){
//      System.err.print("IllegalArgumentException, file can't be null, start and end Node need in range of 0 and size of graph");
//      return null;}
    
    ArrayList<Integer> block = new ArrayList<Integer>();
    ArrayList<Integer> curpath = new ArrayList<Integer>();
    ArrayList<Integer> stand = new ArrayList<Integer>();
    
    if (startNode == endNode){                                                         //done if starNode equal to endNode
      //   System.out.println(startNode);                      // Testing   print node if start same with end
      return Integer.toString(endNode);}
    else{
      try{
        stand.addAll(   getPath(adjacencyMatrix, block , curpath, startNode, endNode)  );         
      }
      catch(NullPointerException e){  //System.err.print("!no path!");                 //if answer returned is null, print no path
      }                   
      
      //System.out.println(stand.size());                       // Testing
      if(stand.size() == 0){                                                           //print no path if size 0 
        //System.out.print("    no path");                      // Testing      print no path if answer array size 0
        return "no path";}
      
      String ans =Integer.toString(startNode);
      for(int i = 1; i<stand.size(); i++){
        ans += "->" + Integer.toString(stand.get(i));}
      
      //System.out.println(ans);                                // Testing
      return  ans;}
  }
  
  
  //----------------------------------------------------------------------------------------------------------------------
  
  /**
   * recursive method to find path of one node to another from a 2d array
   * @param m  the 2d array used for inofor
   * @param block store the nodes that are passed, block them.
   * @param curpath store the anw, which is tht path are currently going to endNode
   * @param cur the Node now at
   * @param end the target node.
   * @return greturn the array (curpath) 
   */ 
  private static ArrayList<Integer> getPath(int[][] m,ArrayList<Integer> block,ArrayList<Integer> curpath, int cur, int end){
    ArrayList<Integer> AL = new ArrayList<Integer>();
    
    if (block.contains(cur) == false){     
      //System.out.print("    !"+cur+ "!   ");                // Testing    tracking what node are processing 
      block.add(cur);                                                   //set current to block --can't access if was blocked
    }
    if (curpath.contains(cur) == false){                                //put this node to the answer path temporarly
      curpath.add(cur);  
    }
    
    if(cur == end){                                                     // find path if current node equal to endNOde
      //        TEST with answer print by System.out          // Testing
      //System.out.println("1 DONE 1");                                 //print DONE if reach this part and method should end
      //System.out.println(curpath.size() + "!!");                      //print size of curpath when it should end
      //System.err.print(curpath.get(0));                               //print the answer
      //for(int q =1; q<curpath.size(); q++){
      //  System.err.print("->" + curpath.get(q)); 
      //}     
      AL.addAll(curpath);
      return AL;}
    
    else{
      if(block.size() == m[cur].length){                                // if block all node, return no path,   should not reach it.
       // System.err.print( "no path1 ");                     // Testing    print no path situation 1
        return null;
      }
      int temp = 0;
      
      for(int i = 0; i<m[cur].length; i++){                             //loop all potential path of this node
        for(int s = 0; s<block.size(); s++){                            //loop all passed node
          if (i == block.get(s)){ i++;                                  //if i == any value in path, skip
          }}
        
        if (i==7){// System.err.print( "no path2 ");          // Testing   print no path situation 2(error)
          return null;
        }
        
        if(m[cur][i] != 0){                                             // able to move to other node if not 0
          temp = i;
          getPath(m, block ,curpath, temp, end);                        // recursive
        }
      }
      
      curpath.remove(curpath.size()-1);                                 // un choose the path if no way to go
      temp = curpath.get(curpath.size()-1);                             // set cur equal to last node
      getPath(m, block ,curpath,temp, end);                             // recursive     go back find other path
    }
    //System.err.print( "no path3 ");                         //testing  print no path situation 3
    return null;                                                        // return null if no path to end Node
  }
}