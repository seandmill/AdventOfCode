/**
 * https://adventofcode.com/2024/day/4
 * 
 */

package day4;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class AOCDay4 {
    private static final String FILE = "input.txt";

    public static void main(String[] args) throws IOException {
        int xmasTimes = 0, masTimes = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            int offset = 0;
            List<StringBuilder> verticalList = new ArrayList<>();
            List<StringBuilder> diagonalLeftList = new ArrayList<>();
            List<StringBuilder> diagonalRightList = new ArrayList<>();
            List<StringBuilder> horizontalList = new ArrayList<>();
            List<List<StringBuilder>> metaList = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                char[] ch = line.toCharArray();
                for (int i = 0; i < ch.length; i++) {
                    String letter = String.valueOf(ch[i]);
                    if (offset == 0) {
                        verticalList.add(new StringBuilder(letter));
                        diagonalRightList.add(new StringBuilder(letter));
                        diagonalLeftList.add(new StringBuilder(letter));
                    } else if (i == 0) {
                        diagonalLeftList.add(new StringBuilder(letter));
                        diagonalRightList.get(offset).append(letter);
                    } else if (i == ch.length - 1) {
                        diagonalRightList.add(new StringBuilder(letter));
                        diagonalLeftList.get(i - offset).append(letter);
                    } else if (i < offset) {
                        diagonalLeftList.get(ch.length - i - 1 + offset).append(letter);
                        diagonalRightList.get(i + offset).append(letter);
                    } else {
                        diagonalRightList.get(i + offset).append(letter);
                        diagonalLeftList.get(i - offset).append(letter);
                    }

                    if (offset != 0)
                        verticalList.get(i).append(letter);

                }

                horizontalList.add(new StringBuilder(line));
                offset++;
            }

            metaList.add(horizontalList);
            metaList.add(verticalList);
            metaList.add(diagonalRightList);
            metaList.add(diagonalLeftList);

            Pattern xmasPattern = Pattern.compile("XMAS");
            Pattern samxPattern = Pattern.compile("SAMX");

            // Part 1
            for (List<StringBuilder> l : metaList) {
                for (StringBuilder sb : l) {
                    Matcher xmasMatcher = xmasPattern.matcher(sb);
                    Matcher samxMatcher = samxPattern.matcher(sb);

                    while (xmasMatcher.find())
                        xmasTimes++;
                    while (samxMatcher.find())
                        xmasTimes++;
                }
            }
            // Part 2
            for (int i = 0; i < horizontalList.size(); i++) {
                if (i == 0 || i == horizontalList.size() - 1)
                    continue;
                for (int j = 0; j < horizontalList.size(); j++) {
                    if (j == 0 || j == horizontalList.size() - 1)
                        continue;
                    String left = String.valueOf(horizontalList.get(i - 1).charAt(j - 1))
                            + String.valueOf(horizontalList.get(i).charAt(j))
                            + String.valueOf(horizontalList.get(i + 1).charAt(j + 1));
                    String right = String.valueOf(horizontalList.get(i - 1).charAt(j + 1))
                            + String.valueOf(horizontalList.get(i).charAt(j))
                            + String.valueOf(horizontalList.get(i + 1).charAt(j - 1));
                    if ((left.equals("MAS") || left.equals("SAM"))
                            && (right.equals("MAS") || right.equals("SAM"))) {
                        masTimes++;
                    }
                }
            }

        }
        System.out.println("XMAS appears: " + xmasTimes + " times.");
        System.out.println("X-MAS appears: " + masTimes + " times.");
    }
}
