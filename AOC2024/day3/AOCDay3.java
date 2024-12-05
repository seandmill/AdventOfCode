/**
 * https://adventofcode.com/2024/day/3
 */

package day3;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class AOCDay3 {
    private static final String FILE = "input.txt";

    static class Memory {
        private final int pos;
        private final int values;

        public Memory(int pos, int values) {
            this.pos = pos;
            this.values = values;
        }

        public int getPos() {
            return pos;
        }

        public int getValues() {
            return values;
        }
    }

    public static void main(String[] args) throws IOException {
        int mul = 0, revisedMul = 0;
        boolean cont = true;
        List<Memory> vals = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            int index = 0;

            while ((line = br.readLine()) != null) {
                Pattern yes = Pattern.compile("do\\(\\)");
                Pattern no = Pattern.compile("don't\\(\\)");
                Pattern numbers = Pattern.compile("(?:mul\\()(\\d+),(\\d+)\\)");

                Matcher product = numbers.matcher(line);
                Matcher findDo = yes.matcher(line);
                Matcher findDont = no.matcher(line);

                while (findDo.find()) {
                    vals.add(new Memory(index + findDo.start(), 1));
                }
                while (findDont.find()) {
                    vals.add(new Memory(index + findDont.start(), 0));
                }
                while (product.find()) {
                    mul += (Integer.parseInt(product.group(1)) * Integer.parseInt(product.group(2)));
                    vals.add(
                            new Memory(index + product.start(),
                                    Integer.parseInt(product.group(1)) * Integer.parseInt(product.group(2))));
                }
                index += line.length();
            }

        }

        vals.sort(Comparator.comparingInt(Memory::getPos));

        for (Memory m : vals) {
            int i = m.getValues();
            if (i == 1)
                cont = true;
            else if (i == 0)
                cont = false;
            else if (cont)
                revisedMul += i;
        }
        System.out.println("Sum of products: " + mul);
        System.out.println("Revised sum of products: " + revisedMul);
    }
}
