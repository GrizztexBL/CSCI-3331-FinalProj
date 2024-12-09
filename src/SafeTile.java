public class SafeTile extends Tile{
    // constructor
    public SafeTile(BoardModel model) {
        // parent constructor
        super(model);
    }

    @Override
    protected void leftClick() {
        if (!this.getText().equals("🚩")) {
            // if the tile is not adjacent to mines
            if (this.adjacentMineCount == 0) {
                this.setText("");
                this.revealed = true;
                // reveal all adjacent mines using recursion
                while(!adjacentTiles.empty()){
                    adjacentTiles.pop().leftClick();
                }
            }
            else{
                // display number of adjacent mines
                setColor();
                this.setText(String.valueOf(this.adjacentMineCount));
            }   
            // disable tile and decrement safe tiles
            if(!this.isDisabled()){model.decSafeTile();}
            this.setDisable(true);
        }
    }

    protected void setColor(){
        // give numbers different colors
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
