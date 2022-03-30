package Sudoku;


public class Grille {
    CaseSudoku[][] grilleCase = new CaseSudoku[9][9]; // create new grille: grilleCase;

    public Grille() {
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <=8; j++) {
                grilleCase[i][j] = new CaseSudoku(0); // set the value of each case in grille equals to 0;
            }
        }
    }

    // display the values of grilleCase;
    public void affichage () {
        for (CaseSudoku []eachline : grilleCase) {
            String string1 = "";
            for (CaseSudoku eachcase: eachline){
                string1 = string1 + eachcase.getCaseSudoku();
            }
            System.out.println(string1);
        }
    }

    // modify the the value of the case of the grilleCase;
    public void changeValue(int x, int y, int value) {
        if (x < 9 && y < 9 ) {
        this.grilleCase[x][y].modifyCase(value);
            }
        else {
            System.out.println("Value must be less than 9");
        }
    }

    // check the rule of each line
    public boolean checkrow ( int  numRowl, int numcoll, int vall ) {
        for (int i = 0 ;i<=8;i++) {
            if ( grilleCase[numRowl][i].getCaseSudoku()==vall )
                return false;
        }
        return true;
    }

    // check the rule of each column
    public boolean checkcolumn (int numRowr, int numColr, int valr) {
        for (int i = 0; i <= 8; i++) {
            if (grilleCase[i][numColr].getCaseSudoku() == valr)
                return  false;
        } return true;
    }

    // check the rule of each square
    public boolean checksquare (int numrowrl, int numcolrl, int valrw) {
        // identify the position of case, it belongs to which square;
        int isquare = (numrowrl-(numrowrl%3))/3;
        int jsquare = (numcolrl-(numcolrl%3))/3;
        //loop over the identified square;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (grilleCase[3*isquare+i][3*jsquare+j].getCaseSudoku() == valrw ) {
                    System.out.println( isquare+ " 0 " + jsquare);
                    return false;
                }
            }
        } return true;
    }

    public static void main(String[] args) {
//        some tests to check the methods;
        Grille grilleTest = new Grille();
        grilleTest.changeValue(5,4,9);
        grilleTest.affichage();
        Grille grilleTest2 = new Grille();
        grilleTest2.changeValue(8,4,10);
        grilleTest2.affichage();
    }
}
