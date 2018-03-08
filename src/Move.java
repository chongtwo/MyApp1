import com.sun.javaws.util.JfxHelper;

import javax.swing.*;
import java.awt.*;

public class Move {
    JFrame frame;
    int startX = 20;
    int startY = 50;
    int endX = 60;
    int endY = 80;

    int currentX = startX;
    int currentY = startY;

    int increment = 5;

    public static void main(String args[]){
        Move m = new Move();
        m.go();
    }

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        MyDrawPanel panel = new MyDrawPanel();
        panel.setBounds(0, 0, 300, 300);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        while(currentX != endX){
//            frame.setVisible(true);
            currentX = currentX + 1;
            currentY = currentY + 1;
            panel.repaint();

            try{
                Thread.sleep(50);
            }catch(Exception ex){}
        }
    }

        class MyDrawPanel extends JPanel {
            public void paintComponent(Graphics g) {
                g.setColor(Color.orange);
                g.fillOval(currentX, currentY, 100, 100);
            }
        }
    }
