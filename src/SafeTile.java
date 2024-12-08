public class SafeTile extends Tile{
    public SafeTile(BoardModel model) {
        super(model);
    }

    public boolean getMined() {
        return mined;
    }

    @Override
    protected void leftClick() {
        if (!this.getText().equals("🚩")) {
            if (this.adjacentMineCount == 0) {
                this.setText(String.valueOf(this.adjacentMineCount));
                this.revealed = true;
                while(!adjacentTiles.empty()){
                    adjacentTiles.pop().leftClick();
                }
            }
            else 
                this.setText(String.valueOf(this.adjacentMineCount));
            if(!this.isDisabled()){model.decSafeTile();}
            this.setDisable(true);
        }
    }
}
