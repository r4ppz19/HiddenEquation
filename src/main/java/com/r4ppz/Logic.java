package com.r4ppz;

public class Logic {
    private double firstNumber;
    private double secondNumber;
    private String operation;

    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public double calculate() {
        return switch (operation) {
            case "+" -> firstNumber + secondNumber;
            case "-" -> firstNumber - secondNumber;
            case "*" -> firstNumber * secondNumber;
            case "/" -> firstNumber / secondNumber;
            case "%" -> firstNumber % secondNumber;
            default -> 0;
        };
    }

    public void reset() {
        firstNumber = 0;
        secondNumber = 0;
        operation = "";
    }
}