package pl.mikolajp.creditapp.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.mikolajp.creditapp.core.anotation.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class Expense implements Serializable {
    public static final long serialVersionUID = 1l;
    @NotNull
    @JsonProperty
    private String name;
    @NotNull
    @JsonProperty
    private ExpenseType expenseType;
    @JsonProperty
    private double amount;

    public Expense(){}
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
