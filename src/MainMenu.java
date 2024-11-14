import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainMenu extends Pane{
    Stage stage;
    int sceneWidth, sceneHeight;
    int btnCounter = 0;

    public MainMenu(Stage stage) {
        this.stage = stage;
        displayMenu();
    }

    public void displayMenu() {
        Scene scene = new Scene(this, sceneWidth = 1000, sceneHeight = 600);
        stage.setScene(scene);
        stage.setResizable(false);

        Button easybtn = new Button("Easy Mode: \n10x12");
        startingButtonSetup(easybtn);
        Button medbtn = new Button("Medium Mode: \n15x18");
        startingButtonSetup(medbtn);
        Button hardbtn = new Button("Hard Mode: \n24x28");
        startingButtonSetup(hardbtn);
        Button quitbtn = new Button("Exit Game");
        startingButtonSetup(quitbtn);

        easybtn.setOnAction(e-> {
            Board board = new Board(stage, 12, 10, 15);
            stage.setHeight(board.getButtonSize() * board.getColNum());
            stage.setWidth(board.getButtonSize() * board.getRowNum());
            stage.getScene().setRoot(board.getRootPane());
        });
        medbtn.setOnAction(e-> {
            Board board = new Board(stage, 18, 15, 45);
            stage.setHeight(board.getButtonSize() * board.getColNum());
            stage.setWidth(board.getButtonSize() * board.getRowNum());
            stage.getScene().setRoot(board.getRootPane());
        });
        hardbtn.setOnAction(e-> {
            Board board = new Board(stage, 28, 24, 120);
            stage.setHeight(board.getButtonSize() * board.getColNum());
            stage.setWidth(board.getButtonSize() * board.getRowNum());
            stage.getScene().setRoot(board.getRootPane());
        });
        quitbtn.setOnAction(e-> {
            System.exit(0);
        });

        BackgroundImage myBI= new BackgroundImage(new Image("Cave.png",1000,600,false,true),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT);

        getChildren().add(easybtn);
        getChildren().add(medbtn);
        getChildren().add(hardbtn);
        getChildren().add(quitbtn);

        setBackground(new Background(myBI));
    }

    public void startingButtonSetup(Button btn) {
        Font font = Font.font("Courier New", FontWeight.BOLD, 25);
        btn.setFont(font);
        btn.setStyle("-fx-background-color: #ff0000; ");
        btn.autosize();
        if (btnCounter == 0) {
            btn.setLayoutX(50);
            btn.setLayoutY(75);
        } else if (btnCounter == 1) {
            btn.setLayoutX(50);
            btn.setLayoutY(225);
        } else if (btnCounter == 2) {
            btn.setLayoutX(50);
            btn.setLayoutY(375);
        } else if (btnCounter == 3) {
            btn.setLayoutX(50);
            btn.setLayoutY(500);
        }
        
        btn.setMinSize(250, 75);
        btn.setMaxSize(250,75);
        btnCounter++;
    }

    public Pane getRootPane() {
        return this;
    }

    public void show() {
        stage.show();
    }
}
