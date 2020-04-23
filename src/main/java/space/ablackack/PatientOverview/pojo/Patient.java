package space.ablackack.PatientOverview.pojo;

import space.ablackack.PatientOverview.enums.StatusEnm;
import space.ablackack.PatientOverview.enums.TriageCatEnm;

public class Patient {

  private final String patientId;
  private String name;
  private StatusEnm status;
  private TriageCatEnm category;

  public Patient(String patientId) {
    this.patientId = patientId;
  }

  public Patient(String patientId, String name) {
    this.patientId = patientId;
    this.setName(name);
  }

  public String getPatientId() {
    return patientId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public StatusEnm getStatus() {
    return status;
  }

  public void setStatus(StatusEnm status) {
    this.status = status;
  }

  public TriageCatEnm getCategory() {
    return category;
  }

  public void setCategory(TriageCatEnm category) {
    this.category = category;
  }
}
