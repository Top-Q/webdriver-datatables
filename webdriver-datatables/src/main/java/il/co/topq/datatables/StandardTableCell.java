package il.co.topq.datatables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * The simples cell type. Allows clicking on the cell and reading the text value
 */
public class StandardTableCell extends AbstractTableCell {

    public StandardTableCell(TableRow row, TableHeader tableHeader) {
        super(row, tableHeader);
    }

    @Override
    public void click() {
        findElement(getRow(), getTableHeader()).click();
    }

    @Override
    public String getText() {
        return findElement(getRow(), getTableHeader()).getText();
    }

    protected WebElement findElement(TableRow row, TableHeader tableHeader) {
        final String locator = ".//td[position()='" + tableHeader.getIndex() + "']";
        return row.getRowElement().findElement(By.xpath(locator));
    }

    @Override
    public String toString() {
        return getText();
    }


}
