import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Board extends BorderPane{
    Stage stage;
    Tile[][] boardGrid;
    int rowNum, colNum, mineCount;
    int sceneWidth, sceneHeight;
    int btnSize = 50;
    HBox bottomPane = new HBox();
    Timeline timeline;
    int timer = 0;
    GridPane grid = new GridPane();
    int totalTiles, safeTiles, minedTiles;

    public Board(Stage stage, int rowNum, int colNum, int mineCount) {
        this.stage = stage;
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.mineCount = mineCount;
        this.boardGrid = new Tile[rowNum][colNum];
        totalTiles = rowNum * colNum;
        minedTiles = mineCount;
        safeTiles = totalTiles - minedTiles;
        generateBoard(rowNum, colNum, mineCount);
        setUpTimer();
    }

    public void generateBoard(int rowNum, int colNum, int mineCount) {
        
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                Tile tile = new SafeTile();
                tile.setPrefSize(btnSize, btnSize);
                tile.setOnMousePressed(e -> handleButtonClick(e, tile));
                boardGrid[i][j] = tile;
                grid.add(tile, i, j);
            }
        }
        
        mineBoard(mineCount);
        assignAdjacentTiles();

        Button quitbtn = new Button("Exit Game");
        boardBtnSetup(quitbtn);
        quitbtn.setOnAction(e-> {
            System.exit(0);
        });

        setCenter(grid);
        getChildren().add(quitbtn);
    }

    public void setUpTimer(){
        Label timer = new Label("Time Elapsed: 0");
        bottomPane.getChildren().add(timer);
        bottomPane.setAlignment(Pos.CENTER);

        setBottom(bottomPane);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.play();
    }

    public void updateTimer() {
        timer++;
        Label myLabel = (Label)bottomPane.getChildren().get(0);
        myLabel.setText("Time Elapsed: " + timer);
    }

    public void stopTimer(){
        timeline.stop();
    }

    public void handleButtonClick(MouseEvent e, Tile tile) {
        if (e.getButton() == MouseButton.SECONDARY) {
            tile.rightClick();
        }
        if (e.getButton() == MouseButton.PRIMARY) {
            tile.leftClick();
        }
        
    }

    public void mineBoard(int mineCount) {
        Random rand = new Random();

        int randomRow = rand.nextInt(rowNum);
        int randomCol = rand.nextInt(colNum);

        int count = 0;

        while (count < mineCount) {
            if (boardGrid[randomRow][randomCol].mined == false) {
                Tile tile = new MineTile();
                tile.setOnMousePressed(e -> handleButtonClick(e,tile));
                boardGrid[randomRow][randomCol] = tile;
                tile.setPrefSize(btnSize, btnSize);
                grid.add(tile, randomRow, randomCol);
                count++;
            } else {
                randomRow = rand.nextInt(rowNum);
                randomCol = rand.nextInt(colNum);
            }
        }
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < rowNum && col >= 0 && col < colNum;
    }

    private void assignAdjacentTiles() {
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1}; 

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                for (int k = 0; k < 8; k++) {
                    int newRow = i + rowOffsets[k];
                    int newCol = j + colOffsets[k];

                    if (isInBounds(newRow, newCol)) {
                        if (boardGrid[newRow][newCol].getMined()) {
                            boardGrid[i][j].addAdjacentMine();
                        }
                        boardGrid[i][j].addAdjacentTile(boardGrid[newRow][newCol]);
                    }
                }
            }
        }
    }

    public void boardBtnSetup(Button btn) {
        Font font = Font.font("Courier New", FontWeight.MEDIUM, 10);
        btn.setFont(font);
        btn.setStyle("-fx-background-color: #ff0000; ");
        btn.autosize();

        btn.setLayoutX(900);
        btn.setLayoutY(25);

        btn.setMinSize(75, 25);
        btn.setMaxSize(75, 25);
    }

    public BorderPane getRootPane() {
        return this;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public int getButtonSize() {
        return btnSize;
    }
}