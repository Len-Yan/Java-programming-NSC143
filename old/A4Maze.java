import java.util.*; // for Scanner
import java.io.*;   // for File, PrintWriter
/**
 * Assignment 4
 * user can open a txt file with data of path amze to find the path from A to B.
 * uses recursive backtracking to find a path through a text file or indicate that no path exists.
 * 
 * @author Lengfan Yan
 * @version Assignment 4  11/4
 */
public class A4Maze{
  
  //testing
  public static void main (String[] args) { 
    int[][] m = 
    { {3,0,97,0,0,3,0,0,0,0},
      {0,99,99,0,99,99,99,0,99,99},
      {0,0,99,0,99,0,0,0,0,0},
      {99,99,99,99,99,99,99,99,99,0},
      {99,8,99,0,0,0,5,0,99,0},
      {99,0,99,0,99,99,99,0,99,0},
      {0,7,0,0,0,0,99,0,99,0},
      {0,99,99,99,99,99,99,0,0,0},
      {0,2,1,0,99,0,0,99,99,4},
      {0,99,99,0,0,0,98,0,99,0},
    };
    A4Maze.getPath(m);
  }
  
  
  
  /**
   * read a file and take that file's infor if it is right file
   * @param filename the name of file that needs to get infor
   * @return give a 2d array for the file reading
   */ 
  public static int[][] getMaze(String filename) {
    int row = 0;
    int colum = 0;
    int n = 0;
    int m = 0;
    String line = "";
    boolean Bcheck = false;
    boolean Acheck = false;
    boolean passcheck = true;
    int[][] mat;
    
    try{
      File file = new File(filename);
      Scanner infile = new Scanner(file);                        //read file
      
      if (infile.hasNextLine()){                                 
        m++;
        line = infile.nextLine();
        while(infile.hasNext()){                                 //get n of maze (colum)
          n++;
          infile.next();
        }
      }
      
      while (infile.hasNextLine()){                              //get m of maze (row)
        m++;
        line = infile.nextLine();
      }
      
      mat = new int[m][n];
      int numtest;
      
      infile.close();
      infile = new Scanner(file);                                //re-open file
      
      while(infile.hasNext()){                                   //reading data
        
        if(infile.hasNextInt()){                                 //check for int 0-9
          numtest = infile.nextInt();
          if(numtest>= 0 && numtest <10){
            mat[row][colum] = numtest;
            colum++;
          }
          else { infile.close();
            throw new IllegalArgumentException("#");}
        }
        
        else if(infile.next().equals("A")){                      //set A to 97 in maze
          infile.next();
          if (Acheck = true) {
            infile.close();
            throw new IllegalArgumentException("A");             //check for exactly one A
          }                                                       
          else{
            Acheck = true;
            mat[row][colum] = 97;
            colum++;
          }
        }
        else if(infile.next().equals("B")){                      //set B to 98
          infile.next();
          if (Bcheck = true) {
            infile.close();
            throw new IllegalArgumentException("B");             //check for exactly one B
          }                     
          else{
            Bcheck = true;
            mat[row][colum] = 98;
            colum++;
          }
        }
        else if(infile.next().equals("X")){                      //set X to 99
          infile.next();
          mat[row][colum] = 99;
          colum++;
        }
        else {
          infile.close();                                        // if anything else in file, bad data
          throw new IllegalArgumentException("Q");
        }                                      
        
        if(colum == n){colum = 0; row++;}                       //reset colum if reach end of n and go to next row
        
      }
      infile.close();
      if(Bcheck == false || Acheck == false) {return null;}      // no strat or end point 
      return mat; 
    }
    
    catch(FileNotFoundException e) {
      System.err.println("File: " + filename +"   not found.")  ;
      return null; }
    catch(IllegalArgumentException e) {
      System.err.println("Bad data in file");
      return null; }
    catch(RuntimeException e) {
      System.err.println("something with file goes wrong here");
      return null; }
    catch(Exception e) {
      System.err.println("not sure what wrong here");
      return null; }
  }
  //-------------------------------------------------------------------------------------------------------------------------
//  private static void blockIt(int[][] maze,int i, int j){
//    maze[i][j] = 99;
//  }
//  
//  private static void unblockIt(int[][] maze,int i, int j){
//    maze[i][j] = 0;
//  }
  
  //--------------------------------------------------------------------------------------------------------------------------
  
  /**
   * starting method for recursive method to getpath of maze
   * @param meze  the 2d array used for infor of maze
   * @return give the path from start to end
   */ 
  public static String getPath(int[][] maze){
    int A = 0;
    int B = 0;
    boolean check09x = true;
    
    //throw exception if parameter null
    if (maze == null) {throw new IllegalArgumentException("null?");}         
    
    //check data
    for(int i = 0; i< maze.length; i++){
      for(int j = 0; j< maze[0].length; j++){
        if (maze[i][j] == 97){ A++; }                    //check number of start point
        else if (maze[i][j] == 98){ B++; }               //check number of end point
        
        // check if data is in range 0-9, 97-99
        else if (maze[i][j]<0 || maze[i][j] > 9){ 
          if(maze[i][j] >= 97 && maze[i][j] <= 99){ }
          else{ check09x = false;}
        }
      }
    }
    if( A != 1 || B != 1){throw new IllegalArgumentException("start, end problem");}
    if(check09x == false){throw new IllegalArgumentException("bad data");}
    
    
    int Start = 97;
    int End = 98;
    int Block = 99;
    int row = 0;
    int colum = 0;
    ArrayList<String> curpath = new ArrayList<String>();
    
    
    //store the starting location in maze
    for(int i = 0; i< maze.length; i++){
      for(int j = 0; j< maze[0].length; j++){
        if(maze[i][j] == 97){row = i; colum = j;}
      }}
    
    //go to recursive method
    getPath(maze, row, colum, End, curpath);
    
    
    if (Start == End){                                                         
      return "good" ;}
    
    return "bad";
  }
  
  //----------------------------------------------------------------------------------------------------------------------
  
  /**
   * recursive method to find path of Start to End from a 2d array
   * @param maze  the 2d array used for inofor
   * @param curpath store the anw, which is the path are currently going to end
   * @param end the target position.
   * @return greturn the array (curpath) 
   */ 
  
  public static String getPath(int[][] maze, int row, int colum, int end, ArrayList<String> curpath){
    //int row;
    //int colum;
    // boolean moveable = true;
    String remember = "";
    // case where find path
    if(maze[row][colum] == end){ System.out.print("good");
      return "good";}
    // case where return to start point
    //if(maze[row][colum] == Start && moveable == false){return "no path";}
    else{
      // block current position
      if (maze[row][colum] != 99){ maze[row ][colum] = 99; }
      
      //test E (right)
      if (colum+1 < maze[0].length && maze[row][colum + 1] != 99){
        System.out.print("E ");
        System.out.println(row + "  " + colum);
        getPath( maze, row, colum +1, end, curpath);
        
        
        remember = "E";
        // return "E";
      }
      
      //test S (down)
      if (row+1 < maze.length && maze[row + 1][colum] != 99){
        System.out.print("S ");
        System.out.println(row + "  " + colum);
        getPath( maze, row +1, colum, end, curpath);
        
        
        remember = "S";
        // return "S";
      }
      
      //test W (left)
      if (colum-1 >= 0 && maze[row][colum - 1] != 99){
        System.out.print("W ");
        System.out.println(row + "  " + colum);
        getPath( maze, row, colum -1, end, curpath);
        
        
        remember = "W";
        // return "W";
      }
      
      //test N (up)
      if (row-1 >= 0 && maze[row - 1][colum] != 99){
        System.out.print("N ");
        System.out.println(row + "  " + colum);
        getPath( maze, row -1, colum, end, curpath);
        
        
        remember = "N";
        // return "N";
      }
      /////   HOW to stop it when it reach end, it won't go back keep call
      return "";
    }
  }
}