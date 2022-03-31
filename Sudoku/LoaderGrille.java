package Sudoku;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoaderGrille {
    static Grille load() {
        Grille grille = new Grille();
        try {
            FileReader fileReader = new FileReader("Sudoku/Grilleinitiale.csv");
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
