package edu.westga.comp4420.grocery_list.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Stores one income or expense transaction.
 *
 * @author Levi Rukundo
 * @version Fall 2026
 */
public final class Transaction {

    private final TransactionType type;
    private final BigDecimal amount;
    private final LocalDate date;
    private final String description;
    private final String category;

    /**
     * Creates a validated transaction.
     *
     * @param type the transaction type
     * @param amount a positive monetary amount
     * @param date the transaction date
     * @param description the income source or expense description
     * @param category the expense category; ignored for income
     * @throws IllegalArgumentException if required information is invalid
     */
    public Transaction(TransactionType type, BigDecimal amount,
            LocalDate date, String description, String category) {
        if (type == null) {
            throw new IllegalArgumentException("Select a transaction type.");
        }
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Select a date.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Enter a description.");
        }
        if (type == TransactionType.EXPENSE
                && (category == null || category.isBlank())) {
            throw new IllegalArgumentException("Select an expense category.");
        }
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.description = description.trim();
        this.category = type == TransactionType.INCOME ? "" : category.trim();
    }

    /**
     * Gets the transaction type.
     *
     * @return the type
     */
    public TransactionType getType() {
        return this.type;
    }

    /**
     * Gets the positive transaction amount.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * Gets the transaction date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the category.
     *
     * @return the expense category, or an empty string for income
     */
    public String getCategory() {
        return this.category;
    }
}