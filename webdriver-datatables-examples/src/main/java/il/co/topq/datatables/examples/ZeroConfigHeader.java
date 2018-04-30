package il.co.topq.datatables.examples;

import il.co.topq.datatables.*;

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
        this(index, text, StandardTableCell.class);
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
