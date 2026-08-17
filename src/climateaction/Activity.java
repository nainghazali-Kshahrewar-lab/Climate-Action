package climateaction;

public abstract class Activity {

    private int activityId;
    private int userId;
    private String date;
    private String description;
    private double amount;

    public Activity(int activityId, int userId, String date,
                    String description, double amount) {

        this.activityId = activityId;
        this.userId = userId;
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Abstract method
    public abstract double calculateEmission();

    public abstract String getActivityType();

    @Override
    public String toString() {
        return activityId + " | "
                + userId + " | "
                + date + " | "
                + getActivityType() + " | "
                + description + " | "
                + amount;
    }
}