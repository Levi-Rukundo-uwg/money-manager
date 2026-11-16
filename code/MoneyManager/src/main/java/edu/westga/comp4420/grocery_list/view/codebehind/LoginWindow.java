package edu.westga.comp4420.grocery_list.view.codebehind;

import edu.westga.comp4420.grocery_list.model.LoginService;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Displays the application's login screen.
 *
 * @author Levi Rukundo
 * @version Fall 2026
 */
public final class LoginWindow {

    /**
     * Shows the login dialog and waits for successful login or cancellation.
     *
     * @return true if the user successfully logs in
     */
    public boolean showLogin() {
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Money Manager - Login");
        dialog.setHeaderText("Sign in to Money Manager");
        TextField username = new TextField();
        PasswordField password = new PasswordField();
        Label error = new Label();
        username.setPromptText("Username");
        password.setPromptText("Password");
        VBox form = new VBox(10, new Label("Username"), username,
                new Label("Password"), password, error);
        form.setPadding(new Insets(20));
        dialog.getDialogPane().setContent(form);
        ButtonType login = new ButtonType("Login", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(login, ButtonType.CANCEL);
        Button loginButton = (Button) dialog.getDialogPane().lookupButton(login);
        LoginService service = new LoginService();
        
        loginButton.addEventFilter(ActionEvent.ACTION, event -> {
            if (!service.authenticate(username.getText(), password.getText())) {
                error.setText("Incorrect username or password. Try again.");
                password.clear();
                event.consume();
            }
        });
        dialog.setResultConverter(button -> button == login);
        return dialog.showAndWait().orElse(false);
    }
}