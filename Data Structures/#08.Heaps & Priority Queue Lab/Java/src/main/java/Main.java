public class Main {

    public static void main(String[] args) {

        //WILL BE MAX HEAP !!!
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>();
        binaryHeap.insert(10);
        binaryHeap.insert(3);
        binaryHeap.insert(15);
        binaryHeap.insert(25);
        binaryHeap.insert(4);
        binaryHeap.insert(7);
        binaryHeap.insert(8);
        binaryHeap.insert(43);
        binaryHeap.insert(9);


        System.out.println(binaryHeap.peek());
    }
}
