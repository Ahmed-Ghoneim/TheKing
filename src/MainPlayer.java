
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Derpawy
 */
public class MainPlayer extends Player {
    
    public MainPlayer() {
        super("Ahmed");
    }
    
    @Override
    public FlowPane displayCards() {
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(-25);   
        for (Card c : playerCards) {
            flowPane.getChildren().add(c.FRONT_VIEW);
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
