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

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        String TestInput = br.readLine();
        String[] NumInput = br.readLine().split(" ");

        int maxVal = -1_000_000;
        int minVal = 1_000_000;

        for (String i:NumInput){
            int num = Integer.parseInt(i);
            if (num > maxVal) {maxVal=num;}
            if (num < minVal) {minVal=num;}
        }
        sb.append(minVal+" ");
        sb.append(maxVal);
        System.out.println(sb);
        br.close();
    }
}