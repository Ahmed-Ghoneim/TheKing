
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

class Card {

    private String[] suitS = {"diamonds", "clubs", "hearts", "spades"};
    private String[] rankS = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    private final int RANK;
    private final int SUIT;
    private final Image FRONT;
    private final Image BACK;

//    DIAMONDS = 1;
//    CLUBS = 2;
//    HEARTS = 3;
//    SPADES = 4;
//    JACK = 11;
//    QUEEN = 12;
//    KING = 13;
    public Card(int suit, int rank) {

        RANK = rank;
        SUIT = suit;

        FRONT = new Image("cards/" + rankS[rank - 1] + "_of_" + suitS[suit - 1] + ".png");
        BACK = new Image("png/back.png");
    }

    public int getRank() {
        return RANK;
    }

    public int getSuit() {
        return SUIT;
    }

    public ImageView showFront() {
        ImageView imageView = new ImageView(FRONT);
        DropShadow ds = new DropShadow( 20, Color.BLACK );
        imageView.setEffect(ds);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        return imageView;
    }
   
    public ImageView showBack() {
        ImageView imageView = new ImageView(BACK);
        imageView.autosize();
        return imageView;
    }

    public void setSize(int height) {

    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Card)) {
            return false;
        }
        Card c = (Card) obj;

        return (this.getRank() == c.getRank() && this.getSuit() == c.getSuit());
    }

}
