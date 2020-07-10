package space.ablackack.PatientOverview;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import space.ablackack.PatientOverview.pojo.Patient;
import space.ablackack.PatientOverview.utils.database.PatientDatabaseUtils;

import java.util.List;

@Controller
public class FilteredOverviewController {

    @GetMapping("/filteredOverview")
    public String filteredOverview(@RequestParam(value = "category") String category, Model model) {
        List<Patient> patients = PatientDatabaseUtils.getAllPatientsWithCategory(category);
        model.addAttribute("patients", patients);

        return "filteredOverview";
    }
}
