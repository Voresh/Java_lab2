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

        if (scanner.hasNextInt()) {
            int amount = scanner.nextInt();

            System.out.println("1) ArrayList test");
            System.out.println("2) array test");
            System.out.print("enter test number: ");

            if (scanner.hasNextInt()) {
                int testNumber = scanner.nextInt();
                if (testNumber == 1) {
                    GraphPainter g =  new GraphPainter("ArrayList performance graph",1000,500);
                    g.paintGraph(amount, mPerformanceTest.runTstAL(amount));
                } else if (testNumber == 2) {
                    GraphPainter g =  new GraphPainter("Array performance graph",1000,500);
                    g.paintGraph(amount,mPerformanceTest.runTstAS(amount));
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
