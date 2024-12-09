import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        // create and show main menu
        MainMenu myMenu = new MainMenu(primaryStage);
        myMenu.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
