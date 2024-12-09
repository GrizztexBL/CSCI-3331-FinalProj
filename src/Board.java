import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Board extends BorderPane{
    // member variables
    Stage stage;
    int mineCount;
    HBox topPane = new HBox(40);
    HBox bottomPane = new HBox(40);
    int timer = 0;
    GridPane grid = new GridPane();
    int flagCount;
    MainMenu main;

    // constructor
    public Board(Stage stage, int mineCount, MainMenu main) {
        this.stage = stage;
        this.mineCount = mineCount;
        flagCount = mineCount;
        this.main = main;
        // create visuals
        setUpDisplay();
    }

    public void setUpDisplay(){
        // set up labels
        setUpLabels();
        // set up home and reset buttons
        setUpHomeReset();
    }

    public void setUpHomeReset(){
        // home button
        Button homeBtn = new Button("Home");
        homeBtn.setStyle("-fx-font-size:15; -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-background-insets: 0 0 -1 0, 0, 1, 2;-fx-background-radius: 3px, 3px, 2px, 1px;-fx-focus-color: transparent; -fx-faint-focus-color: transparent");
        homeBtn.setOnAction(e -> {
            main.homeBtn();
        });

        // reset button
        Button resetBtn = new Button("Reset");
        resetBtn.setStyle("-fx-font-size:15; -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-background-insets: 0 0 -1 0, 0, 1, 2;-fx-background-radius: 3px, 3px, 2px, 1px;-fx-focus-color: transparent; -fx-faint-focus-color: transparent");
        resetBtn.setOnAction(e -> {
            main.reset(mineCount);
        });

        // add buttons to bottom pane
        bottomPane.setPrefHeight(50);
        bottomPane.getChildren().addAll(homeBtn, resetBtn);
        bottomPane.setAlignment(Pos.CENTER);

        // add bottom pane to board
        setBottom(bottomPane);
    }

    public void setUpLabels(){
        // create flags label
        Label flagsLabel = new Label("🚩" + flagCount);
        flagsLabel.setStyle("-fx-font-size:15");

        // create timer label
        Label timer = new Label("🕓 0:00");
        timer.setStyle("-fx-font-size:15");

        // add labels to top pane
        topPane.setPrefHeight(50);
        topPane.getChildren().addAll(flagsLabel, timer);
        topPane.setAlignment(Pos.CENTER);

        // add top pane to board
        setTop(topPane);
    }

    public void updateFlagCount(int flagCount){
        // update flag label with new count
        Label myLabel = (Label)topPane.getChildren().get(0);
        myLabel.setText("🚩" + flagCount);
    }

    public void updateTimer(int newTime) {
        // update timer label with new time
        Label myLabel = (Label)topPane.getChildren().get(1);
        myLabel.setText("🕓 " + formatTime(newTime));
    }

    public String formatTime(int time){
        // put time in minutes : seconds format
        int min = time/60;
        String formattedTime = String.valueOf(min) + ":";
        int seconds = time-(min*60);
        if(seconds < 10){
            formattedTime += "0";
        }
        formattedTime += String.valueOf(seconds);
        return formattedTime;
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
        // create win label
        Label wonLabel = new Label("You won! 🕓 " + formatTime(endTime));
        // add win label to top pane of board
        topPane.getChildren().clear();
        topPane.getChildren().add(wonLabel);
    }

    public void setLostLabel(int endTime){
        // set lost label
        Label loseLabel = new Label("You lost 🕓 " + formatTime(endTime));
        // add lost label to top pane of board
        topPane.getChildren().clear();
        topPane.getChildren().add(loseLabel);
    }
}