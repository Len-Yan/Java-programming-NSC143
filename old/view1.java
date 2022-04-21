import java.awt.*;
import javax.swing.*;
/**
 *  text viewer for GraphicsEditorApp
 * 
 * @author Lengfan Yan
 * @version Assignment 3, 2015/10/24
 */ 

public class view1 extends JPanel implements VIEW{
  //ini
  private JLabel numbers;
  private JLabel state;
  private DrawingBoard shapes;
  private int nu;
  private String k;
  
  /**
   * constructor for text view
   * @param d the class that store data for shape 
   */ 
  public view1(DrawingBoard d){
    super();
    this.setPreferredSize(new Dimension(300,50));
    shapes = d;
    nu = d.count();
    k = Integer.toString(nu);
    //shapes.addListener(this);
    numbers = new JLabel( k );
    state = new JLabel("no shape is selected yet.");
    this.add(numbers);
    this.add(state);
  }
  
  /* change text dispaly when notify
   * 
   */ 
  public void viewchange(){
    numbers.setText(shapes.count() + " Shpaes in here. ");
    if(shapes.askselect() == null){state.setText("no shape is selected yet2.");}
    else{
      state.setText(shapes.askselect().toString());}
  }
//  public void notifychange(){}
  
}