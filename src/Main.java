import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<O> arrayList = new ArrayList<O>();
        O o1 = new O("a");
        arrayList.add(o1);
        o1 = new O("b");
        arrayList.add(o1);
        for (O e : arrayList){
            System.out.println(e.nameOfO);
        }

    }
}
