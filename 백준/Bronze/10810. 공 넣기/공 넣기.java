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

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[] basket = new int[n];
        for (int i = 0; i < m; i++) {
            String[] data = br.readLine().split(" ");
            int stt = Integer.parseInt(data[0]);
            int end = Integer.parseInt(data[1]);
            int k = Integer.parseInt(data[2]);
            for (int cur = stt-1; cur < end; cur++) {
                basket[cur] = k;
            }
        }
        for (int i = 0; i < n; i++) {
            sb.append(basket[i] + " ");
        }
        System.out.println(sb);
        br.close();

    }
}