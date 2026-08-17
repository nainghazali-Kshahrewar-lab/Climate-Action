package climateaction;

public class ReportService {

    public static String generateReport(int userId) {

        double transport =
                CarbonCalculator.calculateTransportEmission(userId);

        double energy =
                CarbonCalculator.calculateEnergyEmission(userId);

        double waste =
                CarbonCalculator.calculateWasteEmission(userId);

        double total =
                CarbonCalculator.calculateTotalEmission(userId);

        double reduction =
                CarbonCalculator.calculateTotalReduction(userId);

        double net =
                CarbonCalculator.calculateNetEmission(userId);

        String status =
                CarbonCalculator.getEmissionStatus(total);

        StringBuilder report = new StringBuilder();

        report.append("========================================\n");
        report.append("       CLIMATE ACTION REPORT\n");
        report.append("========================================\n\n");

        report.append(String.format(
                "Transportation Emissions : %.2f kg CO2%n",
                transport));

        report.append(String.format(
                "Energy Emissions         : %.2f kg CO2%n",
                energy));

        report.append(String.format(
                "Waste Emissions          : %.2f kg CO2%n",
                waste));

        report.append("----------------------------------------\n");

        report.append(String.format(
                "Total Emissions          : %.2f kg CO2%n",
                total));

        report.append(String.format(
                "CO2 Reduction            : %.2f kg CO2%n",
                reduction));

        report.append(String.format(
                "Net Carbon Impact        : %.2f kg CO2%n",
                net));

        report.append(String.format(
                "Environmental Status     : %s%n",
                status));

        report.append("\n========================================\n");

        return report.toString();
    }
}