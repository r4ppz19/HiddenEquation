package com.r4ppz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {

    private final MainModel mainModel = new MainModel();
    private boolean operationSet = false;


    @FXML
    private TextField mainTextField;


    @FXML
    private void initialize() {
        mainTextField.setEditable(false);
    }

    @FXML
    private void handleNumberAction(ActionEvent event) {
        // Get the text of the button that was clicked
        String number = ((Button) event.getSource()).getText();

        // If the text field currently displays "Error", clear it
        if ("Error".equals(mainTextField.getText())) {
            mainTextField.clear();
        }

        // Append the number from the button to the text field
        mainTextField.appendText(number);
    }


    @FXML
    private void handleOperationAction(ActionEvent event) {
        // Check if an operation has not been set yet
        if (!operationSet) {
            // Set the first number in the model to the current text in the mainTextField
//            mainModel.setFirstNumber(Double.parseDouble(mainTextField.getText()));
//
            // Set the operation in the model to the text of the button that was clicked
            mainModel.setOperation(((Button) event.getSource()).getText());

            // Append the operation to the mainTextField for display
            mainTextField.appendText(" " + mainModel.getOperation() + " ");

            // Mark that an operation has been set
            operationSet = true;
        }
    }


    @FXML
    private void handleEqualsAction() {
        // Get the current text from the mainTextField
        String currentText = mainTextField.getText();

        // Check if an operation has been set and the current text contains a space (indicating an operation)
        if (operationSet && currentText.contains(" ")) {
            // Split the current text by spaces into parts
            String[] parts = currentText.split(" ");

            // Check if the parts array has exactly 3 elements (first number, operation, second number)
            if (parts.length == 3) {
                try {
                    mainModel.setFirstNumber(Double.parseDouble(parts[0]));
                    mainModel.setSecondNumber(Double.parseDouble(parts[2]));

                    // Perform the calculation using the mainModel
                    double result = mainModel.calculate();

                    // Set the result to the mainTextField
                    mainTextField.setText(currentText + " = " + result);

                    // Reset the operationSet flag
                    operationSet = false;
                } catch (NumberFormatException e) {
                    // If parsing the second number fails, display "Error" in the mainTextField
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
        // Get the current text from the mainTextField
        String currentText = mainTextField.getText();

        // Check if the current text is empty or ends with a space
        if (currentText.isEmpty() || currentText.endsWith(" ")) {
            // If true, append "0." to the mainTextField
            mainTextField.appendText("0.");
            // Otherwise, check if the current text does not already contain a dot
        } else if (!currentText.contains(".")) {
            // If true, append a dot to the mainTextField
            mainTextField.appendText(".");
        }
    }


    @FXML
    private void handleClearAction() {
        mainTextField.clear();
        mainModel.reset();
        operationSet = false;
    }


    @FXML
    private void handleDeleteAction() {
        // Get the current text from the mainTextField
        String currentText = mainTextField.getText();

        // Check if the current text is not empty
        if (!currentText.isEmpty()) {
            // Remove the last character from the current text and set it back to the mainTextField
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