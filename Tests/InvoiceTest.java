import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InvoiceTest
{
    Invoice i1, i2, i3, i4, i5;
    LineItem l1, l2, l3, l4, l5;

    @BeforeEach
    void SetUp()
    {
        i1 = new Invoice("1", "Will's Random Emporium 123 Main St.", new LineItem[0], 0.0);

        Product p1 = new Product( "Widget", 10.0);
        Product p2 = new Product( "Gadget", 20.0);
        Product p3 = new Product("Thingamajig", 30.0);
        Product p4 = new Product("Doohickey", 40.0);
        Product p5 = new Product( "Whatchamacallit", 50.0);

        l1 = new LineItem(p1, 1);
        l2 = new LineItem(p2, 2);
        l3 = new LineItem(p3, 3);
        l4 = new LineItem(p4, 4);
        l5 = new LineItem(p5, 5);
    }


    @Test
    void testAddLineItem()
    {
        i1.addLineItem(l1);
        i1.addLineItem(l2);
        i1.addLineItem(l3);
        i1.addLineItem(l4);
        i1.addLineItem(l5);

        LineItem[] items = i1.getLineItems();
        assertEquals(5, items.length);
        assertEquals(l1, items[0]);
        assertEquals(l2, items[1]);
        assertEquals(l3, items[2]);
        assertEquals(l4, items[3]);
        assertEquals(l5, items[4]);
    }

    @Test
    void testToString()
    {
        i1.addLineItem(l1);
        i1.addLineItem(l2);
        i1.addLineItem(l3);
        i1.addLineItem(l4);
        i1.addLineItem(l5);

        String expected = "1\nWill's Random Emporium 123 Main St.\nWidget, 1, 10.0\nGadget, 2, 40.0\nThingamajig, 3, 90.0\nDoohickey, 4, 160.0\nWhatchamacallit, 5, 250.0\nTotal: 550.0";
        assertEquals(expected, i1.toString());
    }

    @Test
    void testGetLineItems()
    {
        i1.addLineItem(l1);
        i1.addLineItem(l2);
        i1.addLineItem(l3);
        i1.addLineItem(l4);
        i1.addLineItem(l5);

        LineItem[] items = i1.getLineItems();
        assertEquals(5, items.length);
        assertEquals(l1, items[0]);
        assertEquals(l2, items[1]);
        assertEquals(l3, items[2]);
        assertEquals(l4, items[3]);
        assertEquals(l5, items[4]);
    }

    @Test
    void testSetLineItems()
    {
        LineItem[] items = {l1, l2, l3, l4, l5};
        i1.setLineItems(items);

        LineItem[] items2 = i1.getLineItems();
        assertEquals(5, items2.length);
        assertEquals(l1, items2[0]);
        assertEquals(l2, items2[1]);
        assertEquals(l3, items2[2]);
        assertEquals(l4, items2[3]);
        assertEquals(l5, items2[4]);
    }

    @Test
    void testGetInvoiceTotal()
    {
        i1.addLineItem(l1);
        i1.addLineItem(l2);
        i1.addLineItem(l3);
        i1.addLineItem(l4);
        i1.addLineItem(l5);

        assertEquals(550.0, i1.getInvoiceTotal());
    }

    @Test
    void testSetInvoiceTotal()
    {
        i1.setInvoiceTotal(100.0);
        assertEquals(100.0, i1.getInvoiceTotal());
    }

    @Test
    void testGetTitle()
    {
        assertEquals("1", i1.getTitle());
    }

    @Test
    void testSetTitle()
    {
        i1.setTitle("2");
        assertEquals("2", i1.getTitle());
    }

    @Test
    void testGetCustomerAddress()
    {
        assertEquals("Will's Random Emporium 123 Main St.", i1.getCustomerAddress());
    }

    @Test
    void testSetCustomerAddress()
    {
        i1.setCustomerAddress("Will's Random Emporium 456 Main St.");
        assertEquals("Will's Random Emporium 456 Main St.", i1.getCustomerAddress());
    }

}
