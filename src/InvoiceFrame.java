import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class InvoiceFrame extends JFrame
{
    JPanel mainPanel;
    JPanel buttonPanel;
    JPanel inputPanel;
    JPanel invoicePanel;
    JPanel headerPanel;

    JButton addButton;
    JButton addressButton;
    JButton printButton;
    JButton clearButton;
    JButton exitButton;

    JTextField nameField;
    JTextField priceField;
    JTextField quantityField;

    JLabel totalLabel;
    JLabel addressLabel;
    JLabel titleLabel;

    JTextArea addressArea;

    JTable invoiceTable;

    JScrollPane scrollPane;

    Invoice invoice;
    ArrayList <LineItem> lineItems;

    double productTotal;
    double invoiceTotal;

    SafeInput safeInput = new SafeInput();

    public InvoiceFrame()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        createButtonPanel();
        createInputPanel();

        invoice = new Invoice("", "", new LineItem[0], 0);
        lineItems = new ArrayList<>();

        setTitle("Invoice");
        setSize(900, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        setVisible(true);
    }

    public void createInputPanel()
    {
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        nameField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();
        addressArea = new JTextArea();

        nameField.setPreferredSize(new Dimension(100, 20));
        priceField.setPreferredSize(new Dimension(100, 20));
        quantityField.setPreferredSize(new Dimension(100, 20));
        addressArea.setPreferredSize(new Dimension(100, 20));

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);

        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
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
            try{
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            Product product = new Product(name, price);
            LineItem item = new LineItem(product, quantity);
            invoice.addLineItem(item);

            nameField.setText("");
            priceField.setText("");
            quantityField.setText("");
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        });

        addressButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter invoice title:");
            if (title == null)
            {
                return;
            }
            String address = JOptionPane.showInputDialog("Enter customer address:");
            if (address == null)
            {
                return;
            }
            invoice.setTitle(title);
            invoice.setCustomerAddress(address);
            addressArea.setText(address);
        });

        printButton.addActionListener((e -> {
            printInvoice();
            mainPanel.add(invoicePanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        }));

        clearButton.addActionListener(e -> {
            nameField.setText("");
            priceField.setText("");
            quantityField.setText("");
            addressArea.setText("");
            DefaultTableModel model = (DefaultTableModel) invoiceTable.getModel();
            model.setRowCount(0);
            mainPanel.remove(scrollPane);
            mainPanel.remove(totalLabel);
            mainPanel.remove(headerPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
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
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
    }

    public void calculateTotals()
    {
        invoiceTotal = 0.0;
        for (LineItem item : invoice.getLineItems())
        {
            productTotal = item.getProduct().getPrice() * item.getQuantity();
            invoiceTotal += productTotal;
        }
    }

    public void printInvoice()
    {
        calculateTotals();
        invoiceTable = new JTable();

        String[] columnNames = {"Item", "QTY", "Price", "Total"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        System.out.println(Arrays.toString(invoice.getLineItems()));

        for (LineItem item : invoice.getLineItems())
        {
            System.out.println(item);
            Object[] row = {item.getProduct().getName(),
                    item.getQuantity(),
                    String.format("%.2f", item.getProduct().getPrice()),
                    String.format("%.2f", item.getTotal())
            };
            model.addRow(row);
        }

        invoiceTable.setModel(model);
        invoiceTable.setPreferredScrollableViewportSize(new Dimension(300, 200));
        scrollPane = new JScrollPane(invoiceTable);

        totalLabel = new JLabel("Total: " + String.format("%.2f", invoiceTotal));

        headerPanel = new JPanel();
        invoicePanel = new JPanel();
        invoicePanel.setLayout(new BorderLayout());
        titleLabel = new JLabel(invoice.getTitle());
        addressLabel = new JLabel(invoice.getCustomerAddress());
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(addressLabel, BorderLayout.SOUTH);

        invoicePanel.add(headerPanel, BorderLayout.NORTH);
        invoicePanel.add(scrollPane, BorderLayout.CENTER);
        invoicePanel.add(totalLabel, BorderLayout.SOUTH);

    }
}
