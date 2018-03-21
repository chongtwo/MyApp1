import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BeatBox {

    ArrayList<JCheckBox> checkboxList;
    int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
    Sequence sequence;
    Track track;
    Sequencer sequencer;

    public static void main(String[] args){
        new BeatBox().buildGUI();
    }

    public void buildGUI(){
        JFrame theFrame = new JFrame("Cyber BeatBox");
        JPanel background = new JPanel(new BorderLayout());
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        Box nameBox = new Box(BoxLayout.Y_AXIS);
        String[] InstrumentNames = {"Bass Drum", "Closed Hi-Hat",
                "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap",
                "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga",
                "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo","Open Hi Conga"};
        for(String name : InstrumentNames){
            nameBox.add(new JLabel(name));
        }
        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        JButton startButton = new JButton("start");
        JButton stopButton = new JButton("stop");
        JButton tempupButton = new JButton("TempUp");
        JButton tempdownButton = new JButton("TempDown");

        buttonBox.add(startButton);
        buttonBox.add(stopButton);
        buttonBox.add(tempupButton);
        buttonBox.add(tempdownButton);

        startButton.addActionListener(new StartListener());
        stopButton.addActionListener(new StopButtonListener());
        tempupButton.addActionListener(new TempUpListener());
        tempdownButton.addActionListener(new TempDownListener());

        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setHgap(2);
        JPanel mainPanel = new JPanel(grid);

        checkboxList = new ArrayList<>();
        for ( int i = 0; i < 256 ; i ++){
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkboxList.add(c);
            mainPanel.add(c);
        }

        background.add(BorderLayout.WEST, nameBox);
        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.CENTER, mainPanel);
        theFrame.getContentPane().add(background);
        theFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        theFrame.setBounds(50, 50, 300, 300);
        theFrame.pack();
        theFrame.setVisible(true);

        setUpMidi();

    }

    public void setUpMidi(){
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        }catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    private class StartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buildTrackAndStart();
        }
    }

    private void buildTrackAndStart() {
        int[] trackList = null;

        sequence.deleteTrack(track);
        track = sequence.createTrack();
        for (int i = 0; i < 16 ; i++){
            trackList = new int[16];
            int key = instruments[i];
            for (int j = 0 ; j <16 ; j++ ){
                if (checkboxList.get(j + (i*16)).isSelected()){
                    trackList[j] = key;
                }
                else {
                    trackList[j] = 0;
                }
            }
            makeTracks(trackList);
            track.add(makeEvent(176, 1,127, 0, 16));
        }
        track.add(makeEvent(192,9,1,0,15));
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    private void makeTracks(int[] trackList) {
        for (int i  = 0; i <16 ; i++){
            int key = trackList[i];
            if (key != 0){
                track.add(makeEvent(128, 9, key, 100, i));
                track.add(makeEvent(144, 9, key, 100, i+1));
            }
        }
    }

    private MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        ShortMessage a = new ShortMessage();
        try {
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return event;
    }

    private class StopButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sequencer.stop();
        }
    }

    private class TempUpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(tempoFactor * 1.03));
        }
    }

    private class TempDownListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(tempoFactor * 0.97));
        }
    }
}
