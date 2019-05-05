import java.util.ArrayList;
import java.util.List;

public class Labyrinth {

    public static final String START_POSITION_SYMBOL = "*";
    public static final String VISITED_CELL_SYMBOL = "V";
    public static final String WALL_SYMBOL = "W";
    public static final String FINAL_POSITION_SYMBOL = "P";

    private PriorityQueue<Cell> cells;

    private String[][] labyrinth;
    private int startRow;
    private int startColumn;
    private int finishRow;
    private int finishColumn;

    public Labyrinth(String[][] labyrinth, int startRow, int startColumn, int finishRow, int finishColumn) {
        this.labyrinth = labyrinth;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.finishRow = finishRow;
        this.finishColumn = finishColumn;
        this.cells = new PriorityQueue<>();
    }

    public void start() {

        this.insertInitialCells();

        while (this.cells.size() != 0) {
            Cell cell = cells.dequeue();

            if (this.labyrinth[cell.getRow()][cell.getColumn()].equals(FINAL_POSITION_SYMBOL)) {
                this.printPath(cell);
                return;
            }

            if (this.isCordinatesValid(cell.getRow() - 1, cell.getColumn())) {
                Cell upCell = new Cell(cell.getGCost() + 1, cell.getRow() - 1, cell.getColumn(), finishRow, finishColumn, cell);
                this.makeVisited(cell.getRow() - 1, cell.getColumn());
                this.cells.enqueue(upCell);
            }

            if (this.isCordinatesValid(cell.getRow() + 1, cell.getColumn())) {
                Cell downCell = new Cell(cell.getGCost() + 1, cell.getRow() + 1, cell.getColumn(), finishRow, finishColumn, cell);
                this.makeVisited(cell.getRow() + 1, cell.getColumn());
                this.cells.enqueue(downCell);
            }

            if (this.isCordinatesValid(cell.getRow(), cell.getColumn() - 1)) {
                Cell leftCell = new Cell(cell.getGCost() + 1, cell.getRow(), cell.getColumn() - 1, finishRow, finishColumn, cell);
                this.makeVisited(cell.getRow(), cell.getColumn() - 1);
                this.cells.enqueue(leftCell);
            }

            if (this.isCordinatesValid(cell.getRow(), cell.getColumn() + 1)) {
                Cell rightCell = new Cell(cell.getGCost() + 1, cell.getRow(), cell.getColumn() + 1, finishRow, finishColumn, cell);
                this.makeVisited(cell.getRow(), cell.getColumn() + 1);
                this.cells.enqueue(rightCell);
            }
        }

        if(this.cells.size() == 0){
            System.out.println(String.format("{ \"%d %d\" }",this.finishRow,this.finishColumn));
        }

    }

    private void makeVisited(int row, int column) {
        String currentValue = this.labyrinth[row][column];
        if (currentValue.equals("-")) {
            this.labyrinth[row][column] = VISITED_CELL_SYMBOL;
        }
    }

    private void printPath(Cell cell) {
        List<String> result = new ArrayList<>();
        Cell prev = cell;

        while (prev != null) {
            result.add(prev.toString());
            prev = prev.getPreviousCell();
        }
        result.add(String.format("\"%d %d\"", this.startRow, this.startColumn));

        System.out.println(String.format("{ %s }",String.join(", ",result)));
    }

    private void insertInitialCells() {

        if (this.isCordinatesValid(this.startRow - 1, this.startColumn)) {
            Cell upCell = new Cell(this.startRow - 1, this.startColumn, this.finishRow, this.finishColumn);
            this.cells.enqueue(upCell);
        }

        if (this.isCordinatesValid(this.startRow + 1, this.startColumn)) {
            Cell downCell = new Cell(this.startRow + 1, this.startColumn, this.finishRow, this.finishColumn);
            this.cells.enqueue(downCell);
        }

        if (this.isCordinatesValid(this.startRow, this.startColumn - 1)) {
            Cell leftCell = new Cell(this.startRow, this.startColumn - 1, this.finishRow, this.finishColumn);
            this.cells.enqueue(leftCell);
        }

        if (this.isCordinatesValid(this.startRow, this.startColumn + 1)) {
            Cell rightCell = new Cell(this.startRow, this.startColumn + 1, this.finishRow, this.finishColumn);
            this.cells.enqueue(rightCell);
        }

    }

    private boolean isCordinatesValid(int row, int column) {

        if (row > labyrinth.length - 1 || row < 0) {
            return false;
        }

        if (column > labyrinth[0].length - 1 || column < 0) {
            return false;
        }

        String element = labyrinth[row][column];

        if (element.equals(START_POSITION_SYMBOL)) {
            return false;
        }

        if (element.equals(WALL_SYMBOL)) {
            return false;
        }

        if (element.equals(VISITED_CELL_SYMBOL)) {
            return false;
        }

        return true;
    }

    private void printLabyrinth() {
        for (int i = 0; i < this.labyrinth.length; i++) {
            for (int j = 0; j < this.labyrinth[i].length; j++) {
                System.out.print(this.labyrinth[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------->");
    }

}
