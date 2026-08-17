package climateaction;

public class RecommendationService {

    public static String generateRecommendation(int userId) {

        double transport =
                CarbonCalculator.calculateTransportEmission(userId);

        double energy =
                CarbonCalculator.calculateEnergyEmission(userId);

        double waste =
                CarbonCalculator.calculateWasteEmission(userId);

        double total =
                CarbonCalculator.calculateTotalEmission(userId);

        if (total == 0) {
            return "Start recording your daily activities to receive climate recommendations.";
        }

        if (transport > energy && transport > waste) {

            return "Your transportation emissions are your largest source. "
                    + "Consider using public transportation, walking, cycling, "
                    + "or carpooling when possible.";
        }

        if (energy > transport && energy > waste) {

            return "Your energy emissions are your largest source. "
                    + "Consider reducing unnecessary electricity usage, "
                    + "switching off unused appliances, and improving energy efficiency.";
        }

        if (waste > transport && waste > energy) {

            return "Your waste emissions are your largest source. "
                    + "Consider recycling, reducing food waste, "
                    + "and avoiding unnecessary single-use products.";
        }

        return "Your emissions are spread across several categories. "
                + "Try reducing transportation, energy consumption, "
                + "and waste together.";
    }

    public static String getActionSuggestion() {

        return "Suggested green actions:\n\n"
                + "• Walk or cycle for short distances\n"
                + "• Use public transportation\n"
                + "• Reduce unnecessary electricity usage\n"
                + "• Recycle household waste\n"
                + "• Reduce food waste\n"
                + "• Use reusable products";
    }
}