import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyDrawPanel extends JPanel{

    public void paintComponent(Graphics g){
//        g.setColor(Color.orange);
//        g.fillRect(20,50,100,100);
        Image image = new ImageIcon("image/Whimsy_Tree.jpg").getImage();
        g.drawImage(image, 3, 4, this);//画图是按代码顺序的

        int red = (int)(Math.random()*255);
        int green = (int)(Math.random()*255);
        int blue = (int)(Math.random()*255);
        Color c = new Color(red, green ,blue);
        g.setColor(c);
        g.fillRect(0,0,this.getWidth(),this.getHeight());

        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(70,70,Color.blue,150,150, Color.orange);
        g2d.setPaint(gradient);
        g2d.fillOval(70, 70, 100, 50);

    }
}
