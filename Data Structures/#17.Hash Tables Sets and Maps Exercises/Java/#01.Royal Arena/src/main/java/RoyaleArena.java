import java.util.*;
import java.util.stream.Collectors;


public class RoyaleArena implements IArena {

    private Map<Integer, Battlecard> battleCards;

    public RoyaleArena() {
        this.battleCards = new LinkedHashMap<>();
    }

    @Override
    public void add(Battlecard card) {

        if (this.contains(card))
            throw new IllegalArgumentException("The card is already present!");

        this.battleCards.put(card.getId(), card);
    }

    @Override
    public boolean contains(Battlecard card) {
        return this.battleCards.containsKey(card.getId());
    }

    @Override
    public boolean containsById(Integer id) {
        return this.battleCards.containsKey(id);
    }

    @Override
    public int count() {
        return this.battleCards.size();
    }

    @Override
    public void changeCardType(int id, CardType type) {

        if (!this.containsById(id))
            throw new IllegalArgumentException("Card by the given id doesn't exist!");

        Battlecard battlecard = this.battleCards.get(id);
        battlecard.setType(type);
    }

    @Override
    public Battlecard getById(int id) {

        if (!this.containsById(id))
            throw new UnsupportedOperationException("Card by the given id doesn't exist!");

        return this.battleCards.get(id);
    }

    @Override
    public void removeById(int id) {

        if (!this.containsById(id))
            throw new UnsupportedOperationException("Card by the given id doesn't exist!");

        this.battleCards.remove(id);
    }

    @Override
    public Iterable<Battlecard> getByCardType(CardType type) {
        List<Battlecard> battleCards = this.battleCards.values()
                .stream()
                .filter(x -> x.getType().equals(type))
                .sorted(Battlecard::compareTo)
                .collect(Collectors.toList());

        if (battleCards.isEmpty())
            throw new UnsupportedOperationException("No cards of the given type has been found!");

        return battleCards;
    }

    @Override
    public Iterable<Battlecard> getByTypeAndDamageRangeOrderedByDamageThenById(CardType type, int lo, int hi) {
        List<Battlecard> battleCards = this.battleCards.values()
                .stream()
                .filter(x -> x.getType().equals(type) && x.isDamageInRange(lo, hi))
                .sorted(Battlecard::compareTo)
                .collect(Collectors.toList());

        if (battleCards.isEmpty())
            throw new UnsupportedOperationException("No cards of the given type and in range has been found!");

        return battleCards;
    }

    @Override
    public Iterable<Battlecard> getByCardTypeAndMaximumDamage(CardType type, double damage) {
        List<Battlecard> battleCards = this.battleCards.values()
                .stream()
                .filter(x -> x.getType().equals(type) && x.isDamageBelow(damage))
                .sorted(Battlecard::compareTo)
                .collect(Collectors.toList());

        if (battleCards.isEmpty())
            throw new UnsupportedOperationException("No cards of the given type and below range has been found!");

        return battleCards;
    }

    @Override
    public Iterable<Battlecard> getByNameOrderedBySwagDescending(String name) {
        List<Battlecard> battleCards = this.battleCards.values()
                .stream()
                .filter(x -> x.getName().equals(name))
                .sorted(Comparator.comparingDouble(Battlecard::getSwag).reversed().thenComparing(Battlecard::getId))
                .collect(Collectors.toList());

        if (battleCards.isEmpty())
            throw new UnsupportedOperationException("No cards of the given name has been found!");

        return battleCards;
    }

    @Override
    public Iterable<Battlecard> getByNameAndSwagRange(String name, double lo, double hi) {
        List<Battlecard> battleCards = this.battleCards.values()
                .stream()
                .filter(x -> x.getName().equals(name) && x.isSwagInRange(lo, hi - 1))
                .sorted((f, s) -> Double.compare(s.getSwag(), f.getSwag())).sorted(Comparator.comparingInt(Battlecard::getId))
                .collect(Collectors.toList());

        if (battleCards.isEmpty())
            throw new UnsupportedOperationException("No cards of the given name and swag range has been found!");

        return battleCards;
    }

    @Override
    public Iterable<Battlecard> getAllByNameAndSwag() {
        HashMap<String, Battlecard> swagCards = new HashMap<>();

        for (Battlecard battleCard : this.battleCards.values()) {
            String name = battleCard.getName();

            if (!swagCards.containsKey(name)) {
                swagCards.put(name, battleCard);
            } else if (battleCard.getSwag() > swagCards.get(name).getSwag()) {
                swagCards.put(name, battleCard);
            }
        }

        return swagCards.values();
    }

    @Override
    public Iterable<Battlecard> findFirstLeastSwag(int n) {

        if (n > this.battleCards.size())
            throw new UnsupportedOperationException("The n parameter exceeds the size of the area!");

        return this.battleCards.values()
                .stream()
                .sorted((card1, card2) -> {
                    if (card1.getSwag() == card2.getSwag())
                        return Double.compare(card1.getId(), card2.getId());
                    else
                        return Double.compare(card1.getSwag(), card2.getSwag());
                })
                .limit(n)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Battlecard> getAllInSwagRange(double lo, double hi) {
        return this.battleCards.values()
                .stream()
                .filter(x -> x.isSwagInRange(lo, hi))
                .sorted((card1, card2) -> Double.compare(card1.getSwag(), card2.getSwag()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Battlecard> iterator() {
        return this.battleCards.values().iterator();
    }
}
