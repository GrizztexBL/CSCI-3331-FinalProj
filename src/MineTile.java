import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MineTile extends Tile{
    Stage stage;
    
    public MineTile() {
        super();
        setMined();
    }

    public void addAdjacentTile(Tile tile) {
        this.adjacentTiles.add(tile);
    }

    public void addAdjacentMine() {
        super.adjacentMineCount += 1;
    }

    public boolean getMined() {
        return super.mined;
    }

    public void setMined() {
        super.mined = true;
    }

    public void giveStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    protected void leftClick() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("This is a Dialog"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        Button main = new Button();
        main.setOnAction(e -> {
            MainMenu myMenu = new MainMenu(this.stage);
            myMenu.show();
        });
        dialogVbox.getChildren().add(main);
        dialog.setScene(dialogScene);
        dialog.show();
        this.setText("💣");
    }


}
