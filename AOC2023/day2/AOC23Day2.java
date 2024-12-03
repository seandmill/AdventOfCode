package day2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.*;

public class AOC23Day2 {
    private static final String FILE = "input.txt";
    private static Map<String, Integer> colorMax = new HashMap<>();

    public static void initialize() {
        colorMax.put("red", 12);
        colorMax.put("green", 13);
        colorMax.put("blue", 14);
    }

    public static void main(String[] args) throws IOException {
        initialize();
        int sumIDs = 0, sumPower = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            int gameID = 0;

            while ((line = br.readLine()) != null) {
                boolean valid = true;
                Pattern r = Pattern.compile("(\\d+)\\s+(green|blue|red)");
                Matcher m = r.matcher(line);

                Map<String, Integer> maxColors = new HashMap<>();

                while (m.find()) {
                    int cubes = Integer.parseInt(m.group(1));
                    String color = m.group(2);
                    maxColors.put(color, Math.max(maxColors.getOrDefault(color, 0), cubes));
                    if (cubes > colorMax.get(color)) 
                        valid = false;
                }

                gameID++;
                if (valid)
                    sumIDs += gameID;

                int power = 1;
                for (Entry<String, Integer> e : maxColors.entrySet())
                    power *= e.getValue();
                sumPower += power;
            }
        }

        System.out.println("The sum total of possible game IDs: " + sumIDs);
        System.out.println("The sum of the power of sets: " + sumPower);
    }
}
