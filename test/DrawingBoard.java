import java.util.*;
import java.awt.*;
/**
 * this class tarck the states of shapes
 * @author Lengfan Yan
 * @version Assignment 2, 2015/10/14
 */ 

public class DrawingBoard{
  
  //private Shape temp;
  private ArrayList<Shape> list = new ArrayList<Shape> ();
  private ArrayList<view2> vlist = new ArrayList<view2> ();
  private ArrayList<view1> ltext = new ArrayList<view1> ();
  private String button_shape = "no";
  //private int wide, long;
  private int xl = 30;
  private int yl = 30;
  private int hl = 30;
  
  /*
   * constructor for drawing
   */ 
  public DrawingBoard(){
    //wide = w;
    //long = l;
  }
  /**
   * add graph view to notified 
   * @param v new graph view
   */ 
  public void addview(view2 v){
    vlist.add(v);
  }
  /**
   * add text view to notified 
   * @param t new text view
   */ 
  public void addview(view1 t){
    ltext.add(t);
  }
  
  
  /** add a Shape to the DrawingBoard and the last to be selected
    * @param s the Object Shape that is created and want add to list
    */ 
  public void addshape(Shape s){
    list.add(s);
    //s.setSelected(true);
    Iterator<Shape> itr = list.iterator();              
    while(itr.hasNext()){
      itr.next().setSelected(false);
    }
    list.get(list.size()-1).setSelected(true);
  }
  /** count the number of shapes 
    * @return number of shapes in list
    */
  public int count(){
    return list.size();
  }
  
  /** 
   * tell what shape to be drawn when drwing them
   * @param bs thet text tell what to draw
   */ 
  public void setbuttonshape(String bs){ 
    button_shape = bs;
  }
  
  /** 
   * tell what shape to be drawn when drwing them
   * @return  the text tell what to draw
   */ 
  public String getbuttonshape(){
    return button_shape; 
  }
  
  /** pass in an x,y coordinate,topmost shape contains this x,y coordinate becomes the selected shape
    * @param x the x coordinate of point testing
    * @param y the y coordinate of point testing
    */ 
  public void passxy(int x, int y){
    int count = 0;
    if(list.size() == 0){}
    else{
      for(int i = list.size()-1; i >= 0; i--){                  //find isOn for last in list, store value if find isOn true
        if( list.get(i).isOn(x,y) == true){
          count = i;
          break;
        }   
      }
      Iterator<Shape> itr2 = list.iterator();                 // set everything not selected   
      while(itr2.hasNext()){
        Shape temp2 = itr2.next();
        temp2.setSelected(false);
      }
      //System.out.print(count);
      list.get(count).setSelected(true);                             // go back and set top isOn true
    }
  }
  
  //query the DrawingBoard to retrieve a reference to the selected shape. returns null if no Shape is currently selected.
  public Shape askselect(){
    int count = 0;
    Iterator<Shape> itr = list.iterator();
    while(itr.hasNext()){
      Shape temp = itr.next();
      boolean testp = temp.isSelected();
      if (testp == true){ return list.get(count);}
      count++;
    }
    return null;
  }
  
  /*
   * remove the selected shape, throw exception if no shape is selected
   */ 
  public void removeselecet(){
    int count = 0;
    Iterator<Shape> itr = list.iterator();
    
    while(itr.hasNext()){
      Shape temp = itr.next();
      boolean testp = temp.isSelected();
      if (testp == true){ 
        list.remove(count);
        if (list.size() != 0) { list.get(list.size()-1).setSelected(true); }
      }
      count++;
      if (count == list.size() && testp == false){throw new IllegalStateException(" no shape is selected"); }
    }
  }
  
  /**
   * change Color of selected shape
   * @param c the Color want to change
   */ 
  public void changeC(Color c){
    if (list.size() == 0) {System.out.println("there is no shape");}
    else if (list.get(list.size()-1).isSelected() == true){
      list.get(list.size()-1).setColor(c);
    }
    else { throw new IllegalStateException("no shape is selected");}
  }
  
  /**
   * shift selected shape by an amounts, throw exception if no shpae selected
   * @param changeX amounts of x coor wish to shift
   * @param changeY amounts of y coor wish to shit
   */ 
  public void changecoor(int changeX, int changeY){
    if (list.size() == 0) {System.out.println("there is no shape");}
    else if (list.get(list.size()-1).isSelected() == true){
      list.get(list.size()-1).shiftUpperLeftBy(changeX, changeY);
    }
    else { //throw new IllegalStateException("no shape is selected");}
      System.out.println("no shape is selected");}
  }
  
  // return a copy of ArrayList list 
  public ArrayList<Shape> copy(){
    ArrayList<Shape> go = new ArrayList<Shape>(list);
    return go;
  }
  
  //to draw circle
  public void dcircle(){
    this.addshape(new Circle( xl, yl, hl));
  }
  //
  public void dcircle(int xl, int yl, int hl){
    this.addshape(new Circle( xl, yl, hl));
  }
  
  //to draw tee
  public void dtee(){
    this.addshape(new Tee( xl, yl, hl));
  }
  //
  public void dtee(int xl, int yl, int hl){
    this.addshape(new Tee( xl, yl, hl));
  }
  
  //to draw delta
  public void ddelta(){
    this.addshape(new Delta( xl, yl, hl));
  }
  //
  public void ddelta(int xl, int yl, int hl){
    this.addshape(new Delta( xl, yl, hl));
  }
  
  /*
   * add to graph listener list and notify
   * @param v new graph view
   */ 
  public void addListener(view2 v){
    if (v != null){
      vlist.add(v);
      v.viewchange();}
  }
  /*
   * add to text listener list and notify
   * @param t new text view
   */ 
  public void addListener(view1 t){
    if (t != null){
      ltext.add(t);
      t.viewchange();}
  }
  /*
   * notifys to all view
   */ 
  public void notifys(){
    
    Iterator<view2> vl = vlist.iterator();
    while (vl.hasNext()){
      view2 tv = vl.next();
      tv.viewchange();
    }
    Iterator<view1> vt = ltext.iterator();
    while (vt.hasNext()){
      view1 tt = vt.next();
      tt.viewchange();
    }
  }
}