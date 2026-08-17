package climateaction;

public class TransportActivity extends Activity {

    private String vehicleType;
    private double distance;

    public TransportActivity(
            int activityId,
            int userId,
            String date,
            String description,
            double distance,
            String vehicleType) {

        super(activityId, userId, date, description, distance);

        this.distance = distance;
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
        setAmount(distance);
    }

    @Override
    public double calculateEmission() {

        double emissionFactor;

        switch (vehicleType.toLowerCase()) {

            case "car":
                emissionFactor = 0.192;
                break;

            case "motorcycle":
                emissionFactor = 0.103;
                break;

            case "bus":
                emissionFactor = 0.089;
                break;

            case "train":
                emissionFactor = 0.041;
                break;

            case "bicycle":
                emissionFactor = 0.0;
                break;

            case "walking":
                emissionFactor = 0.0;
                break;

            default:
                emissionFactor = 0.192;
        }

        return distance * emissionFactor;
    }

    @Override
    public String getActivityType() {
        return "Transportation";
    }

    public String getVehicleDetails() {
        return vehicleType + " - " + distance + " km";
    }
}