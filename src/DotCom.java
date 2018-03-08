import java.util.ArrayList;

public class DotCom {
    private String dotComName;
    private ArrayList<String> locationCells;

    public void setName(String name){
        dotComName = name;
    }

    public void setLocationCells(ArrayList<String> loc){
        locationCells = loc;
    }

    public String checkYourself(String userinput){
        String result = "miss";
        int index = locationCells.indexOf(userinput);
        if (index >= 0){
            locationCells.remove(index);
            if(locationCells.isEmpty()){
                result = "kill";
                System.out.println("You have sunk " + dotComName);
            }else{
                result = "hit";
            }
        }
        return result;
        }
    }
