package pl.mikolajp.creditapp.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.mikolajp.creditapp.core.anotation.NotNull;
import pl.mikolajp.creditapp.core.anotation.ValidateCollection;

import java.io.Serializable;
import java.util.*;

public class FinanceData implements Serializable {
    public static final long serialVersionUID = 1l;
    @NotNull
    @ValidateCollection
    @JsonProperty
    private List<SourceOfIncome> sourcesOfIncome;
    @NotNull
    @ValidateCollection
    @JsonProperty
    private Set<Expense> expenses;

    public FinanceData(){}
    public FinanceData(SourceOfIncome... sourcesOfIncome) {
        this.sourcesOfIncome = Arrays.asList(sourcesOfIncome);
        this.expenses = new HashSet<>();
    }

    public FinanceData(Set<Expense> expenses, SourceOfIncome... sourcesOfIncome) {
        this.sourcesOfIncome = Arrays.asList(sourcesOfIncome);
        this.expenses = expenses;
    }

    private Map<ExpenseType, Set<Expense>> getExpenseMap() {
        Map<ExpenseType, Set<Expense>> result = new HashMap<>();
        for (Expense expense : expenses) {
            if (result.containsKey(expense.getExpenseType())) {
                result.get(expense.getExpenseType()).add(expense);
            } else {
                Set<Expense> set = new HashSet<>();
                set.add(expense);
                result.put(expense.getExpenseType(), set);
            }
        }
        return result;
    }

    public double getSumOfExpenses(ExpenseType type) {
        double sum = 0.0;
        Map<ExpenseType, Set<Expense>> expenseTypeSetMap = getExpenseMap();

        if (expenseTypeSetMap.isEmpty()) {
            return sum;
        }
        for (Expense expense : expenseTypeSetMap.get(type)) {
            sum += expense.getAmount();
        }

        return sum;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public List<SourceOfIncome> getSourcesOfIncome() {
        return sourcesOfIncome;
    }
}
