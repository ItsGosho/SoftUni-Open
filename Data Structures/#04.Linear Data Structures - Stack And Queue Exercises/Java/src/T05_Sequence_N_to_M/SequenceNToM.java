package T05_Sequence_N_to_M;

import java.util.*;
import java.util.stream.Collectors;

public class SequenceNToM {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();


        if (n >= m) {
            return;
        }

        Deque<Item> items = new ArrayDeque<>();
        List<Item> result = new ArrayList<>();
        items.add(new Item(n, null));


        while (items.size() != 0) {
            Item item = items.pop();

            if (item.getValue() < m) {
                items.add(new Item(item.getValue() + 1, item));
                items.add(new Item(item.getValue() + 2, item));
                items.add(new Item(item.getValue() * 2, item));
            } else {
                items.pop();
            }

            if (item.getValue() == m) {
                result.add(item);
                break;
            }

        }

        List<String> res = result.get(0).getAllAsArray()
                .stream()
                .sorted((x1,x2)-> x1.compareTo(x2))
                .map(x->Integer.toString(x))
                .collect(Collectors.toList());

        System.out.println(String.join(" -> ",res));

    }


}

class Item {

    private Integer value;
    private Item next;

    public Item(Integer value, Item next) {
        this.value = value;
        this.next = next;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Item getNext() {
        return next;
    }

    public void setNext(Item next) {
        this.next = next;
    }

    public List<Integer> getAllAsArray() {
        List<Integer> result = new ArrayList<>();
        this.fillInArray(result,this);

        return result;
    }

    private void fillInArray(List<Integer> arrToFill, Item item) {
        arrToFill.add(item.value);
        if(item.next != null){
            this.fillInArray(arrToFill, item.next);
        }

        return;
    }

}
