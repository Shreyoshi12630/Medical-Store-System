import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MedicalStoreSystem extends JFrame {
    private Map<String, Medicine> medicines;
    private JTextArea displayArea;
    private JTextField medicineNameField;
    private JTextField quantityField;
    private JTextArea customerListArea;

    public MedicalStoreSystem() {
        super("Medical Store System");

        medicines = new HashMap<>();

        // Create components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel sellPanel = new JPanel();
        sellPanel.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel medicineNameLabel = new JLabel("Medicine Name:");
        medicineNameField = new JTextField(20);
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(10);
        JButton sellButton = new JButton("Sell");
        sellButton.addActionListener(new SellButtonListener());

        JLabel customerListLabel = new JLabel("Customer List:");
        customerListArea = new JTextArea(10, 20);
        customerListArea.setEditable(false);
        JScrollPane customerScrollPane = new JScrollPane(customerListArea);

        sellPanel.add(medicineNameLabel);
        sellPanel.add(medicineNameField);
        sellPanel.add(quantityLabel);
        sellPanel.add(quantityField);
        sellPanel.add(new JLabel()); // Empty label for spacing
        sellPanel.add(sellButton);

        mainPanel.add(sellPanel, BorderLayout.WEST);
        mainPanel.add(customerScrollPane, BorderLayout.EAST);

        // Add components to content pane
        Container container = getContentPane();
        container.add(mainPanel);

        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Method to add a medicine to the store
    public void addMedicine(Medicine medicine) {
        medicines.put(medicine.name, medicine);
    }

    // Method to sell a medicine
    public void sellMedicine(String medicineName, int quantity) {
        if (medicines.containsKey(medicineName) && medicines.get(medicineName).stock >= quantity) {
            Medicine medicine = medicines.get(medicineName);
            medicine.stock -= quantity;
            displayArea.append("Sold " + quantity + " units of " + medicineName + "\n");
            customerListArea.append("Sold " + quantity + " units of " + medicineName + " for $" + (medicine.price * quantity) + "\n");
        } else {
            displayArea.append("Medicine not available or insufficient stock.\n");
        }
    }

    // Main method
    public static void main(String[] args) {
        // Creating an instance of MedicalStoreSystem only once
        SwingUtilities.invokeLater(MedicalStoreSystem::new);

        // Instantiating the MedicalStoreSystem
        MedicalStoreSystem storeSystem = new MedicalStoreSystem();

        // Adding some medicines to the store
        storeSystem.addMedicine(new Medicine("Paracetamol", 0.25, 100));
        storeSystem.addMedicine(new Medicine("Aspirin", 0.35, 50));
        storeSystem.addMedicine(new Medicine("Amoxicillin", 1.50, 30));
        storeSystem.addMedicine(new Medicine("Ibuprofen", 0.45, 60));
        storeSystem.addMedicine(new Medicine("Loratadine", 0.60, 40));
        storeSystem.addMedicine(new Medicine("Omeprazole", 1.20, 25));
        storeSystem.addMedicine(new Medicine("Atorvastatin", 2.10, 35));
        storeSystem.addMedicine(new Medicine("Cetirizine", 0.55, 55));
        storeSystem.addMedicine(new Medicine("Metformin", 1.80, 20));
        storeSystem.addMedicine(new Medicine("Ranitidine", 1.30, 45));
        storeSystem.addMedicine(new Medicine("Diazepam", 1.70, 30));
        storeSystem.addMedicine(new Medicine("Ciprofloxacin", 2.30, 25));
        storeSystem.addMedicine(new Medicine("Furosemide", 1.90, 40));
        storeSystem.addMedicine(new Medicine("Alprazolam", 2.50, 20));
    }

    // ActionListener for the "Sell" button
    private class SellButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String medicineName = medicineNameField.getText();
                int quantity = Integer.parseInt(quantityField.getText()); // Added try-catch block
                sellMedicine(medicineName, quantity);
            } catch (NumberFormatException ex) {
                displayArea.append("Please enter a valid number for quantity.\n");
            }
        }
    }
}

// Medicine class remains unchanged
class Medicine {
    String name;
    double price;
    int stock;

    public Medicine(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
