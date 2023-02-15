package ua.goodvice.amo.javaapp;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ua.goodvice.amo.javaapp.Algorithms.Algorithm;
import ua.goodvice.amo.javaapp.Algorithms.BranchingAlgorithm;
import ua.goodvice.amo.javaapp.Algorithms.CyclicAlgorithm;
import ua.goodvice.amo.javaapp.Algorithms.LinearAlgorithm;

public class Controller {
    private Algorithm currentAlgorithmHandler = null;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button closeBtn;

    @FXML
    private Button linearAlgorithmChooseButton;

    @FXML
    private Button branchingAlgorithmBtn;

    @FXML
    private Button cyclicAlgorithmBtn;

    @FXML
    private Button chooseFileToReadBtn;

    @FXML
    private Button readDataBtn;

    @FXML
    private Label algorithmWorkAreaLabel;

    @FXML
    private TextArea dataEntry;

    @FXML
    private Label resultField;

    @FXML
    private ImageView algorithmPreview;

    @FXML
    void initialize() {
        resultField.setVisible(false);
        Alert alert = new Alert(Alert.AlertType.NONE);
        closeBtn.setOnAction(actionEvent -> {
            Platform.exit();
        });

        linearAlgorithmChooseButton.setOnAction(actionEvent -> {
            resultField.setVisible(false);
            dataEntry.clear();
            currentAlgorithmHandler = new LinearAlgorithm();
            chooseFileToReadBtn.setDisable(false);
            dataEntry.setDisable(false);
            readDataBtn.setDisable(false);
            algorithmWorkAreaLabel.setText("Поточний алгоритм: " + currentAlgorithmHandler.getAlgorithmName()
                    + "\nВведіть в поле нижче користувацькі дані у формі b,c");
            algorithmPreview.setImage(new Image(Objects.requireNonNull(getClass()
                    .getResourceAsStream("img/algorithm1.png"))));
        });

        branchingAlgorithmBtn.setOnAction(actionEvent -> {
            resultField.setVisible(false);
            dataEntry.clear();
            currentAlgorithmHandler = new BranchingAlgorithm();
            chooseFileToReadBtn.setDisable(false);
            dataEntry.setDisable(false);
            readDataBtn.setDisable(false);
            algorithmWorkAreaLabel.setText("Поточний алгоритм: " + currentAlgorithmHandler.getAlgorithmName()
                    + "\nВведіть в поле нижче користувацькі дані у формі d,b,k,j,g,f,v,c");
            algorithmPreview.setImage(new Image(Objects.requireNonNull(getClass()
                    .getResourceAsStream("img/algorithm2.png"))));
        });

        cyclicAlgorithmBtn.setOnAction(actionEvent -> {
            resultField.setVisible(false);
            dataEntry.clear();
            currentAlgorithmHandler = new CyclicAlgorithm();
            chooseFileToReadBtn.setDisable(false);
            dataEntry.setDisable(false);
            readDataBtn.setDisable(false);
            algorithmWorkAreaLabel.setText("Поточний алгоритм: " + currentAlgorithmHandler.getAlgorithmName()
                    + "\nВведіть в поле нижче користувацькі дані у формі чотирьох послідовностей з\n50-ти чисел, " +
                    "кожна з нового рядка");
            algorithmPreview.setImage(new Image(Objects.requireNonNull(getClass()
                    .getResourceAsStream("img/algorithm3.png"))));
        });

        readDataBtn.setOnAction(actionEvent -> {
            String userInput = dataEntry.getText();
            if (currentAlgorithmHandler.getClass() == LinearAlgorithm.class) {
                if (userInput.split(",").length != 2) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Увага");
                    alert.setHeaderText("Неправильний ввід");
                    alert.setContentText("Для лінійного алгоритму потрібно ввести 2 значення. Введено: "
                            + userInput.split(",").length);
                    alert.show();
                }
            } else if (currentAlgorithmHandler.getClass() == BranchingAlgorithm.class) {
                if (userInput.split(",").length != 8) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Увага");
                    alert.setHeaderText("Неправильний ввід");
                    alert.setContentText("Для алгоритму, що розгалужується потрібно ввести 8 значень. Введено: "
                            + userInput.split(",").length);
                    alert.show();
                } else {
                    if (currentAlgorithmHandler.calculate(userInput) == -1) {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setTitle("Увага");
                        alert.setHeaderText("Ділення на нуль");
                        alert.setContentText("Під час виконання алгоритму відбулось ділення на нуль, " +
                                "перевірте введені дані");
                        alert.show();
                        return;
                    } else if (currentAlgorithmHandler.calculate(userInput) == -2) {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setTitle("Увага");
                        alert.setHeaderText("Підкореневий вираз менший нуля");
                        alert.setContentText("Під час виконання алгоритму підкореневий вираз є меншим нуля. " +
                                "Перевірте введені дані");
                        alert.show();
                        return;
                    }
                }
            } else if (currentAlgorithmHandler.getClass() == CyclicAlgorithm.class) {
                String[] arrayOfStrArrays = userInput.split("\n");
                int numberOfStrArrays = arrayOfStrArrays.length;
                if (numberOfStrArrays != 4) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Увага");
                    alert.setHeaderText("Неправильний ввід");
                    alert.setContentText("Для циклічного алгоритму потрібно ввести 4 масива значень по 50 елементів " +
                            "в кожному через кому без пробілів. При цьому кожний масив повинен бути введений з нового рядка.\nВведено: "
                            + numberOfStrArrays + " масив(-и/-ів)");
                    alert.show();
                    return;
                }
                boolean isOkay = true;
                for (String currString : arrayOfStrArrays) {
                    String[] currArray = currString.split(",");
                    if (currArray.length != 50) {
                        isOkay = false;
                        break;
                    }
                }

                if (!isOkay) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setTitle("Увага");
                    alert.setHeaderText("Неправильний ввід");
                    alert.setContentText("Для циклічного алгоритму потрібно ввести 4 масива значень по 50 елементів " +
                            "в кожному через кому без пробілів. При цьому кожний масив повинен бути введений з нового " +
                            "рядка.\nВ одному з масивів кількість елементів не дорівнює 50");
                    alert.show();
                }
            }
            resultField.setText(Double.toString(currentAlgorithmHandler.calculate(userInput)));
            resultField.setVisible(true);
        });

        chooseFileToReadBtn.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose file to read data");
            File choosedFile = fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
            StringBuilder userInput = new StringBuilder();
            try (FileReader fr = new FileReader(choosedFile)) {
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    userInput.append(line).append("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            resultField.setText(Double.toString(currentAlgorithmHandler.calculate(userInput.toString())));
            resultField.setVisible(true);
        });
    }

}
