
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import jdk.nashorn.internal.ir.BreakNode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Derpawy
 */
public class King extends BorderPane {

    ArrayList<MouseEvent> m = new ArrayList();

    Deck deck = new Deck();
    MainPlayer player1 = new MainPlayer();
    Player player2 = new Player("Fa5ry");
    Player player3 = new Player("Bayomy");
    Random random = new Random();
    Text round = new Text("Game Started");
    Timeline main;

    King() {
        setStyle("-fx-background-image: url('png/g.png'); "
                + "-fx-background-position: center center; "
                + "-fx-background-repeat: stretch;"
                + "-fx-background-size: cover;");
        System.out.println("We are in King");
        deck.deckShuffle();
        deck.getKingReady();
        deck.deckShuffle();
        round.setFont(Font.font("Arial", 72));
        distribution();
        setAlignment(round, Pos.CENTER);
        round.setFill(Color.RED);
        render();
        setProperty();
        
//        new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                 getChildren().clear();
//        setTop(round);
//        setBottom(player1);
//        setRight(player2);
//        setLeft(player3);
//            }
//
//}.start();
    }

    public void render() {
        getChildren().clear();
        setTop(round);
        setBottom(player1);
        setRight(player2);
        setLeft(player3);
    }
    
    public void setProperty(){
//        player1.layoutYProperty().bind(this.layoutYProperty());
//        player2.layoutXProperty().bind(this.layoutXProperty());
//        player2.layoutYProperty().bind(this.layoutYProperty());
//        player3.layoutXProperty().bind(this.layoutXProperty());
//        player3.layoutYProperty().bind(this.layoutYProperty());
    }

    /**
     * This method is used to distribute the Deck cards and auto invoke
     * startGame method after finishing card distribution.
     */
    public void distribution() {
        deck.deckShuffle();

        do {
            player1.takeCard(deck.distributeCard(0));
            if (!deck.isEmpty()) {
                player2.takeCard(deck.distributeCard(0));
            }
            if (!deck.isEmpty()) {
                player3.takeCard(deck.distributeCard(0));
            }
        } while (!deck.isEmpty());

        System.out.println("Cards distributed Wait the game is about to Start");

        if (player1.remainCards() > player2.remainCards() && player1.remainCards() > player3.remainCards()) {
            startGame(player1, player2, player3);
        } else if (player2.remainCards() > player1.remainCards() && player2.remainCards() > player3.remainCards()) {
            startGame(player2, player3, player1);
        } else {
            startGame(player3, player1, player2);
        }

    }

    /**
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public void startGame(Player p1, Player p2, Player p3) {
        System.out.println(player1.remainCards() + " " + player2.remainCards() + " " + player3.remainCards());

        main = new Timeline();

        KeyFrame p1Turn = new KeyFrame(Duration.seconds(2), e1 -> {
            if (!p1.isFinished()) {
                if (!p2.isFinished()) {
                    playCard(p1, p2);
                } else if (!p3.isFinished()) {
                    playCard(p1, p3);
                }
            }
        });
        main.getKeyFrames().add(p1Turn);

        KeyFrame p2Turn = new KeyFrame(Duration.seconds(4), e2 -> {
            if (!p2.isFinished()) {
                if (!p3.isFinished()) {
                    playCard(p2, p3);
                } else if (!p1.isFinished()) {
                    playCard(p2, p1);
                }
            }
        });
        main.getKeyFrames().add(p2Turn);

        KeyFrame p3Turn = new KeyFrame(Duration.seconds(6), e3 -> {
            if (!p3.isFinished()) {
                if (!p1.isFinished()) {
                    playCard(p3, p1);
                } else if (!p2.isFinished()) {
                    playCard(p3, p2);
                }
            }
        });
        main.getKeyFrames().add(p3Turn);

        main.setCycleCount(Animation.INDEFINITE);
        main.playFromStart();

    }

    public void playCard(Player p1, Player p2) {

        p2.cardsShuffle();
        int x = random.nextInt(p2.remainCards());
        if (x != 0) {
            x--;
        }

        if (p1.equals(player1)) {
            main.pause();

            p2.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    System.out.print("Clicked\n");
                    if (p2.cardClicked(e)) {
                        p1.takeCard(p2.giveClickedCard());
                        p2.setOnMousePressed(null);
                        main.play();
                    } else {
                        playCard(p1, p2);
                    }

                }

            });

        } else {
            
            p1.takeCard(p2.giveCard(x));

        }

    }

    public boolean gameFinished() {

        if (player1.isFinished() && player2.isFinished()) {
            round.setText("Game Finished, " + player3.getName() + " is the looooser!!");
            System.out.println("player 3");
            return true;
        } else if (player2.isFinished() && player3.isFinished()) {
            round.setText("Game Finished, " + player1.getName() + " is the looooser!!");
            System.out.println("player 1");
            return true;
        } else if (player1.isFinished() && player3.isFinished()) {
            round.setText("Game Finished, " + player2.getName() + " is he looooser!!");
            System.out.println("player 2");
            return true;
        } else {
            return false;
        }
    }
}
