/*
Condition
    - TL : 1s (1억)
      ML : 256mb
    - 1<=N,M<=100

Question
    - i번 바구니부터 j번 바구니까지 k공을 넣을 때,
      모든 입력이 끝난 후 n번 바구니까지 어떤 공이 들어있는지 출력하여라

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

        String[] str = br.readLine().split(" ");
        String[] strReversed = new String[2];
        for (int i = 0; i < 2; i++) {
            sb.append(str[i]);
            strReversed[i] = sb.reverse().toString();
            sb.setLength(0);
        }

        int num1 = Integer.parseInt(strReversed[0]);
        int num2 = Integer.parseInt(strReversed[1]);
        sb.append(Math.max(num1, num2));
        System.out.println(sb);
        br.close();
    }
}