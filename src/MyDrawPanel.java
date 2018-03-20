import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyDrawPanel extends JPanel implements ControllerEventListener {

    public void paintComponent(Graphics g){
//        g.setColor(Color.orange);
//        g.fillRect(20,50,100,100);
        if(msg) {
            Graphics2D g2d = (Graphics2D) g;
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            Color c = new Color(red, green, blue);
            g.setColor(c);

            int ht = (int)(Math.random()*120 +10);
            int width = (int)(Math.random() *120 +10);
            int x = (int)(Math.random()*40 + 10);
            int y = (int) (Math.random()*40 +10);

            g.fillRect(x, y, ht, width);
            msg = false;

//            Image image = new ImageIcon("image/Whimsy_Tree.jpg").getImage();
//            g.drawImage(image, 3, 4, this);//画图是按代码顺序的

//            GradientPaint gradient = new GradientPaint(70, 70, Color.blue, 150, 150, Color.orange);
//            g2d.setPaint(gradient);
//            g2d.fillOval(70, 70, 100, 50);


        }
    }
    boolean msg = false;
    @Override
    public void controlChange(ShortMessage event) {
        msg = true;
        repaint();
    }
}
