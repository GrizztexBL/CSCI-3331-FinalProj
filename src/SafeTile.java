import javafx.stage.Stage;

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
        if(this.getText().equals("🚩")) {

        }
        else if (this.adjacentMineCount == 0) {
            this.setText(String.valueOf(this.adjacentMineCount));
            this.revealed = true;
            this.setDisable(revealed);
            for (Tile t: adjacentTiles) {
                if (!t.revealed) {
                    t.leftClick();
                }
            }
        }
        else {
            this.revealed = true;
            this.setText(String.valueOf(this.adjacentMineCount));
            this.setDisable(revealed);
        }
    }

    public void giveStage(Stage stage) {

    }
}
