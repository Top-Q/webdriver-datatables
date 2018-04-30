package il.co.topq.datatables.examples;

import il.co.topq.datatables.DataTable;
import il.co.topq.datatables.StandardDataTable;
import il.co.topq.datatables.TableCell;
import il.co.topq.datatables.TableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;


import java.util.concurrent.TimeUnit;

public class TestDataTables {

    private WebDriver driver;

    private ZeroConfigTable table;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://datatables.net/examples/basic_init/zero_configuration.html");
        driver.manage().window().maximize();
        table = new ZeroConfigTable(driver);
    }

    /**
     * Test reading value of cell from specific row
     */
    @Test
    public void testReadTable() {
        TableRow row = table.findRowByValue(ZeroConfigHeader.NAME, "Cedric Kelly", false);
        TableCell tableCell = row.findCellByHeader(ZeroConfigHeader.POSITION);
        assertEquals(tableCell.getText(), "Senior Javascript Developer");
    }

    /**
     * Test setting the number of shown entries in the table
     */
    @Test
    public void testGetNumberOfRowsAndChangeShownEntries() {
        assertEquals(table.getNumberOfDisplayedRows(), 10);

        table.setNumberOfShownEntried(DataTable.ShownEntries._25);
        assertEquals(table.getNumberOfDisplayedRows(), 25);

        table.setNumberOfShownEntried(DataTable.ShownEntries._50);
        assertEquals(table.getNumberOfDisplayedRows(), 50);

        table.setNumberOfShownEntried(DataTable.ShownEntries._100);
        assertEquals(table.getNumberOfDisplayedRows(), 57);

        table.setNumberOfShownEntried(DataTable.ShownEntries._10);
        assertEquals(table.getNumberOfDisplayedRows(), 10);

    }

    @Test
    public void testGetCurrentPageNumber() {
        table.nextPage();
        assertEquals(table.getCurrentPageNumber(), 2);

        table.nextPage();
        assertEquals(table.getCurrentPageNumber(), 3);

        table.previousPage();
        assertEquals(table.getCurrentPageNumber(), 2);

    }

    @Test
    public void testPageForward() {
        assertTrue(table.isNextPageButtonClickable());
        table.nextPage();
        assertTrue(table.isPreviousButtonClickable());
        table.previousPage();
    }

    @Test
    public void testSearch() {
        table.typeToSearchBox("33");
        assertEquals(table.getNumberOfDisplayedRows(), 2);
    }

    @Test
    public void testSort() {
        table.sortByHeader(ZeroConfigHeader.POSITION);
        assertEquals(table.findRowByIndex(1).findCellByHeader(ZeroConfigHeader.NAME).getText(), "Airi Satou");

        table.sortByHeader(ZeroConfigHeader.OFFICE);
        assertEquals(table.findRowByIndex(1).findCellByHeader(ZeroConfigHeader.NAME).getText(), "Gavin Joyce");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
