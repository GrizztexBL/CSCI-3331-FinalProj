import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameOver extends BorderPane{
    
    boolean win;

    GameOver(boolean win){
        this.win = win;
        displayGameOver();
    }

    public void displayGameOver(){
        if(win){winGame();}
        else{loseGame();}
    }

    public BorderPane getRootPane() {
        return this;
    }

    public void loseGame(){
        Label loseLabel = new Label("You lose");
        loseLabel.setStyle("-fx-font-size:30");
        getChildren().add(loseLabel);
        setCenter(loseLabel);

        Button backToHome = new Button("Home");
        getChildren().add(backToHome);
        loseLabel.layoutXProperty().bind(widthProperty().subtract(loseLabel.widthProperty()).divide(2).subtract(50));
        loseLabel.layoutYProperty().bind(heightProperty().subtract((loseLabel.heightProperty()).divide(2).subtract(20)));

        Button reset = new Button("Restart");
        getChildren().add(reset);
        reset.layoutXProperty().bind(widthProperty().subtract(reset.widthProperty()).divide(2).add(50));
        reset.layoutYProperty().bind(heightProperty().subtract((reset.heightProperty()).divide(2).subtract(20)));
    }

    public void winGame(){

    }
}
