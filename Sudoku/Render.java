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
    int icoordx = 0;
    int icoordy = 0;
    GraphicsContext gc;
//    public void setNewstage(Stage newstage) throws Exception {
//         newstage.show("Hello World");
//    }
//        public  void setText (double x, double y, String text)
//        {
//            for (x = 50; x <=450; x = x +50) {
//            for (y = 50; y <= 450; y = y +50) {
//            text = "0";
//                 }
//              }
//            }

    Grille jeu = new Grille();

    private void draw(Group root) {
        for (int x = 50; x <= 450; x = x + 50) {
            for (int y = 130; y <= 530; y = y + 50) {
                Text test = new Text();
                test.setFont(new Font(50));
                test.setX(x);
                test.setY(y);
                test.setText(jeu.grilleCase[(x - 50) / 50][(y - 130) / 50].getCaseSudoku() + "");
                root.getChildren().add(test);
            }
        }
    }
    private void eraseNumber(Group root){

        for (int Rsx = 40; Rsx <= 440; Rsx = Rsx +50) {
            for (int  Rsy = 88; Rsy <= 488; Rsy = Rsy +50)
                gc.strokeRect(Rsx,Rsy,50,50);
            Rectangle rectanglenumber = new Rectangle((icoordx*50+40)+4, (icoordy*50+90)+2, 44, 40);
           rectanglenumber.setFill(Color.WHITE);
            gc.setFill(Color.WHITE);
            gc.fillRect((icoordx*50+40),(icoordy*50+90),44,44);
//            gc.setFill(Color.BLACK);
//            gc.strokeRect((icoordx*50+40),(icoordy*50+90),49,49);
            root.getChildren().add(rectanglenumber);
        }

    }
    @Override
    public void start(Stage stage) throws Exception {
        // create a group object
        Group root = new Group();

        // create a scene by passing the group object, height and width
        int width = 1000;
        int height = 600;
        Scene scene = new Scene(root,width, height);
        jeu.changeValue(0,0,4);
        jeu.changeValue(0,2,6);
        // Color, title of the scene
        scene.setFill(Color.WHITE);
        stage.setTitle("Solveur de Sudoku");
        draw(root);

        // Add test group
//        for (XYChart)
//        class Text {
//            static private String s;
//            private int x;
//            private int y;
//            static private int size;
//            static private Color c;
//
//            public Text(String s, int x, int y, int size, Color c) {
//                this.s = s;
//                this.x = x;
//                this.y = y;
//                this.size = size;
//                this.c = c;
//            }
//

//        }
//        Text text = new Text("hhhhhh",5,6,100,Color.RED);
//        root.getChildren().add(text);
//        public void fillText;() {
//            int x;
//            int y;
//
//        }
        // Add text
//        Text text = new Text();
//        text.setFont(new Font(50));
//        text.setX(40);
//        text.setY(50);
//        text.setText("0");
//        root.getChildren().add(text);

        // Add label
//        Label label = new Label("Allo");
//        root.getChildren().add(label);

        // Add Scene to stage
        stage.setScene(scene);

        // show Scene
        stage.show();


        // Canvas

//        Scene theScene = new Scene(root);
//        stage.setScene(scene);
        Canvas canvas = new Canvas(1000, 600);
        root.getChildren().add(canvas);
         gc = canvas.getGraphicsContext2D();

        // manipulation of image
//        gc.setFill( Color.BLUE );
//        gc.setStroke( Color.BLACK );
//        gc.setLineWidth(2);
//        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
//        gc.setFont( theFont );
//        gc.fillText( "Hello, World!", 40, 50 );
//        gc.strokeText( "Hello, World!", 40, 50 );
//        Image earth = new javafx.scene.image.Image( "Sudoku/Earth.png" );
//        gc.drawImage( earth, 2, 2);
////         drawImage (Image, x, y, heuteur, longueur);

//       add bouton clavier 1 -9;
            int count =0;
           for (int buttony = 200; buttony <= 340; buttony = buttony +70) {
               for (int buttonx = 560; buttonx <= 700; buttonx = buttonx +70) {
                   count = count +1;
                   Button button = new Button(count+"");
//                   button.setText("count");
                   Font font = Font.font("count", FontWeight.BLACK, 36);
                   button.setFont(font);
                   button.setLayoutX(buttonx);
                   button.setLayoutY(buttony);
                   button.setMinWidth(70);
                   button.setMaxWidth(100);
                   button.setPrefWidth(50);

                   button.setMinHeight(70);
                   button.setMaxHeight(100);
                   button.setPrefHeight(50);

                   button.setMinSize(70,70);
                   button.setMaxSize(100,100);
                   button.setPrefSize(70,70);
                   button.setStyle("-fx-border-color: black;");
//                   button.setStyle("-fx-background-color: white");
                   root.getChildren().add(button);
                   int finalCount = count;
                   button.setOnMouseClicked(e->{
                       System.out.println("change at i ="+icoordx+" and j = "+icoordy+" "+finalCount);
                       eraseNumber(root);
                       jeu.changeValue(icoordx,icoordy,finalCount);
                       draw(root);
                       jeu.changeValue(icoordx,icoordy,finalCount);
                   });
//
//                   button.setOnAction(new EventHandler<ActionEvent>() {
//                       @Override
//                       public void handle(ActionEvent actionEvent) {
//                           if (actionEvent.getSource() == button) {
//
//                           }
//
//                       }
//                   });
               }
           }
//            for (int Rsx = 560; Rsx <= 700; Rsx = Rsx +70) {
//                for (int Rsy = 200; Rsy <= 340; Rsy = Rsy +70)
//                    gc.strokeRect(Rsx,Rsy,70,70);
//            }

            /*Button button = new Button();
//            Font font = new Font(25); //Button font's size should increase to 40
//            button.setFont(font);
            button.setText("1");
            Font font = Font.font("1", FontWeight.BLACK, 36);
            button.setFont(font);
//            button.setVisible(false);
//            button.setBorder();

//            button.setFont(font);
            button.setLayoutX(560);
            button.setLayoutY(200);
            button.setMinWidth(70);
            button.setMaxWidth(100);
            button.setPrefWidth(50);

            button.setMinHeight(70);
            button.setMaxHeight(100);
            button.setPrefHeight(50);

            button.setMinSize(70,70);
            button.setMaxSize(100,100);
            button.setPrefSize(70,70);
            button.setStyle("-fx-border-color: black;");
//            button.setStyle("-fx-background-color: white");

            Image image01 = new Image ("Sudoku/Numero1.png",70,70,true,true);
            ImageView image01View = new ImageView(image01);
//            button.setGraphic(image01View);
            root.getChildren().add(button);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("pause");
                }
            });
*/
            // draw keyboard rectangle



            // draw rectangle
//        Rectangle2D s1 = new Rectangle2D(10, 10,20,30);
//          gc.strokeRect(50, 100,450,450);
//          gc.setFill(Color.BLACK);
//          gc.fillRect(100,100,100,100);
//
//    }
//            Rectangle2D rectangle11 = new Rectangle2D(50,130,150,150);
//            for (int Rbx =40; Rbx <= 340; Rbx = Rbx +150) {
//                for (int Rby = 88; Rby <=388; Rby = Rby +150) {
////                    Rectangle2D bigRectangele = new Rectangle2D(Rbx,Rby,150,150);
//
//                    gc.strokeRect(Rbx,Rby,150,150);
////                    gc.strokeRect().setStyle("-fx-fill: red; -fx-border-style: solid; -fx-border-width: 5; -fx-border-color: black;");
//                }
//            }
            /** add mouse */
            scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEventpositon) {
                    if (mouseEventpositon.getSceneX() <= 490 && mouseEventpositon.getSceneX() >= 40
                    && mouseEventpositon.getSceneY() <= 540 && mouseEventpositon.getSceneY() >= 90) {


                    double coordx = mouseEventpositon.getSceneX();
                    double coordy = mouseEventpositon.getSceneY();
                    icoordx = (int) (coordx-40)/50;
                    icoordy = (int) (coordy-90)/50;
                    System.out.println(icoordx +"  "+ icoordy);}

                }
            });


            /** add keyboard */
            scene.setOnKeyPressed(e->{
                System.out.println(e.getCode());});


            for (int Rsx = 40; Rsx <= 440; Rsx = Rsx +50) {
                for (int Rsy = 88; Rsy <= 488; Rsy = Rsy +50)
                    gc.strokeRect(Rsx,Rsy,50,50);
            }

//            gc.strokeRect(40,88,150,150);
//            gc.strokeRect(190,88,150,150);

            // drawlineX
            for (int Ly = 88; Ly <= 538; Ly = Ly +150) {
                Line blackLine = new Line();
                blackLine.setStartX(40);
                blackLine.setStartY(Ly);
                blackLine.setEndX(490);
                blackLine.setEndY(Ly);
                blackLine.setFill(Color.BLACK);
                blackLine.setStrokeWidth(5);
                root.getChildren().add(blackLine);
            }
            // drawliney
            for (int Lx = 40; Lx <= 490; Lx = Lx +150) {
                Line blackLine = new Line();
                blackLine.setStartX(Lx);
                blackLine.setStartY(88);
                blackLine.setEndX(Lx);
                blackLine.setEndY(538);
                blackLine.setFill(Color.BLACK);
                blackLine.setStrokeWidth(5);
                root.getChildren().add(blackLine);
            }

            // niveau
            Image niveauimg = new javafx.scene.image.Image( "Sudoku/niveau.png" );
            gc.drawImage( niveauimg, 70, 18,100,45);
            Image niveaufacile = new javafx.scene.image.Image( "Sudoku/facile.png" );
            gc.drawImage( niveaufacile, 175, 20.5,80,40);

            // autocheck mode
            Image autocheckmode = new javafx.scene.image.Image( "Sudoku/autocheck mode.png" );
            gc.drawImage( autocheckmode, 350, 15,225,46);
            Image autocheckmodoff = new javafx.scene.image.Image( "Sudoku/autocheck mode off.png" );
            gc.drawImage( autocheckmodoff, 600, 18,106,41.7);


            // prise des notes
            Button buttonnotes = new Button();
//            Font font = new Font(25); //Button font's size should increase to 40
//            button.setFont(font);
//            button.setText("1");
//            Font font = Font.font("1", FontWeight.BLACK, 36);
//            button.setFont(font);
//            button.setVisible(false);
//            button.setBorder();

//            button.setFont(font);
            buttonnotes.setLayoutX(800);
            buttonnotes.setLayoutY(95);
            buttonnotes.setMinWidth(55);
            buttonnotes.setMaxWidth(100);
            buttonnotes.setPrefWidth(50);

            buttonnotes.setMinHeight(55);
            buttonnotes.setMaxHeight(100);
            buttonnotes.setPrefHeight(50);

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

            //  add button reset;
            Button buttonreset = new Button();
            buttonreset.setLayoutX(820);
            buttonreset.setLayoutY(180);
            buttonreset.setMinWidth(55);
            buttonreset.setMaxWidth(100);
            buttonreset.setPrefWidth(50);

            buttonreset.setMinHeight(55);
            buttonreset.setMaxHeight(100);
            buttonreset.setPrefHeight(50);

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
            buttonpause.setMinWidth(55);
            buttonpause.setMaxWidth(100);
            buttonpause.setPrefWidth(50);

            buttonpause.setMinHeight(55);
            buttonpause.setMaxHeight(100);
            buttonpause.setPrefHeight(50);

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
            buttonexit.setMinWidth(55);
            buttonexit.setMaxWidth(100);
            buttonexit.setPrefWidth(50);

            buttonexit.setMinHeight(55);
            buttonexit.setMaxHeight(100);
            buttonexit.setPrefHeight(50);

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


            // add finish
            //  add button reset;
            Button buttonfinish = new Button();
            buttonfinish.setLayoutX(600);
            buttonfinish.setLayoutY(450);
            buttonfinish.setMinWidth(65);
            buttonfinish.setMaxWidth(100);
            buttonfinish.setPrefWidth(65);

            buttonfinish.setMinHeight(65);
            buttonfinish.setMaxHeight(100);
            buttonfinish.setPrefHeight(65);

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



            //  add clock;
            Image imageclock = new javafx.scene.image.Image( "Sudoku/chronometer.png" );
            gc.drawImage( imageclock, 570, 85,65,65);
            Timer timerclock = new Timer();

            // add copy right
            Image imagecopyright = new javafx.scene.image.Image( "Sudoku/copyright.png" );
            gc.drawImage( imagecopyright, 755, 540,220,51.28);


            // add regle de jeu
            Button buttonrule = new Button();
            buttonrule.setLayoutX(780);
            buttonrule.setLayoutY(10);
            buttonrule.setMinWidth(65);
            buttonrule.setMaxWidth(100);
            buttonrule.setPrefWidth(65);

            buttonrule.setMinHeight(65);
            buttonrule.setMaxHeight(100);
            buttonrule.setPrefHeight(65);

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
