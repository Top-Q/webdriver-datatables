package il.co.topq.datatables;

/**
 * Interface for enums that specify the table headers.
 */
public interface TableHeader {

    /**
     * 1 based index of the header index
     *
     * @return
     */
    int getIndex();

    /**
     * The cell type
     *
     * @return
     */
    Class<? extends AbstractTableCell> getCellType();

    /**
     * The actual text
     *
     * @return
     */
    String getText();

}
