
import java.util.ArrayList;
import java.util.Collections;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

public class Player extends VBox {

    ArrayList<Card> playerCards = new ArrayList<>();

    private String name;
    private static int playerNumbers = 0;
    private final int num;
    private Image playerVector;
    private Card given;
    private Label pName;

    public Player(String name) {

        this.playerVector = new Image("png/player.png");
        playerNumbers++;
        this.name = name;
        pName = new Label(this.name);
        pName.setTextFill(Color.AQUA);
        num = playerNumbers;

        setSpacing(25);
        setPadding(new Insets(10, 10, 10, 10));

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                getChildren().clear();
                getChildren().addAll(displayCards(), showVector());
            }

        }.start();
        setAlignment(Pos.CENTER);

    }

    public int getPlayerNumber() {
        return num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
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
        Card give = playerCards.get(cardNumber);
        playerCards.remove(cardNumber);
        return give;
    }

    public Card giveClickedCard() {

        playerCards.remove(given);
        System.out.print("Given\n");
        return given;

    }

    public boolean cardClicked(MouseEvent cl) {
        boolean clicked = false;
        for (Card pc : playerCards) {
            if (pc.FRONT_VIEW == cl.getTarget()) {
                given = pc;
                clicked = true;
                break;
            }
        }
        return clicked;
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

    public void showTurn() {
        pName.setTextFill(Color.RED);
    }

    public void endTurn() {
        pName.setTextFill(Color.AQUA);
    }

    public VBox showVector() {
        VBox vBox = new VBox();
        ImageView imageView = new ImageView(playerVector);
        DropShadow ds = new DropShadow(10, Color.BLACK);
        pName.setStyle("-fx-underline:true;");
        pName.setScaleX(1.3);
        pName.setScaleY(1.3);
        imageView.setEffect(ds);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        vBox.setSpacing(5);
        vBox.getChildren().addAll(imageView, pName);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }
//
//    public ImageView showFrontCard(int cardNumber) {
//        return playerCards.get(cardNumber - 1).showFront();
//    }
//
//    public ImageView showBackCard(int cardNumber) {
//        return playerCards.get(cardNumber - 1).showBack();
//    }

    public FlowPane displayCards() {
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(-25);

        for (Card c : playerCards) {

            flowPane.getChildren().add(c.FRONT_VIEW);
           
        }
        flowPane.setAlignment(Pos.CENTER);
        return flowPane;
    }

    public VBox displayCardsB() {
        VBox vBox = new VBox(-25);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(displayCards(), showVector());
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

}
