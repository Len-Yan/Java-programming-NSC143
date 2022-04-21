import java.awt.*;

/**
 * this class will deal with drawing a Delta with given data
 * @author Lengfan Yan
 * @version Assignment 2, 2015/10/14
 */ 

public class Delta extends AbstractShape{
  //private int x_axis;
  //private int y_axis;
  //private int height;
  
  /*constructor
   * @param x apex of x_axis of the shape(left corner)
   * @param y apex of y_axis of the shape(upper corner)
   * @param h height of shape
   */ 
  public Delta(int x, int y, int h){
    super(x, y, h);
    x_axis = x;
    y_axis = y;
    if (h % 2 != 0){throw new IllegalArgumentException("The height of delta must be a multiple of 2");}
    height = h;
    super.setColor(Color.red);
  }
  
  //paint Delta
  public void paintComponent(Graphics g){
    //super.paintComponent(g);
    Polygon delta = new Polygon();
    delta.addPoint(x_axis + height/2 ,y_axis);
    delta.addPoint(x_axis + height, y_axis + height);
    delta.addPoint(x_axis , y_axis + height);
    g.setColor(super.getColor());
    g.fillPolygon(delta);
  }
  /**
   * check is the point on graph
   * @param x x-coordinate of point want to check
   * @param y y-coordinate of point want to check
   * @return true if this Shape is selected, false if not.
   */ 
  public boolean isOn(int x, int y){
    if (x>=x_axis && x<=x_axis +height/2 && y>= y_axis && y<= y_axis + height){         //check if is in left half shape box
      //System.out.println("left");
    int nx = x - x_axis;
    int ny = height - (y - y_axis);
    if( ny <= 2 * nx){return true;}                                 // check left helf delta
    }
    
    else if (x>=x_axis + height/2 && x<=x_axis +height && y>= y_axis && y<= y_axis + height){       //check if is in right half shape box
     // System.out.println("right");
    int nx2 = x - x_axis - height/2;
    int ny2 = height - (y - y_axis);                                // check right half delta
    if( ny2 <= height - 2 * nx2){return true;}
    }
    return false;}
  
  /*
   * @return String representation of the shape's state
   */ 
  public String toString() {
    return ("The apex of Delta is " + x_axis +", "+y_axis +". It's height " + height + ". it's Color: "+ super.getColor()) ;
  }
}