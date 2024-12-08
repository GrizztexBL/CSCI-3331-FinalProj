import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Board extends BorderPane{
    Stage stage;
    int mineCount;
    HBox topPane = new HBox(40);
    HBox bottomPane = new HBox(40);
    int timer = 0;
    GridPane grid = new GridPane();
    int flagCount;
    MainMenu main;

    public Board(Stage stage, int mineCount, MainMenu main) {
        this.stage = stage;
        this.mineCount = mineCount;
        flagCount = mineCount;
        this.main = main;
        setUpDisplay();
    }

    public void setUpDisplay(){
        setUpTimer();
        setUpHomeReset();
    }

    public void setUpHomeReset(){
        Button homeBtn = new Button("Home");
        homeBtn.setStyle("-fx-font-size:15");
        homeBtn.setOnAction(e -> {
            main.homeBtn();
        });

        Button resetBtn = new Button("Reset");
        resetBtn.setStyle("-fx-font-size:15");
        resetBtn.setOnAction(e -> {
            main.reset(mineCount);
        });

        bottomPane.setPrefHeight(50);
        bottomPane.getChildren().addAll(homeBtn, resetBtn);
        bottomPane.setAlignment(Pos.CENTER);

        setBottom(bottomPane);
    }

    public void setUpTimer(){
        Label flagsLabel = new Label("🚩" + flagCount);
        flagsLabel.setStyle("-fx-font-size:15");

        Label timer = new Label("🕓 0");
        timer.setStyle("-fx-font-size:15");

        topPane.setPrefHeight(50);
        topPane.getChildren().addAll(flagsLabel, timer);
        topPane.setAlignment(Pos.CENTER);

        setTop(topPane);
    }

    public void updateFlagCount(int flagCount){
        Label myLabel = (Label)topPane.getChildren().get(0);
        myLabel.setText("🚩" + flagCount);
    }

    public void updateTimer(int newTime) {
        Label myLabel = (Label)topPane.getChildren().get(1);
        myLabel.setText("🕓 " + newTime);
    }

    public BorderPane getRootPane() {
        return this;
    }

    public GridPane getGrid(){
        return grid;
    }

    public int getMineCount(){
        return mineCount;
    }

    public void setWinLabel(int endTime){
        Label wonLabel = new Label("You won! 🕓 " + endTime);
        topPane.getChildren().clear();
        topPane.getChildren().add(wonLabel);
    }

    public void setLostLabel(int endTime){
        Label loseLabel = new Label("You lost 🕓 " + endTime);
        topPane.getChildren().clear();
        topPane.getChildren().add(loseLabel);
    }
}