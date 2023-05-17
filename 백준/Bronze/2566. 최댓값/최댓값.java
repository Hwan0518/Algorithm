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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // 최댓값을 0으로 설정한 후, 각 수마다 비교해서 최댓값과 위치를 갱신
        int maxValue = 0;
        int[] location = {1,1};
        for (int r = 1; r < 10; r++) {
            List<Integer> data = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            int maxData = Collections.max(data);
            if (maxData >= maxValue) {
                maxValue = maxData;
                int c = data.indexOf(maxData) +1;
                location[0] = r;
                location[1] = c;
            }
        }
        // 최댓값과 위치를 sb에 더함
        sb.append(maxValue + "\n");
        sb.append(location[0] +" "+ location[1]);
        // 결과 출력
        System.out.println(sb);
        br.close();


        }
    }

