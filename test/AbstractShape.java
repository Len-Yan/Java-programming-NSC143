import java.awt.*;
import javax.swing.*;
/**
 * A Abstract base class of Shape holds all common behavior class Delta, Circle, and Tee have 
 * 
 * @author Lengfan Yan
 * @version Assignment 2, 2015/10/14
 */ 

public abstract class AbstractShape extends JPanel implements Shape{
  int x_axis;
  int y_axis;
  int height;
  private boolean selected;
  private java.awt.Color cor; //= java.awt.Color.gray;
  
  /** constructor
   * @param x location of x_axis of the shape
   * @param y location of y_axis of the shape
   * @param h height of shape
   */ 
  public AbstractShape(int x, int y, int h){
    //super();
    x_axis = x;
    y_axis = y;
    height = h;
  }
  /**
   * check is the point on graph(use for subclass)
   * @param x x-coordinate of point want to check
   * @param y y-coordinate of point want to check
   * @return returns true if the point (x,y) is on the Shape, false if not.
   */ 
  public abstract boolean isOn(int x, int y);
   
  /**
   * @param b set selected state
   */ 
  public boolean isSelected() {
    return selected;
  }
  
  /**
   * sets this Shape to be selected (true) or deselected (false)
   * @param b the state wish to set.
   */ 
  public void setSelected(boolean b) {
    selected = b;
  }
  
  /*
   * get color method
   * @return color of shape
   */ 
  public java.awt.Color getColor(){
    return cor;
  }
  /**
   * change color of the shape
   * @param c the color wish to change
   */ 
  public void setColor(Color c){  
    cor = c;
  }
  
  /**
   * use to shift shape's up left corner
   * @param deltaX the x-axis wish to shift from its position 
   * @param deltaY the y-axis wish to shift from its position
   */ 
  public void shiftUpperLeftBy(int deltaX, int deltaY) {
    x_axis = x_axis + deltaX;
    y_axis = y_axis + deltaY;
  }
  /**
   * use to move shape's up left corner
   * @param newX the x-axis wish move to
   * @param newY the y-axis wish move to
   */ 
  public void moveUpperLeftTo(int newX, int newY) {
    x_axis = newX;
    y_axis = newY;
  }
  
  //String representation of the shape's state
  public abstract String toString(); //{
    //return ("x: " + x_axis + ", y: " + y_axis +", h: " + height);
  //}
  public void paintComponent( Graphics g){}
}