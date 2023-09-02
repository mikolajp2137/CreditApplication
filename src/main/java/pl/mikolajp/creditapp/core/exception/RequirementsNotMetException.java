package pl.mikolajp.creditapp.core.exception;

public class RequirementsNotMetException extends Exception{
    private final RequirementsNotMetCause requirementsNotMetCause;

    public RequirementsNotMetException(RequirementsNotMetCause requirementsNotMetCause) {
        this.requirementsNotMetCause = requirementsNotMetCause;
    }

    public RequirementsNotMetCause getRequirementsNotMetCause() {
        return requirementsNotMetCause;
    }


}
