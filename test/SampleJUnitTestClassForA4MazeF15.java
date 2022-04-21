
import junit.framework.TestCase;

/**
 * Example of a JUnit test class for the A4Maze project.
 * This class is intended to demonstrate how JUnit tests can be written.
 * It does not perform comprehensive testing of the getPath method, and
 * does not test the getMaze method at all.
 * 
 * @author CSC 143 vo
 * @version F'15, UPDATED 10/25/15
 */
public class SampleJUnitTestClassForA4MazeF15 extends TestCase {
  
  // master test maze [static variables are OK in a test class]
  private static final int[][] master = 
  { {99,99,99,99,99,99,99,99,00,99},
    {99,05,00,00,00,99,99,99,01,99},
    {99,99,99,06,99,00,00,02,00,00},
    {99,00,00,97,00,00,00,99,99,99},
    {99,99,00,99,99,00,99,99,99,99},
    {99,00,00,00,00,99,98,99,99,99},
    {99,00,99,99,99,00,07,99,99,99},
    {99,00,03,00,00,06,99,99,99,99},
    {99,00,99,99,99,99,99,99,99,99}
  };
  
  private static final int[][] master2 = 
  { {00,00,00,00,99,99},
    {99,99,00,99,00,00},
    {00,00,97,00,00,00},
    {99,00,99,99,00,99},
    {00,00,00,00,99,98},
    {00,99,99,99,00,00},
    {00,00,00,00,00,99},
  };
  
  // working test maze [instance variables are OK in a test class]
  private int[][] maze;
  
  // return a copy of the master maze array [private helper/utility methods are OK in a test class]
  private int[][] getMazeCopy(int[][] orig) {
    int[][] result = new int[orig.length][orig[0].length];
    for (int i = 0; i < orig.length; i++)
      for (int j = 0; j < orig[0].length; j++)
        result[i][j] = orig[i][j];
    return result;
  }
  
  // get a fresh copy of the master maze before each test method is called
  protected void setUp() {
    maze = getMazeCopy(master);
  }
  
  // getPath method basic test for correct path  
  public void testGetPathGood() {
    String result = A4Maze.getPath(maze);
    assertEquals("test for correct path", "WSSWSSEEEENEN", result);
    result = A4Maze.getPath(getMazeCopy(master2));
    assertEquals("test for correct path", "WSSWSSEEEENEN", result);
  }
  
  // getPath method basic test for null result when there is no path 
  public void testGetPathBlocked() {
    maze[7][3] = 99; // block path
    String result = A4Maze.getPath(maze);
    assertNull("test for correct path", result);
  }
  
  // getPath method second test for correct path 
  public void testGetPathAlternate() {
    maze[7][4] = 99; // block original path
    maze[6][4] = 00; // open new path
    String result = A4Maze.getPath(maze);
    assertEquals("test for correct path", "WSSEESEEN", result);
  }
  
  // getPath method test for proper exception thrown when the maze is invalid 
  public void testGetPathBadMazeArray() {
    maze[5][6] = 99; // remove target
    try {
      String result = A4Maze.getPath(maze);
      // should not get here
      fail("expected IllegalArugmentException not thrown");
    } catch (IllegalArgumentException e) {
      // no action -- this is the expected result
    } catch (Exception e) {
      fail("incorrect exception thrown (expected IllegalArugmentException): " + e.getClass());
    }
  }
  
  // getPath method test for proper exception thrown when the parameter is null 
  public void testGetPathNullParameter() {
    try {
      String result = A4Maze.getPath(null);
      // should not get here
      fail("expected IllegalArugmentException not thrown");
    } catch (IllegalArgumentException e) {
      // no action -- this is the expected result
    } catch (Exception e) {
      fail("incorrect exception thrown: (expected IllegalArugmentException)" + e.getClass());
    }
  }

  // test getMaze with a bad file name
  public void testGetMazeBadFileName() {
    System.out.println("\ntestGetMazeBadFileName -- output produced:");
    try {
      int[][] actualResult = A4Maze.getMaze("___BAD_fiLE_nAmE.txt");
      assertNull("getMaze result with bad file name", actualResult);
    } catch (Exception e) {
      fail("getMaze threw an unexpected exception: \n" + e.toString());
    } finally {
      System.out.println("testGetMazeBadFileName -- end.");
    }
  }
  
}