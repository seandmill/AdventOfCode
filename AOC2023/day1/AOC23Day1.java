package day1;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class AOC23Day1 {
    private static final String FILE = "input.txt";
    private static Map<String, Integer> ints = new HashMap<>();

    public static void initialize() {
        ints.put("one", 1);
        ints.put("two", 2);
        ints.put("three", 3);
        ints.put("four", 4);
        ints.put("five", 5);
        ints.put("six", 6);
        ints.put("seven", 7);
        ints.put("eight", 8);
        ints.put("nine", 9);
    }

    public static void main(String[] args) throws IOException {
        initialize();
        int calibration = 0, revisedCalibration = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                int first = 0, last = 0, cal = 0, revisedFirst = 0, revisedLast = 0, revisedCal = 0,
                        firstPos = Integer.MAX_VALUE, lastPos = Integer.MIN_VALUE;

                for (Entry<String, Integer> e : ints.entrySet()) {
                    int pos = line.indexOf(e.getKey());

                    while (pos != -1) {
                        if (pos < firstPos) {
                            revisedFirst = e.getValue();
                            firstPos = pos;
                        }
                        if (pos > lastPos) {
                            revisedLast = e.getValue();
                            lastPos = pos;
                        }
                        pos = line.indexOf(e.getKey(), pos + 1);
                    }

                }

                char[] lineArray = line.toCharArray();
                for (int i = 0; i < lineArray.length; i++) {
                    if (Character.isDigit(lineArray[i])) {
                        int digit = lineArray[i] - '0';
                        if (i < firstPos) {
                            revisedFirst = digit;
                            firstPos = i;
                        }
                        if (i > lastPos) {
                            revisedLast = digit;
                            lastPos = i;
                        }
                        if (first == 0)
                            first = digit;
                        last = digit;
                    }
                }

                cal = Integer.parseInt(first + "" + last);
                revisedCal = Integer.parseInt(revisedFirst + "" + revisedLast);
                calibration += cal;
                revisedCalibration += revisedCal;
            }
        }

        System.out.println("Total caliibration value: " + calibration);
        System.out.println("Revised calibration value: " + revisedCalibration);
    }
}
