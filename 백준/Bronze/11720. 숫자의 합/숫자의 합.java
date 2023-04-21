/*
Condition
    - TL : 
      ML : 
    
Question
    - 

Access
    -

input&output hint
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
StringBuffer sb = new StringBuffer();
String input = br.readLine();
int val = Integer.parseInt(input);
sout(sb);
br.close();
*/

import java.io.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();


        int input = Integer.parseInt(br.readLine());
        String nums = br.readLine();
        int sum = 0;
        for (int i = 0; i < input; i++) {
            char val = nums.charAt(i);
            sum += Character.getNumericValue(val);
        }
        sb.append(sum);
        System.out.println(sb);
        br.close();
    }
}