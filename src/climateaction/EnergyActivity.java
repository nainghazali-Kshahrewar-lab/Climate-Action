package climateaction;

public class EnergyActivity extends Activity {

    private String energyType;
    private double kilowattHours;

    public EnergyActivity(
            int activityId,
            int userId,
            String date,
            String description,
            double kilowattHours,
            String energyType) {

        super(activityId, userId, date, description, kilowattHours);

        this.kilowattHours = kilowattHours;
        this.energyType = energyType;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public double getKilowattHours() {
        return kilowattHours;
    }

    public void setKilowattHours(double kilowattHours) {
        this.kilowattHours = kilowattHours;
        setAmount(kilowattHours);
    }

    @Override
    public double calculateEmission() {

        // Educational estimate of CO2 emissions per kWh.
        double emissionFactor = 0.60;

        return kilowattHours * emissionFactor;
    }

    @Override
    public String getActivityType() {
        return "Energy";
    }

    public String getEnergyDetails() {
        return energyType + " - " + kilowattHours + " kWh";
    }
}