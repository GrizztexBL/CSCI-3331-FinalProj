public class MineTile extends Tile{
    public MineTile(BoardModel model) {
        super(model);
        setMined();
    }

   

    public boolean getMined() {
        return super.mined;
    }

    public void setMined() {
        super.mined = true;
    }

    @Override
    protected void leftClick() {
        if (!this.getText().equals("🚩")) {
            this.setStyle("-fx-font-size: 20; -fx-text-fill: red; -fx-font-weight: bold");
            this.setText("💣");
            model.lose();
        }
    }

}
