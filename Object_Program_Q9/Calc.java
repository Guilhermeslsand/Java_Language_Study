import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.*;
import java.awt.*;

public class Calc extends JFrame {
  public Calc() throws Exception{
    super("Calculadora");
    
    try {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (UnsupportedLookAndFeelException e) {
        // handle exception
    } catch (ClassNotFoundException e) {
        // handle exception
    } catch (InstantiationException e) {
        // handle exception
    } catch (IllegalAccessException e) {
        // handle exception
    }

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(350,350);
    setLocation(400, 200);
    
    setLayout(new BorderLayout());
    
    TextPanel textPanel = new TextPanel();
    add(BorderLayout.NORTH,textPanel);
    
    JPanel digitsPanel = new JPanel();
    NumbersPanel numbers = new NumbersPanel (textPanel.getTxtNumber());
    OperationsPanel operations = new OperationsPanel (textPanel.getTxtNumber());
    digitsPanel.setLayout(new BorderLayout());
    digitsPanel.add(BorderLayout.CENTER, numbers);
    digitsPanel.add(BorderLayout.EAST, operations);
    
    add(BorderLayout.CENTER, digitsPanel);
    

    setVisible(true);
  }
}