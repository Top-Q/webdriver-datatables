package il.co.topq.datatables;

public abstract class AbstractTableCell implements TableCell {

    private final TableRow row;

    private final TableHeader tableHeader;

    public AbstractTableCell(TableRow row, TableHeader tableHeader) {
        this.row = row;
        this.tableHeader = tableHeader;
    }

    public TableRow getRow() {
        return row;
    }

    public TableHeader getTableHeader() {
        return tableHeader;
    }

}
