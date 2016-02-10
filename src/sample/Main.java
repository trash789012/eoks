package sample;

import data_base.query;
import dialogs.information;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.sql.SQLException;

public class Main extends Application {
    Thread Connector;
    @Override
    public void start(Stage primaryStage) throws Exception{

        Connector = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    query.Connection();
                    query.CreateTB("Ares");
                    query.CreateTB("Titan");
                    query.CreateTB("Apollon");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Поиск дел EOKS (БТИ)");
        primaryStage.setScene(new Scene(root));
        Controller.stage = primaryStage;
        primaryStage.setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Connector.start();
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
    }
}
