package space.ablackack.PatientOverview;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientOverviewController {

  @GetMapping("/patientOverview")
  public String patientOverview(Model model) {
    model.addAttribute("patients", patientCollector.getPatientList());

    return "patientOverview";
  }
}
