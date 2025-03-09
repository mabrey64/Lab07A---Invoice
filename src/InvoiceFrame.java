import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class InvoiceFrame extends JFrame
{
    JPanel mainPanel;
    JPanel buttonPanel;
    JPanel inputPanel;

    JButton addButton;
    JButton addressButton;
    JButton printButton;
    JButton clearButton;
    JButton exitButton;

    JTextField nameField;
    JTextField priceField;
    JTextField quantityField;

    JTextArea addressArea;
    JTextArea invoiceArea;

    JScrollPane scrollPane;

    Invoice invoice;
    ArrayList <LineItem> lineItems;

    public InvoiceFrame()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        createButtonPanel();
        createInputPanel();

        setTitle("Invoice");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        setVisible(true);
    }

    public void createInputPanel()
    {

    }

    public void createButtonPanel()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        addButton = new JButton("Add Product");
        addressButton = new JButton("Enter Address");
        printButton = new JButton("Print Invoice");
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            Product product = new Product(name, price);
            LineItem item = new LineItem(product, quantity);
            invoice.addLineItem(item);
            invoiceArea.setText(invoice.toString());
        });

        addressButton.addActionListener(e -> {
            String address = JOptionPane.showInputDialog("Enter customer address:");
            invoice = new Invoice("Invoice", address);
            addressArea.setText(address);
        });

        printButton.addActionListener((e -> {
            JOptionPane.showMessageDialog(null, invoice.toString());
        }));

        clearButton.addActionListener(e -> {
            nameField.setText("");
            priceField.setText("");
            quantityField.setText("");
            addressArea.setText("");
            invoiceArea.setText("");
        });

        exitButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Would you like to exit the program?", "Exit", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(addressButton);
        buttonPanel.add(printButton);
        buttonPanel.add(exitButton);

    }

    public void printInvoice()
    {
        calculateTotal();
        
    }
}
