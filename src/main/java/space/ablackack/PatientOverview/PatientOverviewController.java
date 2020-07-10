package space.ablackack.PatientOverview;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import space.ablackack.PatientOverview.utils.database.PatientDatabaseUtils;

@Controller
public class PatientOverviewController {

    @GetMapping("/patientOverview")
    public String patientOverview(Model model) {
        model.addAttribute("patients", PatientDatabaseUtils.getAllPatients());

        return "patientOverview";
    }
}
