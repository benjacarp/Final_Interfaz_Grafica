package ui.prestamo;

import exception.DIGAppException;
import exception.FileGenerationException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Prestamo;
import service.CarService;
import service.PrestamoService;
import util.FraInvoiceFactory;
import util.PrestamoInvoiceFactory;

import java.util.Date;

/**
 * Created by ASUS on 17/03/2016.
 */
public class DevolucionDialog extends Stage {

    private final long id;
    private TextField prestamoNumber;
    private Button btnDevolucion;
    private Label lblDate;
    private Date date;

    public DevolucionDialog(long id) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        this.id = id;
        initComponents();
        createStage();
    }

    private void createStage() {
        VBox vBox = new VBox();

        vBox.getChildren().add(new Label("Nro. de Prestamo"));
        vBox.getChildren().add(prestamoNumber);
        vBox.getChildren().add(btnDevolucion);
        vBox.getChildren().add(lblDate);

        Scene scene = new Scene(vBox,300,300);
        scene.getStylesheets().add("/style.css");
        this.setScene(scene);
    }

    private void initComponents() {
        prestamoNumber = new TextField();
        if (id != 0) {
            prestamoNumber.setText(String.valueOf(id));
        }
        btnDevolucion = new Button("Devolver");
        btnDevolucion.setOnAction(event -> devolver());

        lblDate = new Label();
        date = new Date();
        lblDate.setText(date.getDate() + "/" + (date.getMonth()+1) + "/" + (1900 + date.getYear()));
    }

    private void devolver() {
        String number = prestamoNumber.getText();
        Prestamo prestamo = null;
        try {
            prestamo = PrestamoService.getInstance().findOne(Long.valueOf(number));
        } catch (DIGAppException e) {
            e.printStackTrace();
        }

        prestamo.setActive(false);
        prestamo.getCar().setAvailable(true);
        prestamo.setEnd(date);
        prestamo.calcularDias();
        prestamo.calcularTotal();

        try {
            PrestamoService.getInstance().update(prestamo);
            CarService.getInstance().update(prestamo.getCar());
        } catch (DIGAppException e) {
            e.printStackTrace();
        }

        if (prestamo != null) {
            try {
                FraInvoiceFactory.generateInvoice(prestamo);
            } catch (FileGenerationException ex) {
                ex.printStackTrace();
            }
        }

        this.close();

    }
}
