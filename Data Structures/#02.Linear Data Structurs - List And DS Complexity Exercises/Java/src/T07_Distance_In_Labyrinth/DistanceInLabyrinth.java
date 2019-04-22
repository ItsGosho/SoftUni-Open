package T07_Distance_In_Labyrinth;

import java.util.Scanner;

public class DistanceInLabyrinth {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int rowsAndColumnsFromInput = Integer.parseInt(scanner.nextLine());
        Labyrinth labyrinth = new Labyrinth(rowsAndColumnsFromInput,rowsAndColumnsFromInput);

        int labyrinthFillerCurrentRow = 0;

        while (labyrinthFillerCurrentRow < rowsAndColumnsFromInput) {

            String firstRow = scanner.nextLine();
            String[] splittedFirstRow = firstRow.split("");

            labyrinth.fillRow(labyrinthFillerCurrentRow,splittedFirstRow);

            labyrinthFillerCurrentRow++;
        }

        labyrinth.start();
        labyrinth.print();
    }

}


