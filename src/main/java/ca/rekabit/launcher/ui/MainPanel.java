package ca.rekabit.launcher.ui;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainPanel {

    private Stage stage;
    private GridPane layout, topLayout;
    private double WIDTH, HEIGHT;
    private Label NOM, empty1, empty2;

    public MainPanel(Stage stage, int width, int height) {
        this.stage = stage;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    public void createScene() {
        RowConstraints topControlsConstraints = new RowConstraints();
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        ColumnConstraints col4 = new ColumnConstraints();
        ColumnConstraints col5 = new ColumnConstraints();
        MaterialDesignIconView close = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_CLOSE);
        MaterialDesignIconView hide = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_MINIMIZE);
        this.NOM = new Label("UdeM Launcher");
        this.empty1 = new Label();
        this.empty2 = new Label();
        layout = new GridPane();
        topLayout = new GridPane();

        stage.setScene(new Scene(layout, this.WIDTH, this.HEIGHT));
        stage.setTitle("UdeM Launcher");
        stage.setMinWidth(this.WIDTH); stage.setMinHeight(this.HEIGHT);
        stage.setMaxWidth(this.WIDTH); stage.setMaxHeight(this.HEIGHT);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();

        topControlsConstraints.setValignment(VPos.TOP);
        topControlsConstraints.setMinHeight(25);
        topControlsConstraints.setMaxHeight(25);

        close.setFill(Color.WHITE);
        close.setOpacity(0.70f);
        close.setSize("18.0px");
        close.setOnMouseEntered(e -> close.setOpacity(1.0f));
        close.setOnMouseExited(e -> close.setOpacity(0.70f));
        close.setOnMousePressed(e -> System.exit(0));
        hide.setFill(Color.WHITE);
        hide.setOpacity(0.70f);
        hide.setSize("18.0px");
        hide.setOnMouseEntered(e -> hide.setOpacity(1.0f));
        hide.setOnMouseExited(e -> hide.setOpacity(0.70f));
        hide.setOnMousePressed(e -> stage.setIconified(true));

        col1.setMaxWidth(this.WIDTH / 3);col1.setMinWidth(this.WIDTH / 3);
        col2.setMaxWidth(this.WIDTH / 3);col2.setMinWidth(this.WIDTH / 3);
        col3.setMaxWidth(277);col3.setMinWidth(277);
        col4.setMaxWidth(28);col4.setMinWidth(28);
        col5.setMaxWidth(28);col5.setMinWidth(28);

        this.NOM.setStyle("-fx-font-weight: bolder; -fx-font-size: 16px;");
        this.NOM.setTextFill(Color.WHITE);
        this.NOM.setOpacity(0.7f);
        this.NOM.setOnMouseEntered(e -> this.NOM.setOpacity(1.0f));
        this.NOM.setOnMouseExited(e -> this.NOM.setOpacity(0.7f));

        layout.getRowConstraints().addAll(topControlsConstraints, new RowConstraints());

        //layout.setStyle("-fx-background-color: #061938;");
        layout.setStyle(setResponsiveBackground("https://www.umontreal.ca/public/www/images/galerie/3e-niveau/im_campus-mtl.jpg"));
        layout.add(topLayout, 0,0);

        topLayout.getColumnConstraints().addAll(col1,col2, col3, col4, col5);
        topLayout.setStyle("-fx-background-color: rgb(31,35,37)");
        topLayout.add(empty1, 0, 0);
        topLayout.add(this.NOM, 1, 0);
        topLayout.add(empty2, 2, 0);
        topLayout.add(hide, 3, 0);
        topLayout.add(close, 4, 0);

        topLayout.setOnMousePressed(pressEvent -> {
            topLayout.setOnMouseDragged(dragEvent -> {
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });

        GridPane.setHalignment(this.NOM, HPos.CENTER);
        GridPane.setHalignment(hide, HPos.RIGHT);
        GridPane.setHalignment(close, HPos.CENTER);

        stage.show();
    }

    private String setResponsiveBackground(String url) {
        return "-fx-background-image: url('"+ url +"'); -fx-background-repeat: no-repeat; " +
                "-fx-background-attachment: fixed; -fx-background-origin: center; -fx-background-position: center;" +
                "-fx-background-size: cover;";
    }

}
