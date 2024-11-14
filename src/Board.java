import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Board extends BorderPane{
    Stage stage;
    ArrayList<Tile> boardButtons = new ArrayList<>();
    Tile[][] boardGrid;
    int rowNum, colNum, mineCount;

    public Board(Stage stage, int rowNum, int colNum, int mineCount) {
        this.stage = stage;
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.mineCount = mineCount;
        this.boardGrid = new Tile[rowNum][colNum];
        generateBoard(rowNum, colNum, mineCount);
    }

    public void generateBoard(int rowNum, int colNum, int mineCount) {
        fillBoard (rowNum, colNum);
        mineBoard(mineCount);
        assignAdjacentTiles();

        GridPane grid = new GridPane();
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                Tile tile = new SafeTile();
                tile.setMinSize(50, 50);
                tile.setOnAction(e -> handleButtonClick(tile));
                boardGrid[i][j] = tile;
                grid.add(tile, i, j);
            }
        }

        Button quitbtn = new Button("Exit Game");
        boardBtnSetup(quitbtn);
        quitbtn.setOnAction(e-> {
            System.exit(0);
        });

        setCenter(grid);
        getChildren().add(quitbtn);
    }

    public void handleButtonClick(Tile tile) {

    }

    private void fillBoard(int rowLength, int colLength) {
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                boardGrid[i][j] = new SafeTile();
            }
        }
    }

    public void mineBoard(int mineCount) {
        Random rand = new Random();

        int randomRow = rand.nextInt(rowNum);
        int randomCol = rand.nextInt(colNum);

        int count = 0;

        while (count < mineCount) {
            if (boardGrid[randomRow][randomCol].mined == false) {
                boardGrid[randomRow][randomCol] = new MineTile();
                boardGrid[randomRow][randomCol].mined = true;
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


}
