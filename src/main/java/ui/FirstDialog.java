package ui;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.scene.control.agenda.Agenda;

import java.time.LocalDate;

/**
 * Created by benjamin.salas on 01/03/2016.
 */
public class FirstDialog extends Stage {

    private ListView dia;
    private Agenda agenda;

    public FirstDialog() {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        initComponents();

        createStage();
    }

    private void createStage() {
        StackPane layout = new StackPane();
        layout.getChildren().add(dia);

        agenda = new Agenda();
        agenda.appointments().addAll(
                new Agenda.AppointmentImplLocal()
                        .withStartLocalDateTime(LocalDate.now().atTime(4, 00))
                        .withEndLocalDateTime(LocalDate.now().atTime(15, 30))
                        .withDescription("It's time")
                        .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
        );

        Scene scene = new Scene(agenda,200,250);
        scene.getStylesheets().add("/style.css");   //la ruta tambien podría sacarse a una constante
        setScene(scene);
    }

    private void initComponents() {
        dia = new ListView();
        dia.getItems().addAll(true,false,false,true,true);
    }
}
