import java.awt.*;

/**
 * this class will deal with drawing a Tee with given data
 * 
 * @author Lengfan Yan
 * @version Assignment 2, 2015/10/14
 */ 

public class Tee extends AbstractShape{
  //private int x_axis;
  //private int y_axis;
  //private int height;
  
  /**
   * constructor
   * @param x x_axis where left corner of whole shape
   * @param y y_axis where top corner of whole shape
   * @param h height of whole shape
   */ 
  public Tee(int x, int y, int h){
    super(x,y,h);
    x_axis = x;
    y_axis = y;
    if (h % 10 != 0){throw new IllegalArgumentException("The height of Tee must be a multiple of 10");}
    height = h;
  }
  //paint Tee
  public void paintComponent(Graphics g){
    //super.paintComponent(g);
    g.setColor(Color.green);
    g.fillRect(x_axis , y_axis, height, height/5);
    g.fillRect(x_axis + height/5*2 , y_axis, height/5, height);
  }
  
  /**
   * check is the point on graph
   * @param x x-coordinate of point want to check
   * @param y y-coordinate of point want to check
   * @return true if this Shape is selected, false if not.
   */ 
  public boolean isOn(int x, int y){
    int nx = x;
    int ny = y;
    
    if(nx >= x_axis && nx <= x_axis + height){
      if(ny >= y_axis && ny <= y_axis + height/5){ return true;}}           //check top rect
    if(nx >= x_axis + height/5*2 && nx <= x_axis + height/5*3){
      if(ny >= y_axis && ny <= y_axis + height){return true;}}              // check mid rect
    return false;
  }
  
  
  /*
   * @return String representation of the shape's state
   */ 
  public String toString() {
    return ("Upper left corner of Tee is " + x_axis +", "+y_axis +". It's height " + height +". it's Color: "+ this.getColor()) ;
  }
}