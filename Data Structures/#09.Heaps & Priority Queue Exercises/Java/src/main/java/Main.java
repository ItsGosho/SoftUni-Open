public class Main {
    public static void main(String[] args) {

//        PriorityQueue<Cell> binaryHeap = new PriorityQueue<Cell>();
//
//
//        Cell firstCell = new Cell(1, 0, 1, 3, 3);
//        Cell secondCell = new Cell(1,1,0,3,3);
//        Cell thirdCell = new Cell(1,1,2,3,3);
//
//        binaryHeap.enqueue(firstCell);
//        binaryHeap.enqueue(secondCell);
//        binaryHeap.enqueue(thirdCell);
//        binaryHeap.dequeue();
//        System.out.println();


        //TODO:
        // 1.read it from console
        // 2.Pass the start position and fiinish position
        // 3. !!! Its working now , but i didnt have time to finish the two things above
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
