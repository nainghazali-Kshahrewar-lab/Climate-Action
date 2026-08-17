package climateaction;

public class WasteActivity extends Activity {

    private String wasteType;
    private double kilograms;

    public WasteActivity(
            int activityId,
            int userId,
            String date,
            String description,
            double kilograms,
            String wasteType) {

        super(activityId, userId, date, description, kilograms);

        this.kilograms = kilograms;
        this.wasteType = wasteType;
    }

    public String getWasteType() {
        return wasteType;
    }

    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    public double getKilograms() {
        return kilograms;
    }

    public void setKilograms(double kilograms) {
        this.kilograms = kilograms;
        setAmount(kilograms);
    }

    @Override
    public double calculateEmission() {

        double emissionFactor;

        switch (wasteType.toLowerCase()) {

            case "food":
                emissionFactor = 2.5;
                break;

            case "plastic":
                emissionFactor = 2.0;
                break;

            case "paper":
                emissionFactor = 1.3;
                break;

            case "general":
                emissionFactor = 1.8;
                break;

            case "recycled":
                emissionFactor = 0.5;
                break;

            default:
                emissionFactor = 1.8;
        }

        return kilograms * emissionFactor;
    }

    @Override
    public String getActivityType() {
        return "Waste";
    }

    public String getWasteDetails() {
        return wasteType + " - " + kilograms + " kg";
    }
}