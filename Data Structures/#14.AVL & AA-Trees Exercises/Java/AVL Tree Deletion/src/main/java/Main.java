public class Main {

    public static void main(String[] args) {


        AVL<Integer> avl = new AVL<>();
        avl.insert(5);
        avl.insert(17);
//        avl.insert(12);
//        avl.insert(18);
//        avl.insert(37);
//        avl.insert(48);
//        avl.insert(60);
//        avl.insert(80);
//        avl.insert(1);
//        avl.insert(2);
//        avl.insert(4);
//        avl.insert(16);
//        avl.insert(8);
//        avl.insert(14);
//        avl.insert(15);
//        avl.insert(13);

        //avl.delete(37);
        avl.deleteMin();
        System.out.println();

    }
}
