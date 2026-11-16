package edu.westga.comp4420.grocery_list.test.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.grocery_list.model.Transaction;
import edu.westga.comp4420.grocery_list.model.TransactionLog;
import edu.westga.comp4420.grocery_list.model.TransactionType;

class TestTransactionLog {

    private Transaction createTransaction(TransactionType type) {
        return new Transaction(type, new BigDecimal("25.00"),
                LocalDate.of(2026, 11, 15), "Example", "Food");
    }

    @Test
    void testInitiallyEmpty() {
        TransactionLog log = new TransactionLog();

        assertTrue(log.getTransactions().isEmpty());
    }

    @Test
    void testRecordsIncomeAndExpenseInOrder() {
        TransactionLog log = new TransactionLog();
        Transaction income = this.createTransaction(TransactionType.INCOME);
        Transaction expense = this.createTransaction(TransactionType.EXPENSE);

        log.addTransaction(income);
        log.addTransaction(expense);

        assertEquals(2, log.getTransactions().size());
        assertEquals(income, log.getTransactions().get(0));
        assertEquals(expense, log.getTransactions().get(1));
    }

    @Test
    void testRejectsNullWithoutChangingLog() {
        TransactionLog log = new TransactionLog();

        assertThrows(IllegalArgumentException.class,
                () -> log.addTransaction(null));
        assertTrue(log.getTransactions().isEmpty());
    }

    @Test
    void testReturnedListCannotBeModified() {
        TransactionLog log = new TransactionLog();
        log.addTransaction(this.createTransaction(TransactionType.INCOME));

        assertThrows(UnsupportedOperationException.class,
                () -> log.getTransactions().clear());
        assertEquals(1, log.getTransactions().size());
    }

    @Test
    void testSnapshotDoesNotChangeWhenTransactionAdded() {
        TransactionLog log = new TransactionLog();
        List<Transaction> snapshot = log.getTransactions();

        log.addTransaction(this.createTransaction(TransactionType.INCOME));

        assertTrue(snapshot.isEmpty());
        assertEquals(1, log.getTransactions().size());
    }
}