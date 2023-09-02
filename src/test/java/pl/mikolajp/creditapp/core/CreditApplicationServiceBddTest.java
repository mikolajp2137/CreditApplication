package pl.mikolajp.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.mikolajp.creditapp.core.exception.RequirementsNotMetCause;
import pl.mikolajp.creditapp.core.model.*;
import pl.mikolajp.creditapp.core.scoring.EducationCalculator;
import pl.mikolajp.creditapp.core.scoring.GuarantorsCalculator;
import pl.mikolajp.creditapp.core.scoring.IncomeCalculator;
import pl.mikolajp.creditapp.core.scoring.MaritalStatusCalculator;
import pl.mikolajp.creditapp.core.validation.*;
import pl.mikolajp.creditapp.core.validation.reflection.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreditApplicationServiceBddTest {
    private GuarantorsCalculator guarantorsCalculator = new GuarantorsCalculator();
    private EducationCalculator educationCalculator = new EducationCalculator();
    private MaritalStatusCalculator maritalStatusCalculator = new MaritalStatusCalculator();
    private IncomeCalculator incomeCalculator = new IncomeCalculator();
    private SelfEmployedScoringCalculator selfEmployedScoringCalculator = new SelfEmployedScoringCalculator();
    private PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(selfEmployedScoringCalculator, educationCalculator, incomeCalculator, maritalStatusCalculator, guarantorsCalculator);
    private List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
    private List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
    private CompoundPostValidator compoundPostValidator= new CompoundPostValidator(new PurposeOfLoanPostValidator(), new ExpensesPostValidator());

    private CreditApplicationValidator creditApplicationValidator = new CreditApplicationValidator(new ObjectValidator(fieldProcessors, classProcessors));
    private CreditApplicationService cut = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator(), creditApplicationValidator, compoundPostValidator);


    @Test
    @DisplayName("Should return NEGATIVE_REQUIREMENTS_NOT_MET, when loan amount is below 100000 for mortgage")
    public void test1() {
        //given
        List<FamilyMember> familyMembers = Arrays.asList(new FamilyMember("Ann", 18));
        NaturalPerson person = NaturalPerson.Builder.create()
                .withFamilyMembers(familyMembers)
                .withPesel("12312312312")
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test").withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 10000.00)))
                .withContactData(ContactData.Builder.create()
                        .withHomeAddress(Address.Builder.create()
                                .withState("test").withZipCode("11-111")
                                .withCity("test").withStreet("test").withHouseNumber("1")
                                .build())
                        .withCorrespondenceAddress(Address.Builder.create()
                                .withState("test").withZipCode("11-111")
                                .withCity("test").withStreet("test").withHouseNumber("1")
                                .build())
                        .withEmail("email@e.mail")
                        .withPhoneNumber("123456789")
                        .build())
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);

        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.NEGATIVE_REQUIREMENTS_NOT_MET, decision.getDecisionType());
        assertEquals(600, decision.getScoring());
        assertEquals(360000.00, decision.getCreditRate());
    }

    @Test
    @DisplayName("Should return Decision is negative, when years since founded <2")
    public void test2() {
        //given
        List<FamilyMember> familyMembers = Arrays.asList(new FamilyMember("Ann", 18));
        SelfEmployed person = SelfEmployed.Builder.create()
                .withFamilyMembers(familyMembers)
                .withNip("111111")
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test").withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 7000.00)))
                .withContactData(ContactData.Builder.create()
                        .withHomeAddress(Address.Builder.create()
                                .withState("test").withZipCode("11-111")
                                .withCity("test").withStreet("test").withHouseNumber("1")
                                .build())
                        .withCorrespondenceAddress(Address.Builder.create()
                                .withState("test").withZipCode("11-111")
                                .withCity("test").withStreet("test").withHouseNumber("1")
                                .build())
                        .withEmail("email@e.mail")
                        .withPhoneNumber("123456789")
                        .build())
                .withYearsSinceFounded(1)
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 500000.00, 30);

        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.NEGATIVE_SCORING, decision.getDecisionType());
        assertEquals(200, decision.getScoring());
    }

    @Test
    @DisplayName("Should return Decision is contact required, when years since founded >=2")
    public void test3() {
        //given
        List<FamilyMember> familyMembers = Arrays.asList(new FamilyMember("Ann", 18));
        SelfEmployed person = SelfEmployed.Builder.create()
                .withFamilyMembers(familyMembers)
                .withNip("111111")
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test").withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 7000.00)))
                .withContactData(ContactData.Builder.create()
                        .withHomeAddress(Address.Builder.create()
                                .withState("test").withZipCode("11-111")
                                .withCity("test").withStreet("test").withHouseNumber("1")
                                .build())
                        .withCorrespondenceAddress(Address.Builder.create()
                                .withState("test").withZipCode("11-111")
                                .withCity("test").withStreet("test").withHouseNumber("1")
                                .build())
                        .withEmail("email@e.mail")
                        .withPhoneNumber("123456789")
                        .build())
                .withYearsSinceFounded(3)
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 500000.00, 30);

        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.CONTACT_REQUIRED, decision.getDecisionType());
        assertEquals(400, decision.getScoring());
    }

    @Test
    @DisplayName("Should return Decision is negative requirements not met, cause: too high personal expenses")
    public void test4() {
        //given
        Set<Expense> expenseSet = Set.of(new Expense("1", ExpenseType.PERSONAL,500), new Expense("2",ExpenseType.PERSONAL, 750));
        final FinanceData financeData = new FinanceData(expenseSet, new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 2000.00));
        SelfEmployed person = SelfEmployed.Builder.create()
                .withNip("111111")
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test").withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(financeData)
                .withContactData(ContactData.Builder.create()
                        .withHomeAddress(Address.Builder.create()
                                .withState("test").withZipCode("11-111")
                                .withCity("test").withStreet("test").withHouseNumber("1")
                                .build())
                        .withCorrespondenceAddress(Address.Builder.create()
                                .withState("test").withZipCode("11-111")
                                .withCity("test").withStreet("test").withHouseNumber("1")
                                .build())
                        .withEmail("email@e.mail")
                        .withPhoneNumber("123456789")
                        .build())
                .withYearsSinceFounded(3)
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 500000.00, 30);

        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.NEGATIVE_REQUIREMENTS_NOT_MET, decision.getDecisionType());
        assertTrue(decision.getRequirementsNotMetCause().isPresent());
        assertEquals(RequirementsNotMetCause.TOO_HIGH_PERSONAL_EXPENSES, decision.getRequirementsNotMetCause().get());
    }
}