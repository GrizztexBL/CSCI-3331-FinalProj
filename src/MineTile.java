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
}
