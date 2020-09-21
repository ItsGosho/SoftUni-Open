import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        RoyaleArena royaleArena = new RoyaleArena();


        Battlecard buildingCard = new Battlecard(1, CardType.BUILDING, "The Builder!", 10D, 1D);
        Battlecard rangedCard = new Battlecard(2, CardType.RANGED, "The Ranger!", 60D, 1.5D);

        royaleArena.add(buildingCard);
        royaleArena.add(rangedCard);

        royaleArena.changeCardType(1, CardType.RANGED);

        List<Battlecard> test = (List<Battlecard>) royaleArena.getByCardType(CardType.RANGED);
        System.out.println();
    }
}
