package Sudoku;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.*;
import javax.print.attribute.ResolutionSyntax;
import java.awt.*;
import java.util.Timer;

public class Render extends Application {
    int icoordx = 0; // define the coordinate x which will be converted between 0 and 8;
    int icoordy = 0; // define the coordinate x which will be converted between 0 and 8;
    GraphicsContext gc; // add Graphics Context in canvas
    Grille newgame = new Grille(); // create a new game Grille

    // get value from getCaseSudoku: initially the value is 0 for all the cases;
    private void draw(Group root) {
        for (int x = 50; x <= 450; x = x + 50) {
            for (int y = 130; y <= 530; y = y + 50) {
                Text casetext = new Text();
                casetext.setFont(new Font(40));
                casetext.setX(x+5);
                casetext.setY(y-1);
                casetext.setText(newgame.grilleCase[(x - 50) / 50][(y - 130) / 50].getCaseSudoku() + "");
                root.getChildren().add(casetext);
            }
        }
    }

    // cover the value of a selected case with a white rectangle;
    private void eraseNumber(Group root){
        for (int Rsx = 40; Rsx <= 440; Rsx = Rsx +50) {
            for (int  Rsy = 88; Rsy <= 488; Rsy = Rsy +50)
                gc.strokeRect(Rsx,Rsy,50,50);
                Rectangle rectanglenumber = new Rectangle((icoordx*50+40)+4, (icoordy*50+90)+2, 41, 43);
                rectanglenumber.setFill(Color.WHITE);
                root.getChildren().add(rectanglenumber);
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

        // change some values in the cases of the newgame grille;
        newgame.changeValue(0,0,4);
        newgame.changeValue(0,2,6);

        // Color, title of the scene
        scene.setFill(Color.WHITE);
        stage.setTitle("Solveur de Sudoku");
        draw(root);

        // Add Scene to stage
        stage.setScene(scene);

        // show Scene
        stage.show();

        //  add Canvas
        Canvas canvas = new Canvas(1000, 600);
        root.getChildren().add(canvas);
         gc = canvas.getGraphicsContext2D();

         // add 9x9 grille;
        for (int Rsx = 40; Rsx <= 440; Rsx = Rsx +50) { //draw rectangle;
            for (int Rsy = 88; Rsy <= 488; Rsy = Rsy +50)
                gc.strokeRect(Rsx,Rsy,50,50);
        }
        for (int Ly = 88; Ly <= 538; Ly = Ly +150) { //draw bold row line
            Line blackLine = new Line();
            blackLine.setStartX(40);
            blackLine.setStartY(Ly);
            blackLine.setEndX(490);
            blackLine.setEndY(Ly);
            blackLine.setFill(Color.BLACK);
            blackLine.setStrokeWidth(5);
            root.getChildren().add(blackLine);
        }
        for (int Lx = 40; Lx <= 490; Lx = Lx +150) { // draw bold column line
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
                //      button.setStyle("-fx-background-color: white");
                root.getChildren().add(button);
                int finalCount = count;
                button.setOnMouseClicked(e -> {
                    // check the rule checkrow, checkcolumn and chekcolum;
                    if (newgame.checkrow(icoordx, icoordy, finalCount)
                            && (newgame.checkcolumn(icoordx, icoordy, finalCount))
                            && (newgame.checksquare(icoordx, icoordy, finalCount))) {
                        eraseNumber(root);
                        newgame.changeValue(icoordx, icoordy, finalCount);
                        draw(root);
                        // show the position of the changed case and the input value;
                        System.out.println("clic at the position [" + icoordx + "]" + "[" + icoordy + "]" + ", the input value is:  " + finalCount);
                    } else {
                        System.out.println("position at [" + icoordx + "]" + "[" + icoordy + "]" + ", can't change value,please check the rule");
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
                    System.out.println("clic at the position ["+icoordx + "]" + "["+icoordy+"]");}
                }
            });

        // add keyboard;
        scene.setOnKeyPressed(e->{
           System.out.println(e.getCode());});

        // add image niveau and facile;
        Image niveauimg = new javafx.scene.image.Image( "Sudoku/niveau.png" );
        gc.drawImage( niveauimg, 70, 18,100,45);
        Image niveaufacile = new javafx.scene.image.Image( "Sudoku/facile.png" );
        gc.drawImage( niveaufacile, 175, 20.5,80,40);

        // add image autocheck mode
        Image autocheckmode = new javafx.scene.image.Image( "Sudoku/autocheck mode.png" );
        gc.drawImage( autocheckmode, 350, 15,225,46);
        Image autocheckmodoff = new javafx.scene.image.Image( "Sudoku/autocheck mode off.png" );
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

        Text textrest = new Text();
        textrest.setFont(new Font(30));
        textrest.setX(880);
        textrest.setY(218);
        textrest.setText("reset");
        root.getChildren().add(textrest);

        //  add button pause;
        Button buttonpause = new Button();
        buttonpause.setLayoutX(820);
        buttonpause.setLayoutY(270);
        buttonpause.setMinSize(55,55);
        buttonpause.setMaxSize(100,100);
        buttonpause.setPrefSize(55,55);
        buttonpause.setStyle("-fx-border-color: white;");
        buttonpause.setStyle("-fx-background-color: white");

        Image imagepause = new Image ("Sudoku/pause.png",60,60,true,true);
        ImageView imagepauseView = new ImageView(imagepause);
        buttonpause.setGraphic(imagepauseView);
        root.getChildren().add(buttonpause);

        Text textpause = new Text();
        textpause.setFont(new Font(30));
        textpause.setX(880);
        textpause.setY(307);
        textpause.setText("pause");
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
//        Timer timerclock = new Timer();

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

        Text textrule = new Text();
        textrule.setFont(new Font(25));
        textrule.setX(849);
        textrule.setY(52);
        textrule.setText("Règle du jeu");
        root.getChildren().add(textrule);
    }
}
