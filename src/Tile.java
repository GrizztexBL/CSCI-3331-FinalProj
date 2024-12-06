import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public abstract class Tile extends Button{
    boolean revealed;

    boolean flagged;

    boolean mined;

    ArrayList<Tile> adjacentTiles;

    public Tile() {
        revealed = false;
        flagged = false;
        mined = false;
        adjacentTiles = new ArrayList<>();
    }

    protected abstract void addAdjacentTile(Tile tile);

    protected abstract String getAdjacentTiles();

    protected void rightClick(){
        if(this.getText().equals("")){
            this.setText("🚩");
            this.setStyle("-fx-font-size: 20; -fx-text-fill: red;");
        }
        else
            this.setText("");
    }

    protected abstract void leftClick();
}
