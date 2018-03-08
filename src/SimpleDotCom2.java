import java.util.ArrayList;
//使用ArrayList的简单版DotCom类
public class SimpleDotCom2 {
    ArrayList<String> locationCells;
    public void setLocationCells(ArrayList<String> loc){
        locationCells = loc;
    }
    public String checkYourself(String userInput){
        String result = "miss";
        int index = locationCells.indexOf(userInput);
        if (index >= 0){
            result = "hit";
            locationCells.remove(index);
            if(locationCells.isEmpty()) {
                result = "kill";
            }
        }
        return result;
    }
}
