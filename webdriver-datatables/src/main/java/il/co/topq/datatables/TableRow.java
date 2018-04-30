package il.co.topq.datatables;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Represents a single table row
 */
public class TableRow {

    private final WebElement rowElement;

    private final WebDriver driver;

    public TableRow(WebDriver driver, WebElement rowElement) {
        this.driver = driver;
        this.rowElement = rowElement;
    }

    @Override
    public String toString() {
        return rowElement.getText();
    }

    public TableCell findCellByHeader(TableHeader tableHeader) {
        Class<? extends AbstractTableCell> cellClass = tableHeader.getCellType();
        Constructor<?> ctor = null;
        try {
            ctor = cellClass.getConstructor(TableRow.class, TableHeader.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        AbstractTableCell tableCell = null;
        try {
            tableCell = (AbstractTableCell) ctor.newInstance(new Object[]{this, tableHeader});
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return tableCell;
    }

    WebElement getRowElement() {
        return rowElement;
    }


}
