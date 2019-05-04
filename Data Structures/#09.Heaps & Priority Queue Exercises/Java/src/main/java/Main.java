public class Main {
    public static void main(String[] args) {

//        BinaryHeap<Cell> binaryHeap = new BinaryHeap<Cell>();
//
//
//        Cell firstCell = new Cell(1, 0, 1, 3, 3);
//        Cell secondCell = new Cell(1,1,0,3,3);
//        Cell thirdCell = new Cell(1,1,2,3,3);
//
//        binaryHeap.insert(firstCell);
//        binaryHeap.insert(secondCell);
//        binaryHeap.insert(thirdCell);
//        binaryHeap.pull();
//        System.out.println();


        String[][] labyrinth = {
                {"-", "-", "-", "-", "-"},
                {"-", "*", "-", "-", "-"},
                {"W", "W", "W", "W", "W"},
                {"-", "-", "-", "P", "-"},

        };

        Labyrinth lab = new Labyrinth(labyrinth, 1, 1, 3, 3);
        lab.start();

    }
}
