public class Main {

    public static void main(String[] args) {

        //WILL BE MAX HEAP !!!
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>();
        binaryHeap.insert(3);
        binaryHeap.insert(5);
        binaryHeap.insert(6);
        binaryHeap.insert(7);


        System.out.println(binaryHeap.pull());
    }
}
