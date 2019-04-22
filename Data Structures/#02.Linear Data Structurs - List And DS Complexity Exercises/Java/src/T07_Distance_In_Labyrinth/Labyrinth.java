package T07_Distance_In_Labyrinth;

import java.util.ArrayDeque;

public class Labyrinth {

    private final String startPositionElementPattern = "*";
    private final String emptyElementPattern = "0";
    private final String blockedElementPattern = "u";
    private final String fullElementPattern = "x";

    private int startPositionRow;
    private int startPositionColumn;
    private int rows;
    private int columns;
    private String[][] labyrinth;

    public Labyrinth(int rows, int columns) {

        this.startPositionRow = rows;
        this.startPositionColumn = columns;

        this.rows = rows;
        this.columns = columns;
        this.labyrinth = new String[this.rows][this.columns];
    }


    public void fillRow(int row, String[] data) {

        for (int i = 0; i < data.length; i++) {

            String element = data[i];
            this.labyrinth[row][i] = element;

            if (element.equals("*")) {
                this.startPositionRow = row;
                this.startPositionColumn = i;
            }

        }
    }

    public void start() {


        ArrayDeque<Cell> cellsToFill = this.getNextCellsAround(new Cell(this.startPositionRow,this.startPositionColumn,0));

        ArrayDeque<Cell> nextCells = new ArrayDeque<>();
        while (true) {

            int currentCellsToFillSize = cellsToFill.size();
            for (int i = 0; i < currentCellsToFillSize; i++) {
                Cell cell = cellsToFill.pop();
                this.fillCell(cell,cell.getValue());

                nextCells.addAll(this.getNextCellsAround(cell));
            }

            cellsToFill.clear();
            cellsToFill.addAll(nextCells);
            nextCells.clear();

            if(cellsToFill.size() == 0){
                break;
            }

        }

        this.proceedAllBlockedCells();
    }

    private void proceedAllBlockedCells(){
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if(this.labyrinth[i][j].equals(this.emptyElementPattern)){
                    this.labyrinth[i][j] = this.blockedElementPattern;
                }
            }
        }
    }

    private void fillCell(Cell cell,int value){
        this.labyrinth[cell.getRow()][cell.getColumn()] = Integer.toString(value);
    }

    private ArrayDeque<Cell> getNextCellsAround(Cell cell) {
        ArrayDeque<Cell> nextCells = new ArrayDeque<>();
        int currentPositionRow = cell.getRow();
        int currentPositionColumn = cell.getColumn();

        //Is UP position dead end
        if (!this.isPositionEnd(currentPositionRow - 1, currentPositionColumn)) {
            Cell nextCell = new Cell(currentPositionRow - 1, currentPositionColumn, cell.getValue()+1);
            nextCells.add(nextCell);
        }

        //Is DOWN position dead end
        if (!this.isPositionEnd(currentPositionRow + 1, currentPositionColumn)) {
            Cell nextCell = new Cell(currentPositionRow + 1, currentPositionColumn, cell.getValue()+1);
            nextCells.add(nextCell);
        }

        //Is RIGHT position dead end
        if (!this.isPositionEnd(currentPositionRow, currentPositionColumn + 1)) {
            Cell nextCell = new Cell(currentPositionRow, currentPositionColumn + 1, cell.getValue()+1);
            nextCells.add(nextCell);
        }

        //Is LEFT position dead end
        if (!this.isPositionEnd(currentPositionRow, currentPositionColumn - 1)) {
            Cell nextCell = new Cell(currentPositionRow, currentPositionColumn - 1, cell.getValue()+1);
            nextCells.add(nextCell);
        }

        return nextCells;
    }

    private boolean isPositionEnd(int row, int column) {

        if (row < 0 || row > this.rows-1) {
            return true;
        }

        if (column < 0 || column > this.columns-1) {
            return true;
        }

        String element = this.labyrinth[row][column];

        try {
            if (Integer.parseInt(element) >= 1) {
                return true;
            }

        } catch (NumberFormatException ex) {
        }

        switch (element) {
            case startPositionElementPattern:
                return true;
            case fullElementPattern:
                return true;
        }

        return false;
    }

    public void print() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.print(this.labyrinth[i][j]);
            }
            System.out.println();
        }
    }

}
