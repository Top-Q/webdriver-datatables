package il.co.topq.datatables.examples;

import il.co.topq.datatables.DataTable;
import il.co.topq.datatables.TableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Represents the table from data tables example <a href='https://datatables.net/examples/basic_init/zero_configuration.html'>Zero configuration</a>
 */
public class ZeroConfigTable extends DataTable<TableRow> {


    public ZeroConfigTable(WebDriver driver) {
        super(driver, driver.findElement(By.id("example")));
    }

    /**
     * Only for example. There is actually no need to override this since the default values are fine in this case.
     *
     * @return
     */
    @Override
    protected By getPreviousPageSelector() {
        return By.id("example_previous");
    }

    /**
     * Only for example. There is actually no need to override this since the default values are fine in this case.
     *
     * @return
     */
    @Override
    protected By getNextPageSelector() {
        return By.id("example_next");
    }

    /**
     * Only for example. There is actually no need to override this since the default values are fine in this case.
     *
     * @return
     */
    @Override
    protected By getSearchBoxSelector() {
        return By.cssSelector("#example_filter input[type='search']");
    }

    /**
     * Only for example. There is actually no need to override this since the default values are fine in this case.
     *
     * @return
     */
    @Override
    protected By getAllRowsSelector() {
        return By.xpath(".//tbody/tr[@role='row']");
    }

    /**
     * Only for example. There is actually no need to override this since the default values are fine in this case.
     *
     * @return
     */
    @Override
    protected By getShownEntriesSelector() {
        return By.name("example_length");
    }

    /**
     * Only for example. There is actually no need to override this since the default values are fine in this case.
     *
     * @return
     */
    @Override
    protected By getCurrentPageNumberSelector() {
        return By.cssSelector("a.paginate_button.current");
    }

    /**
     * Only for example. There is actually no need to override this since the default values are fine in this case.
     *
     * @return
     */
    @Override
    protected TableRow createRowInstance(WebElement rowElement) {
        return new TableRow(getWebDriver(), rowElement);
    }


}
