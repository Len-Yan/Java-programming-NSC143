import java.awt.*;
import javax.swing.*;
import java.util.*;
/**
 *  Graphical viewer for GraphicsEditorApp
 * 
 * @author Lengfan Yan
 * @version Assignment 3, 2015/10/24
 */ 

public class View2 extends JPanel implements VIEW{
  //private JLabel numbers;
  private DrawingBoard shapes;
  
  /**
   * constructor for garph view
   * @param d the class that store data for shape 
   */ 
  public View2(DrawingBoard d){
    super();
    shapes = d;
    shapes.addListener(this);
  }
  
  /*
   * repaint graph when notify
   */ 
  public void viewChange(){
    repaint();
  }
  
  /**
   * repaint all shape one by one
   * @param g the graphics context where the painting should take place
   */ 
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    
    java.util.ArrayList<Shape> shap = shapes.copy( );
    Iterator<Shape> it = shap.iterator( );
    while ( it.hasNext( ) ) {
      Shape sh = (Shape)it.next( );
      sh.paintComponent( g );
    }
  }
 // public void notifychange(){}
  
}