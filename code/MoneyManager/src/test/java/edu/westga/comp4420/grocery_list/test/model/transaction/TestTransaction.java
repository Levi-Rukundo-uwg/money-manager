package edu.westga.comp4420.grocery_list.test.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.grocery_list.model.Transaction;
import edu.westga.comp4420.grocery_list.model.TransactionType;

class TestTransaction {

    private static final LocalDate DATE = LocalDate.of(2026, 11, 15);
    private static final BigDecimal AMOUNT = new BigDecimal("25.00");

    @Test
    void testValidExpense() {
        Transaction transaction = new Transaction(
                TransactionType.EXPENSE, AMOUNT, DATE, " Lunch ", " Food ");

        assertEquals(TransactionType.EXPENSE, transaction.getType());
        assertEquals(AMOUNT, transaction.getAmount());
        assertEquals(DATE, transaction.getDate());
        assertEquals("Lunch", transaction.getDescription());
        assertEquals("Food", transaction.getCategory());
    }

    @Test
    void testIncomeDoesNotRequireCategory() {
        Transaction transaction = new Transaction(
                TransactionType.INCOME, AMOUNT, DATE, "Job", null);

        assertEquals(TransactionType.INCOME, transaction.getType());
        assertEquals("", transaction.getCategory());
    }

    @Test
    void testIncomeIgnoresProvidedCategory() {
        Transaction transaction = new Transaction(
                TransactionType.INCOME, AMOUNT, DATE, "Job", "Food");

        assertEquals("", transaction.getCategory());
    }

    @Test
    void testNullType() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(
                null, AMOUNT, DATE, "Lunch", "Food"));
    }

    @Test
    void testNullAmount() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(
                TransactionType.EXPENSE, null, DATE, "Lunch", "Food"));
    }

    @Test
    void testZeroAmount() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(
                TransactionType.EXPENSE, BigDecimal.ZERO, DATE, "Lunch", "Food"));
    }

    @Test
    void testNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(
                TransactionType.EXPENSE, new BigDecimal("-1"), DATE, "Lunch", "Food"));
    }

    @Test
    void testNullDate() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(
                TransactionType.EXPENSE, AMOUNT, null, "Lunch", "Food"));
    }

    @Test
    void testNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(
                TransactionType.EXPENSE, AMOUNT, DATE, null, "Food"));
    }

    @Test
    void testBlankDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(
                TransactionType.EXPENSE, AMOUNT, DATE, "   ", "Food"));
    }

    @Test
    void testNullExpenseCategory() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(
                TransactionType.EXPENSE, AMOUNT, DATE, "Lunch", null));
    }

    @Test
    void testBlankExpenseCategory() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(
                TransactionType.EXPENSE, AMOUNT, DATE, "Lunch", "   "));
    }
}