import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Condition
 *     - TL : 2s
 *       ML :
 *     - 2<=N<=100, 1<=M<=5000
 *     - M개의 경로
 *
 * Question
 *     - 임의의 두사람 최소 몇 단계 만에 이어지는가?
 *     - 케빈 베이컨 수가 가장 작은 사람(모든사람과의 단계수가 가장 작은사람)
 * Access
 *     - 시간복잡도는
 *     - 플로이드워셜로 구현 가능할지도? -> N^3 = 100*100*100 = 1_000_000 -> 가능
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // 1. n,m을 입력받음
        String[] input = br.readLine().split(" ");
        Integer n = Integer.parseInt(input[0]);
        Integer m = Integer.parseInt(input[1]);

        // 2. 그래프 -> 2차원 리스트 생성
        Integer INF = 2147483647;
        ArrayList<Integer> rows = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            rows.add(INF);
        }
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            ArrayList<Integer> row = (ArrayList<Integer>) rows.clone();
            graph.add(row); // 여기서 깊은 복사가 일어난다고??? -> 당연히 같은 객체니까... 깊은 복사가 아니라 그냥 같은 객체지 맙소사..
        }

        // 3. 자기 자신으로 가는 비용 0으로 초기화
        for (int self=0; self < n + 1; self++) {
            graph.get(self).set(self,0);
        }

        // 4. 간선 정보 입력
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            Integer stt = Integer.parseInt(edge[0]);
            Integer end = Integer.parseInt(edge[1]);
            graph.get(stt).set(end, 1);
            graph.get(end).set(stt, 1);
        }

        // 5. 전체탐색
        for (int k = 1; k < n + 1; k++) {
            for (int a = 1; a < n + 1; a++) {
                for (int b = 1; b < n + 1; b++) {
                    int cur = graph.get(a).get(b);
                    int firstStop = graph.get(a).get(k);
                    int secondStop = graph.get(k).get(b);
                    int compare = INF;
                    if (firstStop != INF && secondStop != INF) {
                        compare = graph.get(a).get(k) + graph.get(k).get(b);
                    }
                    graph.get(a).set(b, Math.min(cur, compare));
                }
            }
        }

        // 6. 결과출력 -> 1~n까지중 sum(row)가 가장 작은 n을 출력한다
        int result = 1;
        int minSum = (int) 1e9;

        for (int i = 1; i < n + 1; i++) {
            int rowSum = graph.get(i).stream()
                    .mapToInt(Integer::intValue)
                    .filter(value -> value != INF)
                    .sum();
//            System.out.println("rowSum = " + rowSum);// 6 8 5 5 8이 떠야함
            if (rowSum < minSum) {
                result = i;
                minSum = rowSum;
            }
        }
        sb.append(result);
        System.out.println(sb);
    }
}
