package com.example.lab5;

import com.example.lab5.domain.Patient;
import com.example.lab5.repository.BinaryFileRepository;
import com.example.lab5.repository.FileRepository;
import com.example.lab5.repository.JDBC;
import com.example.lab5.repository.PatientRepo;
import com.example.lab5.service.PatientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    private PatientRepo readPropertiesCreateRepo() {

        PatientRepo repo = null;

        Properties properties = new Properties();
        try(InputStream is = new FileInputStream("F:\\Info\\AdvanceProgramming\\lab5\\lab5\\src\\main\\java\\com\\example\\lab5\\settings.properties")) // try-with-resources
        {
            properties.load(is);

            // read repository information from properties and instantiate accordingly
            String repoType = properties.getProperty("Repository");
            String filePath = properties.getProperty("Patients");

            if (repoType.equals("memory")){

                return createDefaultRepo();}

            if (repoType.equals("text"))
                return new FileRepository(filePath);

            if (repoType.equals("binary"))
                return new BinaryFileRepository(filePath);
            if (repoType.equals("database"))
                return crateDatabaseRep();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return createDefaultRepo();
    }
    PatientRepo patients = readPropertiesCreateRepo();
     PatientService assigments=new PatientService(patients);

    @FXML
    private Button AddButtonId;
    @FXML
    private Button RemoveButtonId;

    @FXML
    private TextField addTextId;
    @FXML
    private Button EditButtonId;

    @FXML
    private TextField EditTextId;

    @FXML
    private ListView<Patient> listView;
    private ObservableList<Patient> listaObservabilaPatient=FXCollections.observableArrayList();
    public void  PopulateTable(){
        listaObservabilaPatient.addAll(assigments.getAll());
        listView.setItems(listaObservabilaPatient);
    }
    public  PatientRepo GetControllerRepo(){
        return patients;
    }
    @FXML
    void AddButtonAction() {
       String line=addTextId.getText();
        line.strip();
        if (!line.equals("")){
            String[] parts = line.split(" ");
            String name=parts[0];
            String phoneNr=parts[1];
            Integer id =Integer.parseInt(parts[2]);
            Integer year=Integer.parseInt(parts[3]);
            Integer month=Integer.parseInt(parts[4]);
            Integer day=Integer.parseInt(parts[5]);
            Integer hour=Integer.parseInt(parts[6]);
            Integer minute=Integer.parseInt(parts[7]);
            Patient p=new Patient(name,phoneNr,id,year,month,day,hour,minute);
            assigments.AddAppointmentNoExtraInfo(p);
            listaObservabilaPatient.add(p);
            listView.setItems(listaObservabilaPatient);
            }


    }

    @FXML
    void RemoveThing(MouseEvent event) {
        System.out.println("aicii" +
                "aaaa");
        Patient selectedID=listView.getSelectionModel().getSelectedItem();
        assigments.CancelAppointment(selectedID.getId());
        listaObservabilaPatient.remove(selectedID);
        listView.setItems(listaObservabilaPatient);
    }

    @FXML
    void EditButtonAction(ActionEvent event) {
        Patient selectedID=listView.getSelectionModel().getSelectedItem();
        String line=EditTextId.getText();
        line.strip();
        if (!line.equals("")){
            String[] parts = line.split(" ");
            String name=parts[0];
            String phoneNr=parts[1];
            Integer id =Integer.parseInt(parts[2]);
            Integer year=Integer.parseInt(parts[3]);
            Integer month=Integer.parseInt(parts[4]);
            Integer day=Integer.parseInt(parts[5]);
            Integer hour=Integer.parseInt(parts[6]);
            Integer minute=Integer.parseInt(parts[7]);
            Date date=new Date(year,month,day,hour,minute);
            Patient p=new Patient(name,phoneNr,id,year,month,day,hour,minute);
            assigments.ChangeAppointment(id,name,phoneNr,date);
            listaObservabilaPatient.remove(selectedID);
            listaObservabilaPatient.add(p);
            listView.setItems(listaObservabilaPatient);
        }

    }
    @FXML
    private Button RaportsButtonId;
    @FXML
    void RaportsButtonAction(ActionEvent event) throws IOException {
        Stage stage= (Stage) RaportsButtonId.getScene().getWindow();
        Parent root= FXMLLoader.load(getClass().getResource("Sample.fxml"));
        stage.setTitle("Sample");
        stage.setScene(new Scene(root));
    }

    private PatientRepo crateDatabaseRep() {
        JDBC db_example = new JDBC();
        db_example.openConnection();
        db_example.createSchema();
        //db_example.initTables();

        return db_example;

    }

    private PatientRepo createDefaultRepo() {

        PatientRepo repo=new PatientRepo();
        Patient p1=new Patient("Vlad","3232",1,2222,11,1,32,32);
        Patient p2=new Patient("ioan","3232",2,2222,11,1,32,32);
        Patient p3=new Patient("Constantin","3232",3,2222,11,1,32,32);
        repo.add(p1);
        repo.add(p2);
        repo.add(p3);
        return repo;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PopulateTable();
    }
}