
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

class Deck {

    Random random = new Random();
    private final int numSuits = 4;
    private final int numRanks = 13;

    ArrayList<Card> cards = new ArrayList();

    public Deck() {

        for (int i = 1; i <= numSuits; i++) {
            for (int j = 1; j <= numRanks; j++) {

                Card card = new Card(i, j);
                cards.add(card);
            }
        }
        System.out.println("Deck created");

    }

    public Card distributeCard(int x) {

        Card distribute = cards.get(x);
        cards.remove(x);
        return distribute;

    }

    public void getKingReady() {
        int deletedSuit = random.nextInt(3) + 1;
        Card card = new Card(deletedSuit, 13);
        if (cards.contains(card)) {
            cards.remove(card);
            System.out.println("Deleted");
        }

    }

    public FlowPane displayCards() {
        
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(-25);
        ObservableList list = flowPane.getChildren();
        for (Card c : cards) {

            list.add(c.showFront());
        }
        flowPane.setMaxSize(700, 700);
        return flowPane;
    }

    public void deckShuffle() {

        for (int i = 0; i < 10; i++) {
            Collections.shuffle(cards);
        }

    }

    public int getSize() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.size() <= 0;
    }

    
}
