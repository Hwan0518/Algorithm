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

        // 주어진 입력
        String input = br.readLine();

        while(input!=null && input.isEmpty() == false){
            sb.append(input +"\n");
            input = br.readLine();
        }


        System.out.println(sb);
        br.close();
    }
}