import javafx.scene.input.MouseEvent;

public class MineTile extends Tile{
    public MineTile(Board board) {
        super(board);
        setMined();
    }

    public void addAdjacentTile(Tile tile) {
        this.adjacentTiles.add(tile);
    }

    public void addAdjacentMine() {
        super.adjacentMineCount += 1;
    }

    public boolean getMined() {
        return super.mined;
    }

    public void setMined() {
        super.mined = true;
    }

    @Override
    protected void leftClick() {
        this.setText("💣");
    }


}
