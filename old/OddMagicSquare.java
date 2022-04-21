/**
 * This is a code will ask user to input a positive odd number for the size to create a oddMagicSquare and shows it.
 * And with a build-in method that can test a 2d interger array is a MagicSquare or not.
 * @author Lengfan Yan
 * @version Assignment 1, 2015/10/5
 */ 

public class OddMagicSquare{
  private int[][] create;
  
  /**
   * construct a MagicSquare of order n.n is a positive odd number, 
   * @param n size of MagicSquare
   * @throws IllegalArgumentException if user input not positve and odd
   */ 
  public OddMagicSquare(int n){
    if(n<0 || n%2 == 0) {                                                       //check excetion
      System.out.println("must enter a positive odd number.() ");
    }
    
    else{
      create = new int[n][n];   
      int start = n/2;
      int row = start;
      int column = 0;
      
      create[row][column] = 1;                                                    // strat with 1 in right place
      
      for(int i = 2; i<=(n*n); i++){                                              //create MagicSquare
        if(row == n-1){row = 0;}
        else{row = row+1;}
        if(column == 0){column = n-1;}
        else{column = column-1;}
        
        if(create[row][column] == 0){
          create[row][column] = i;
        }
        
        else{
          row = row-1;
          if(row < 0){row = row + n;}
          column = column+2;
          if(column>=n){column = column - n;}
          create[row][column] = i;
        }
      }
    }
  }
  
  /**
   * This method test the 2-d array is a magicSquare or not, return true if it is.
   * @param a the MagicSquare which wants to test on
   * @return bollean value for is a MagicSquare or not
   */ 
  public static boolean isMagic(int[][] a){
    int length = a.length;
    int ans = length * (length*length + 1) / 2;
    int sum;
    //int rows = 0;
    int columns = 0;
    
    //check is a square or not
    for(int i = 0; i< length; i++){                                           
        columns = a[i].length;
        if(columns != length){return false;}
      }
    
    //check sum of rows
    for(int i = 0; i< length; i++){                                        
      sum = 0;
      for(int j = 0; j<length; j++){
        sum = sum + a[i][j];
      }
      if(sum != ans){return false;}
    }
    
     //check sum of columns
    for(int j = 0; j< length; j++){                                             
      sum = 0;
      for(int i = 0; i<length; i++){
        sum = sum + a[i][j];
      }
      if(sum != ans){return false;}
    }
    
    sum = 0;
    //check sum diagonally
    for(int i = 0; i< length; i++){                                             
      sum = sum + a[i][i];
    }
    if(sum != ans){return false;} 
    
    //check if all wished number appear
    int[] checknum = new int[length * length];
   // for(int i =0; i< (length * length); i++){
   //  checknum[i] = i+1;
   // }
    
    int c = 0;
    int c2 = 0;
    
    for(int i = 0; i< length; i++){                                        
      for(int j = 0; j< length; j++){
        checknum[c] = a[i][j];
        c++;
      }
    }
    
    
    for(int j = 0; j< length*length; j++){
      for(int i = 0; i< length*length; i++){
        ///////
        }
      }
    }
    
    System.out.println(c2);
    if(c2 != length*length){//System.out.println(c2);
      return false;}
    
    return true;
  }
  
  /**
   * @return the String representation
   */
  public String toString(){
    for ( int i = 0; i < create.length; i++) {
      System.out.printf(java.util.Arrays.toString(create[i]) + "\n");
    }
    return "";
  }
  
  /**
   * testing and running
   */ 
  public static void main(String[] args){
    int input;
    
    do{
      java.util.Scanner in = new java.util.Scanner(System.in);
      System.out.println("Please enter a postive odd number to create an OddMagicSquare, press 0 for exit.");
      System.out.print("Enter a number: ");
      input = in.nextInt();
      
      if(input == 0){break;}                                      //exit key
      while(input<0 || input%2 == 0){                             //check if the input number able to make an oddmagicsquare.
        System.out.println("! Must enter a positive odd number, press 0 for exit.");
        System.out.print("Enter a number: ");
        input = in.nextInt();
        if(input == 0){break;}                                    //exit key
      }
      if(input != 0){                                       
      //else{
        OddMagicSquare magic = new OddMagicSquare(input);
        System.out.println("Here is your MagicSquare: ");
        magic.toString();
        in.close(); 
      }
    }while(input != 0);                                          // loop user code till exit key
  }
}