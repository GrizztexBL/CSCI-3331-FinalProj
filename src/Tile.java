import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public abstract class Tile extends Button{
    boolean revealed;

    boolean flagged;

    boolean mined;

    ArrayList<Tile> adjacentTiles;

    int adjacentMineCount;

    public Tile() {
        revealed = false;
        flagged = false;
        mined = false;
        adjacentTiles = new ArrayList<>();
        adjacentMineCount = 0;
    }

    protected abstract void addAdjacentTile(Tile tile);

    protected void rightClick(){
        if(this.getText().equals("")){
            this.setText("🚩");
            this.setStyle("-fx-font-size: 20; -fx-text-fill: red;");
        }
        else
            this.setText("");
    }

    public abstract void giveStage(Stage stage);

    protected abstract void leftClick();

    public abstract void addAdjacentMine();

    public abstract boolean getMined();
}
