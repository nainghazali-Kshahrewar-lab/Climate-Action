package climateaction;

public class CarbonCalculator {

    public static double calculateActivityEmission(Activity activity) {
        return activity.calculateEmission();
    }

    public static double calculateTotalEmission(int userId) {

        double total = 0.0;

        for (Activity activity : DataStore.getActivities()) {

            if (activity.getUserId() == userId) {
                total += activity.calculateEmission();
            }
        }

        return total;
    }

    public static double calculateTransportEmission(int userId) {

        double total = 0.0;

        for (Activity activity : DataStore.getActivities()) {

            if (activity.getUserId() == userId
                    && activity instanceof TransportActivity) {

                total += activity.calculateEmission();
            }
        }

        return total;
    }

    public static double calculateEnergyEmission(int userId) {

        double total = 0.0;

        for (Activity activity : DataStore.getActivities()) {

            if (activity.getUserId() == userId
                    && activity instanceof EnergyActivity) {

                total += activity.calculateEmission();
            }
        }

        return total;
    }

    public static double calculateWasteEmission(int userId) {

        double total = 0.0;

        for (Activity activity : DataStore.getActivities()) {

            if (activity.getUserId() == userId
                    && activity instanceof WasteActivity) {

                total += activity.calculateEmission();
            }
        }

        return total;
    }

    public static double calculateTotalReduction(int userId) {

        double total = 0.0;

        for (GreenAction action : DataStore.getGreenActions()) {

            if (action.getUserId() == userId) {
                total += action.getCarbonReduction();
            }
        }

        return total;
    }

    public static double calculateNetEmission(int userId) {

        double emissions = calculateTotalEmission(userId);
        double reduction = calculateTotalReduction(userId);

        return Math.max(0, emissions - reduction);
    }

    public static String getEmissionStatus(double emission) {

        if (emission >= 50) {
            return "HIGH";
        }

        if (emission >= 25) {
            return "MODERATE";
        }

        return "LOW";
    }
}