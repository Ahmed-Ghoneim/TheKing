
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

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
            flowPane.getChildren().add(c.showFront());
        }
        flowPane.setAlignment(Pos.CENTER);
        return flowPane;
    }
    
    

}
