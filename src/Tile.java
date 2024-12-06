import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public abstract class Tile extends Button{
    boolean revealed;

    boolean flagged;

    boolean mined;

    ArrayList<Tile> adjacentTiles;

    public Tile() {
        revealed = false;
        flagged = false;
        mined = false;
        adjacentTiles = new ArrayList<>();
    }

    protected abstract void addAdjacentTile(Tile tile);

    protected abstract String getAdjacentTiles();

    // protected abstract void onClick(MouseEvent mouseEvent);

    // protected void rightClick(){

    // }

    // protected abstract void leftClick();
}
