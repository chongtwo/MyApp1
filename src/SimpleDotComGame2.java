import java.util.ArrayList;
//使用了ArrayList的简单版DotComGame

public class SimpleDotComGame2 {
    public static void main(String[] args){
        int numOfHits = 0;
        SimpleDotCom2 theDotCom = new SimpleDotCom2();
        SimpleGameHelper helper = new SimpleGameHelper();
        boolean isAlive = true;

        //初始化位置
        int random = (int)(Math.random()*5);
        ArrayList<String> locationCells = new ArrayList<String>();
        locationCells.add(Integer.toString(random));
        locationCells.add(Integer.toString(random+1));
        locationCells.add(Integer.toString(random+2));
        theDotCom.setLocationCells(locationCells);
        //比较输入与答案
        while (isAlive == true) {
            numOfHits++;
            String userGuess = helper.getUserInput("enter your guess: ");
            String checkResult = theDotCom.checkYourself(userGuess);
            if(checkResult.equals("kill")){
                isAlive = false;
            }
            System.out.println(checkResult);
        }
        System.out.println("You took " + numOfHits + " guesses ");
    }

}
