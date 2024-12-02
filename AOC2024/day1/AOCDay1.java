/**
 * https://adventofcode.com/2024/day/1
 */

package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AOCDay1 {
    private static final String file = "input.txt";
    private static final String delimiter = "   ";

    public static void main(String[] args) throws IOException {
        int distance = 0;
        int similarity = 0;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                left.add(Integer.parseInt(line.split(delimiter)[0]));
                right.add(Integer.parseInt(line.split(delimiter)[1]));
            }
        }
        Collections.sort(left);
        Collections.sort(right);
        
        int entries = left.size();

        for(int i = 0; i < entries; i++) {
            int counter = 0;
            for(int j = 0; j < entries; j++) {
                if(left.get(i) < right.get(j)) break;
                if(left.get(i).equals(right.get(j))) counter++;
            }
            distance += Math.abs(right.get(i) - left.get(i));
            similarity += left.get(i) * counter;
        }

        System.out.println("Distance: " + distance);
        System.out.println("Similarity: " + similarity);
    }
}
