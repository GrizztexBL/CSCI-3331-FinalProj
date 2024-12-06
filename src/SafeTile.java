import javafx.scene.input.MouseEvent;

public class SafeTile extends Tile{
    public SafeTile() {
        super();
    }

    public void addAdjacentTile(Tile tile) {
        this.adjacentTiles.add(tile);
    }

    public void addAdjacentMine() {
        super.adjacentMineCount += 1;
    }

    public boolean getMined() {
        return mined;
    }

    @Override
    protected void leftClick() {
        if (this.adjacentMineCount == 0) {
            this.setText(String.valueOf(this.adjacentMineCount));
            this.revealed = true;
            for (Tile t: adjacentTiles) {
                if (!t.revealed) {
                    t.leftClick();
                }
            }
        }
        else 
            this.setText(String.valueOf(this.adjacentMineCount));
    }
}
