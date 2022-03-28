package Sudoku;

import java.util.Arrays;

public class Grille {
    CaseSudoku[][] grilleCase = new CaseSudoku[9][9];
    public Grille() {
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <=8; j++) {
                grilleCase[i][j] = new CaseSudoku(0);
            }
        }
    }
    public void affichage () {
        for (CaseSudoku []eachline : grilleCase) {
            String string1 = "";
            for (CaseSudoku eachcase: eachline){
                string1 = string1 + eachcase.getCaseSudoku();
            }
            System.out.println(string1);
    }
    }
    public void changeValue(int x, int y, int value) {
        if (x < 9 && y < 9 ) {
        this.grilleCase[x][y].modifyCase(value);
            }
        else {
            System.out.println("Value must be less than 9");
        }
    }

//    @Override
//    public String toString() {
//        return "Grille{" +
//                "grilleCase=" + Arrays.toString(grilleCase) +
//                '}';
//    }

    public static void main(String[] args) {
//        System.out.println(new Grille());
        Grille grilleTest = new Grille();
        grilleTest.changeValue(5,4,9);
        grilleTest.affichage();
        Grille grilleTest2 = new Grille();
        grilleTest2.changeValue(8,4,10);
        grilleTest2.affichage();
    }
}
