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

        String input = br.readLine();
        for (char c = 'a'; c <= 'z'; c++) {
            int idx = input.indexOf(c);
            sb.append(idx+" ");
        }
        System.out.println(sb);
        br.close();
    }
}