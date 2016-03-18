import config.HibernateUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.main.MainFrame;

/**
 * Created by benjamin.salas on 01/03/2016.
 */
public class App extends Application {

    public static void main(String[] args) {
        System.out.println("Interfaz");
        HibernateUtil.init();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new MainFrame();
        primaryStage.setOnCloseRequest(event -> close());
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    private void close() {
        try {
            HibernateUtil.closeHibernateContext();
            this.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
