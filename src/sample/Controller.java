package sample;

import data_base.query;
import dialogs.Err;
import dialogs.information;
import files_Operations.Write;
import find.searchtools;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    @FXML
    public static Stage stage;
    public Button but1;
    public  ListView list1;
    public static ArrayList dir, temp;
    public Label wait;
    public Button butStop;
    public TextField edittext;
    public  ProgressBar bar;
    public Button ConnBut;
    private int CountAres, CountTitan, CountApollon;
    Thread ares, titan, apollon, animBar;
    public static Thread FindAres, FindTitan, FindApollon;
    String s = null;
    public static String ServerName = null;
    public static long count = 0;



    public void DirFind(ActionEvent actionEvent) throws Exception {
        butStop.setDisable(false);
        but1.setDisable(true);
        s = edittext.getText();
        dir = new ArrayList();
        temp = new ArrayList();
        temp.clear();
        dir.clear();
        searchtools searchtools = new searchtools();
        wait.setText("Выполняется поиск");

        animBar = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; true; ) {
                    bar.setProgress(bar.getProgress() + 0.001f);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (bar.getProgress() >= 1.0f) {
                        bar.setProgress(0);
                    }
                }

            }
        });

                    /*ServerName = "Apollon";
                    searchtools.findDirectories("\\\\apollon\\E", edittext.getText());
                    searchtools.findDirectories("\\\\titan\\IMAGE_HDD",edittext.getText());
                    searchtools.findDirectories("\\\\titan\\В ЦОД",edittext.getText());
                    searchtools.findDirectories("\\\\ares\\D", edittext.getText());
                    searchtools.findDirectories("\\\\ares\\E", edittext.getText());
                    searchtools.findDirectories("\\\\ares\\image_hdd", edittext.getText());*/


        new information("Сканирование завершено","Алилуйя!");
        //animBar.start();
    }

    public void StopSearch(ActionEvent actionEvent) {
        FindApollon.stop();
        wait.setText("Stopped");
    }



    private void StoppingInformation() {
        animBar.stop();
        bar.setProgress(0);
        new information("Поиск завершен","" + CountAres + " папок на Ares \n" + CountTitan + " папок на Titan \n" + CountApollon + " папок на Apollon");
        butStop.setDisable(true);
        but1.setDisable(false);
        wait.setText("Поиск завершен!");
        dir.clear();
    }

    public void ClearAll(ActionEvent actionEvent) {
        ArrayList clear = new ArrayList();
        clear.clear();
        ObservableList items1 = FXCollections.observableArrayList(clear);
        list1.setItems(items1);
        dir.clear();
    }

    public void SaveDir(ActionEvent actionEvent) throws IOException {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Сохранить адреса");
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Текстовый документ (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extensionFilter);
            File file = fileChooser.showSaveDialog(stage);

            FileWriter writer = new FileWriter(file, true);

            if (file != null & temp.size() != 0) {
                //File otchet = new File(file.getPath() + ".txt");
                for (int i = 0; i < dir.size(); i++) {
                    // Write.write(file.getPath(), dir.get(i).toString());
                    writer.write(temp.get(i).toString());
                    writer.write("\n");
                }
                writer.close();
            }
        } catch (Exception e) {

        }
    }

    public String[] Storages(String Path) throws IOException, SQLException {
        File file = new File(Path);
        ArrayList<String> s = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        //System.out.println(file.length());
        int i = 0;
        while (scanner.hasNext()){
            s.add(scanner.next());
            i++;
            //query.WriteTB("Apollon", scanner.next());
        }
        for (int j = 0; j < s.size(); j++) {
            query.WriteTB("Titan", s.get(j));
        }
        new information("write complete", "наконец то епрстблин!");
        return null;
    }


    public void read(ActionEvent actionEvent) throws IOException, SQLException {
        Storages("D:\\JAVA\\EOKS\\out\\artifacts\\Find_Inv_jar\\Titan.txt");
    }
}
