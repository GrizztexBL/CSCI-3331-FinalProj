import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends StackPane{

    Stage stage;
    MainMenu myMainMenu;
    int sceneWidth, sceneHeight;

    View(Stage stage){
        this.stage = stage;
    }

    public void setUp(){
        Scene scene = new Scene(this, sceneWidth = 1000, sceneHeight = 600);
        stage.setScene(scene);
        stage.setResizable(false);
        myMainMenu = new MainMenu(this);
        getChildren().add(myMainMenu);

    }

    public void easy(){

    }

    public void show(){
        stage.show();
    }

    public void gameOver(boolean won){
        if(won){won();}
        else{lost();}
    }

    public void won(){
        Label youWin = new Label("You win!");
        youWin.setStyle("-fx-font-size:40");
        getChildren().add(youWin);
    }

    public void lost(){
        VBox loseLayer = new VBox(30);

        Label youlose = new Label("You lost");
        youlose.setStyle("-fx-font-size:40");
        loseLayer.getChildren().add(youlose);

        Button homeButton = new Button("Home");
        homeButton.setStyle("-fx-font-size:30");
        Button restartButton = new Button("Restart");
        restartButton.setStyle("-fx-font-size:30");
        
        getChildren().add(loseLayer);
        setAlignment(loseLayer, Pos.CENTER);
        loseLayer.toFront();
    }
}
