package com.r4ppz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    private final Logic logic = new Logic();
    private boolean operationSet = false;

    @FXML
    private TextField mainTextField;

    @FXML
    private void initialize() {
        mainTextField.setEditable(false);
    }

    @FXML
    private void handleNumberAction(ActionEvent event) {
        String number = ((Button) event.getSource()).getText();
        if ("Error".equals(mainTextField.getText())) {
            mainTextField.clear();
        }
        mainTextField.appendText(number);
    }

    @FXML
    private void handleOperationAction(ActionEvent event) {
        if (!operationSet) {
            logic.setOperation(((Button) event.getSource()).getText());
            mainTextField.appendText(" " + logic.getOperation() + " ");
            operationSet = true;
        }
    }

    @FXML
    private void handleEqualsAction() {
        String currentText = mainTextField.getText();
        if (operationSet && currentText.contains(" ")) {
            String[] parts = currentText.split(" ");
            if (parts.length == 3) {
                try {
                    logic.setFirstNumber(Double.parseDouble(parts[0]));
                    logic.setSecondNumber(Double.parseDouble(parts[2]));
                    double result = logic.calculate();
                    mainTextField.setText(currentText + " = " + result);
                    operationSet = false;
                } catch (NumberFormatException e) {
                    mainTextField.setText("Error");
                }
            }
        }

        if (currentText.equals("12312005")) {
            mainTextField.setEditable(true);
            mainTextField.setAlignment(Pos.CENTER);
            mainTextField.setText("Who are you?!");
            System.out.println("Congratulations! You have found the secret number!");
        } else if (currentText.equals("John Rey Rabosa")) {
            mainTextField.setAlignment(Pos.CENTER);
            mainTextField.setText("WELCOME !!!");
            mainTextField.setEditable(false);

        }
    }

    @FXML
    private void handleDotAction() {
        String currentText = mainTextField.getText();

        if (currentText.isEmpty() || currentText.endsWith(" ")) {
            mainTextField.appendText("0.");
        } else if (!currentText.contains(".")) {
            mainTextField.appendText(".");
        }
    }

    @FXML
    private void handleClearAction() {
        mainTextField.clear();
        logic.reset();
        operationSet = false;
    }

    @FXML
    private void handleDeleteAction() {
        String currentText = mainTextField.getText();

        if (!currentText.isEmpty()) {
            mainTextField.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    @FXML
    private void handleToggleSignAction() {
        String currentText = mainTextField.getText();

        if (!currentText.isEmpty()) {
            String[] parts = currentText.split(" ");
            try {
                if (parts.length == 3) {
                    double value2 = Double.parseDouble(parts[2]);
                    value2 = -value2;
                    parts[2] = String.valueOf(value2);
                } else if (parts.length == 1) {
                    double value1 = Double.parseDouble(parts[0]);
                    value1 = -value1;
                    parts[0] = String.valueOf(value1);
                }
                mainTextField.setText(String.join(" ", parts));
            } catch (NumberFormatException e) {
                mainTextField.setText("Error");
            }
        }
    }
}