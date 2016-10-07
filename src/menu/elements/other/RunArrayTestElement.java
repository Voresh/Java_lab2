package menu.elements.other;

import lab4.PerformanceTest;
import lab5.GraphPainter;

import java.util.Scanner;

public class RunArrayTestElement extends MenuElement{
    private PerformanceTest mPerformanceTest;

    public RunArrayTestElement(String name, PerformanceTest mPerformanceTest) {
        super(name);
        this.mPerformanceTest = mPerformanceTest;
    }

    @Override
    public void execute() {
        System.out.print("enter test values amount: ");
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int amount = scanner.nextInt();
            GraphPainter g =  new GraphPainter("Array performance graph",1000,500);
            g.paintGraph(amount,mPerformanceTest.runArrayTest(amount));
        } else {
            System.out.println("incorrect input... canceled");
            return;
        }
    }
}
