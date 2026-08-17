package climateaction;

public class GreenAction {

    private int actionId;
    private int userId;
    private String date;
    private String actionName;
    private double carbonReduction;

    public GreenAction(
            int actionId,
            int userId,
            String date,
            String actionName,
            double carbonReduction) {

        this.actionId = actionId;
        this.userId = userId;
        this.date = date;
        this.actionName = actionName;
        this.carbonReduction = carbonReduction;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public double getCarbonReduction() {
        return carbonReduction;
    }

    public void setCarbonReduction(double carbonReduction) {
        this.carbonReduction = carbonReduction;
    }

    @Override
    public String toString() {
        return actionId + " | "
                + userId + " | "
                + date + " | "
                + actionName + " | "
                + carbonReduction;
    }
}