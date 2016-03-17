package ui.main;

import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jiconfont.icons.FontAwesome;
import jiconfont.javafx.IconFontFX;
import jiconfont.javafx.IconNode;
//import jiconfont.Icons
import ui.utils.UIConstants;

/**
 * Created by benjamin.salas on 01/03/2016.
 */
public class MainFrame extends Stage{

    private MenuBar menuBar;
    private Menu menuFile;
    private Menu menuPrestamos;
    private MenuItem menuItemSocios;
    private MenuItem menuItemCars;
    private MenuItem menuItemNewPrestamo;
    private MenuItem menuItemPrestamos;
    private MenuItem menuItemDevolucion;

    private MainController controller;

    public MainFrame() {
        this.controller = new MainController(this);

        initComponents();

        createStage();
    }

    private void createStage() {
        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);

        Scene scene = new Scene(layout, UIConstants.HORIZONTAL_DEFAULT_DIALOG_SIZE,UIConstants.VERTICAL_DEFAULT_DIALOG_SIZE);
        scene.getStylesheets().add("/style.css");
        setScene(scene);
    }

    private void initComponents() {

        menuBar = new MenuBar();
        menuFile = new Menu("Archivo");
        menuPrestamos = new Menu("Prestamos");
        menuItemSocios = new MenuItem("Socios...");
        menuItemCars = new MenuItem("Autos...");
        menuItemDevolucion = new MenuItem("Devolucion...");
        menuItemPrestamos = new MenuItem("Ver Prestamos...");
        menuItemNewPrestamo = new MenuItem("Nuevo Prestamo");

        menuBar.getMenus().add(menuFile);
        menuBar.getMenus().add(menuPrestamos);
        menuFile.getItems().add(menuItemSocios);
        menuFile.getItems().add(menuItemCars);
        menuPrestamos.getItems().add(menuItemNewPrestamo);
        menuPrestamos.getItems().add(menuItemDevolucion);
        menuPrestamos.getItems().add(menuItemPrestamos);


        menuItemSocios.setGraphic(addIcon(FontAwesome.USER, UIConstants.ICON_STANDAR_SIZE, Color.BLACK));
        menuItemSocios.setOnAction(e -> controller.socioClick());
        menuItemCars.setGraphic(addIcon(FontAwesome.CAR, UIConstants.ICON_STANDAR_SIZE, Color.BLACK));
        menuItemCars.setOnAction(e -> controller.carClick());

        menuItemNewPrestamo.setGraphic(addIcon(FontAwesome.HOURGLASS_START, UIConstants.ICON_STANDAR_SIZE, Color.BLACK));
        menuItemNewPrestamo.setOnAction(event -> controller.nuevoPrestamoClick());
        menuItemDevolucion.setGraphic(addIcon(FontAwesome.HOURGLASS_END, UIConstants.ICON_STANDAR_SIZE, Color.BLACK));
        menuItemDevolucion.setOnAction(event -> controller.devolucionClick());
        menuItemPrestamos.setGraphic(addIcon(FontAwesome.HISTORY, UIConstants.ICON_STANDAR_SIZE, Color.BLACK));
        menuItemPrestamos.setOnAction(event -> controller.prestamoClick());
    }

    public static Node addIcon(FontAwesome floppyO, int size, Color color) {
        IconFontFX.register(FontAwesome.getIconFont());

        IconNode icon = new IconNode(floppyO);
        icon.setIconSize(size);
        icon.setFill(color);
        icon.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        return icon;
    }
}
