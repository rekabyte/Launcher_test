package ca.rekabit.launcher;

import ca.rekabit.launcher.ui.MainPanel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class FxApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainPanel mainPanel = new MainPanel(stage, 999, 700);
        mainPanel.createScene();
    }
}
