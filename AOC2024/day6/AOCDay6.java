/**
 * https://adventofcode.com/2024/day/6
 * 
 */

package day6;

import java.io.*;
import java.util.*;

public class AOCDay6 {
    private static final String FILE = "input.txt";
    public static char[][] grid = null;
    public static int[] directions = { -1, 0, +1, 0 };
    public static int rows = 0, columns = 0, x = 0, y = 0, dy = 0, dx = 3, positions = 1;
    public static int startX = 0, startY = 0, obstructions = 0, infinityLoops = 0;

    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            List<char[]> ingestGrid = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                ingestGrid.add(line.toCharArray());
                if (line.contains("^")) {
                    x = line.indexOf("^");
                    y = rows;
                    columns = line.length() - 1;
                }
                rows++;
            }
            grid = ingestGrid.toArray(new char[0][0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        rows--;

        startX = x;
        startY = y;

        // Part 1
        simulate(0, 0, "positions");

        System.out.println("Distinct positions: " + positions);

        // Part 2
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= columns; j++) {
                if (grid[i][j] == "#".charAt(0) || grid[i][j] == "^".charAt(0)) {
                    continue;
                } else {
                    boolean infinity = simulate(i, j, "infinity");
                    if (infinity)
                        infinityLoops++;
                }
            }
        }
        System.out.println("Obstruction Positions Causing Infinity Loops: " + infinityLoops);
    }

    public static boolean simulate(int i, int j, String type) {
        reset();
        Map<String, Integer> intersections = new HashMap<>();
        boolean infinity = false;
        char[][] freshGrid = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
        String lastMove = "";
        if (type.equals("infinity"))
            freshGrid[i][j] = "#".charAt(0);

        outerloop: while (true) {
            if (y + directions[dy] > rows || y + directions[dy] < 0 || x - directions[dx] > columns
                    || x - directions[dx] < 0)
                break outerloop;
            String eval = String.valueOf(freshGrid[y + directions[dy]][x - directions[dx]]);
            switch (eval) {
                case "#":
                    dy = (dy + 1) % 4;
                    dx = (dx + 1) % 4;
                    String checkPos = "(" + x + "," + y + ")";
                    if (eval.equals(lastMove)) {
                        // encountered n > 1 obstacles in a row, do nothing
                    } else if (intersections.containsKey(checkPos)) {
                        infinity = true;
                        break outerloop;
                    } else {
                        intersections.put(checkPos, 0);
                        lastMove = "#";
                    }
                    break;
                case ".":
                    freshGrid[y + directions[dy]][x - directions[dx]] = "X".charAt(0);
                    positions++;
                    x -= directions[dx];
                    y += directions[dy];
                    lastMove = ".";
                    break;
                case "X":
                    x -= directions[dx];
                    y += directions[dy];
                    lastMove = "X";
                    break;
                case "^":
                    freshGrid[y + directions[dy]][x - directions[dx]] = "X".charAt(0);
                    x -= directions[dx];
                    y += directions[dy];
                    lastMove = "X";
                    break;
                default:
                    break outerloop;
            }
        }
        return infinity;
    }

    public static void reset() {
        x = startX;
        y = startY;
        dy = 0;
        dx = 3;
        positions = 1;
    }
}
