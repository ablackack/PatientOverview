package space.ablackack.PatientOverview;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import space.ablackack.PatientOverview.pojo.Patient;
import space.ablackack.PatientOverview.utils.database.PatientDatabaseUtils;

@Controller
public class ShowPatientController {
    @RequestMapping("/showPatient")
    public String showPatient(@RequestParam(value = "patientNumber") String patientNumber, Model model) {
        Patient patient = PatientDatabaseUtils.getPatient(patientNumber);
        model.addAttribute("patient", patient);

        return "showPatient";
    }
}
