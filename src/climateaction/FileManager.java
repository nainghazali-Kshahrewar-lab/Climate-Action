package climateaction;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String DATA_FOLDER = "data";

    private static final String ACTIVITIES_FILE =
            DATA_FOLDER + "/activities.txt";

    private static final String GREEN_ACTIONS_FILE =
            DATA_FOLDER + "/green_actions.txt";

    private static final String USERS_FILE =
            DATA_FOLDER + "/users.txt";

    public static void initializeFiles() {

        File folder = new File(DATA_FOLDER);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        createFileIfNotExists(ACTIVITIES_FILE);
        createFileIfNotExists(GREEN_ACTIONS_FILE);
        createFileIfNotExists(USERS_FILE);
    }

    private static void createFileIfNotExists(String fileName) {

        File file = new File(fileName);

        try {

            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException e) {

            System.out.println(
                    "Unable to create file: " + fileName);
        }
    }

    public static void saveUsers() {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(USERS_FILE))) {

            for (User user : DataStore.getUsers()) {

                writer.write(
                        user.getUserId() + "|" +
                        user.getName() + "|" +
                        user.getEmail());

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error saving users: " + e.getMessage());
        }
    }

    public static void saveActivities() {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(ACTIVITIES_FILE))) {

            for (Activity activity :
                    DataStore.getActivities()) {

                String type;

                if (activity instanceof TransportActivity) {

                    TransportActivity transport =
                            (TransportActivity) activity;

                    type = "TRANSPORT|" +
                            transport.getVehicleType();

                } else if (activity instanceof EnergyActivity) {

                    EnergyActivity energy =
                            (EnergyActivity) activity;

                    type = "ENERGY|" +
                            energy.getEnergyType();

                } else if (activity instanceof WasteActivity) {

                    WasteActivity waste =
                            (WasteActivity) activity;

                    type = "WASTE|" +
                            waste.getWasteType();

                } else {
                    continue;
                }

                writer.write(
                        activity.getActivityId() + "|" +
                        activity.getUserId() + "|" +
                        activity.getDate() + "|" +
                        activity.getDescription() + "|" +
                        activity.getAmount() + "|" +
                        type);

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error saving activities: "
                            + e.getMessage());
        }
    }

    public static void saveGreenActions() {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(
                                     GREEN_ACTIONS_FILE))) {

            for (GreenAction action :
                    DataStore.getGreenActions()) {

                writer.write(
                        action.getActionId() + "|" +
                        action.getUserId() + "|" +
                        action.getDate() + "|" +
                        action.getActionName() + "|" +
                        action.getCarbonReduction());

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error saving green actions: "
                            + e.getMessage());
        }
    }

    public static void saveAll() {

        initializeFiles();

        saveUsers();
        saveActivities();
        saveGreenActions();

        System.out.println("All data saved successfully.");
    }

    public static void loadUsers() {

        initializeFiles();

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(USERS_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|");

                if (parts.length >= 3) {

                    int id = Integer.parseInt(parts[0]);

                    String name = parts[1];
                    String email = parts[2];

                    DataStore.addUser(
                            new User(id, name, email));
                }
            }

        } catch (IOException | NumberFormatException e) {

            System.out.println(
                    "Error loading users: "
                            + e.getMessage());
        }
    }

    public static void loadActivities() {

        initializeFiles();

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(ACTIVITIES_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|");

                if (parts.length < 7) {
                    continue;
                }

                int activityId =
                        Integer.parseInt(parts[0]);

                int userId =
                        Integer.parseInt(parts[1]);

                String date = parts[2];

                String description = parts[3];

                double amount =
                        Double.parseDouble(parts[4]);

                String category = parts[5];

                String specificType = parts[6];

                Activity activity;

                if (category.equals("TRANSPORT")) {

                    activity =
                            new TransportActivity(
                                    activityId,
                                    userId,
                                    date,
                                    description,
                                    amount,
                                    specificType);

                } else if (category.equals("ENERGY")) {

                    activity =
                            new EnergyActivity(
                                    activityId,
                                    userId,
                                    date,
                                    description,
                                    amount,
                                    specificType);

                } else if (category.equals("WASTE")) {

                    activity =
                            new WasteActivity(
                                    activityId,
                                    userId,
                                    date,
                                    description,
                                    amount,
                                    specificType);

                } else {
                    continue;
                }

                DataStore.addActivity(activity);
            }

        } catch (IOException | NumberFormatException e) {

            System.out.println(
                    "Error loading activities: "
                            + e.getMessage());
        }
    }

    public static void loadGreenActions() {

        initializeFiles();

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(
                                     GREEN_ACTIONS_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|");

                if (parts.length >= 5) {

                    int actionId =
                            Integer.parseInt(parts[0]);

                    int userId =
                            Integer.parseInt(parts[1]);

                    String date = parts[2];

                    String actionName = parts[3];

                    double reduction =
                            Double.parseDouble(parts[4]);

                    DataStore.addGreenAction(
                            new GreenAction(
                                    actionId,
                                    userId,
                                    date,
                                    actionName,
                                    reduction));
                }
            }

        } catch (IOException | NumberFormatException e) {

            System.out.println(
                    "Error loading green actions: "
                            + e.getMessage());
        }
    }

    public static void loadAll() {

        initializeFiles();

        DataStore.clearAll();

        loadUsers();
        loadActivities();
        loadGreenActions();

        System.out.println("All data loaded successfully.");
    }
}