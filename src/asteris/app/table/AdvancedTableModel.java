package asteris.app.table;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AdvancedTableModel extends AbstractTableModel {

    private final List<Column> columns = new ArrayList<>();

    List<Color> rowColours = Arrays.asList(
        Color.RED,
        Color.GREEN,
        Color.CYAN
    );
    
    public void setRowColour(int row, Color c) {
        rowColours.set(row, c);
        fireTableRowsUpdated(row, row);
    }

    public Color getRowColour(int row) {
        return rowColours.get(row);
    }
    
    /**
     * Adds the specified columns to this model.
     *
     * @param columns the columns to add
     */
    protected final void addColumns(Column... columns) {
        for (Column c : columns) {
            if (c == null) {
                throw new NullPointerException();
            }
            this.columns.add(c);
        }
        fireTableStructureChanged();
    }

    /**
     * Returns the index of the specified column. If the model does not contain
     * the column, -1 is returned. If it contains multiple instances of the
     * column, the index of the first occurence is returned.
     *
     * @param column a column
     * @return the index of the column, or -1 if the model does not contain the
     * column
     */
    public int getColumnIndex(Column column) {
        return columns.indexOf(column);
    }

    /**
     * Returns the number of columns.
     *
     * @return the number of columns
     */
    @Override
    public int getColumnCount() {
        return columns.size();
    }

    /**
     * Forwards the call to the {@code get} function of the appropriate column.
     *
     * @param rowIndex the row of the cell
     * @param columnIndex the column of the cell
     * @return the value in the cell
     * @see Column#get(int)
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return columns.get(columnIndex).get(rowIndex);
    }

    /**
     * Forwards the call to the {@code getName} function of the appropriate
     * column.
     *
     * @param column the column index
     * @return the name of the column
     * @see Column#getName()
     */
    @Override
    public String getColumnName(int column) {
        return columns.get(column).getName();
    }

    /**
     * Forwards the call to the {@code getColumnClass} function of the
     * appropriate column.
     *
     * @param columnIndex the column index
     * @return the class of the values in the column
     * @see Column#getColumnClass
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns.get(columnIndex).getColumnClass();
    }

    /**
     * Forwards the call to the {@code isEditable} function of the appropriate
     * column.
     *
     * @param rowIndex row of cell
     * @param columnIndex column of cell
     * @return whether the cell is editable
     * @see Column#isEditable(int)
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columns.get(columnIndex).isEditable(rowIndex);
    }

    /**
     * Forwards the call to the {@code set} function of the appropriate column.
     *
     * @param aValue value to assign to cell
     * @param rowIndex row of cell
     * @param columnIndex column of cell
     * @see Column#set(Object, int)
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        columns.get(columnIndex).set(aValue, rowIndex);
    }

    @Override
    public int getRowCount() {
        return columns.size();
    }
    
}
