import java.awt.*;
/**
 * Interface for AbstractShape class
 * @author Lengfan Yan
 * @version Assignment 2, 2015/10/14
 */ 

public interface Shape {
  /**
   * check is the point on graph
   * @param x x-coordinate of point want to check
   * @param y y-coordinate of point want to check
   * @returns true if the point (x,y) is on the Shape, false if not.
   */ 
  public boolean isOn(int x, int y);
 
  /**
   * check is shape selected
   * @return true if this Shape is selected, false if not.
   */ 
  public boolean isSelected();
  
  /**
   * sets this Shape to be selected (true) or deselected (false).  
   * @param b set selected state
   */ 
  public void setSelected(boolean b);

  /**
   * get method for Color
   * @return Color of shape
   */ 
  public java.awt.Color getColor();
  
  /**
   * set method for Color
   * @param c the color want to set
   */ 
  public void setColor(java.awt.Color c);
  
  /**
   * use to shift shape's up left corner
   * @param deltaX the x-axis wish to shift from its position 
   * @param deltaY the y-axis wish to shift from its position
   */ 
  public void shiftUpperLeftBy(int deltaX, int deltaY);

   /**
   * move the upper-left corner of of the "bounding box" of this Shape to the new location.
   * @param newX the x-axis wish move to
   * @param newY the y-axis wish move to
   */ 
  public void moveUpperLeftTo(int newX, int newY);
  
  /*
   * String representation of the shape's state
   */ 
  public String toString();
  // paintComponent
  public void paintComponent( Graphics g);
}
