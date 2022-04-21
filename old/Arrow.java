import java.awt.*;

/**
 * this class will deal with drawing a Arrow with given data
 * @author Lengfan Yan
 * @version Assignment 2, 2015/1/22
 */ 

public class Arrow extends AbstractShape{
  //int x_axis;
  // int y_axis;
  //int height;
  
  /*constructor
   * @param x tip of x_axis of the shape(left corner)
   * @param y tip of y_axis of the shape(upper corner)
   * @param h height of shape
   */ 
  public Arrow(int x, int y, int h){
    super(x, y, h);
    x_axis = x;
    y_axis = y;
    if (h % 2 != 0){throw new IllegalArgumentException("The height of Arrow must be a multiple of 2");}
    height = h;
  }
  
  //paint Arrow
  public void paintComponent(Graphics g){
    //super.paintComponent(g);
    Polygon arrow = new Polygon();
    arrow.addPoint(x_axis ,y_axis);
    arrow.addPoint(x_axis + height,y_axis + height/2);
    arrow.addPoint(x_axis ,y_axis + height);
    g.setColor(cor);
    g.fillPolygon(arrow);
  }
  /**
   * check is the point on graph
   * @param x x-coordinate of point want to check
   * @param y y-coordinate of point want to check
   * @return true if this Shape is selected, false if not.
   */ 
  public boolean isOn(int x, int y){
    if (x>=x_axis && x<=x_axis +height && y>= y_axis && y<= y_axis + height){       //check if is in shape box
      
    int nx = x - x_axis;
    int ny = y - y_axis;
    if( ny <= height/2 && nx / ny <= 2){return true;}                            // check top half of arrow
    int ny2 = y - y_axis - (y - y_axis  - height/2)*2;
    if(ny2 <= height/2 && nx/ny <= 2){return true;}
    }
    return false;}
  
  /*
   * @return String representation of the shape's state
   */ 
  public String toString() {
    return ("The tip of Arrow is " + x_axis +", "+y_axis +". It's height " + height + ". it's Color: "+ cor) ;
  }
}