import javafx.scene.input.MouseEvent;

public class SafeTile extends Tile{
    public SafeTile() {
        super();
    }

    public void addAdjacentTile(Tile tile) {
        this.adjacentTiles.add(tile);
    }

    public String getAdjacentTiles() {
        String tileString = "";
        for(int i = 0; i < adjacentTiles.size(); i++) {
            if (adjacentTiles.get(i).mined == true) {
                tileString += "1";
            } else {
                tileString += "0";
            }
        }
        System.out.println("---------------");
        return tileString;
    }

    // protected void setOnClicks(){
    //     this.setOnMouseClicked(event ->
    //     {
    //         //left click
    //         if (event.isPrimaryButtonDown()) {
    //             this.setDisable(true);
    //             this.
    //         }
    //         //right click
    //         if (event.isSecondaryButtonDown()) {
                
    //         }
    //     });
    // }

    // @Override
    // protected void onClick(MouseEvent mouseEvent) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'onClick'");
    // }

    // @Override
    // protected void leftClick() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'leftClick'");
    // }
}
