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
                //this.setText(String.valueOf(this.adjacentMineCount));
                this.setText("");
                this.revealed = true;
                while(!adjacentTiles.empty()){
                    adjacentTiles.pop().leftClick();
                }
            }
            else{
                setColor();
                this.setText(String.valueOf(this.adjacentMineCount));
            }   
            if(!this.isDisabled()){model.decSafeTile();}
            this.setDisable(true);
        }
    }

    protected void setColor(){
        if(this.adjacentMineCount == 1){
            this.setStyle("-fx-stroke: black; -fx-stroke-width: 1px; -fx-font-size: 20; -fx-text-fill: magenta; -fx-font-weight: bold");
        }
        else if(this.adjacentMineCount == 2){
            this.setStyle("-fx-stroke: black; -fx-stroke-width: 1px; -fx-font-size: 20; -fx-text-fill: blue; -fx-font-weight: bold");
        }
        else if(this.adjacentMineCount == 3){
            this.setStyle("-fx-stroke: black; -fx-stroke-width: 1px; -fx-font-size: 20; -fx-text-fill: yellow; -fx-font-weight: bold");
        }
        else if(this.adjacentMineCount == 4){
            this.setStyle("-fx-stroke: black; -fx-stroke-width: 1px; -fx-font-size: 20; -fx-text-fill: orange; -fx-font-weight: bold");
        }
        else{
            this.setStyle("-fx-stroke: black; -fx-stroke-width: 1px; -fx-font-size: 20; -fx-text-fill: red; -fx-font-weight: bold");
        }
    }
}
