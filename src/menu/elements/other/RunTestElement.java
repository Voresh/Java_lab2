package menu.elements.other;

import lab4.PerformanceTest;

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
        int amount = 0;

        if (scanner.hasNextInt()) {
            amount = scanner.nextInt();
        } else {
            System.out.println("incorrect input... canceled");
            return;
        }

        System.out.println("1) ArrayList test");
        System.out.println("2) array test");
        System.out.print("enter test number: ");

        if (scanner.hasNextInt()) {
            int testNumber = scanner.nextInt();
            if (testNumber == 1) {
                mPerformanceTest.runArrayListTest(amount);
            } else if (testNumber == 2) {
                mPerformanceTest.runSimpleArrayTest(amount);
            } else {
                System.out.println("incorrect test number... canceled");
            }
        } else {
            System.out.println("incorrect input... canceled");
            return;
        }
    }
}
