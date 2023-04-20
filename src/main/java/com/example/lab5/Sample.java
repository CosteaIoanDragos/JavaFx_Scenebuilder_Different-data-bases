package com.example.lab5;

import com.example.lab5.service.PatientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.lab5.HelloController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Sample extends com.example.lab5.HelloController implements Initializable {
    @FXML
    private Button GenerateRaportsButtonId;

    @FXML
    private Button GoBackButtonId;

    @FXML
    private Label LabelId;

    @FXML
    private TextField raport1Id;

    @FXML
    private TextField raport2Id;

    @FXML
    void GenerateRaportsButtonAction(ActionEvent event) {
        Integer raport1Info= Integer.valueOf(raport1Id.getText());
        Integer raport2Info= Integer.valueOf(raport2Id.getText());
        String AllInfo= String.valueOf(assigments.raport1(raport1Info));
        //assigments.raport1(raport1Info);

        //assigments.raport3();
        assigments.raport4();
        assigments.raport5();
        AllInfo.concat(assigments.raport1(raport1Info));
        LabelId.setText(AllInfo+"\n\n"+String.valueOf(assigments.raport2(raport2Info))+"\n\n"+String.valueOf(assigments.raport3())+"\n\n"+String.valueOf(assigments.raport4())+"\n\n"+String.valueOf(assigments.raport5()));
        System.out.println("here");


    }

    @FXML
    void GoBackButtonAction(ActionEvent event) throws IOException {
        Stage stage= (Stage) GoBackButtonId.getScene().getWindow();
        Parent root= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setTitle("Hello!");
        stage.setScene(new Scene(root));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
