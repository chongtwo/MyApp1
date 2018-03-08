import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyDrawPanelTestDrive{
    JFrame frame;//实例变量
    JLabel label;
    public static void main(String[] args){
        MyDrawPanelTestDrive testDrive = new MyDrawPanelTestDrive();
        //testDrive.frame = new JFrame();
        testDrive.go();

    }
    public void go(){
        MyDrawPanel panel = new MyDrawPanel();
        panel.setBounds(1,1,200,200);
        frame = new JFrame();
        frame.add(BorderLayout.CENTER, panel);
        frame.setSize(200,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton buttonChangeColor = new JButton("change color");
        ColorListener colorListener = new ColorListener();
        buttonChangeColor.addActionListener(colorListener);
        frame.add(BorderLayout.SOUTH, buttonChangeColor);
        label = new JLabel("I'm a label");
        frame.add(BorderLayout.WEST, label);
        JButton buttonChangeLabel = new JButton();
        frame.add(BorderLayout.EAST, buttonChangeLabel);
        LabelListener labelListener = new LabelListener();
        buttonChangeLabel.addActionListener(labelListener);
        frame.setVisible(true);
    }

    class LabelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText("label changed");
        }
    }

    class ColorListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.repaint();
        }
    }
}
