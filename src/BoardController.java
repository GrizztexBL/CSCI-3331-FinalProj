import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class BoardController {
    BoardModel model;
    Board board;
    IntegerProperty timer = new SimpleIntegerProperty(0);
    Timeline timeline;

    public BoardController(BoardModel model, Board board){
        this.model = model;
        this.board = board;
        setUpTimer();
        elapseTime();
        generateBoard();
        setUpFlags();
        setUpWin();
        setUpLose();
    }

    public void setUpTimer(){
        timer.addListener(ov -> {
            board.updateTimer(timer.getValue());
        });
    }

    public void elapseTime(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> incrementTime()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void incrementTime(){
        timer.setValue(timer.getValue()+1);
    }

    public void setUpFlags(){
        model.getFlagCount().addListener(ov -> {
            board.updateFlagCount(model.getFlagCount().getValue());
        });
    }

    public void setUpWin(){
        model.getSafeTiles().addListener(ov -> {
            if(model.getSafeTiles().getValue() == 0){
                won();
            }
        });
    }

    public void setUpLose(){
        model.getLost().addListener(ov -> {
            if(model.getLost().getValue()){
                lost();
            }
        });
    }

    public void generateBoard() {
        
        for (int i = 0; i < model.getRowNum(); i++) {
            for (int j = 0; j < model.getColNum(); j++) {
                Tile tile = new SafeTile(model);
                tile.setPrefSize(MineSweeperConstants.TILE_SIZE, MineSweeperConstants.TILE_SIZE);
                tile.setOnMousePressed(e -> handleButtonClick(e, tile));
                model.getBoardGrid()[i][j] = tile;

            }
        }
        
        mineBoard(board.getMineCount());
        assignAdjacentTiles();
        for (int a = 0; a < model.getRowNum(); a++) {
            for (int b = 0; b < model.getColNum(); b++) {
                board.getGrid().add(model.boardGrid[a][b], a, b);
            }
        }

        board.setCenter(board.getGrid());
    }

    public void handleButtonClick(MouseEvent e, Tile tile) {
        if(e.getButton() == MouseButton.SECONDARY) {
            tile.rightClick();
        }
        if (e.getButton() == MouseButton.PRIMARY) {
            tile.leftClick();
        }
    }

    public void mineBoard(int mineCount) {
        Random rand = new Random();

        int randomRow = rand.nextInt(model.getRowNum());
        int randomCol = rand.nextInt(model.getColNum());

        int count = 0;

        while (count < mineCount) {
            if (model.getBoardGrid()[randomRow][randomCol].mined == false) {
                Tile tile = new MineTile(model);
                tile.setOnMousePressed(e -> handleButtonClick(e,tile));
                model.getBoardGrid()[randomRow][randomCol] = tile;
                tile.setPrefSize(MineSweeperConstants.TILE_SIZE, MineSweeperConstants.TILE_SIZE);
                count++;
            } else {
                randomRow = rand.nextInt(model.getRowNum());
                randomCol = rand.nextInt(model.getColNum());
            }
        }
    }

    private void assignAdjacentTiles() {
        //Offsets for grabbing the adjacents
        /*
         * [(-1,-1)(-1, 0)(-1, 1)]
         * [( 0,-1)( 0, 0)( 0, 1)]
         * [( 1,-1)( 1, 0)( 1, 1)]
         */
        //Each offset value is for a cordinate of the nearby tiles, (0,0) is
        //not in the offsets because it is the base tile
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1}; 

        //For loops to go though each tile
        for (int i = 0; i < model.getRowNum(); i++) {
            for (int j = 0; j < model.getColNum(); j++) {
                //This loop is for going through each offset to grab adjacents
                for (int k = 0; k < 8; k++) {
                    //Take row and column of current tile and apply offset
                    int newRow = i + rowOffsets[k];
                    int newCol = j + colOffsets[k];

                    //If that adjacent tile is in the bounds of the board add them to the adjacent tiles stack
                    if (isInBounds(newRow, newCol)) {
                        //If that adjacent tile is a mine, also add increase that tiles adjacent mine count
                        if (model.getBoardGrid()[newRow][newCol].getMined()) {
                            model.getBoardGrid()[i][j].addAdjacentMine();
                        }
                        model.getBoardGrid()[i][j].addAdjacentTile(model.getBoardGrid()[newRow][newCol]);
                    }
                }
            }
        }
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < model.getRowNum() && col >= 0 && col < model.getColNum();
    }

    public void won(){
        timeline.stop();
        disableButtons();
        board.setWinLabel(timer.getValue());
    }

    public void lost(){
        timeline.stop();
        disableButtons();
        board.setLostLabel(timer.getValue());
    }

    public void disableButtons(){
        for(int r = 0; r < model.getBoardGrid().length; r++){
            for(int c = 0; c < model.getBoardGrid()[0].length; c++){
                if(!model.getBoardGrid()[r][c].mined){
                    model.getBoardGrid()[r][c].setDisable(true);
                }
                else{
                    model.getBoardGrid()[r][c].setStyle("-fx-font-size: 20; -fx-text-fill: red; -fx-font-weight: bold");
                    model.getBoardGrid()[r][c].setText("💣");
                    model.getBoardGrid()[r][c].setDisable(true);
                }
            }
        }
    }

}
