import java.util.ArrayList;

public abstract class Tile implements Clickable {
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
}