//使用数组的简单版DotComGame
public class SimpleDotComGame{
    public static void main(String[] args){
        int numOfGuess = 0;
        SimpleGameHelper helper = new SimpleGameHelper();

        SimpleDotCom theDotCom = new SimpleDotCom();
        int randomNum = (int)(Math.random()*5);
        int[] locations = {randomNum, randomNum+1, randomNum+2};
        theDotCom.setLocationCells(locations);

        boolean isAlive = true;

        while(isAlive == true){
            String guess = helper.getUserInput("enter a number");
            String result = theDotCom.checkYourself(guess);
            numOfGuess++;
            if(result.equals("kill")){
                System.out.println("Your number of guess is " + numOfGuess);
                isAlive = false;
            }
        }

    }
}
