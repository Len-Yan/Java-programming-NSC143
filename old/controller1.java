import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.util.*;

/**
 * controller for shape to do things with the view window and handle user action
 * 
 * @author Lengfan Yan
 * @version Assignment 3, 2015/10/24
 */ 

public class controller1 extends JPanel {
  //ini
  private view1 textview;
  private view2 graphview;
  private DrawingBoard window;
  private buttonlistener butlis;
  private mouselistener mouslis;
  
  /** constructor for DrawingBoard
   * @param d the DrawingBoard object pass in
   */ 
  public controller1(DrawingBoard d){
    window = d;
    setLayout( new BorderLayout( ) );
    
    graphview = new view2(window);
    graphview.setPreferredSize(new Dimension(500,400));
    textview = new view1(window);
    add(graphview, BorderLayout.CENTER);
    add(textview, BorderLayout.NORTH);
    window.addview(graphview);
    window.addview(textview);
    
    mouslis = new mouselistener(window);
    graphview.addMouseListener(mouslis);
    
    //create buttons that make shapes
    JButton circle = new JButton( "circle" );
    JButton tee = new JButton( "tee" );
    JButton delta = new JButton( "delta" );
    JButton stop = new JButton("stop adding shape");
    JPanel bu = new JPanel( );
    bu.add(circle);
    bu.add(tee);
    bu.add(delta );
    bu.add(stop);
    this.add(bu, BorderLayout.SOUTH);
    
    butlis = new buttonlistener( window );
    circle.addActionListener( butlis );
    tee.addActionListener( butlis );
    delta.addActionListener( butlis );
    stop.addActionListener(butlis);
    
  }
}



//button click event
class buttonlistener implements ActionListener{
  
  private DrawingBoard window;
  
  /**
   * constructor for buttonlistener in drawingboard
   * @param w DrawingBoard object pass in
   */ 
  public buttonlistener(DrawingBoard w){
    window = w;
  }
  /**
   * reacts with buttons click, store the click value
   * @param e the event created by the button click
   */ 
  public void actionPerformed(ActionEvent e){
    if(e.getActionCommand().equals("circle")){           //I CAN DO INT 1,2,3 INSTEAD OF STRING!@!!!!!
      //window.dcircle();
      window.setbuttonshape("circle");
      window.notifys();}
    
    else if(e.getActionCommand().equals("delta")){
      //window.ddelta();
      window.setbuttonshape("delta");
      window.notifys();}
    
    else if(e.getActionCommand().equals("tee")){
      //window.dtee();
      window.setbuttonshape("tee");
      window.notifys();
    }
    // tells not creat any more shapes
    else if(e.getActionCommand().equals("stop adding shape")){
      window.setbuttonshape("no");
      window.notifys();
    }
  }
  
//mouse click event
  class mouselistener implements MouseListener{
    private boolean dragging;
    private DrawingBoard window;
    private int x1, y1 ,x2, y2;
    
   /**
   * constructor for mouselistener in drawingboard
   * @param w DrawingBoard object pass in
   */ 
    public mouselistener(DrawingBoard w){
      super();
      window = w;
    }
    /**
     * draw shape when mouse click on panle, shape base on the pass in value from button clicked
     * @param e the mouse click event
     */ 
    public void mouseClicked(MouseEvent e){
      //window.dcircle(e.getX(),e.getY(),10);
      System.out.println("uuu");
       // window.notifys();
      
      //draw tee if active buttone is circle
      if(window.getbuttonshape().equals("circle")){
        window.dcircle(e.getX(),e.getY(),20);
        window.notifys();}
      //draw tee if active buttone is delta
      if(window.getbuttonshape().equals("delta")){
        window.ddelta(e.getX(),e.getY(),20);
        window.notifys();}
      //draw tee if active buttone is tee
      if(window.getbuttonshape().equals("tee")){
        window.dtee(e.getX(),e.getY(),20);
        window.notifys(); }
      
      //if(window.getbuttonshape().equals("no")){
      //try to select the top most shape if not drawing.
      else{
            System.out.println("sdfasdf");
        window.passxy(e.getX(), e.getY());
        System.out.println(Integer.toString(e.getX())+"   " + Integer.toString(e.getY()));
        window.notifys();
        
        
      }
    }
    
    // nothing
    public void mouseEntered ( MouseEvent e ) { }
    public void mouseExited  ( MouseEvent e ) { }
    /**
     * step 1, get x,y values rady from press mouse for next step, if any
     * @param e the mouse click event
     */ 
    public void mousePressed ( MouseEvent e ) { 
       System.out.println("uuu");
      x1 = e.getX();
      y1 = e.getY();
    }
    /**
     * next step of mousepressed, for moseReleased event
     * @param e the mouse click event
     */ 
    public void mouseDragged( MouseEvent e ) {
      dragging = true;
    }
    /**
     * step 3, base on last 2 step , drageed slected shape to this x,y coor.
     * @param e the mouse click event
     */ 
    public void mouseReleased( MouseEvent e ) {
      x2 = e.getX();
      y2 = e.getY();  
      if (dragging == true){
        window.changecoor(x2-x1,y2-y1);
      }
    }
    
  }
}
