/*
Condition
    - TL :
      ML :

Question
    -

Access
    -

*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // n,m 입력
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        // 행렬 A 생성
        List<List<Integer>> matrix = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            String[] elements = br.readLine().split(" ");
            List<Integer> e = Arrays.stream(elements)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            matrix.add(e);
        }

        // 행렬 B를 A에 더함
        for (int r = 0; r < n; r++) {
            String[] elements = br.readLine().split(" ");
            List<Integer> e = Arrays.stream(elements)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            for (int c = 0; c < m; c++) {
                int value = matrix.get(r).get(c) + e.get(c);
                matrix.get(r).set(c, value);
            }

            // sb에 추가
            for (int element : matrix.get(r)) {
                sb.append(element+" ");
            }
            sb.append("\n");
        }

        // 결과 출력
        System.out.println(sb);
        br.close();
    }
}
