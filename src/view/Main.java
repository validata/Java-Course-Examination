package view;

import controller.*;
import dbCom.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {
    private Controller controller;
    private DBInsert dbInsert;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Controller();
        final ToggleGroup groupUserOrTeacher = new ToggleGroup();
        primaryStage.setTitle("Welcome!");
        Label labelName = new Label("Name");
        Label labelEmail = new Label("Email *");
        Label labelPassword = new Label("Password *");
        TextField tfEmail = new TextField();
        TextField tfName = new TextField();
        PasswordField passwordField = new PasswordField();
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList("Student", "Teacher", "Admin"));
        choiceBox.getSelectionModel().selectFirst();
        choiceBox.setTooltip(new Tooltip("Select if user or teacher..."));
        Button buttonLogin = new Button("Login");
        Button buttonRegister = new Button("Register");

        EventHandler handlerCoursesShowAll = new EventHandler() {
            @Override
            public void handle(Event event) {
                ArrayList getCoursesAll = controller.getCoursesAll();
                System.out.println(getCoursesAll);

                final Label label = new Label("Selected");
                final ListView<String> listView = new ListView<>();

                // TODO Calculate length of all courses and loop creating obj:
                for (int counter = 0; counter < getCoursesAll.size(); counter++) {
                    System.out.println(getCoursesAll.get(counter));
                }
                String obj1 = "Monday";
                String obj2 = "Tuesday";
                ObservableList<String> list = FXCollections.observableArrayList(
                        obj1,
                        obj2);
                listView.setItems(list);
                listView.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        label.setText("Selected: " +
                                listView.getSelectionModel().getSelectedItems());
                    }
                });
                VBox vBox = new VBox();
                vBox.getChildren().addAll(label, listView);
                StackPane root = new StackPane();
                root.getChildren().add(vBox);
                primaryStage.setScene(new Scene(root, 300, 250));
                primaryStage.show();
            }
        };

        EventHandler handlerCoursesShowMy = new EventHandler() {
            @Override
            public void handle(Event event) {
                String email = "TEST";
                String getCoursesMy = controller.getCoursesMy(email);
                final Label label = new Label("Selected");
                final ListView<String> listView = new ListView<>();

                // TODO Calculate length of all courses and loop creating obj:
                String obj1 = getCoursesMy;
                String obj2 = "Tuesday";
                ObservableList<String> list = FXCollections.observableArrayList(
                        obj1,
                        obj2);
                listView.setItems(list);
                listView.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        label.setText("Selected: " +
                                listView.getSelectionModel().getSelectedItems());
                    }
                });
                VBox vBox = new VBox();
                vBox.getChildren().addAll(label, listView);
                StackPane root = new StackPane();
                root.getChildren().add(vBox);
                primaryStage.setScene(new Scene(root, 300, 250));
                primaryStage.show();
            }
        };

        EventHandler handlerCoursesCreateNew = new EventHandler() {
            @Override
            public void handle(Event event) {
                //String setCoursesRegister = controller.setCoursesCreateNew(email, course);
                //System.out.println(setCoursesRegister);
                Label lab1 = new Label("Here you can register for a course:");
                TextField tf1 = new TextField("11");
                Button butRegister = new Button("Register");
                String course = tf1.getText();
                Controller controller = new Controller();
                butRegister.setOnMousePressed(Event -> controller.setCoursesCreateNew(course));
                GridPane gridPane = new GridPane();
                gridPane.add(lab1, 0, 0);
                gridPane.add(tf1, 0, 1);
                gridPane.add(butRegister, 0, 2);
                Scene registerCoursesScene = new Scene(new Group(gridPane));
                Stage registerCoursesStage = new Stage();
                registerCoursesStage.setScene(registerCoursesScene);
                registerCoursesStage.show();
            }
        };

        EventHandler handlerCoursesSignupStudent = new EventHandler() {
            @Override
            public void handle(Event event) {
                String courseToRegister = "Dummy";
                String email = "Test";
                //String setCoursesRegister = controller.setCoursesRegisterStudent(email, course);
                Label lab1 = new Label("Here you can register for a course:");
                TextField tf1 = new TextField("");
                Button butRegister = new Button("Register");
                //butRegister.setOnMouseClicked(controller.setCoursesRegister(email, courseToRegister));
                GridPane gridPane = new GridPane();
                gridPane.add(lab1, 0, 0);
                gridPane.add(tf1, 0, 1);
                gridPane.add(butRegister, 0, 2);
                Scene registerCoursesScene = new Scene(new Group(gridPane));
                Stage registerCoursesStage = new Stage();
                registerCoursesStage.setScene(registerCoursesScene);
                registerCoursesStage.show();
            }
        };

        EventHandler handlerTryRegister = new EventHandler() {
            @Override
            public void handle(Event event) {
                String name = tfName.getText();
                String email = tfEmail.getText();
                String password = passwordField.getText();
                String studentOrTeacher = (String) choiceBox.getValue();
                boolean isTeacher;
                if (studentOrTeacher.equals("Student")) {
                    isTeacher = false;
                } else {
                    isTeacher = true;
                }
                boolean resultOfRegister = controller.tryRegister(name, email, password, isTeacher);
                if (resultOfRegister) {
                    if (!isTeacher) {
                        Scene registerScene = new Scene(new Group(new Label("Welcome: " + name + ", you are now registered")), 200, 100);
                        Stage loginStage = new Stage();
                        loginStage.setScene(registerScene);
                        loginStage.show();
                    }
                } else {
                    Scene loginSceneFail = new Scene(new Group(new Label("Register failed! User already exists.")), 200, 20);
                    Stage loginStageFail = new Stage();
                    loginStageFail.setScene(loginSceneFail);
                    loginStageFail.show();
                }
            }
        };

        EventHandler handlerTryLogin = new EventHandler() {
            @Override
            public void handle(Event event) {
                String name = tfName.getText();
                String email = tfEmail.getText();
                String password = passwordField.getText();
                String StudentOrTeacher = choiceBox.getValue().toString();
                if (StudentOrTeacher.equals("Student")) {
                    boolean resultOfLoginStudent = controller.tryLogin(email,password,false);
                    if (resultOfLoginStudent) {


                        Label welcomeUser = new Label("Welcome student " + name);
                        Button buttonShowCourses = new Button("Show my courses");
                        Button buttonRegisterCourse = new Button("Register new course");
                        Button buttonShowAllCourses = new Button("Show all courses");
                        buttonShowCourses.setOnMouseClicked(handlerCoursesShowMy);
                        buttonShowAllCourses.setOnMouseClicked(handlerCoursesShowAll);
                        GridPane gridPane = new GridPane();
                        gridPane.add(welcomeUser, 0, 1);
                        gridPane.add(buttonShowCourses, 0, 2);
                        gridPane.add(buttonRegisterCourse, 0, 3);
                        gridPane.add(buttonShowAllCourses, 0, 4);
                        Scene loginSceneUser = new Scene(new Group(gridPane));
                        Stage loginStageUser = new Stage();
                        loginStageUser.setScene(loginSceneUser);
                        loginStageUser.show();
                    }
                    return;
                }
                if (StudentOrTeacher.equals("Teacher")) {
                    boolean resultOfLoginTeacher = controller.tryLogin(email, password, true);
                    if (resultOfLoginTeacher) {

                        Label welcomeTeacher = new Label("Welcome teacher: " + name + "!");
                        Button buttonShowCourses = new Button("Show my courses");
                        Button buttonRegisterCourse = new Button("Register new course");
                        Button buttonShowAllCourses = new Button("Show all courses");
                        buttonShowCourses.setOnMouseClicked(handlerCoursesShowMy);
                        buttonRegisterCourse.setOnMouseClicked(handlerCoursesSignupStudent);
                        buttonShowAllCourses.setOnMouseClicked(handlerCoursesShowAll);
                        GridPane gridPane = new GridPane();
                        gridPane.add(welcomeTeacher, 0, 1);
                        gridPane.add(buttonShowCourses, 0, 2);
                        gridPane.add(buttonRegisterCourse, 0, 3);
                        gridPane.add(buttonShowAllCourses, 0, 4);
                        Scene loginSceneTeacher = new Scene(new Group(gridPane));
                        Stage loginStageTeacher = new Stage();
                        loginStageTeacher.setScene(loginSceneTeacher);
                        loginStageTeacher.show();
                    } else {
                        Scene loginSceneFail = new Scene(new Group(new Label("Login failed!")), 200, 20);
                        Stage loginStageFail = new Stage();
                        loginStageFail.setScene(loginSceneFail);
                        loginStageFail.show();
                        return;
                    }
                    // Create a else to handle failed login, both this and the method for student login.

                    return;
                }
                else if (StudentOrTeacher.equals("Admin") && password.equals("Password")) {
                    Label welcomeAdmin = new Label("Welcome Admin: " + name + "!");
                    Button buttonShowCourses = new Button("Show all courses");
                    Button buttonRegisterCourse = new Button("Register new course");
                    buttonShowCourses.setOnMouseClicked(handlerCoursesShowAll);
                    buttonRegisterCourse.setOnMouseClicked(handlerCoursesCreateNew);
                    GridPane gridpane = new GridPane();
                    //gridpane.add(welcomeAdmin, 0, 1);
                    gridpane.add(buttonRegisterCourse, 0, 2);
                    gridpane.add(buttonShowCourses, 0, 3);
                    gridpane.getRowConstraints().add(new RowConstraints(10));
                    gridpane.getRowConstraints().add(new RowConstraints(20));
                    Scene loginSceneAdmin = new Scene(new Group(gridpane));
                    Stage loginStageAdmin = new Stage();
                    loginStageAdmin.setScene(loginSceneAdmin);
                    loginStageAdmin.show();
                    return;
                } else {
                    Scene loginSceneFail = new Scene(new Group(new Label("Login failed!")), 200, 20);
                    Stage loginStageFail = new Stage();
                    loginStageFail.setScene(loginSceneFail);
                    loginStageFail.show();
                    return;
                }
            }
        };

        GridPane gridpane = new GridPane();
        Scene scene = new Scene(gridpane, Color.DARKKHAKI);
        gridpane.setPrefSize(300, 250);
        gridpane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        gridpane.getRowConstraints().add(new RowConstraints(30));
        gridpane.getRowConstraints().add(new RowConstraints(30));
        gridpane.getRowConstraints().add(new RowConstraints(30));
        gridpane.getRowConstraints().add(new RowConstraints(30));
        gridpane.getRowConstraints().add(new RowConstraints(35));
        gridpane.getRowConstraints().add(new RowConstraints(35));
        gridpane.getRowConstraints().add(new RowConstraints(35));
        gridpane.getColumnConstraints().add(new ColumnConstraints(85));
        gridpane.add(labelName, 0, 1);
        gridpane.add(labelEmail, 0, 2);
        gridpane.add(labelPassword, 0, 3);
        gridpane.add(tfName, 1, 1);
        gridpane.add(tfEmail, 1, 2);
        gridpane.add(passwordField, 1, 3);
        gridpane.add(choiceBox, 1, 4);
        gridpane.add(buttonLogin, 1, 5);
        gridpane.add(buttonRegister, 1, 6);

        buttonLogin.setOnMouseClicked(handlerTryLogin);
        buttonRegister.setOnMouseClicked(handlerTryRegister);

        primaryStage.setScene(scene);
        primaryStage.show();
        }
    }
