public class Cell implements Comparable<Cell> {

    private Integer FCost;
    private Integer GCost;
    private Integer HCost;

    private Integer row;
    private Integer column;

    private Cell previousCell;

    public Cell(Integer GCost, Integer row, Integer column,Integer goalRow,Integer goalColumn,Cell previousCell) {
        this.GCost = GCost;
        this.row = row;
        this.column = column;
        this.previousCell = previousCell;

        this.HCost = Math.abs((column-goalColumn)) + Math.abs((row-goalRow));
        this.FCost = this.GCost + HCost;
    }

    public Cell(Integer row, Integer column,Integer goalRow,Integer goalColumn) {
        this.GCost = 1;
        this.row = row;
        this.column = column;

        this.HCost = Math.abs((column-goalColumn)) + Math.abs((row-goalRow));
        this.FCost = this.GCost + HCost;
    }

    public Integer getFCost() {
        return FCost;
    }

    public Integer getGCost() {
        return GCost;
    }

    public Integer getHCost() {
        return HCost;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public Cell getPreviousCell() {
        return previousCell;
    }

    @Override
    public int compareTo(Cell o) {
        return this.FCost.compareTo(o.getFCost());
    }

    @Override
    public String toString() {
        return String.format("\"%d %d\"", this.row, this.column);
    }
}
