import java.util.Stack;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public abstract class Tile extends Button{
    // member variables
    boolean revealed;

    boolean flagged;

    boolean mined;

    Stack<Tile> adjacentTiles;

    int adjacentMineCount;

    BoardModel model;
    Image image = new Image("rock.png", MineSweeperConstants.TILE_SIZE, MineSweeperConstants.TILE_SIZE, false, true, true);
    BackgroundImage bImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(MineSweeperConstants.TILE_SIZE, MineSweeperConstants.TILE_SIZE, true, true, true, true));
    Background backGround = new Background(bImage);

    public Tile(BoardModel model) {
        super();
        revealed = false;
        flagged = false;
        mined = false;
        this.model = model;
        adjacentTiles = new Stack<>();
        adjacentMineCount = 0;

        // add graphics to tile
        setMinSize(MineSweeperConstants.TILE_SIZE-2, MineSweeperConstants.TILE_SIZE-2);
        setMaxSize(MineSweeperConstants.TILE_SIZE-2, MineSweeperConstants.TILE_SIZE-2);
        setBackground(backGround);

        this.setStyle("-fx-font-size: 20; -fx-text-fill: red; -fx-font-weight: bold");
    }

    public void addAdjacentTile(Tile tile) {
        this.adjacentTiles.push(tile);
    }

    protected void rightClick(){
        // place flag
        if(this.getText().equals("") && model.getFlagCount().getValue() > 0){
            this.setText("🚩");
            this.setStyle("-fx-font-size: 20; -fx-text-fill: red; -fx-font-weight: bold");
            model.changeFlag(-1);
        }
        // remove flag
        else if(this.getText().equals("🚩")){
            this.setText("");
            this.setStyle("-fx-font-size: 20; -fx-text-fill: red; -fx-font-weight: bold");
            model.changeFlag(1);
        }
    }

    protected abstract void leftClick();

    public void addAdjacentMine() {
        adjacentMineCount += 1;
    }

    public boolean getMined() {
        return mined;
    }
}
