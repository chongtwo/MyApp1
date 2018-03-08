import java.util.ArrayList;


//使用chap05中的GameHelper
public class DotComBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
    int numOfGuess = 0;

    private void setUpGame(){
        DotCom d1 = new DotCom();
        d1.setName("A");
        DotCom d2 = new DotCom();
        d2.setName("B");
        DotCom d3 = new DotCom();
        d3.setName("C");

        dotComList.add(d1);
        dotComList.add(d2);
        dotComList.add(d3);

        for(DotCom d: dotComList){
            ArrayList<String> newLocation = helper.placeDotCom(3);
            d.setLocationCells(newLocation);
        }
    }

    private void startPlaying(){
        while(!dotComList.isEmpty()){
            String guess = helper.getUserInput("enter your guess");
            checkUserGuess(guess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess){
        numOfGuess++;
        String result = "miss";
        for(DotCom d: dotComList){
            result = d.checkYourself(userGuess);
            if(result.equals("kill")){
                dotComList.remove(d);
                break;
            }
            else if (result.equals("hit")){
                result = "hit";
                break;
            }
        }
        System.out.println(result);
    }

    private void finishGame(){
        System.out.println("GAME OVER");
        if (numOfGuess <= 10){
            System.out.println("good");
        }
        else {
            System.out.println("bad");
        }
    }
    public static void main(String[] args){
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
}
