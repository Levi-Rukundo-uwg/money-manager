package edu.westga.comp4420.grocery_list;

import java.io.IOException;

import edu.westga.comp4420.grocery_list.view.codebehind.LoginWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Starts the Money Manager application.
 *
 * @author Levi Rukundo
 * @version Fall 2026
 */
public class Main extends Application {

    public static final String WINDOW_TITLE = "Money Manager";
    public static final String MAIN_WINDOW_RESOURCE =
            "view/codebehind/MainWindow.fxml";
    public static final String ADD_ITEM_WINDOW_RESOURCE =
            "view/codebehind/AddItemWindow.fxml";

    /**
     * Displays login before opening the transaction log.
     *
     * @param primaryStage the main application window
     * @throws IOException if the main window cannot be loaded
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Platform.setImplicitExit(false);
        LoginWindow login = new LoginWindow();
        if (!login.showLogin()) {
            Platform.exit();
            return;
        }
        Parent parent = FXMLLoader.load(
                Main.class.getResource(MAIN_WINDOW_RESOURCE));
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
        Platform.setImplicitExit(true);
    }

    /**
     * Launches the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Main.launch(args);
    }
}