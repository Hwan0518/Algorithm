import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // n,m 입력
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0];
        int m = input[1];

        // 치킨 선호도 입력
        int[][] prefer = new int[n][m];
        for (int i = 0; i < n; i++) {
            int[] person = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            prefer[i] = person;
        }

        // 치킨 m개중 3개를 뽑아서 최댓값을 계산 (i,j,k)
        int maxSum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i+1; j < m; j++) {
                for (int k = j + 1; k < m; k++) {
                    // n명의 사람 각각의 i,j,k 치킨 선호도중 최대값을 골라서 더한다
                    int curSum = 0;
                    for (int p = 0; p < n; p++) {
                        curSum += Math.max(prefer[p][i], Math.max(prefer[p][j], prefer[p][k]));
                    }
                    // curSum을 최댓값으로 갱신
                    if (curSum > maxSum) {
                        maxSum = curSum;
                    }
                }
            }
        }
        sb.append(maxSum);
        System.out.println(sb);
    }
}
