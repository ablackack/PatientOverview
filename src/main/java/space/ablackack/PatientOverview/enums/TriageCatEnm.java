package space.ablackack.PatientOverview.enums;

public enum TriageCatEnm {
  RED("CAT I / SK 1"),
  YELLOW("CAT II / SK 2"),
  GREEN("CAT III / SK 3"),
  BLUE("CAT IV / SK 4"),
  BLACK("EX");

  private final String name;

  TriageCatEnm(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
