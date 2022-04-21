import java.awt.*;

/**
 * this class will deal with drawing a Circle with given data
 * 
 * @author Lengfan Yan
 * @version Assignment 2, 2015/10/14
 */ 

public class Circle extends AbstractShape{
  //private int centerx;
  //private int centery;
  
  /**
   * constructor
   * @param x x_axis of center of circle
   * @param y y_axis of center of circle
   * @param r radius of circle
   */ 
  public Circle(int x, int y, int r){
    super(x,y,r);
    x_axis = x - r;
    y_axis = y - r;
    if (r <= 0){throw new IllegalArgumentException("I believe a circle's radius should not be negative");}
    height = r;             //height here means radius
  }
  
  //paint Circle
  public void paintComponent(Graphics g){
    //super.paintComponent(g);
    g.setColor(cor);
    g.fillOval(x_axis ,y_axis ,2*height ,2*height);
  }
  
  /**
   * check is the point on graph
   * @param x x-coordinate of point want to check
   * @param y y-coordinate of point want to check
   * @return true if this Shape is selected, false if not.
   */ 
  public boolean isOn(int x, int y){
    
   // int r2 = (x-centerx) *(x-centerx) + (y - centery) +(y - centery);
    //r2 = r2 * r2;
   // if((height * height) >= r2){return true;}
    
    int dis = (x-(x_axis+height)) * (x-(x_axis+height)) + (y - (y_axis+height)) * (y - (y_axis+height));
    if (dis <= height * height){return true;}
    else return false;
  }
  
 
  /*
   * @return String representation of the shape's state
   */ 
  public String toString() {
    return ("The center of the circle is " + (x_axis+height) +", "+(y_axis+height) +". It's radius " + height + ". it's Color: "+ cor) ;
    
  }
}