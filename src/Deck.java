
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

class Deck {

    private Random random = new Random();
    private final int NUMBER_OF_SUITS = 4;
    private final int NUMBER_OF_RANKS = 13;

    private ArrayList<Card> cards = new ArrayList();

    public Deck() {

        for (int i = 1; i <= NUMBER_OF_SUITS; i++) {
            for (int j = 1; j <= NUMBER_OF_RANKS; j++) {

                Card card = new Card(i, j);
                cards.add(card);
                
            }
        }
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
        }

    }

    public FlowPane displayCards() {
        
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10, 10, 10, 10));
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(-25);
        
        cards.forEach((c) -> {
            flowPane.getChildren().add(c.FRONT_VIEW);
        });
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
