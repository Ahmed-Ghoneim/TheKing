
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Player extends BorderPane {

    ArrayList<Card> playerCards = new ArrayList<>();
    private String name;
    private static int playerNumbers = 0;
    private Image playerVector;
    private Card given;
    private final int num;
    public Player(String name) {
        
        this.playerVector = new Image("png/player.png");
        playerNumbers++;
        this.name = name;
        num = playerNumbers;
    }

    public int getPlayerNumber() {
        return num;
    }

    public String getName(){
        return name;
    }
    
    public void takeCard(Card card) {

        boolean deleted = false;
        for (int i = 0; i < playerCards.size(); i++) {
            if (playerCards.get(i).getRank() == card.getRank()) {
                playerCards.remove(i);
                deleted = true;
                break;
            }
        }

        if (!deleted) {
            playerCards.add(card);
        }
    }

    public Card giveCard(int cardNumber) {
        Card give = playerCards.get(cardNumber - 1);
        playerCards.remove(cardNumber - 1);
        return (give);
    }

    public Card giveClickedCard() {
        playerCards.remove(given);
        return given;
    }

    public void cardsShuffle() {
        Collections.shuffle(playerCards);
    }

    public int remainCards() {
        return playerCards.size();
    }

    public boolean isFinished() {
        return playerCards.isEmpty();
    }

    public VBox showVector() {
        VBox vBox = new VBox();
        ImageView imageView = new ImageView(playerVector);
        DropShadow ds = new DropShadow(10, Color.BLACK);
        Text text = new Text(name);
        text.setStyle("-fx-underline:true;-fx-stroke: aqua;");
        imageView.setEffect(ds);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        vBox.setSpacing(5);
        vBox.getChildren().addAll(imageView, text);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }

    public ImageView showFrontCard(int cardNumber) {
        return playerCards.get(cardNumber - 1).showFront();
    }

    public ImageView showBackCard(int cardNumber) {
        return playerCards.get(cardNumber - 1).showBack();
    }

    public FlowPane displayCards() {
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(-25);

        for (Card c : playerCards) {
            c.showFront().setOnMouseClicked(e -> {
                given = c;
            });
            flowPane.getChildren().add(c.showFront());
        }
        flowPane.setAlignment(Pos.CENTER);
        return flowPane;
    }

    public VBox displayCardsV() {
        VBox vBox = new VBox(-25);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(displayCards(), showVector());
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

}
