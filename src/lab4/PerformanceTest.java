package lab4;

import lab2.Grass;
import lab2.GrassType;
import lab3.Logger;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PerformanceTest {
    private String mLogFileName = "performance";
    private String mLogFileDir = "logs";

    private ArrayList<PerformanceTestClass> arrayListClass = new ArrayList<PerformanceTestClass>();
    private PerformanceTestClass[] arraySimpleClass = new PerformanceTestClass[0];

    private Logger mLog;

    private Random random = new Random();

    public PerformanceTest(){
        mLog = new Logger(mLogFileName, mLogFileDir);
    }

    public void runTest(int amount) {
        LocalTime time = LocalTime.now();

        mLog.writeOtherMessage("test started with operations amount " + amount);
        for (int i = 0; i < amount; i++) {
            generateRandomGrass();
        }
        LocalTime newTime = LocalTime.now();
        long timeDifference = ChronoUnit.MILLIS.between(time, newTime);
        mLog.writeOtherMessage("test ended at " + timeDifference/1000.0 + " seconds");
    }

    public void runArrayListTest(int amount) {
        LocalTime time = LocalTime.now();

        mLog.writeOtherMessage("ArrayList test started with operations amount " + amount);
        for (int i = 0; i < amount; i++) {
            arrayListClass.add(generateRandomTestClass());
        }
        LocalTime newTime = LocalTime.now();
        long timeDifference = ChronoUnit.MILLIS.between(time, newTime);
        mLog.writeOtherMessage("test ended at " + timeDifference/1000.0 + " seconds");
    }

    public void runSimpleArrayTest(int amount) {
        LocalTime time = LocalTime.now();

        mLog.writeOtherMessage("array test started with operations amount " + amount);
        for (int i = 0; i < amount; i++) {
            arraySimpleClass = Arrays.copyOf(arraySimpleClass, arraySimpleClass.length + 1);
            arraySimpleClass[i] = generateRandomTestClass();
        }
        LocalTime newTime = LocalTime.now();
        long timeDifference = ChronoUnit.MILLIS.between(time, newTime);
        mLog.writeOtherMessage("test ended at " + timeDifference/1000.0 + " seconds");
    }

    private PerformanceTestClass generateRandomTestClass() {
        return new PerformanceTestClass("test", random.nextInt());
    }

    private void generateRandomGrass() {
        new Grass(GrassType.values()[random.nextInt(GrassType.values().length)]);
    }
}
