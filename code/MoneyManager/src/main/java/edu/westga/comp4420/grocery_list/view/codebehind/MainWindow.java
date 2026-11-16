package edu.westga.comp4420.grocery_list.view.codebehind;

import java.math.BigDecimal;
import java.time.LocalDate;

import edu.westga.comp4420.grocery_list.model.Transaction;
import edu.westga.comp4420.grocery_list.model.TransactionLog;
import edu.westga.comp4420.grocery_list.model.TransactionType;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Handles the transaction log and entry windows.
 *
 * @author Levi Rukundo
 * @version Fall 2026
 */
public class MainWindow {

    @FXML
    private AnchorPane guiPane;

    private final TransactionLog log = new TransactionLog();
    private final TableView<Transaction> table = new TableView<>();
    private final Label message = new Label();

    @FXML
    void initialize() {
        Button incomeButton = new Button("Add Income");
        Button expenseButton = new Button("Add Expense");
        Button refreshButton = new Button("View Log");
        incomeButton.setOnAction(event -> this.openEntry(TransactionType.INCOME));
        expenseButton.setOnAction(event -> this.openEntry(TransactionType.EXPENSE));
        refreshButton.setOnAction(event -> this.refreshLog());
        this.configureTable();

        HBox buttons = new HBox(10, incomeButton, expenseButton, refreshButton);
        VBox content = new VBox(12, new Label("Money Manager"),
                buttons, this.table, this.message);
        content.setPadding(new Insets(20));
        AnchorPane.setTopAnchor(content, 0.0);
        AnchorPane.setBottomAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);
        this.guiPane.getChildren().setAll(content);
        this.refreshLog();
    }

    private void configureTable() {
        TableColumn<Transaction, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(
                cell.getValue().getDate().toString()));
        TableColumn<Transaction, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(
                cell.getValue().getType().toString()));
        TableColumn<Transaction, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(
                cell.getValue().getCategory()));
        TableColumn<Transaction, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(
                cell.getValue().getDescription()));
        TableColumn<Transaction, String> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(
                this.formatAmount(cell.getValue())));
        this.table.getColumns().add(dateColumn);
        this.table.getColumns().add(typeColumn);
        this.table.getColumns().add(categoryColumn);
        this.table.getColumns().add(descriptionColumn);
        this.table.getColumns().add(amountColumn);
        this.table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.table.setPlaceholder(new Label("No transactions recorded."));
        this.table.setPrefHeight(350);
    }

    private String formatAmount(Transaction transaction) {
        String prefix = "+$";
        if (transaction.getType() == TransactionType.EXPENSE) {
            prefix = "-$";
        }
        return prefix + transaction.getAmount().toPlainString();
    }

    private void refreshLog() {
        this.table.setItems(FXCollections.observableArrayList(
                this.log.getTransactions()));
        this.message.setText("Transactions: " + this.table.getItems().size());
    }

    private void openEntry(TransactionType type) {
        Stage window = new Stage();
        window.initOwner(this.guiPane.getScene().getWindow());
        window.initModality(Modality.WINDOW_MODAL);
        window.setTitle("Add " + type);

        TextField amount = new TextField();
        amount.setPromptText("Example: 25.00");
        DatePicker date = new DatePicker(LocalDate.now());
        TextField description = new TextField();
        description.setPromptText("Source or item description");
        ComboBox<String> category = new ComboBox<>();
        category.getItems().addAll("Food", "Housing", "Entertainment",
                "Transportation", "Utilities", "Other");
        category.setPromptText("Select category");
        Label error = new Label();
        error.setWrapText(true);
        Button save = new Button("Save");
        Button cancel = new Button("Cancel");
        save.setOnAction(event -> this.saveEntry(type, amount, date,
                description, category, error));
        cancel.setOnAction(event -> window.close());

        VBox form = this.createForm(amount, date, description, category);
        category.setDisable(type == TransactionType.INCOME);
        form.getChildren().addAll(error, new HBox(10, save, cancel));
        window.setScene(new Scene(form, 400, 440));
        window.showAndWait();
    }

    private VBox createForm(TextField amount, DatePicker date,
            TextField description, ComboBox<String> category) {
        VBox form = new VBox(10);
        form.setPadding(new Insets(20));
        form.getChildren().addAll(new Label("Amount"), amount,
                new Label("Date"), date, new Label("Description / Source"),
                description, new Label("Expense Category"), category);
        return form;
    }

    private void saveEntry(TransactionType type, TextField amount,
            DatePicker date, TextField description, ComboBox<String> category,
            Label error) {
        try {
            BigDecimal value = new BigDecimal(amount.getText().trim());
            Transaction transaction = new Transaction(type, value,
                    date.getValue(), description.getText(), category.getValue());
            this.log.addTransaction(transaction);
            this.refreshLog();
            this.message.setText("Transaction recorded successfully.");
            ((Stage) error.getScene().getWindow()).close();
        } catch (NumberFormatException exception) {
            error.setText("Enter a valid numeric amount, such as 25.00.");
        } catch (IllegalArgumentException exception) {
            error.setText(exception.getMessage());
        }
    }
}