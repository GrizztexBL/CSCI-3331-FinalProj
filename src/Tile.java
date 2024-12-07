import java.util.ArrayList;
import javafx.scene.control.Button;

public abstract class Tile extends Button{
    boolean revealed;

    boolean flagged;

    boolean mined;

    ArrayList<Tile> adjacentTiles;

    int adjacentMineCount;

    Board board;

    public Tile(Board board) {
        super();
        revealed = false;
        flagged = false;
        mined = false;
        this.board = board;
        adjacentTiles = new ArrayList<>();
        adjacentMineCount = 0;
        this.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-background-insets: 0 0 -1 0, 0, 1, 2;-fx-background-radius: 3px, 3px, 2px, 1px;-fx-focus-color: transparent; -fx-faint-focus-color: transparent");
    }

    protected abstract void addAdjacentTile(Tile tile);

    protected void rightClick(){
        if(this.getText().equals("") && board.getFlag() > 0){
            this.setText("🚩");
            this.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-background-insets: 0 0 -1 0, 0, 1, 2;-fx-background-radius: 3px, 3px, 2px, 1px;-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-font-size: 20; -fx-text-fill: red;");
            board.changeFlag(-1);
        }
        else if(this.getText().equals("🚩")){
            this.setText("");
            this.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-background-insets: 0 0 -1 0, 0, 1, 2;-fx-background-radius: 3px, 3px, 2px, 1px;-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-text-fill: black;");
            board.changeFlag(1);
        }
    }

    protected abstract void leftClick();

    public abstract void addAdjacentMine();

    public abstract boolean getMined();
}
