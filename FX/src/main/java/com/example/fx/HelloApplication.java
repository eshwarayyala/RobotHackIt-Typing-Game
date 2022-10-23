package com.example.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class HelloApplication extends Application {


    Stage window;
    Scene scene1,scene2, scene3, scene4;






    public static void main(String[] args) throws IOException {

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;




        //Scene 1 Objects
        Label welcomeLabel = new Label("Welcome to Type Racer!");
        Button playButton = new Button("Play!");
        //Button settingsButton = new Button("Settings");

        //Scene 1 Layout
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(welcomeLabel, playButton/*settingsButton*/);


        AtomicReference<String> answer = new AtomicReference<>("");
        //Scene 1 Play button
        playButton.setOnAction(e->{
            window.setScene(scene2);


        });


        //Scene 1 Settings Button
        //settingsButton.setOnAction(e->window.setScene(scene4));



        //Create Scene1
        scene1 = new Scene(layout1,600,300);




        //Scene 2 Objects
        Label welcome = new Label("Your Text: ");
        Label textLabel = new Label();

    Scanner scannerer = new Scanner("src/main/java/com/example/fx/AnswerWord");
        Button resetButton = new Button("reset");
       //char[] chars scannerer;
        char[] chars = "Quick".toCharArray();

        //System.out.println(Arrays.toString(chars));


        Label[] labels = new Label[chars.length];

        for(int i =0; i < chars.length; i++)
        {
            labels[i] = new Label();
            labels[i].setText(String.valueOf(chars[i]));
        }


        //Scene2 Layout
        VBox layout2 = new VBox(20);
        HBox subLayout2 = new HBox(0);
        subLayout2.getChildren().addAll(labels);
        layout2.getChildren().addAll(welcome, textLabel, subLayout2,resetButton);


        //Scene 2 reset button
        resetButton.setOnAction(e->{textLabel.setText(""); for(int i =0; i < chars.length; i++){labels[i].setTextFill(Color.BLACK);};});



        //Create Scene2
        scene2 = new Scene(layout2, 600, 300);


        AtomicInteger counter = new AtomicInteger();






        scene2.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            //if(key.getCode() == KeyCode.ALPHANUMERIC){

            int i = textLabel.getText().length();
            if(i > chars.length-1)
            {

                try {
                    PrintWriter pw = new PrintWriter("src/main/java/com/example/fx/Words");
                    pw.write(String.valueOf(counter));
                    pw.flush();
                    pw.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                textLabel.setText("");
                for(int k = 0; k < chars.length; k++){labels[k].setTextFill(Color.BLACK);}
                window.setScene(scene3);
            }
            else{
                if(key.getCode() == KeyCode.BACK_SPACE)
                {
                    StringBuilder temp = new StringBuilder();
                    temp.append(textLabel.getText());
                    temp.deleteCharAt(temp.length()-1);
                    textLabel.setText(temp.toString());
                }


                if(key.getCode() != KeyCode.SHIFT && key.getCode() != KeyCode.CAPS && key.getText().equals(String.valueOf(chars[i]))) {
                    labels[i].setTextFill(Color.CHOCOLATE);
                    counter.getAndIncrement();
                    System.out.println(counter);


                }
                else{labels[i].setTextFill(Color.AQUA);}

                textLabel.setText(textLabel.getText() + key.getText());


                System.out.println(key.getText());
            }







        //}
    });
        //Create Scene 3
        Label accuracy =  new Label();
        accuracy.setText(String.valueOf(counter));
        Scanner sc = new Scanner(new File("src/main/java/com/example/fx/Words"));


            accuracy.setText(String.valueOf(sc.nextInt()));

        Button butt1 = new Button("Go back to home");
        butt1.setOnAction(e->window.setScene(scene1));
        VBox sp = new VBox(20);
        sp.getChildren().addAll(accuracy,butt1);
        scene3 = new Scene(sp, 600,300);






        /*Create Scene 4
        //change answerLabel
        VBox outerLayout = new VBox(20);


        TextField tf = new TextField();
        tf.setPromptText("Put word in here");
        PrintWriter pw = new PrintWriter("src/main/java/com/example/fx/AnswerWord");

        Button butt2 = new Button();
        butt2.setText("Go Home");
        butt2.setOnAction(e->window.setScene(scene1));
        Button butt3 = new Button("Submit");
        butt3.setOnAction(e->{pw.write(tf.getText());
        pw.flush();
        pw.close();});
        outerLayout.getChildren().addAll(tf, butt3,butt2);

        scene4 = new Scene(outerLayout,600,300);

         */











        window.setScene(scene1);
        window.setTitle("Typing Game");
        window.show();

    }




}