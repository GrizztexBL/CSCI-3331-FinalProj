import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

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

    }

    protected abstract void leftClick();

    public abstract void addAdjacentMine();

    public abstract boolean getMined();
}
