import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BoardModel {
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
        totalTiles = rowNum * colNum;
        safeTiles = new SimpleIntegerProperty(totalTiles - mineCount);
        flagCount = new SimpleIntegerProperty(mineCount);
        lost = new SimpleBooleanProperty(false);
    }

    public void changeFlag(int num){
        flagCount.setValue(flagCount.getValue() + num);
    }

    public void decSafeTile(){
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
        lost.setValue(true);
    }

    public int getRowNum(){
        return rowNum;
    }

    public int getColNum(){
        return colNum;
    }
}
