package Sudoku;


public class CaseSudoku {
    private int a;

    /**
     * create constructor Casesudoku;
     * @param a;
     */
    public CaseSudoku (int a) {
        this.a = a;
    }

    /**
     * get the value of the CaseSudoku;
     * @return a;
     */
    public int getCaseSudoku () {
        return a;
    }

    /**
     *  modidy the value of the CaseSudodu;
     * @param i;
     */
    public void modifyCase (int i) {
        if (i >= 0 && i < 10) {
        a = i;}
        else {
            System.out.println("error");
        }
    }
}