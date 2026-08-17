package climateaction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;

public class Main {

    private static int currentUserId = 1;

    public static void main(String[] args) {

        FileManager.initializeFiles();
        FileManager.loadAll();

        createDefaultUser();

        SwingUtilities.invokeLater(
                () -> createAndShowGUI());
    }

    private static void createDefaultUser() {

        if (DataStore.getUsers().isEmpty()) {

            User user = new User(
                    1,
                    "Climate User",
                    "user@example.com");

            DataStore.addUser(user);

            FileManager.saveUsers();
        }
    }

    private static void createAndShowGUI() {

        JFrame frame = new JFrame(
                "Climate Action Manager");

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        frame.setSize(900, 600);

        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(
                new BorderLayout());

        mainPanel.setBorder(
                new EmptyBorder(
                        20, 20, 20, 20));

        JLabel title = new JLabel(
                "🌍 CLIMATE ACTION MANAGER",
                SwingConstants.CENTER);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26));

        mainPanel.add(
                title,
                BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(
                new GridLayout(
                        3,
                        2,
                        15,
                        15));

        JButton activityButton =
                new JButton(
                        "Record Activity");

        JButton greenActionButton =
                new JButton(
                        "Record Green Action");

        JButton reportButton =
                new JButton(
                        "Carbon Report");

        JButton recommendationButton =
                new JButton(
                        "Recommendations");

        JButton viewButton =
                new JButton(
                        "View Activities");

        JButton saveButton =
                new JButton(
                        "Save Data");

        buttonPanel.add(activityButton);
        buttonPanel.add(greenActionButton);
        buttonPanel.add(reportButton);
        buttonPanel.add(recommendationButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(saveButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER);

        JLabel status = new JLabel(
                "SDG 13: Climate Action");

        status.setHorizontalAlignment(
                SwingConstants.CENTER);

        mainPanel.add(
                status,
                BorderLayout.SOUTH);

        activityButton.addActionListener(
                e -> showActivityDialog(frame));

        greenActionButton.addActionListener(
                e -> showGreenActionDialog(frame));

        reportButton.addActionListener(
                e -> showReportDialog(frame));

        recommendationButton.addActionListener(
                e -> showRecommendationDialog(frame));

        viewButton.addActionListener(
                e -> showActivitiesDialog(frame));

        saveButton.addActionListener(
                e -> {

                    FileManager.saveAll();

                    JOptionPane.showMessageDialog(
                            frame,
                            "Data saved successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                });

        frame.add(mainPanel);

        frame.setVisible(true);
    }

    private static void showActivityDialog(
            JFrame parent) {

        String[] types = {
                "Transportation",
                "Energy",
                "Waste"
        };

        String type = (String) JOptionPane.showInputDialog(
                parent,
                "Select activity type:",
                "Record Activity",
                JOptionPane.QUESTION_MESSAGE,
                null,
                types,
                types[0]);

        if (type == null) {
            return;
        }

        String description =
                JOptionPane.showInputDialog(
                        parent,
                        "Enter activity description:");

        if (description == null
                || description.trim().isEmpty()) {

            return;
        }

        String amountText =
                JOptionPane.showInputDialog(
                        parent,
                        getAmountMessage(type));

        if (amountText == null) {
            return;
        }

        try {

            double amount =
                    Double.parseDouble(amountText);

            if (amount <= 0) {

                JOptionPane.showMessageDialog(
                        parent,
                        "Amount must be greater than zero.");

                return;
            }

            String specificType;

            if (type.equals("Transportation")) {

                String[] vehicles = {
                        "Car",
                        "Motorcycle",
                        "Bus",
                        "Train",
                        "Bicycle",
                        "Walking"
                };

                specificType =
                        (String) JOptionPane.showInputDialog(
                                parent,
                                "Select vehicle:",
                                "Transportation",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                vehicles,
                                vehicles[0]);

                if (specificType == null) {
                    return;
                }

            } else if (type.equals("Energy")) {

                String[] energyTypes = {
                        "Electricity",
                        "Air Conditioning",
                        "Appliances"
                };

                specificType =
                        (String) JOptionPane.showInputDialog(
                                parent,
                                "Select energy type:",
                                "Energy",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                energyTypes,
                                energyTypes[0]);

                if (specificType == null) {
                    return;
                }

            } else {

                String[] wasteTypes = {
                        "Food",
                        "Plastic",
                        "Paper",
                        "General",
                        "Recycled"
                };

                specificType =
                        (String) JOptionPane.showInputDialog(
                                parent,
                                "Select waste type:",
                                "Waste",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                wasteTypes,
                                wasteTypes[0]);

                if (specificType == null) {
                    return;
                }
            }

            int id =
                    DataStore.getActivities().size() + 1;

            Activity activity;

            if (type.equals("Transportation")) {

                activity =
                        new TransportActivity(
                                id,
                                currentUserId,
                                LocalDate.now().toString(),
                                description,
                                amount,
                                specificType);

            } else if (type.equals("Energy")) {

                activity =
                        new EnergyActivity(
                                id,
                                currentUserId,
                                LocalDate.now().toString(),
                                description,
                                amount,
                                specificType);

            } else {

                activity =
                        new WasteActivity(
                                id,
                                currentUserId,
                                LocalDate.now().toString(),
                                description,
                                amount,
                                specificType);
            }

            DataStore.addActivity(activity);

            double emission =
                    activity.calculateEmission();

            FileManager.saveActivities();

            JOptionPane.showMessageDialog(
                    parent,
                    String.format(
                            "Activity recorded successfully!\n\n"
                                    + "Type: %s\n"
                                    + "Description: %s\n"
                                    + "Estimated CO2: %.2f kg",
                            type,
                            description,
                            emission),
                    "Activity Added",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    parent,
                    "Please enter a valid number.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String getAmountMessage(
            String type) {

        if (type.equals("Transportation")) {
            return "Enter distance travelled (km):";
        }

        if (type.equals("Energy")) {
            return "Enter energy consumption (kWh):";
        }

        return "Enter waste amount (kg):";
    }

    private static void showGreenActionDialog(
            JFrame parent) {

        String[] actions = {
                "Walked instead of driving",
                "Used public transportation",
                "Recycled waste",
                "Reduced electricity usage",
                "Used reusable products",
                "Reduced food waste",
                "Planted a tree"
        };

        String action =
                (String) JOptionPane.showInputDialog(
                        parent,
                        "Select green action:",
                        "Green Action",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        actions,
                        actions[0]);

        if (action == null) {
            return;
        }

        String reductionText =
                JOptionPane.showInputDialog(
                        parent,
                        "Enter estimated CO2 reduction (kg):");

        if (reductionText == null) {
            return;
        }

        try {

            double reduction =
                    Double.parseDouble(reductionText);

            if (reduction <= 0) {

                JOptionPane.showMessageDialog(
                        parent,
                        "Reduction must be greater than zero.");

                return;
            }

            int id =
                    DataStore.getGreenActions().size() + 1;

            GreenAction greenAction =
                    new GreenAction(
                            id,
                            currentUserId,
                            LocalDate.now().toString(),
                            action,
                            reduction);

            DataStore.addGreenAction(greenAction);

            FileManager.saveGreenActions();

            JOptionPane.showMessageDialog(
                    parent,
                    String.format(
                            "Green action recorded!\n\n"
                                    + "Action: %s\n"
                                    + "Estimated CO2 reduction: %.2f kg",
                            action,
                            reduction),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    parent,
                    "Please enter a valid number.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void showReportDialog(
            JFrame parent) {

        String report =
                ReportService.generateReport(
                        currentUserId);

        JTextArea textArea =
                new JTextArea(report);

        textArea.setEditable(false);

        textArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14));

        JScrollPane scrollPane =
                new JScrollPane(textArea);

        scrollPane.setPreferredSize(
                new Dimension(
                        550,
                        400));

        JOptionPane.showMessageDialog(
                parent,
                scrollPane,
                "Carbon Footprint Report",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showRecommendationDialog(
            JFrame parent) {

        String recommendation =
                RecommendationService
                        .generateRecommendation(
                                currentUserId);

        String suggestions =
                RecommendationService
                        .getActionSuggestion();

        JTextArea textArea =
                new JTextArea(
                        recommendation
                                + "\n\n"
                                + suggestions);

        textArea.setEditable(false);

        textArea.setLineWrap(true);

        textArea.setWrapStyleWord(true);

        textArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14));

        JScrollPane scrollPane =
                new JScrollPane(textArea);

        scrollPane.setPreferredSize(
                new Dimension(
                        550,
                        350));

        JOptionPane.showMessageDialog(
                parent,
                scrollPane,
                "Climate Recommendations",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showActivitiesDialog(
            JFrame parent) {

        StringBuilder output =
                new StringBuilder();

        output.append(
                "ACTIVITY RECORDS\n");

        output.append(
                "====================================\n\n");

        if (DataStore.getActivities().isEmpty()) {

            output.append(
                    "No activities recorded yet.");

        } else {

            for (Activity activity :
                    DataStore.getActivities()) {

                output.append(
                        activity.toString());

                output.append(
                        String.format(
                                " | CO2: %.2f kg",
                                activity.calculateEmission()));

                output.append("\n");
            }
        }

        JTextArea textArea =
                new JTextArea(
                        output.toString());

        textArea.setEditable(false);

        textArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        12));

        JScrollPane scrollPane =
                new JScrollPane(textArea);

        scrollPane.setPreferredSize(
                new Dimension(
                        700,
                        400));

        JOptionPane.showMessageDialog(
                parent,
                scrollPane,
                "Activities",
                JOptionPane.INFORMATION_MESSAGE);
    }
}