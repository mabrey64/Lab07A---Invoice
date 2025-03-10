public class Invoice
{
    private LineItem[] lineItems;
    private double invoiceTotal;
    private String title;
    private String customerAddress;

    public Invoice(String title, String customerAddress, LineItem[] lineItems, double invoiceTotal)
    {
        this.title = title;
        this.customerAddress = customerAddress;
        this.lineItems = lineItems;
        this.invoiceTotal = invoiceTotal;
    }


    public void addLineItem(LineItem item)
    {
        LineItem[] temp = new LineItem[lineItems.length + 1];
        for (int i = 0; i < lineItems.length; i++)
        {
            temp[i] = lineItems[i];
        }
        temp[lineItems.length] = item;
        lineItems = temp;
        invoiceTotal += item.getTotal();
    }

    public String toString()
    {
        String output = title + "\n" + customerAddress + "\n";
        for (LineItem item : lineItems)
        {
            output += item + "\n";
        }
        output += "Total: " + invoiceTotal;
        return output;
    }

    public LineItem[] getLineItems()
    {
        return lineItems;
    }

    public void setLineItems(LineItem[] lineItems)
    {
        this.lineItems = lineItems;
    }

    public double getInvoiceTotal()
    {
        return invoiceTotal;
    }

    public void setInvoiceTotal(double invoiceTotal)
    {
        this.invoiceTotal = invoiceTotal;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getCustomerAddress()
    {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress)
    {
        this.customerAddress = customerAddress;
    }
}
