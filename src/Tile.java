import java.util.Stack;

import javafx.scene.control.Button;

public abstract class Tile extends Button{
    boolean revealed;

    boolean flagged;

    boolean mined;

    Stack<Tile> adjacentTiles;

    int adjacentMineCount;

    BoardModel model;

    public Tile(BoardModel model) {
        super();
        revealed = false;
        flagged = false;
        mined = false;
        this.model = model;
        adjacentTiles = new Stack<>();
        adjacentMineCount = 0;
        this.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-background-insets: 0 0 -1 0, 0, 1, 2;-fx-background-radius: 3px, 3px, 2px, 1px;-fx-focus-color: transparent; -fx-faint-focus-color: transparent");
    }

    public void addAdjacentTile(Tile tile) {
        this.adjacentTiles.push(tile);
    }

    protected void rightClick(){
        if(this.getText().equals("") && model.getFlagCount().getValue() > 0){
            this.setText("🚩");
            this.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-background-insets: 0 0 -1 0, 0, 1, 2;-fx-background-radius: 3px, 3px, 2px, 1px;-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-font-size: 20; -fx-text-fill: red;");
            model.changeFlag(-1);
        }
        else if(this.getText().equals("🚩")){
            this.setText("");
            this.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-background-insets: 0 0 -1 0, 0, 1, 2;-fx-background-radius: 3px, 3px, 2px, 1px;-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-text-fill: black;");
            model.changeFlag(1);
        }
    }

    protected abstract void leftClick();

    public void addAdjacentMine() {
        adjacentMineCount += 1;
    }

    public abstract boolean getMined();
}
