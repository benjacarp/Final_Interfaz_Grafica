package ui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.weathericons.WeatherIcon;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import de.jensd.fx.glyphs.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jiconfont.IconFont;
import jiconfont.icons.FontAwesome;
import jiconfont.javafx.IconFontFX;
import jiconfont.javafx.IconNode;
//import jiconfont.Icons
import ui.utils.UIConstants;


import javax.swing.*;

/**
 * Created by benjamin.salas on 01/03/2016.
 */
public class MainFrame extends Stage{

    private MenuBar menuBar;
    private Menu menuSocio;
    private MenuItem menuItemSocio;

    private TextField textField;
    private Button button;
    private MainController controller;

    public MainFrame() {
        this.controller = new MainController(this);

        initComponents();

        createStage();
    }

    private void createStage() {
        // esto podría ponerse en un metodo reenderizar vistas
        BorderPane layout = new BorderPane();
        layout.getChildren().add(textField);
        layout.getChildren().add(button);
        layout.setTop(menuBar);

        StackPane pane = new StackPane();
        pane.getChildren().add(button);
        layout.setCenter(pane);

        //esto podría ponerse en un metodo setear propiedades
        Scene scene = new Scene(layout, UIConstants.HORIZONTAL_DEFAULT_DIALOG_SIZE,UIConstants.VERTICAL_DEFAULT_DIALOG_SIZE);
        scene.getStylesheets().add("/style.css");
        setScene(scene);
    }

    private void initComponents() {


        menuBar = new MenuBar();
        menuSocio = new Menu("Socios");
        menuItemSocio = new MenuItem("Editar");

        menuBar.getMenus().add(menuSocio);
        menuSocio.getItems().add(menuItemSocio);

        textField = new TextField();

        button = new Button();

//        GlyphsDude.setIcon(button, WeatherIcon.ALIEN, "6em");

        button.setText("Click me!");
        button.setGraphic(addIcon(FontAwesome.FLOPPY_O, UIConstants.ICON_STANDAR_SIZE, Color.WHITE));
        button.setContentDisplay(ContentDisplay.TOP);
//        button = createIconButton(FontAwesomeIcon.STAR, "Nice!", "18.0", "20.0", ContentDisplay.LEFT);
        button.setOnAction(e -> controller.buttonClicked());

        menuItemSocio.setOnAction(e -> controller.buttonClicked());
    }

    private Button createIconButton(GlyphIcons icon, String text, String iconSize, String fontSize, ContentDisplay contentDisplay) {
        Text label = GlyphsDude.createIcon(icon, iconSize);
        Button button = new Button(text);
        button.setStyle("-fx-font-size: " + fontSize);
        button.setGraphic(label);
        button.setContentDisplay(contentDisplay);
        return button;
    }

    public static Node addIcon(FontAwesome floppyO, int size, Color color) {
        IconFontFX.register(FontAwesome.getIconFont());

        IconNode icon = new IconNode(floppyO);
        icon.setIconSize(size);
        icon.setFill(color);
        icon.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        return icon;
    }

    public Button getButton() {
        return button;
    }
}
