import com.sun.jmx.mbeanserver.JmxMBeanServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class QuizCardBuilder {
    JFrame frame;
    JTextArea question;
    JTextArea answer;
    ArrayList<QuizeCard> cardList;

    public static void main(String args[]){
        QuizCardBuilder builder = new QuizCardBuilder();
        builder.go();
    }

    public void go(){
        frame = new JFrame("Quiz Card Builder");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Font bigFont = new Font("sanserif", Font.BOLD, 24);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        JLabel labelQ = new JLabel("Question");
        JLabel labelA = new JLabel("Answer");
        labelQ.setFont(bigFont);
        labelA.setFont(bigFont);
        JButton buttonNextCard = new JButton("next card");
        question = new JTextArea(6,20);
        answer = new JTextArea(6, 20);
        JScrollPane scrollerQ = new JScrollPane(question);
        JScrollPane scrollerA = new JScrollPane(answer);

        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        scrollerA.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollerA.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollerQ.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollerQ.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("new");
        JMenuItem saveMenuItem = new JMenuItem("save");

        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());
        buttonNextCard.addActionListener(new NextCardListener());

        mainPanel.add(labelQ);
        mainPanel.add(scrollerQ);
        mainPanel.add(labelA);
        mainPanel.add(scrollerA);
        mainPanel.add(buttonNextCard);
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);

        cardList = new ArrayList<>();
    }


    public class NextCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            QuizeCard quizeCard = new QuizeCard(question.getText(), answer.getText());
            cardList.add(quizeCard);
            clearCard();
        }
    }

    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    private class NewMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardList.clear();
            clearCard();
        }
    }

    private class SaveMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            QuizeCard quizeCard = new QuizeCard(question.getText(), answer.getText());
            cardList.add(quizeCard);
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    private void saveFile(File selectedFile) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));

            for(QuizeCard card: cardList){
                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("couldn't write the cardList out");
            e.printStackTrace();
        }
    }
}
