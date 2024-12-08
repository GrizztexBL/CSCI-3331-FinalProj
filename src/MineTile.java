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
            this.setText("💣");
            model.lose();
        }
    }

}
