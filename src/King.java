
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

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

    Deck deck = new Deck();
    MainPlayer player1 = new MainPlayer();
    Player player2 = new Player("Player 2 Name here");
    Player player3 = new Player("Player 3 Name here");
    Random random = new Random();

    King() {
        setStyle("-fx-background-image: url('png/g.png'); "
                + "-fx-background-position: center center; "
                + "-fx-background-repeat: stretch;"
                + "-fx-background-size: cover;");
        System.out.println("We are in King");
        deck.deckShuffle();
        deck.getKingReady();
        deck.deckShuffle();

        distribution();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                getChildren().clear();
                setBottom(player1.displayCardsV());
                setRight(player2.displayCardsV());
                setLeft(player3.displayCardsV());
            }

        }.start();

    }

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
//
//        Timeline timeline = new Timeline(new KeyFrame(
//                Duration.seconds(5),
//                e -> {

        if (player1.remainCards() > player2.remainCards() && player1.remainCards() > player3.remainCards()) {
            startGame(player1, player2, player3);
        } else if (player2.remainCards() > player1.remainCards() && player2.remainCards() > player3.remainCards()) {
            startGame(player2, player3, player1);
        } else {
            startGame(player3, player1, player2);
        }
//
//                }));
//        timeline.play();
    }

    public void startGame(Player p1, Player p2, Player p3) {
        System.out.println("Game Started");

        System.out.println(player1.remainCards() + " " + player2.remainCards() + " " + player3.remainCards());

        Timeline main = new Timeline();

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

        //if(gameFinished())
        //  main.pause();
        // System.out.println(player1.remainCards() + " " + player2.remainCards() + " " + player3.remainCards() + " Game Finished");
    }

    public void playCard(Player p1, Player p2) {

        p1.takeCard(p2.giveCard(random.nextInt(p2.remainCards()) + 1));

    }

    public boolean gameFinished() {

        if (player1.isFinished() && player2.isFinished()) {
            System.out.println("player 3");
            return true;
        } else if (player2.isFinished() && player3.isFinished()) {
            System.out.println("player 1");
            return true;
        } else if (player1.isFinished() && player3.isFinished()) {
            System.out.println("player 2");
            return true;
        } else {
            return false;
        }
    }
}
