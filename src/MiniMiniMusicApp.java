import javax.sound.midi.*;
import javax.sound.midi.Sequencer;

public class MiniMiniMusicApp {

    public static void main(String[] args){
        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        mini.paly();
    }

    public void paly(){
        try {
            // 取得Sequencer并将它打开
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            //创建新的Sequence
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            //从Sequence中创建新的track
            Track track = seq.createTrack();
            //往track上填入MidiEvent并让Sequence按时间顺序组织
            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, 10, 0);
            MidiEvent f = new MidiEvent(first, 1);
            track.add(f);


            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, 63, 100);
            MidiEvent noteOn = new MidiEvent(a, 1);
            track.add(noteOn);


            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, 63, 100);
            MidiEvent noteOff = new MidiEvent(b, 20);
            track.add(noteOff);




            //把Sequence交给播放器，并播放
            player.setSequence(seq);
            player.start();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

    }
}
