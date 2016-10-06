package menu.elements.other;

import lab4.PerformanceTest;
import lab5.GraphPainter;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class RunTestElement extends MenuElement{
    private PerformanceTest mPerformanceTest;

    public RunTestElement(String name, PerformanceTest mPerformanceTest) {
        super(name);
        this.mPerformanceTest = mPerformanceTest;
    }

    @Override
    public void execute() {
        System.out.print("enter operations amount or random symbol to cancel: ");
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            userInput = userInput.replaceAll(" ", "");
            String[] amounts = userInput.split(",");

            System.out.println("1) ArrayList test");
            System.out.println("2) array test");
            System.out.print("enter test number: ");

            if (scanner.hasNextInt()) {
                int testNumber = scanner.nextInt();
                if (testNumber == 1) {
                    for (String amount: amounts) {
                        if (parsableToInteger(amount)) {
                            GraphPainter g =  new GraphPainter("ArrayList performance graph",1000,500, 10);
                            g.paintGraph(Integer.parseInt(amount), mPerformanceTest.runTstAL(Integer.parseInt(amount)));
                        }
                    }
                } else if (testNumber == 2) {
                    for (String amount: amounts) {
                        if (parsableToInteger(amount)) {
                            GraphPainter g =  new GraphPainter("Array performance graph",1000,500, 10);
                            g.paintGraph(Integer.parseInt(amount),mPerformanceTest.runTstAS(Integer.parseInt(amount)));
                        }
                    }
                } else {
                    System.out.println("incorrect test number... canceled");
                }
            } else {
                System.out.println("incorrect input... canceled");
                return;
            }
        } else {
            System.out.println("incorrect input... canceled");
            return;
        }
    }


    public boolean parsableToInteger(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
