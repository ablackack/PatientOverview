package space.ablackack.PatientOverview.enums;

public enum TriageCatEnm {
  RED("CAT I / SK 1"),
  YELLOW("CAT II / SK 2"),
  GREEN("CAT III / SK 3"),
  BLUE("CAT IV / SK 4"),
  BLACK("EX"),
  INVALID_VALUE("Value is invalid or not set");

  private final String displayValue;

  TriageCatEnm(String displayValue) {
    this.displayValue = displayValue;
  }

  public static TriageCatEnm fromString(String text) {
    for (TriageCatEnm cat : TriageCatEnm.values()) {
      if (cat.getDisplayValue().equalsIgnoreCase(text)) {
        return cat;
      }
    }
    return null;
  }

  public String getAsString() {
    return this.toString();
  }

  public String getDisplayValue() {
    return this.displayValue;
  }
}
