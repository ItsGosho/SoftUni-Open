public class Main {

    public static void main(String[] args) {
        FirstLastList<Integer> firstLastList = new FirstLastList<>();

        firstLastList.add(1);
        firstLastList.add(5);
        firstLastList.add(7);
        firstLastList.add(2);
        firstLastList.add(7);

        //1 2 3
        firstLastList.removeAll(7);
        System.out.println();
    }
}
