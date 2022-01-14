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


    private Parent createContent() {
        VBox vBox = new VBox();
        GridPane gp = new GridPane();
        Label lblTitle = new Label("Support Ticket");
        Label lblEmail = new Label("Email");
        Label lblPriority = new Label("Priority");
        Label lblProblem = new Label("Problem");
        Label lblDescription = new Label("Description");
        TextField tfEmail = new TextField();
        TextField tfProblem = new TextField();
        TextArea taDescription = new TextArea();
        ObservableList<String> priorities = FXCollections.observableArrayList("Medium", "High", "Low");
        ComboBox<String> cbPriority = new ComboBox<>(priorities);
        Separator sep = new Separator();
        ButtonBar buttonBar = new ButtonBar();
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        buttonBar.setPadding(new Insets(10.0d));
        ButtonBar.setButtonData(saveButton, ButtonBar.ButtonData.OK_DONE);
        ButtonBar.setButtonData(cancelButton, ButtonBar.ButtonData.CANCEL_CLOSE);
        buttonBar.getButtons().addAll(saveButton, cancelButton);

        vBox.getChildren().addAll(gp, sep, buttonBar);

        gp.setPadding(new Insets(10));
        gp.setHgap(4);
        gp.setVgap(8);

        VBox.setVgrow(gp, Priority.ALWAYS);

        gp.add(lblTitle, 1, 1);
        gp.add(lblEmail, 0, 2);     gp.add(tfEmail,1, 2);
        gp.add(lblPriority, 0, 3);  gp.add(cbPriority, 1, 3);
        gp.add(lblProblem, 0, 4);   gp.add(tfProblem, 1, 4);
        gp.add(lblDescription, 0, 5);gp.add(taDescription, 1, 5);

        return vBox;
    }


    @Override
    public void start(Stage stage) throws Exception {
        MainPanel mainPanel = new MainPanel(stage, 999, 700);
        mainPanel.createScene();
    }
}
