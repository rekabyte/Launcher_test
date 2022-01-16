package ca.rekabit.launcher.ui;

import ca.rekabit.launcher.Main;
import ca.rekabit.launcher.auth.premium.Auth;
import ca.rekabit.launcher.auth.premium.exceptions.AuthenticationUnavailableException;
import ca.rekabit.launcher.auth.premium.exceptions.RequestException;
import ca.rekabit.launcher.auth.premium.responses.AuthenticationResponse;
import com.sun.javafx.util.Utils;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;


public class MainPanel {

    private Stage stage;
    private GridPane layout, topLayout, loginPanel, mainPanel, bottomPanel;
    private double WIDTH, HEIGHT;
    private Label NOM, empty1, empty2;

    public MainPanel(Stage stage, int width, int height) {
        this.stage = stage;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    public void createScene() {
        createTopPanel();
        createLoginPanel();
        stage.show();
    }

    private void createLoginPanel() {
        loginPanel = new GridPane();
        mainPanel = new GridPane();
        bottomPanel = new GridPane();
        AtomicBoolean connectWithMojang = new AtomicBoolean(false);

        loginPanel.setMaxWidth(400);loginPanel.setMinWidth(400);
        loginPanel.setMaxHeight(580);loginPanel.setMinHeight(580);

        GridPane.setVgrow(loginPanel, Priority.ALWAYS);
        GridPane.setHgrow(loginPanel, Priority.ALWAYS);
        GridPane.setHalignment(loginPanel, HPos.CENTER);
        GridPane.setValignment(loginPanel, VPos.CENTER);

        RowConstraints bottomConstraints = new RowConstraints();
        bottomConstraints.setValignment(VPos.BOTTOM);
        bottomConstraints.setMaxHeight(55);
        loginPanel.getRowConstraints().addAll(new RowConstraints(), bottomConstraints);
        loginPanel.add(mainPanel,0,0);
        loginPanel.add(bottomPanel,0,1);

        GridPane.setVgrow(mainPanel, Priority.ALWAYS);
        GridPane.setHgrow(mainPanel, Priority.ALWAYS);
        GridPane.setVgrow(bottomPanel, Priority.ALWAYS);
        GridPane.setHgrow(bottomPanel, Priority.ALWAYS);

        loginPanel.setStyle("-fx-background-color: #181818");
        bottomPanel.setStyle("-fx-background-color: #181818;");
        bottomPanel.setOpacity(0.5);
        Label noAccount = new Label("Vous n'avez pas encore de compte ?");
        Label registerHere = new Label("S'inscire ici");

        GridPane.setVgrow(noAccount, Priority.ALWAYS);
        GridPane.setHgrow(noAccount, Priority.ALWAYS);
        GridPane.setHalignment(noAccount, HPos.CENTER);
        GridPane.setValignment(noAccount, VPos.TOP);
        noAccount.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 14px;");

        GridPane.setVgrow(registerHere, Priority.ALWAYS);
        GridPane.setHgrow(registerHere, Priority.ALWAYS);
        GridPane.setHalignment(registerHere, HPos.CENTER);
        GridPane.setValignment(registerHere, VPos.BOTTOM);
        registerHere.setStyle("-fx-text-fill: #69a7ed; -fx-font-size: 14px;");
        registerHere.setUnderline(true);
        registerHere.setTranslateY(-10);
        registerHere.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        registerHere.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        registerHere.setOnMouseClicked(e-> {
            if(connectWithMojang.get()) openURL("https://www.minecraft.net/fr-fr");
            else openURL("https://www.google.fr");
        });

        bottomPanel.getChildren().addAll(noAccount, registerHere);
        loginPanel.setGridLinesVisible(true);
        layout.add(loginPanel,0,1);

        Label connectLabel = new Label("SE CONNECTER");
        GridPane.setHgrow(connectLabel, Priority.ALWAYS);
        GridPane.setVgrow(connectLabel, Priority.ALWAYS);
        GridPane.setValignment(connectLabel, VPos.TOP);
        connectLabel.setTranslateX(27);
        connectLabel.setTranslateY(37.5);
        connectLabel.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 16px;");

        Separator connectSeparator = new Separator();
        GridPane.setHgrow(connectSeparator, Priority.ALWAYS);
        GridPane.setVgrow(connectSeparator, Priority.ALWAYS);
        GridPane.setValignment(connectSeparator, VPos.TOP);
        GridPane.setHalignment(connectSeparator, HPos.CENTER);
        connectSeparator.setTranslateY(70);
        connectSeparator.setMinWidth(350);connectSeparator.setMaxWidth(350);
        connectSeparator.setStyle("-fx-background-color: white;");
        connectSeparator.setOpacity(0.5);

        Separator usernameSeparator = new Separator();
        GridPane.setHgrow(usernameSeparator, Priority.ALWAYS);
        GridPane.setVgrow(usernameSeparator, Priority.ALWAYS);
        GridPane.setValignment(usernameSeparator, VPos.TOP);
        GridPane.setHalignment(usernameSeparator, HPos.CENTER);
        usernameSeparator.setTranslateY(180);
        usernameSeparator.setMinWidth(325);usernameSeparator.setMaxWidth(325);
        usernameSeparator.setMaxHeight(1);
        usernameSeparator.setStyle("-fx-background-color: white;");
        usernameSeparator.setOpacity(0.5);

        Label usernameLabel = new Label("Nom d'utilisateur");
        GridPane.setHgrow(usernameLabel, Priority.ALWAYS);
        GridPane.setVgrow(usernameLabel, Priority.ALWAYS);
        GridPane.setValignment(usernameLabel, VPos.TOP);
        GridPane.setHalignment(usernameLabel, HPos.LEFT);
        usernameLabel.setStyle("-fx-text-fill: #95bad3; -fx-font-size: 14px;");
        usernameLabel.setTranslateY(110);
        usernameLabel.setTranslateX(37);

        TextField usernameField = new TextField();
        GridPane.setHgrow(usernameField, Priority.ALWAYS);
        GridPane.setVgrow(usernameField, Priority.ALWAYS);
        GridPane.setValignment(usernameField, VPos.TOP);
        GridPane.setHalignment(usernameField, HPos.LEFT);
        usernameField.setMaxWidth(325);
        usernameField.setMaxHeight(40);
        usernameField.setTranslateX(37.5);
        usernameField.setTranslateY(140);
        usernameField.setStyle("-fx-text-fill: #e5e5e5; -fx-font-size: 16px; -fx-background-color: #1e1e1e");

        Separator passwordSeparator = new Separator();
        GridPane.setHgrow(passwordSeparator, Priority.ALWAYS);
        GridPane.setVgrow(passwordSeparator, Priority.ALWAYS);
        GridPane.setValignment(passwordSeparator, VPos.TOP);
        GridPane.setHalignment(passwordSeparator, HPos.CENTER);
        passwordSeparator.setTranslateY(270);
        passwordSeparator.setMinWidth(325);passwordSeparator.setMaxWidth(325);
        passwordSeparator.setMaxHeight(1);
        passwordSeparator.setStyle("-fx-background-color: white;");
        passwordSeparator.setOpacity(0.5);

        Label passwordLabel = new Label("Mot de passe");
        GridPane.setHgrow(passwordLabel, Priority.ALWAYS);
        GridPane.setVgrow(passwordLabel, Priority.ALWAYS);
        GridPane.setValignment(passwordLabel, VPos.TOP);
        GridPane.setHalignment(passwordLabel, HPos.LEFT);
        passwordLabel.setStyle("-fx-text-fill: #95bad3; -fx-font-size: 14px;");
        passwordLabel.setTranslateY(200);
        passwordLabel.setTranslateX(37);

        PasswordField passwordField = new PasswordField();
        GridPane.setHgrow(passwordField, Priority.ALWAYS);
        GridPane.setVgrow(passwordField, Priority.ALWAYS);
        GridPane.setValignment(passwordField, VPos.TOP);
        GridPane.setHalignment(passwordField, HPos.LEFT);
        passwordField.setMaxWidth(325);
        passwordField.setMaxHeight(40);
        passwordField.setTranslateX(37.5);
        passwordField.setTranslateY(230);
        passwordField.setStyle("-fx-text-fill: #e5e5e5; -fx-font-size: 16px; -fx-background-color: #1e1e1e");

        Label forgotPasswordLabel = new Label("Mot de passe oublié ?");
        GridPane.setHgrow(forgotPasswordLabel, Priority.ALWAYS);
        GridPane.setVgrow(forgotPasswordLabel, Priority.ALWAYS);
        GridPane.setValignment(forgotPasswordLabel, VPos.CENTER);
        GridPane.setHalignment(forgotPasswordLabel, HPos.LEFT);
        forgotPasswordLabel.setStyle("-fx-text-fill: #69a7ed; -fx-font-size: 12px;");
        forgotPasswordLabel.setUnderline(true);
        forgotPasswordLabel.setTranslateX(37.5);
        forgotPasswordLabel.setTranslateY(40);
        forgotPasswordLabel.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        forgotPasswordLabel.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        forgotPasswordLabel.setOnMouseClicked(e-> {
            if(connectWithMojang.get()) openURL("https://www.minecraft.net/fr-fr");
            else openURL("https://www.google.ca");
        });

        Button connectionButton = new Button("Se connecter");
        GridPane.setHgrow(connectionButton, Priority.ALWAYS);
        GridPane.setVgrow(connectionButton, Priority.ALWAYS);
        GridPane.setValignment(connectionButton, VPos.CENTER);
        GridPane.setHalignment(connectionButton, HPos.LEFT);
        connectionButton.setTranslateX(37.5);
        connectionButton.setTranslateY(90);
        connectionButton.setMaxWidth(325);connectionButton.setMinWidth(325);
        connectionButton.setMinHeight(50);connectionButton.setMaxHeight(50);
        connectionButton.setStyle("-fx-background-color: #007dbe; -fx-border-radius: 0px;" +
                "-fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: white;");
        connectionButton.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        connectionButton.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        connectionButton.setOnMouseClicked(e-> {
            if(connectWithMojang.get()) {
                try {
                    this.layout.setCursor(Cursor.WAIT);
                    AuthenticationResponse response = Auth.authenticate(usernameField.getText(), passwordField.getText());
                    System.out.println(response.getSelectedProfile().getName());
                    this.layout.setCursor(Cursor.DEFAULT);
                    this.layout.setCursor(Cursor.DEFAULT);

                } catch (RequestException | AuthenticationUnavailableException ex) {
                    System.out.println("No");
                }



            }
            else {
                //TODO MOJANG
            }
        });
        
        Separator chooseConnectSeparator = new Separator();
        GridPane.setHgrow(chooseConnectSeparator, Priority.ALWAYS);
        GridPane.setVgrow(chooseConnectSeparator, Priority.ALWAYS);
        GridPane.setValignment(chooseConnectSeparator, VPos.CENTER);
        GridPane.setHalignment(chooseConnectSeparator, HPos.CENTER);
        chooseConnectSeparator.setTranslateY(160);
        chooseConnectSeparator.setMinWidth(325);chooseConnectSeparator.setMaxWidth(325);
        chooseConnectSeparator.setOpacity(0.3);

        Button chooseButton = new Button("CONNEXION AVEC");
        GridPane.setHgrow(chooseButton, Priority.ALWAYS);
        GridPane.setVgrow(chooseButton, Priority.ALWAYS);
        GridPane.setValignment(chooseButton, VPos.CENTER);
        GridPane.setHalignment(chooseButton, HPos.CENTER);
        chooseButton.setTranslateY(160);
        chooseButton.setStyle("-fx-background-color: #181818; -fx-text-fill: #5e5e5e; -fx-font-size: 14px;");

        Image logoMojang = new Image(Main.class.getResource("/mojangLogo.png").toExternalForm());
        ImageView imageViewMojang = new ImageView(logoMojang);
        imageViewMojang.setFitHeight(25);
        imageViewMojang.setFitWidth(25);

        Image logoServ = new Image(Main.class.getResource("/mojangLogo.png").toExternalForm());
        ImageView imageViewServ = new ImageView(logoServ);
        imageViewServ.setFitHeight(25);
        imageViewServ.setFitWidth(25);

        Button mojangButton = new Button("Mojang");
        GridPane.setHgrow(mojangButton, Priority.ALWAYS);
        GridPane.setVgrow(mojangButton, Priority.ALWAYS);
        GridPane.setValignment(mojangButton, VPos.CENTER);
        GridPane.setHalignment(mojangButton, HPos.LEFT);
        mojangButton.setTranslateX(37.5);
        mojangButton.setTranslateY(210);
        mojangButton.setMinWidth(140);mojangButton.setMaxWidth(140);
        mojangButton.setMinHeight(40);mojangButton.setMaxHeight(40);
        mojangButton.setStyle("-fx-background-color: #34aa2f; -fx-border-radius: 0px; " +
                "-fx-background-insets: 0; -fx-font-size: 14px; -fx-text-fill: white;");
        mojangButton.setGraphic(imageViewMojang);
        mojangButton.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        mojangButton.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        mojangButton.setOnMouseClicked(e-> {
            if(connectWithMojang.get())  {
                connectWithMojang.set(false);
                usernameLabel.setText("Nom d'utilisateur");
                mojangButton.setGraphic(imageViewMojang);
                mojangButton.setText("Mojang");
            }
            else {
                connectWithMojang.set(true);
                usernameLabel.setText("Adresse mail:");
                mojangButton.setGraphic(imageViewServ);
                mojangButton.setText("UdeM-Serveur");
            }
        });



        mainPanel.getChildren().addAll(connectLabel, connectSeparator, usernameLabel, usernameField, usernameSeparator
        ,passwordField, passwordLabel, passwordSeparator, forgotPasswordLabel, connectionButton, chooseConnectSeparator,
                chooseButton, mojangButton);

    }

    private void createTopPanel() {
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

        layout.setStyle("-fx-background-color: #061938;");
        //layout.setStyle(setResponsiveBackground("https://www.umontreal.ca/public/www/images/galerie/3e-niveau/im_campus-mtl.jpg"));
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
    }

    private void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private String setResponsiveBackground(String url) {
        return "-fx-background-image: url('"+ url +"'); -fx-background-repeat: no-repeat; " +
                "-fx-background-attachment: fixed; -fx-background-origin: center; -fx-background-position: center;" +
                "-fx-background-size: cover;";
    }



}
