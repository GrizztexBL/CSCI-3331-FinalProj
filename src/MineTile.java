public class MineTile extends Tile{
    // constructor
    public MineTile(BoardModel model) {
        // parent constructor
        super(model);
        setMined();
    }

    public void setMined() {
        super.mined = true;
    }

    @Override
    protected void leftClick() {
        // end game if player clicks on mine
        if (!this.getText().equals("🚩")) {
            this.setStyle("-fx-font-size: 20; -fx-text-fill: red; -fx-font-weight: bold");
            this.setText("💣");
            model.lose();
        }
    }

}
