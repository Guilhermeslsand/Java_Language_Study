import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.*;
import java.awt.*;

public class Calc extends JFrame {
  public Calc() throws Exception{
    super("Calculadora");
    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
  

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(350,350);
    setLocation(400, 200);

    setVisible(true);

    
  }
  public static void main(String[] args) throws Exception {
    new Calc();
  }
}
