package il.co.topq.datatables;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * The most simple table
 */
public class StandardDataTable extends DataTable<TableRow> {

    public StandardDataTable(WebDriver driver, WebElement tableElement) {
        super(driver, tableElement);
    }
}
