/**
 * https://adventofcode.com/2024/day/5
 * 
 */

package day5;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class AOCDay5 {
    private static final String FILE = "input.txt";
    public static HashMap<String, Integer> rules = new HashMap<>();
    public static List<String> pages = new ArrayList<>();
    public static int correct = 0, incorrect = 0;

    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            int nullCount = 0;

            while (((line = br.readLine()) == null ? nullCount++ : 0) < 2) {
                if (line == null) {
                    continue;
                } else if (line.contains("|")) {
                    rules.put(line, 0);
                } else if (line.contains(",")) {
                    pages.add(line);
                }
            }
        }

        for (String s : pages) {
            Pattern p = Pattern.compile(",");
            String[] eval = p.split(s);
            boolean valid = true;

            for (int i = eval.length - 1; i >= 0; i--) {
                for (int j = i - 1; j >= 0; j--) {
                    String str = eval[i] + "|" + eval[j];
                    if (rules.containsKey(str)) {
                        String temp = eval[i];
                        eval[i] = eval[j];
                        eval[j] = temp;
                        valid = false;
                    }
                }
            }

            int mid = Math.divideExact(eval.length, 2);
            if (valid) {
                correct += Integer.valueOf(eval[mid]);
            } else {
                incorrect += Integer.valueOf(eval[mid]);
            }
        }
        System.out.println("Correct Results: " + correct);
        System.out.println("Corrected Results: " + incorrect);
    }
}
