package space.ablackack.PatientOverview.utils;

import space.ablackack.PatientOverview.utils.database.PatientDatabaseUtils;

import java.time.LocalDateTime;

public class OverviewUtils {
    public static Integer getTodaysDay() {
        LocalDateTime localDateTime = LocalDateTime.now();

        return localDateTime.getDayOfMonth();
    }

    public static String getNextPatientNumber() {
        StringBuilder patientNumber = new StringBuilder();
        String day = OverviewUtils.getTodaysDay().toString();
        String number = String.valueOf(PatientDatabaseUtils.getNumberOfTodaysPatients() + 1);

        if (day.length() < 2) day = "0" + day;
        switch (number.length()) {
            case 1:
                number = "00" + number;
                break;
            case 2:
                number = "0" + number;
                break;
            default:
                break;
        }

        return patientNumber.append(day).append("-").append(number).toString();
    }
}
