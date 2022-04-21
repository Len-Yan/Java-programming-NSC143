import java.awt.*;

/**
 * this class will deal with drawing a Cross with given data
 * 
 * @author Lengfan Yan
 * @version Assignment 2, 2015/1/22
 */ 

public class Cross extends AbstractShape{
  
  /**
   * constructor
   * @param x x_axis where left corner of whole shape
   * @param y y_axis where top corner of whole shape
   * @param h height of whole Cross shape
   */ 
  public Cross(int x, int y, int h){
    super(x,y,h);
    x_axis = x;
    y_axis = y;
    if (h % 5 != 0){throw new IllegalArgumentException("The height of Cross must be a multiple of 5");}
    height = h;
  }
  //paint Cross
  public void paintComponent(Graphics g){
    //super.paintComponent(g);
    g.setColor(cor);
    g.fillRect(x_axis , y_axis + height/5*2, height, height/5);
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
      if(ny >= y_axis + height/5*2 && ny <= y_axis + height/5*3){ return true;}} 
    else if(nx >= x_axis + height/5*2 && nx <= x_axis + height/5*3){
      if(ny >= y_axis && ny <= y_axis + height){return true;}}
    return false;
  }
  
  
  /*
   * @return String representation of the shape's state
   */ 
  public String toString() {
    return ("Upper left corner of Cross is " + x_axis +", "+y_axis +". It's height " + height +". it's Color: "+ cor) ;
  }
}