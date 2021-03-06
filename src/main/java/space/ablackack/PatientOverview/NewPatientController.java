package space.ablackack.PatientOverview;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import space.ablackack.PatientOverview.enums.StatusEnm;
import space.ablackack.PatientOverview.pojo.Patient;
import space.ablackack.PatientOverview.utils.OverviewUtils;
import space.ablackack.PatientOverview.utils.database.PatientDatabaseUtils;

@Controller
public class NewPatientController {
    @GetMapping("/newPatient")
    public String newPatientForm(Model model) {
        model.addAttribute("patient", PatientDatabaseUtils.getLatestPatient());

        return "newPatient";
    }

    @PostMapping("/newPatient")
    public String newPatientSubmit(@ModelAttribute Patient patient) {
        patient.setPatientNumber(OverviewUtils.getNextPatientNumber());
        patient.setStatus(StatusEnm.UNDER_TREATMENT);
        PatientDatabaseUtils.savePatient(patient);

        return "newPatient";
    }
}
