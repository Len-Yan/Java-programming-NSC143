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

public class Controller1 extends JPanel {
  //ini
  private View1 textview;
  private View2 graphview;
  private DrawingBoard window;
  private buttonlistener butlis;
  private mouselistener mouslis;
  
  /** constructor for DrawingBoard
    * @param d the DrawingBoard object pass in
    */ 
  public Controller1(DrawingBoard d){
    window = d;
    setLayout( new BorderLayout( ) );
    
    graphview = new View2(window);
    graphview.setPreferredSize(new Dimension(550,400));
    textview = new View1(window);
    add(graphview, BorderLayout.CENTER);
    add(textview, BorderLayout.NORTH);
    window.addView(graphview);
    window.addView(textview);
    
    mouslis = new mouselistener(window);
    graphview.addMouseListener(mouslis);
    graphview.addMouseMotionListener(mouslis);
    
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
    if(e.getActionCommand().equals("circle")){           
      //window.dcircle();
      window.setButtonShape("circle");
      window.notifys();}
    
    else if(e.getActionCommand().equals("delta")){
      //window.ddelta();
      window.setButtonShape("delta");
      window.notifys();}
    
    else if(e.getActionCommand().equals("tee")){
      //window.dtee();
      window.setButtonShape("tee");
      window.notifys();
    }
    // tells not creat any more shapes
    else if(e.getActionCommand().equals("stop adding shape")){
      window.setButtonShape("no");
      window.notifys();
    }
  }
}


//mouse click event
class mouselistener extends JPanel implements MouseListener, MouseMotionListener{
  private boolean dragging;
  private DrawingBoard window;
  private int x1, y1 ,x2, y2;
  // private boolean state;
  
  /**
   * constructor for mouselistener in drawingboard
   * @param w DrawingBoard object pass in
   */ 
  public mouselistener(DrawingBoard w){
    super();
    window = w;
    //addMouseListener( this );
    addMouseMotionListener(this);
    dragging = false;
    //state = false;
  }
  
  /**
   * draw shape when mouse click on panle, shape base on the pass in value from button clicked
   * @param e the mouse click event
   */ 
  public void mouseClicked(MouseEvent e){
    
    //draw tee if active buttone is circle
    if(window.getButtonShape().equals("circle")){
      window.dcircle(e.getX(),e.getY(),20);
      window.notifys();}
    //draw tee if active buttone is delta
    if(window.getButtonShape().equals("delta")){
      window.ddelta(e.getX(),e.getY(),30);
      window.notifys();}
    //draw tee if active buttone is tee
    if(window.getButtonShape().equals("tee")){
      window.dtee(e.getX(),e.getY(),30);
      window.notifys(); }
    
    else{
      //window.passxy(e.getX(), e.getY());
      //window.notifys();
    }
  }
  
  // nothing
  public void mouseEntered ( MouseEvent e ) { }
  public void mouseExited  ( MouseEvent e ) { }
  public void mouseMoved  ( MouseEvent e ) { }
  
  /**
   * step 1, get x,y values rady from press mouse for next step, if any
   * @param e the mouse click event
   */ 
  public void mousePressed ( MouseEvent e ) { 
    x1 = e.getX();
    y1 = e.getY();
    window.passxy(e.getX(), e.getY());                      //set selected
    window.notifys();
  }
  
  /**
   * next step of mousepressed, for moseReleased event
   * @param e the mouse click event
   */ 
  public void mouseDragged( MouseEvent e ) {
    dragging = true;
    repaint();
  }
  /**
   * step 3, base on last 2 step , drageed slected shape to this x,y coor.
   * @param e the mouse click event
   */ 
  public void mouseReleased( MouseEvent e ) {
    x2 = e.getX();
    y2 = e.getY();  
    if(window.getButtonShape().equals("no")){           // only move shape after click stop 
      if (dragging == true){
        window.changecoor(x2-x1,y2-y1);
      }
      dragging = false;
      window.notifys();
      repaint();
    } 
  }
}