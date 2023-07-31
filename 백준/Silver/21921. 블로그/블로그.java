import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // n,x를 입력받음
        String[] input = br.readLine().split(" ");
        Integer n = Integer.parseInt(input[0]);
        Integer x = Integer.parseInt(input[1]);

        // arr를 입력받음
        String[] arr = br.readLine().split(" ");

        // Stream을 사용하여 초기 max값 설정
        Integer maxNum = Arrays.stream(Arrays.copyOfRange(arr,0,x))
                .mapToInt(Integer::parseInt)
                .sum();

        // 반복문을 사용하여 최댓값과 그 개수를 구함
        Integer cnt = 1;
        Integer before = maxNum;
        for (int i = 0; i < n - x; i++) {
            Integer left = Integer.parseInt(arr[i]);
            Integer right = Integer.parseInt(arr[i + x]);
            Integer cur = before - left + right;

            if (cur > maxNum) {
                cnt = 1;
                maxNum = cur;
            } else if (cur.equals(maxNum)) {
                cnt ++;
            }
            before = cur;
        }

        if (maxNum != 0) {
            sb.append(maxNum + "\n");
            sb.append(cnt);
        } else {
            sb.append("SAD");
        }
        System.out.println(sb);

    }

}