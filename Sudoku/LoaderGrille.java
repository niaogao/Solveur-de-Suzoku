package Sudoku;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoaderGrille {
    // create a arrayliste which contains the name of the grille files;
    static String[] paths = {"./Sudoku/Grilleinitiale00.csv", "./Sudoku/Grilleinitiale01.csv", "./Sudoku/Grilleinitiale02.csv"};
    static String currentPath = "./Sudoku/Grilleinitiale02.csv";

    /**
     *  get a random grille files;
     * @return
     */
    static  String getRandomString (){
        int r = (int) (Math.random()*3);
        String nameGrille =  paths [r];
        return nameGrille;
    }

    /**
     *  load the grille file;
     * @param path
     * @return
     */
    static Grille load(String path) {
        Grille grille = new Grille();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            for (int i = 0; i < 9; i++) {
                String line = reader.readLine();
                String[] chiffres = line.split(",");
                for (int j = 0; j < 9; j++) {
                    grille.changeValue(j, i, Integer.parseInt(chiffres[j]));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return grille;
    }

}
