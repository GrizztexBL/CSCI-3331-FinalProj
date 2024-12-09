import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BoardModel {
    // member variables
    int rowNum, colNum, mineCount;
    int totalTiles;
    Tile[][] boardGrid;
    IntegerProperty safeTiles;
    IntegerProperty flagCount;
    BooleanProperty lost;

    public BoardModel(int rowNum, int colNum, int mineCount){
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.boardGrid = new Tile[rowNum][colNum];
        this.mineCount = mineCount;
        // set up properties and calculated values
        totalTiles = rowNum * colNum;
        safeTiles = new SimpleIntegerProperty(totalTiles - mineCount);
        flagCount = new SimpleIntegerProperty(mineCount);
        lost = new SimpleBooleanProperty(false);
    }

    public void changeFlag(int num){
        // update flag count depending on num (1 for removal, -1 for placing)
        flagCount.setValue(flagCount.getValue() + num);
    }

    public void decSafeTile(){
        // subtract 1 from safeTiles
        safeTiles.setValue(safeTiles.getValue()-1);
    }

    public Tile[][] getBoardGrid(){
        return boardGrid;
    }

    public int getMineCount(){
        return mineCount;
    }

    public IntegerProperty getFlagCount(){
        return flagCount;
    }

    public IntegerProperty getSafeTiles(){
        return safeTiles;
    }

    public BooleanProperty getLost(){
        return lost;
    }

    public void lose(){
        // updated when the player loses
        lost.setValue(true);
    }

    public int getRowNum(){
        return rowNum;
    }

    public int getColNum(){
        return colNum;
    }
}
