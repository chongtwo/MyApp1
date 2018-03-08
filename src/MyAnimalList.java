public class MyAnimalList {
    private Animal[] myAnimalList = new Animal[5];
    private int nextIndex = 0;

    public void add(Animal a){
        if(nextIndex < myAnimalList.length){
            myAnimalList[nextIndex] = a;
            System.out.println("Add animal at " + nextIndex);
            nextIndex++;
        }
    }
}
