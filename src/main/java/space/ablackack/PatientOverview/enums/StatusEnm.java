package space.ablackack.PatientOverview.enums;

public enum StatusEnm {
  DISCHARGED("discharged"),
  HOSPITAL("hospital"),
  AGAINST_MEDICAL_ADVICE("left against medical advice"),
  UNDER_TREATMENT("under treatment");

  private final String name;

  StatusEnm(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
