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

        String star = "*";
        String inputCnt = br.readLine();
        int starCnt = Integer.parseInt(inputCnt);
        int cnt=0;

        while(cnt<starCnt){
            System.out.printf("%"+starCnt+"s\n", star);
            star += "*";
            cnt ++;
        }
        br.close();
    }
}