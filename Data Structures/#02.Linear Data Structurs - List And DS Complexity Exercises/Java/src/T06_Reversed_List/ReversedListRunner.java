package T06_Reversed_List;

public class ReversedListRunner {
    public static void main(String[] args) {


        ReversedList<Integer> reversedList = new ReversedList<>();
        reversedList.add(1);
        reversedList.add(2);
        reversedList.add(3);
        reversedList.add(4);
        reversedList.add(5);
        reversedList.add(6);
        reversedList.add(7);

        //7 6 5 4 3 2 1
        System.out.println(reversedList.get(6));
    }
}
