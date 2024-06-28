import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");
        Set<String> subStringSet = new HashSet<>();
        for (int i=0; i<input.length; i++) {
            String subString = input[i];
            subStringSet.add(subString);
            for (int j=i+1; j<input.length; j++) {
                subString += input[j];
                subStringSet.add(subString);
            }
        }
        System.out.println(subStringSet.size());
    }
}