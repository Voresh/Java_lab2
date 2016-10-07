package lab4;

import lab3.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PerformanceTest {
    private String mLogFileName = "performance";
    private String mLogFileDir = "logs";

    private ArrayList<PerformanceTestClass> arrayListClass = new ArrayList<PerformanceTestClass>();
    private PerformanceTestClass[] arraySimpleClass = new PerformanceTestClass[10];

    private Logger mLog;

    private Random random = new Random();

    public PerformanceTest(){
        mLog = new Logger(mLogFileName, mLogFileDir);
    }

    public long[] runArrayListTest(int amount) {
        long[] result = new long[amount];
        PerformanceTestClass[] testClasses = new PerformanceTestClass[amount];
        long startTime = 0;
        //long sumTime = 0;

        for (int i = 0; i < amount; i++) {
            testClasses[i] = generateRandomTestClass();
        }

        //mLog.writeOtherMessage("ArrayList test started with operations amount " + amount);
        for (int i = 0; i < amount; i++) {
            startTime = System.nanoTime();
            arrayListClass.add(testClasses[i]);
            result [i] = System.nanoTime() - startTime;
        }

        //for(int i = 0; i < amount; i++) {
        //    mLog.writeOtherMessage(String.format("%.9f",result[i]/1000000000.0));
        //    sumTime += result[i];
        //}
        //mLog.writeOtherMessage("test ended at " + String.format("%.9f",sumTime/1000000000.0) + " seconds");
        return result;
    }

    public long[] runArrayTest(int amount) {
        long[] result = new long[amount];
        PerformanceTestClass[] testClasses = new PerformanceTestClass[amount];
        long startTime = 0;
        //long sumTime = 0;

        for (int i = 0; i < amount; i++) {
            testClasses[i] = generateRandomTestClass();
        }

        //mLog.writeOtherMessage("ArrayList test started with operations amount " + amount);
        for (int i = 0; i < amount; i++) {
            startTime = System.nanoTime();
            if (i >= arraySimpleClass.length) {
                arraySimpleClass = Arrays.copyOf(arraySimpleClass, (arraySimpleClass.length * 3) / 2 + 1);
            }
            arraySimpleClass[i] = testClasses[i];
            result[i] = System.nanoTime() - startTime;
        }

        /*for(int i = 0; i < amount; i++) {
            mLog.writeOtherMessage(String.format("%.9f",result[i]/1000000000.0));
            sumTime += result[i];
        }
        mLog.writeOtherMessage("test ended at " + String.format("%.9f",sumTime/1000000000.0) + " seconds");*/
        return  result;
    }

    private PerformanceTestClass generateRandomTestClass() {
        return new PerformanceTestClass("test", random.nextInt());
    }
}
