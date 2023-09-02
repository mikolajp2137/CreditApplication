package pl.mikolajp.creditapp.core.model;

import pl.mikolajp.creditapp.core.anotation.NotNull;

import java.util.Objects;

public class Expense {
    @NotNull
    private final String name;
    @NotNull
    private final ExpenseType expenseType;
    private final double amount;

    public Expense(String name, ExpenseType expenseType, double amount) {
        this.name = name;
        this.expenseType = expenseType;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Objects.equals(name, expense.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
