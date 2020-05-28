package space.ablackack.PatientOverview;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import space.ablackack.PatientOverview.pojo.Patient;
import space.ablackack.PatientOverview.utils.DatabaseUtils;

@Controller
public class PatientOverviewController {

  @GetMapping("/patientOverview")
  public String patientOverview(Model model) {
    model.addAttribute("patients", DatabaseUtils.getAllPatients());

    return "patientOverview";
  }
}
