/*
Condition
    - TL : 1s (1억)
      ML : 256mb
    - 1<=N,M<=100

Question
    - i와 j바구니를 교환한다. 교환이 끝난 후 바구니에 들어있는 공을 출력하여라

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


        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[] baguni = IntStream.range(1, n+1).toArray();
        for (int cnt = 0; cnt < m; cnt++) {
            input = br.readLine().split(" ");
            int i = Integer.parseInt(input[0]) -1;
            int j = Integer.parseInt(input[1]) -1;
            // change
            int temp = baguni[i];
            baguni[i] = baguni[j];
            baguni[j] = temp;
        }
        for (int cnt = 0; cnt < n; cnt++) {
            sb.append(baguni[cnt] + " ");
        }
        System.out.println(sb);
        br.close();
    }
}