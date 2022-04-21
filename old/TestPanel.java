import java.awt.*;
import javax.swing.*;

public class TestPanel extends JPanel{
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    //g.setColor(Color.red);
    g.drawRect(50,50,100,100);
   g.fillRect(50, 90, 100, 20);
   g.fillRect(90, 50, 20, 100);
  }
  public static void main(String[] args){
    JFrame frame = new JFrame("asd");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    TestPanel panel = new TestPanel();
    panel.setPreferredSize(new Dimension(200,200));
    frame.getContentPane().add(panel);
    frame.pack();
    frame.setVisible(true);}}