/**
 * https://adventofcode.com/2024/day/2
 */

package day2;

import java.io.*;

public class AOCDay2 {
    private static final String FILE = "input.txt";
    private static final String DELIMITER = " ";

    public static void main(String[] args) throws IOException {
        int reports = 0, safeReports = 0, revisedSafeReports = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] level = line.split(DELIMITER);
                int delta = 0, direction = 0, violations = 0;

                for (int i = 1; i < level.length; i++) {
                    delta = Integer.parseInt(level[i]) - Integer.parseInt(level[i - 1]);
                    int directionCheck = Integer.signum(delta);
                    if (Math.abs(delta) < 1 || Math.abs(delta) > 3) {
                        violations++;
                    } else if (direction == 0) {
                        direction = directionCheck;
                    } else if (directionCheck != direction) {
                        violations++;
                    }
                    if (violations > 1)
                        break;
                }

                if (violations == 0)
                    safeReports++;
                if (violations <= 1)
                    revisedSafeReports++;
                reports++;
            }
        }

        System.out.println("Number of safe reports: " + safeReports + " out of " + reports);
        System.out.println("Revised number of safe reports: " + revisedSafeReports + " out of " + reports);
    }
}
