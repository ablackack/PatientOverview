package space.ablackack.PatientOverview.pojo;

import space.ablackack.PatientOverview.enums.StatusEnm;
import space.ablackack.PatientOverview.enums.TriageCatEnm;

import java.io.Serializable;

public class Patient implements Serializable {

    private String patientNumber;
    private String name;
    private StatusEnm status;
    private TriageCatEnm category;

    public Patient() {

    }

    public Patient(String patientNumber) {
        this(patientNumber, "Anonymous", StatusEnm.INVALID_VALUE, TriageCatEnm.INVALID_VALUE);
    }

    public Patient(String patientNumber, String name) {
        this(patientNumber, name, StatusEnm.INVALID_VALUE, TriageCatEnm.INVALID_VALUE);
    }

    public Patient(String patientNumber, String name, StatusEnm status, TriageCatEnm category) {
        this.patientNumber = patientNumber;
        this.setName(name);
        this.setStatus(status);
        this.setCategory(category);
    }

    public Patient(String patientNumber, String name, StatusEnm status) {
        this(patientNumber, name, status, TriageCatEnm.INVALID_VALUE);
    }

    public Patient(String patientNumber, String name, TriageCatEnm category) {
        this(patientNumber, name, StatusEnm.INVALID_VALUE, category);
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
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
