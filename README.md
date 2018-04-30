# WebDriver DataTables

This is a Selenium WebDriver wrapper that helps in testing Jquery [DataTables](https://datatables.net/). The wrapper provides a comperhensive and object orietned API for performing various operations like reading values of cells, moving between pages, searching and more. 

## Examples

*Reading values from cell*

```Java
    StandardDataTable table = new StandardDataTable(driver, driver.findElement(By.id("example")));
    TableRow row = table.findRowByValue(MyTableHeader.NAME, "Cedric Kelly", false);
    TableCell tableCell = row.findCellByHeader(MyTableHeader.POSITION);
    System.out.println(tableCell.getText());

```

*Changing the number of displayed rows*

```Java
    StandardDataTable table = new StandardDataTable(driver, driver.findElement(By.id("example")));
    table.setNumberOfShownEntried(DataTable.ShownEntries._25);
    assertEquals(table.getNumberOfDisplayedRows(),25);
```

*Moving between pages*

```Java
    StandardDataTable table = new StandardDataTable(driver, driver.findElement(By.id("example")));
    table.nextPage();
    assertEquals(table.getCurrentPageNumber(),2);

    table.nextPage();
    assertEquals(table.getCurrentPageNumber(),3);

    table.previousPage();
    assertEquals(table.getCurrentPageNumber(),2);
```

*Searching in table*

```Java
    table.typeToSearchBox("33");
    assertEquals(table.getNumberOfDisplayedRows(),2);
```


*Sorting*

```Java
    table.sortByHeader(MyTableHeader.POSITION);
    assertEquals(table.findRowByIndex(1).findCellByHeader(MyTableHeader.NAME).getText(),"Airi Satou");

    table.sortByHeader(MyTableHeader.OFFICE);
    assertEquals(table.findRowByIndex(1).findCellByHeader(MyTableHeader.NAME).getText(),"Gavin Joyce");
```

## Adding to your project

*Maven*

In your pom file

```xml

.
    <repositories>
		<repository>
			<id>topq</id>
			<url>http://maven.top-q.co.il/content/groups/public</url>
		</repository>
	</repositories>
.
.
    <dependency>
        <groupId>il.co.topq</groupId>
        <artifactId>webdriver-datatables</artifactId>
        <version>1.0.00</version>
    </dependency>
.
```

## Usage

Start by inheriting the `DataTable` class and overriding the needed methods as shown in the next example:

```Java
public class ZeroConfigTable extends DataTable<TableRow> {


    public ZeroConfigTable(WebDriver driver) {
        super(driver, driver.findElement(By.id("example")));
    }

    @Override
    protected By getPreviousPageSelector() {
        return By.id("example_previous");
    }

    @Override
    protected By getNextPageSelector() {
        return By.id("example_next");
    }

    @Override
    protected By getSearchBoxSelector() {
        return By.cssSelector("#example_filter input[type='search']");
    }

    @Override
    protected By getAllRowsSelector() {
        return By.xpath(".//tbody/tr[@role='row']");
    }

    @Override
    protected TableRow createRowInstance(WebElement rowElement) {
        return new TableRow(getWebDriver(), rowElement);
    }


}

```

It is possible that you will need to override more or less methods according to the table that you are testing

Next, create an enum that implements the `TableHeader` interface and specify your table headers and cell types as seen here:

```Java
public enum ZeroConfigHeader implements TableHeader {

    NAME(1, "Name"),
    POSITION(2, "Position"),
    OFFICE(3, "Office"),
    AGE(4, "Age"),
    START_DATE(5, "Start date"),
    SALARY(6, "Salary", StandardTableCell.class);

    private final int index;

    private final String text;

    private final Class<? extends AbstractTableCell> cellType;

    ZeroConfigHeader(int index, String text, Class<? extends AbstractTableCell> cellType) {
        this.index = index;
        this.text = text;
        this.cellType = cellType;
    }

    ZeroConfigHeader(int index, String text) {
        this(index,text, StandardTableCell.class);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public Class<? extends AbstractTableCell> getCellType() {
        return this.cellType;
    }

    @Override
    public String getText() {
        return this.text;
    }
}

```

The default cell type is `StandardTableCell` which allows clicking and reading the values from typical table cells. You can inherite it to create new types of cells. 

## Example Project

Please refer to the [example project](https://github.com/Top-Q/webdriver-datatables/tree/master/webdriver-datatables-examples) for more information



