import java.awt.*;
import javax.swing.*;
//import java.awt.event.*;
/**
 * this is the test code for drawingboard
 * 
 * @author Lengfan Yan
 * @version Assignment 3, 2015/10/24
 */ 
public class GraphicsEditorApp extends JPanel {
  //private Shape s;
  
  public static void main( String[] args){
    JFrame win = new JFrame(" Graph Shapes");
    win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    DrawingBoard d = new DrawingBoard();
    controller1 pan = new controller1(d);
    //win.setPreferredSize(new Dimension(600,500));
    
    win.getContentPane().add(pan);
    //Circle c = new Circle(0,0,30);
   // Tee t = new Tee(165,25,50);
    //d.addshape(c);
    //d.addshape(t);
    
    
    
    //cr.setPreferredSize(new Dimension(400,400));
    //win.getContentPane().add(cr);
    
    win.pack();
    win.setVisible(true);
  }
}