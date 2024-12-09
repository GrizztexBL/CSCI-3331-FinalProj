import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainMenu extends Pane{
    Stage stage;
    int btnCounter = 0;

    public MainMenu(Stage stage) {
        this.stage = stage;
        displayMenu();
    }

    public void displayMenu() {
        Scene scene = new Scene(this, MineSweeperConstants.MENU_WIDTH, MineSweeperConstants.MENU_HEIGHT);
        stage.setScene(scene);
        stage.setResizable(false);

        Button easybtn = new Button("Easy Mode: \n8x8");
        startingButtonSetup(easybtn);
        Button medbtn = new Button("Medium Mode: \n12x12");
        startingButtonSetup(medbtn);
        Button hardbtn = new Button("Hard Mode: \n14x20");
        startingButtonSetup(hardbtn);
        Button quitbtn = new Button("Exit Game");
        startingButtonSetup(quitbtn);

        easybtn.setOnAction(e->easyBoard());
        medbtn.setOnAction(e->medBoard());
        hardbtn.setOnAction(e->hardBoard());
        quitbtn.setOnAction(e-> {
            System.exit(0);
        });

        BackgroundImage myBI= new BackgroundImage(new Image("Cave.png",MineSweeperConstants.MENU_WIDTH,MineSweeperConstants.MENU_HEIGHT,false,true),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT);

        getChildren().add(easybtn);
        getChildren().add(medbtn);
        getChildren().add(hardbtn);
        getChildren().add(quitbtn);

        setBackground(new Background(myBI));
    }

    public void easyBoard(){
        Board board = new Board(stage, MineSweeperConstants.EASY_MINES, this);
        stage.setHeight(MineSweeperConstants.TILE_SIZE * MineSweeperConstants.EASY_COL+120);
        stage.setWidth(MineSweeperConstants.TILE_SIZE * MineSweeperConstants.EASY_ROW-2);
        stage.getScene().setRoot(board.getRootPane());
        BoardModel model = new BoardModel(MineSweeperConstants.EASY_ROW, MineSweeperConstants.EASY_COL, MineSweeperConstants.EASY_MINES);
        BoardController controller = new BoardController(model, board);
    }

    public void medBoard(){
        Board board = new Board(stage, MineSweeperConstants.MED_MINES, this);
        stage.setHeight(MineSweeperConstants.TILE_SIZE * MineSweeperConstants.MED_COL+110);
        stage.setWidth(MineSweeperConstants.TILE_SIZE * MineSweeperConstants.MED_ROW-10);
        stage.getScene().setRoot(board.getRootPane());
        BoardModel model = new BoardModel(MineSweeperConstants.MED_ROW, MineSweeperConstants.MED_COL, MineSweeperConstants.MED_MINES);
        BoardController controller = new BoardController(model, board);
    }

    public void hardBoard(){
        Board board = new Board(stage, MineSweeperConstants.HARD_MINES, this);
        stage.setHeight(MineSweeperConstants.TILE_SIZE * MineSweeperConstants.HARD_COL+105);
        stage.setWidth(MineSweeperConstants.TILE_SIZE * MineSweeperConstants.HARD_ROW-26);
        stage.getScene().setRoot(board.getRootPane());
        BoardModel model = new BoardModel(MineSweeperConstants.HARD_ROW, MineSweeperConstants.HARD_COL, MineSweeperConstants.HARD_MINES);
        BoardController controller = new BoardController(model, board);
    }

    public void homeBtn() {
        stage.getScene().setRoot(this.getRootPane());
        stage.setHeight(MineSweeperConstants.MENU_HEIGHT + 37);
        stage.setWidth(MineSweeperConstants.MENU_WIDTH);
    }

    public void reset(int mines){
        if(mines == MineSweeperConstants.EASY_MINES){
            easyBoard();
        }
        else if(mines == MineSweeperConstants.MED_MINES){
            medBoard();
        }
        else if(mines == MineSweeperConstants.HARD_MINES){
            hardBoard();
        }
    }

    public void startingButtonSetup(Button btn) {
        Font font = Font.font("Courier New", FontWeight.BOLD, 25);
        btn.setFont(font);
        btn.setStyle("-fx-background-color: #ff0000; ");
        btn.autosize();
        if (btnCounter == 0) {
            btn.setLayoutX(50);
            btn.setLayoutY(75);
        } else if (btnCounter == 1) {
            btn.setLayoutX(50);
            btn.setLayoutY(225);
        } else if (btnCounter == 2) {
            btn.setLayoutX(50);
            btn.setLayoutY(375);
        } else if (btnCounter == 3) {
            btn.setLayoutX(50);
            btn.setLayoutY(500);
        }
        
        btn.setMinSize(250, 75);
        btn.setMaxSize(250,75);
        btnCounter++;
    }

    public Pane getRootPane() {
        return this;
    }

    public void show() {
        stage.show();
    }
}
