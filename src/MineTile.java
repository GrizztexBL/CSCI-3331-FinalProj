import javafx.scene.input.MouseEvent;

public class MineTile extends Tile{
    public MineTile() {
        super();
        super.mined = true;
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
