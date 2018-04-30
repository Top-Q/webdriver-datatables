package il.co.topq.datatables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Represents a single data table. Designed for inheritance in case there is a need to change the selectors
 * or add functionality
 *
 * @author Itai Agmon
 */
public class DataTable<T extends TableRow> {

    /**
     * Represents the number of lines that are shown in the table
     */
    public enum ShownEntries {
        _10(10), _25(25), _50(50), _100(100);

        private int value;

        ShownEntries(int value) {
            this.value = value;
        }

        int getValue() {
            return this.value;
        }
    }

    /**
     * Selenium web driver element
     */
    private final WebDriver driver;

    /**
     * The root element of the table
     */
    private final WebElement tableElement;

    public DataTable(WebDriver driver, WebElement tableElement) {
        this.driver = driver;
        this.tableElement = tableElement;
    }

    /**
     * Find specific table row according value in the cell with the specified header
     *
     * @param tableHeader
     * @param value
     * @return
     */
    public T findRowByValue(TableHeader tableHeader, String value) {
        return findRowByValue(tableHeader, value, false);
    }

    /**
     * @param tableHeader
     * @param value
     * @param pagination  Should pagintation
     * @return
     */
    public T findRowByValue(TableHeader tableHeader, String value, boolean pagination) {
        if (pagination) {
            return findRowByValueWithPagination(tableHeader, value);
        }
        return findRowByValueWithoutPagination(tableHeader, value);
    }

    private T findRowByValueWithPagination(TableHeader tableHeader, String value) {
        throw new RuntimeException("Not implemented");
    }

    private T findRowByValueWithoutPagination(TableHeader tableHeader, String value) {
        final String locator = ".//tbody/tr/td[position()='" + tableHeader.getIndex() + "' and text()='" + value + "']/..";
        return createRowInstance(tableElement.findElement(By.xpath(locator)));
    }

    public TableRow findRowByIndex(int index) {
        final String locator = ".//tbody/tr['" + index + "']";
        return new TableRow(driver, tableElement.findElement(By.xpath(locator)));
    }

    /**
     * Click on the next page button
     *
     * @return
     */
    public DataTable nextPage() {
        driver.findElement(getNextPageSelector()).click();
        return this;
    }

    /**
     * Check if the previous page button is enabled
     *
     * @return True if enabled
     */
    public boolean isPreviousButtonClickable() {
        final WebElement previousButtonElement = driver.findElement(getPreviousPageSelector());
        return previousButtonElement.isDisplayed() && previousButtonElement.isEnabled();
    }

    /**
     * Check if the next page button is enabled
     *
     * @return True if enabled
     */
    public boolean isNextPageButtonClickable() {
        final WebElement nextButtonElement = driver.findElement(getNextPageSelector());
        return nextButtonElement.isDisplayed() && nextButtonElement.isEnabled();
    }

    /**
     * Change the number of shown entried in the table.
     *
     * @param shownEntries
     * @return
     */
    public DataTable setNumberOfShownEntried(ShownEntries shownEntries) {
        Select shownEntriesSelect = new Select(driver.findElement(getShownEntriesSelector()));
        shownEntriesSelect.selectByValue(shownEntries.value + "");
        return this;
    }

    /**
     * Move to the previous page
     *
     * @return
     */
    public DataTable previousPage() {
        driver.findElement(getPreviousPageSelector()).click();
        return this;
    }

    /**
     * Type the given text in the table search box
     *
     * @param textToSearch
     * @return
     */
    public DataTable typeToSearchBox(String textToSearch) {
        driver.findElement(getSearchBoxSelector()).sendKeys(textToSearch);
        return this;
    }

    /**
     * Get the current number of rows that are displayed in the table
     *
     * @return
     */
    public int getNumberOfDisplayedRows() {
        List<WebElement> elements = tableElement.findElements(getAllRowsSelector());
        return elements.size();
    }

    /**
     * Get the number in the current page number
     *
     * @return
     */
    public int getCurrentPageNumber() {
        WebElement currentPageNumberBtn = driver.findElement(getCurrentPageNumberSelector());
        return Integer.parseInt(currentPageNumberBtn.getText());
    }

    /**
     * Click on the header. This is usually results in changing the table sorting.
     *
     * @param header
     * @return
     */
    public DataTable sortByHeader(TableHeader header) {
        tableElement.findElement(By.xpath(".//thead/tr/th[text()='" + header.getText() + "']")).click();
        return this;
    }

    /**
     * Override this to change the previous page button selector
     *
     * @return
     */
    protected By getPreviousPageSelector() {
        return By.id("example_previous");
    }

    /**
     * Override this to change the next page button selector
     *
     * @return
     */
    protected By getNextPageSelector() {
        return By.id("example_next");
    }

    /**
     * Override this to change the search box selector
     *
     * @return
     */
    protected By getSearchBoxSelector() {
        return By.cssSelector("#example_filter input[type='search']");
    }


    /**
     * Overreide this to change the selector that find all the rows in the table
     *
     * @return
     */
    protected By getAllRowsSelector() {
        return By.xpath(".//tbody/tr[@role='row']");
    }

    /**
     * Override this to change the selector of the number of entries in the drop down list.
     *
     * @return
     */
    protected By getShownEntriesSelector() {
        return By.name("example_length");
    }

    /**
     * Override this to change the selector of the button with the current page number
     *
     * @return
     */
    protected By getCurrentPageNumberSelector() {
        return By.cssSelector("a.paginate_button.current");
    }

    /**
     * Override this in case you want a different class of table row
     *
     * @param rowElement
     * @return
     */
    protected T createRowInstance(WebElement rowElement) {
        return (T) new TableRow(driver, rowElement);
    }

    protected WebDriver getWebDriver() {
        return this.driver;
    }


}
