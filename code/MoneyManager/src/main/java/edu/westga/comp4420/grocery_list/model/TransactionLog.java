package edu.westga.comp4420.grocery_list.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores the recorded income and expense transactions.
 *
 * @author Levi Rukundo
 * @version Fall 2026
 */
public final class TransactionLog {

    private final List<Transaction> transactions;

    /**
     * Creates an empty transaction log.
     */
    public TransactionLog() {
        this.transactions = new ArrayList<>();
    }

    /**
     * Records a transaction.
     *
     * @param transaction the transaction to record
     * @throws IllegalArgumentException if transaction is null
     */
    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Provide a transaction.");
        }
        this.transactions.add(transaction);
    }

    /**
     * Gets a read-only snapshot of the recorded transactions.
     *
     * @return the transactions in insertion order
     */
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(
                new ArrayList<>(this.transactions));
    }
}