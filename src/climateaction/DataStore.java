package climateaction;

import java.util.ArrayList;

public class DataStore {

    private static final ArrayList<User> users = new ArrayList<>();
    private static final ArrayList<Activity> activities = new ArrayList<>();
    private static final ArrayList<GreenAction> greenActions = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static ArrayList<Activity> getActivities() {
        return activities;
    }

    public static ArrayList<GreenAction> getGreenActions() {
        return greenActions;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void addGreenAction(GreenAction action) {
        greenActions.add(action);
    }

    public static void clearAll() {
        users.clear();
        activities.clear();
        greenActions.clear();
    }
}