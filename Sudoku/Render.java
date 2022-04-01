package Sudoku;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Render extends Application {
    int icoordx = 0; // define the coordinate x which will be converted between 0 and 8;
    int icoordy = 0; // define the coordinate x which will be converted between 0 and 8;
    GraphicsContext gc; // add Graphics Context in canvas;
    Grille newgameinitial = new Grille(); // create a initial game Grille;
    Grille newgame = new Grille(); // create a new game Grille

    /**
     * fix the initial value of the newgameinitial, we can't change after.
     * So we can't change the initial values of the original game;
     * @param i
     * @param j
     * @return
     */
    private boolean checknewvalue (int i, int j) {
        if  (newgameinitial.grilleCase[i][j].getCaseSudoku()!=0) {
            return false;
        } return true;
    }

    /**
     * calculate the sum of the values of the newgameinitial;
     * @return
     */
    private   int sumnewgameinitial () {
        int suminitial = 0;
        for (int i = 0; i <9; i++) {
            for (int j = 0; j <9; j++) {
                suminitial = suminitial+newgameinitial.grilleCase[i][j].getCaseSudoku();
            }
        } return suminitial;
    }

    /**
     * calculate the sum of the values of the newgame;
     * @return
     */
    private   int sumnewgame () {
        int sumnew = 0;
        for (int i = 0; i <9; i++) {
            for (int j = 0; j <9; j++) {
                sumnew = sumnew+newgame.grilleCase[i][j].getCaseSudoku();
            }
        } return sumnew;
    }

    /**
     * input the values of newgame in the grille: initially the value is 0 for all the cases;
     * @param root
     */
    private void drawnewgame(Group root) {
        for (int x = 50; x <= 450; x = x + 50) {
            for (int y = 130; y <= 530; y = y + 50) {
                Text casetext = new Text();
                casetext.setFont(new Font(40));
                casetext.setFill(Color.BLUE);
                casetext.setX(x+5);
                casetext.setY(y-1);
                if (newgame.grilleCase[(x - 50) / 50][(y - 130) / 50].getCaseSudoku() != 0){
                casetext.setText(newgame.grilleCase[(x - 50) / 50][(y - 130) / 50].getCaseSudoku() + "");
                root.getChildren().add(casetext);}
            }
        }
    }

    /**
     * input the values of newgameinitial in the grille: initially the value is 0 for all the cases;
     * @param root
     */
    private void drawnewgameinitial(Group root) {
        for (int x = 50; x <= 450; x = x + 50) {
            for (int y = 130; y <= 530; y = y + 50) {
                Text casetext = new Text();
                casetext.setFont(new Font(40));
                casetext.setFill(Color.BLACK);
                casetext.setX(x+5);
                casetext.setY(y-1);
                if (newgameinitial.grilleCase[(x - 50) / 50][(y - 130) / 50].getCaseSudoku() != 0){
                    casetext.setText(newgameinitial.grilleCase[(x - 50) / 50][(y - 130) / 50].getCaseSudoku() + "");
                    root.getChildren().add(casetext);}
            }
        }
    }


     /**
     * cover the value of a selected case with a white rectangle;
     * @param root
     */
    private void eraseNumber(Group root){
                Rectangle rectanglenumber = new Rectangle((icoordx*50+40)+4, (icoordy*50+90)+2, 41, 43);
                rectanglenumber.setFill(Color.WHITE);
                root.getChildren().add(rectanglenumber);
    }

    /**
     * cover all the cases with white rectangles;
     * @param root
     */
    private void eraseAll(Group root) {
        for (icoordx = 0; icoordx <= 8; icoordx++) {
            for (icoordy = 0; icoordy <= 8; icoordy++) {
                if (newgame.grilleCase[icoordx][icoordy] != newgameinitial.grilleCase[icoordx][icoordy]) {
                    eraseNumber(root);
                }
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
    // create a group object
        Group root = new Group();

    // create a scene by defining the group object, height and width
        int width = 1000;
        int height = 600;
        Scene scene = new Scene(root,width, height);

    // load newgame and newgameinital grille;
        LoaderGrille.currentPath = LoaderGrille.getRandomString();
        newgameinitial = LoaderGrille.load(LoaderGrille.currentPath);
        newgame = LoaderGrille.load(LoaderGrille.currentPath);

    // Color, title of the scene;
        scene.setFill(Color.WHITE);
        stage.setTitle("Solveur de Sudoku");
        drawnewgame(root);
        drawnewgameinitial(root);

    // Add Scene to stage
        stage.setScene(scene);

    // show Scene
        stage.show();

    //  add Canvas
        Canvas canvas = new Canvas(1000, 600);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

    // add 9x9 grille: 81 rectangles and lines
        for (int Rsx = 40; Rsx <= 440; Rsx = Rsx +50) { //draw rectangle;
            for (int Rsy = 88; Rsy <= 488; Rsy = Rsy +50)
                gc.strokeRect(Rsx,Rsy,50,50);
        }
        for (int Ly = 88; Ly <= 538; Ly = Ly +150) { //draw bold row lines
            Line blackLine = new Line();
            blackLine.setStartX(40);
            blackLine.setStartY(Ly);
            blackLine.setEndX(490);
            blackLine.setEndY(Ly);
            blackLine.setFill(Color.BLACK);
            blackLine.setStrokeWidth(5);
            root.getChildren().add(blackLine);
        }
        for (int Lx = 40; Lx <= 490; Lx = Lx +150) { // draw bold column lines
            Line blackLine = new Line();
            blackLine.setStartX(Lx);
            blackLine.setStartY(88);
            blackLine.setEndX(Lx);
            blackLine.setEndY(538);
            blackLine.setFill(Color.BLACK);
            blackLine.setStrokeWidth(5);
            root.getChildren().add(blackLine);
        }

    // add bouton clavier 1 -9;
        int count =0;
        for (int buttony = 200; buttony <= 340; buttony = buttony +70) {
            for (int buttonx = 560; buttonx <= 700; buttonx = buttonx +70) {
                count = count + 1;
                Button button = new Button(count + "");

                // button.setText("count");
                Font font = Font.font("count", FontWeight.BLACK, 36);
                button.setFont(font);
                button.setLayoutX(buttonx);
                button.setLayoutY(buttony);
                button.setMinSize(70, 70);
                button.setMaxSize(100, 100);
                button.setPrefSize(70, 70);
                button.setStyle("-fx-border-color: black;");
                root.getChildren().add(button);
                int finalCount = count;
                button.setOnMouseClicked(e -> {
                    /*check the rule before input the values in newgame grille (checkrow, checkcolumn, chekcolum and
                    if the value is equal to the value in newgameinital); */
                    if (newgame.checkrow(icoordx, icoordy, finalCount)
                        && (newgame.checkcolumn(icoordx, icoordy, finalCount))
                        && (newgame.checksquare(icoordx, icoordy, finalCount))
                        && checknewvalue(icoordx,icoordy)) {
                        eraseNumber(root);
                        newgame.changeValue(icoordx, icoordy, finalCount);
                        drawnewgame(root); // color blue
                        drawnewgameinitial(root); // color black

                        // show the position of the changed case and the input value;
                        System.out.println("clic at the position [" + icoordx + "]" + "[" + icoordy + "]" +
                                ", the input value is:  " + finalCount);

                        if ( sumnewgame() == 405) {  // check the state of the game
                            final Stage dialogautofinish = new Stage();
                            dialogautofinish.initModality(Modality.APPLICATION_MODAL);
                            VBox dialogVboxautofinish = new VBox();
                            Text textautofinish = new Text();
                            textautofinish.setFont(new Font(35));
                            textautofinish.setFill(Color.BLUE);
                            textautofinish.setText("Bravo!! New game ??");
                            dialogVboxautofinish.getChildren().add(textautofinish);

                            Button buttonnextgame = new Button();
                            buttonnextgame.setLayoutX(300);
                            buttonnextgame.setLayoutY(300);
                            buttonnextgame.setMinSize(168,50);
                            buttonnextgame.setMaxSize(200,100);
                            buttonnextgame.setPrefSize(170,50);
                            buttonnextgame.setStyle("-fx-border-color: white;");
                            buttonnextgame.setStyle("-fx-background-color: white");

                            Image imagefinish = new Image ("Sudoku/new game.png",168,50,true,true);
                            ImageView imagefinishView = new ImageView(imagefinish);
                            buttonnextgame.setGraphic(imagefinishView);
                            dialogVboxautofinish.getChildren().add(buttonnextgame);

                            Scene dialogScene = new Scene(dialogVboxautofinish, 400, 300);
                            dialogautofinish.setScene(dialogScene);
                            dialogautofinish.show();
                            System.out.println("Bravo! You won.");

                            // reload a new game
                            buttonnextgame.setOnMouseClicked(e1 -> {
                                eraseAll(root);
                                System.out.println("reload the initial game");
                                LoaderGrille.currentPath = LoaderGrille.getRandomString();
                                newgame = LoaderGrille.load(LoaderGrille.currentPath);
                                newgameinitial =LoaderGrille.load(LoaderGrille.currentPath);
                                drawnewgame(root);
                                drawnewgameinitial(root);
                            });
                        }
                    } else { System.out.println("position at [" + icoordx + "]" +
                            "[" + icoordy + "]" + ", can't change value,please check the rule");
                    }
                });
            }
        }

    // add mouse with action;
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEventpositon) {
               if (mouseEventpositon.getSceneX() <= 490 && mouseEventpositon.getSceneX() >= 40
                 && mouseEventpositon.getSceneY() <= 540 && mouseEventpositon.getSceneY() >= 90) {
                    double coordx = mouseEventpositon.getSceneX();
                    double coordy = mouseEventpositon.getSceneY();
                    icoordx = (int) (coordx-40)/50;
                    icoordy = (int) (coordy-90)/50;

                    // display the selected case by a blue rectangle;
                   if (newgame.grilleCase[icoordx][icoordy].getCaseSudoku() != newgameinitial.grilleCase[icoordx][icoordy].getCaseSudoku()
                   || newgame.grilleCase[icoordx][icoordy].getCaseSudoku() == 0) {
                   Rectangle rectanglenumberblue = new Rectangle((icoordx*50+44), (icoordy*50+90+2), 41, 43);
                   rectanglenumberblue.setFill(Color.BLUE);
                   rectanglenumberblue.setStroke(Color.WHITE);
                   root.getChildren().add(rectanglenumberblue);}

                    System.out.println("clic at the position ["+icoordx + "]" + "["+icoordy+"]");}
                }
            });

    // add image niveau and facile;
        Image niveauimg = new javafx.scene.image.Image( "Sudoku/niveau.png" );
        gc.drawImage( niveauimg, 70, 18,100,45);
        Image niveaufacile = new javafx.scene.image.Image( "Sudoku/facile.png" );
        gc.drawImage( niveaufacile, 175, 20.5,80,40);

    // add image autocheck mode on
        Image autocheckmode = new javafx.scene.image.Image( "Sudoku/autocheck mode.png" );
        gc.drawImage( autocheckmode, 350, 15,225,46);
        Image autocheckmodoff = new javafx.scene.image.Image( "Sudoku/autocheck mode on.png" );
        gc.drawImage( autocheckmodoff, 600, 18,106,41.7);

    // add button prise des notes and text notes;
        Button buttonnotes = new Button();
        buttonnotes.setLayoutX(800);
        buttonnotes.setLayoutY(95);
        buttonnotes.setMinSize(55,55);
        buttonnotes.setMaxSize(100,100);
        buttonnotes.setPrefSize(55,55);
        buttonnotes.setStyle("-fx-border-color: white;");
        buttonnotes.setStyle("-fx-background-color: white");

        Image imagenotes = new Image ("Sudoku/take notes.png",60,60,true,true);
        ImageView imagenotesView = new ImageView(imagenotes);
        buttonnotes.setGraphic(imagenotesView);
        root.getChildren().add(buttonnotes);

        Text textnotes = new Text();
        textnotes.setFont(new Font(30));
        textnotes.setX(870);
        textnotes.setY(130);
        textnotes.setText("notes");
        root.getChildren().add(textnotes);

    //  add button reset and text reset;
        Button buttonreset = new Button();
        buttonreset.setLayoutX(820);
        buttonreset.setLayoutY(180);
        buttonreset.setMinSize(55,55);
        buttonreset.setMaxSize(100,100);
        buttonreset.setPrefSize(55,55);
        buttonreset.setStyle("-fx-border-color: white;");
        buttonreset.setStyle("-fx-background-color: white");

        Image imagereset = new Image ("Sudoku/reset.png",55,55,true,true);
        ImageView imageresetView = new ImageView(imagereset);
        buttonreset.setGraphic(imageresetView);
        root.getChildren().add(buttonreset);

    // reset to the initial game;
        buttonreset.setOnMouseClicked(e -> {
            if (sumnewgameinitial() != sumnewgame()) {
                eraseAll(root);
                System.out.println("reload the initial game");
                newgame = LoaderGrille.load(LoaderGrille.currentPath);
                newgameinitial =LoaderGrille.load(LoaderGrille.currentPath);
                drawnewgame(root);
                drawnewgameinitial(root);
            } else System.out.println("it's the initial game");
        });

        Text textrest = new Text();
        textrest.setFont(new Font(30));
        textrest.setX(880);
        textrest.setY(218);
        textrest.setText("reset");
        root.getChildren().add(textrest);

        //  add button eraser;
        Button buttoneraser = new Button();
        buttoneraser.setLayoutX(820);
        buttoneraser.setLayoutY(270);
        buttoneraser.setMinSize(55,55);
        buttoneraser.setMaxSize(100,100);
        buttoneraser.setPrefSize(55,55);
        buttoneraser.setStyle("-fx-border-color: white;");
        buttoneraser.setStyle("-fx-background-color: white");

        Image imagepause = new Image ("Sudoku/eraser.png",60,60,true,true);
        ImageView imagepauseView = new ImageView(imagepause);
        buttoneraser.setGraphic(imagepauseView);
        root.getChildren().add(buttoneraser);

    // deleting the value by entering 0;
        buttoneraser.setOnMouseClicked(e -> {
            if (newgame.grilleCase[icoordx][icoordy].getCaseSudoku()
                != newgameinitial.grilleCase[icoordx][icoordy].getCaseSudoku()){
            eraseNumber(root);
            newgame.changeValue(icoordx, icoordy, 0);
            }
        });

        Text textpause = new Text();
        textpause.setFont(new Font(30));
        textpause.setX(880);
        textpause.setY(307);
        textpause.setText("eraser");
        root.getChildren().add(textpause);

    //  add button exit;
        Button buttonexit = new Button();
        buttonexit.setLayoutX(820);
        buttonexit.setLayoutY(365);
        buttonexit.setMinSize(55,55);
        buttonexit.setMaxSize(100,100);
        buttonexit.setPrefSize(55,55);
        buttonexit.setStyle("-fx-border-color: white;");
        buttonexit.setStyle("-fx-background-color: white");

        Image imageexit = new Image ("Sudoku/exit.png",60,60,true,true);
        ImageView imageexitView = new ImageView(imageexit);
        buttonexit.setGraphic(imageexitView);
        root.getChildren().add(buttonexit);

        buttonexit.setOnMouseClicked(e -> {
            Platform.exit();
        });

        Text textexit = new Text();
        textexit.setFont(new Font(30));
        textexit.setX(880);
        textexit.setY(401);
        textexit.setText("exit");
        root.getChildren().add(textexit);


    // add button finish;
        Button buttonfinish = new Button();
        buttonfinish.setLayoutX(600);
        buttonfinish.setLayoutY(450);
        buttonfinish.setMinSize(65,65);
        buttonfinish.setMaxSize(100,100);
        buttonfinish.setPrefSize(65,65);
        buttonfinish.setStyle("-fx-border-color: white;");
        buttonfinish.setStyle("-fx-background-color: white");

        Image imagefinish = new Image ("Sudoku/finish.png",65,65,true,true);
        ImageView imagefinishView = new ImageView(imagefinish);
        buttonfinish.setGraphic(imagefinishView);
        root.getChildren().add(buttonfinish);

        buttonfinish.setOnMouseClicked(e -> {

            if ( sumnewgame() == 405) {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox();
                Text textnotfish = new Text();
                textnotfish.setFont(new Font(35));
                textnotfish.setFill(Color.BLUE);
                textnotfish.setText("Bravo! You are the best!!!");
                dialogVbox.getChildren().add(textnotfish);
                Scene dialogScene = new Scene(dialogVbox, 400, 300);
                dialog.setScene(dialogScene);
                dialog.show();
                System.out.println("Bravo! You won.");
            } else {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox();
                Text textnotfish = new Text();
                textnotfish.setFont(new Font(35));
                textnotfish.setFill(Color.RED);
                textnotfish.setText("please finish the game!");
                dialogVbox.getChildren().add(textnotfish);
                Scene dialogScene = new Scene(dialogVbox, 400, 300);
                dialog.setScene(dialogScene);
                dialog.show();
                System.out.println("it's not finished yet, continue the game");
            }
        });

        Text textfinish = new Text();
        textfinish.setFont(new Font(30));
        textfinish.setFill(Color.RED);
        textfinish.setX(668);
        textfinish.setY(493);
        textfinish.setText("finish");
        root.getChildren().add(textfinish);

    //  add image clock;
        Image imageclock = new javafx.scene.image.Image( "Sudoku/chronometer.png" );
        gc.drawImage( imageclock, 570, 85,65,65);

    // add copy right image;
        Image imagecopyright = new javafx.scene.image.Image( "Sudoku/copyright.png" );
        gc.drawImage( imagecopyright, 755, 540,220,51.28);


    // add regle de jeu button;
        Button buttonrule = new Button();
        buttonrule.setLayoutX(780);
        buttonrule.setLayoutY(10);
        buttonrule.setMinSize(65,65);
        buttonrule.setMaxSize(100,100);
        buttonrule.setPrefSize(65,65);
        buttonrule.setStyle("-fx-border-color: white;");
        buttonrule.setStyle("-fx-background-color: white");

        Image imagerule = new Image ("Sudoku/règle du jeu.png",65,65,true,true);
        ImageView imageruleView = new ImageView(imagerule);
        buttonrule.setGraphic(imageruleView);
        root.getChildren().add(buttonrule);

        buttonrule.setOnMouseClicked(e -> {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox();
                Image imageofrule = new Image ("Sudoku/règledétailsudoku.png",600,400,true,true);
                ImageView imageofruleview = new ImageView(imageofrule);
                dialogVbox.getChildren().add(imageofruleview);
                Scene dialogScene = new Scene(dialogVbox, 600, 400);
                dialog.setScene(dialogScene);
                dialog.show();
        });

        Text textrule = new Text();
        textrule.setFont(new Font(25));
        textrule.setX(849);
        textrule.setY(52);
        textrule.setText("Règle du jeu");
        root.getChildren().add(textrule);
    }
}
