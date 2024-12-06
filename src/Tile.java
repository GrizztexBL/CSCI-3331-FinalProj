import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public abstract class Tile extends Button{
    boolean revealed;

    boolean flagged;

    boolean mined;

    ArrayList<Tile> adjacentTiles;

    int adjacentMineCount;

    Board board;

    public Tile(Board board) {
        revealed = false;
        flagged = false;
        mined = false;
        this.board = board;
        adjacentTiles = new ArrayList<>();
        adjacentMineCount = 0;
    }

    protected abstract void addAdjacentTile(Tile tile);

    protected void rightClick(){
        if(this.getText().equals("") && board.getFlag() > 0){
            this.setText("🚩");
            this.setStyle("-fx-font-size: 20; -fx-text-fill: red;");
            board.changeFlag(-1);
        }
        else if(this.getText().equals("🚩")){
            this.setText("");
            board.changeFlag(1);
        }
    }

    protected abstract void leftClick();

    public abstract void addAdjacentMine();

    public abstract boolean getMined();
}
