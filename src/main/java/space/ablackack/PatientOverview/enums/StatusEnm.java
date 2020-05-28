package space.ablackack.PatientOverview.enums;

public enum StatusEnm {
    DISCHARGED("Discharged"),
    HOSPITAL("In hospital"),
    AGAINST_MEDICAL_ADVICE("Left against medical advice"),
    UNDER_TREATMENT("Under treatment"),
    INVALID_VALUE("Value is invalid or not set");

    private final String displayValue;

    StatusEnm(String displayValue) {
        this.displayValue = displayValue;
    }

    public static StatusEnm fromString(String text) {
        for (StatusEnm status : StatusEnm.values()) {
            if (status.getDisplayValue().equalsIgnoreCase(text)) {
                return status;
            }
        }
        return null;
    }

    public static StatusEnm fromValueString(String string) {
        if (string != null) {
            try {
                return StatusEnm.valueOf(string.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
