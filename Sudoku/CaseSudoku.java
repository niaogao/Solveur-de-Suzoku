package Sudoku;

public class CaseSudoku {
    private int a;
    public CaseSudoku (int a) {
        this.a = a;
    }
    public int getCaseSudoku () {
        return a;
    }
    public void modifyCase (int i) {
        if (i > 0 && i < 10) {
        a = i;}
        else {
            System.out.println("error");
        }
    }
    public void eraseCase () {
        a = 0;
    }
}