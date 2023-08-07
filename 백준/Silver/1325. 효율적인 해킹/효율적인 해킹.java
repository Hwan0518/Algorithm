/*
Condition
    - TL :
      ML :

Question
    - param0 = String, 특정 시점
      parmm1 = Integer, 승객 인원수
    - input = 년도4자리+월2자리, 승객수
    - solution()의 리턴값은 String
    - 생산시작과 생산종료를 param0와 비교하여, 생산중인 시점의 모델을 출력
    - param1의 승객 인원수와 비교하여 탑승 가능한 모델만 출력
    - 단종된 모델은 *를 붙여서 출력
    - 최종 출력값은 모델 이름순으로 정렬해야함

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
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.max;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // n,m을 입력받음
        String[] input_1 = br.readLine().split(" ");
        int n = Integer.parseInt(input_1[0]);
        int m = Integer.parseInt(input_1[1]);

        // m개의 edge를 입력받음
        HashMap<Integer,ArrayList<Integer>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String[] input_2 = br.readLine().split(" ");
            int a = Integer.parseInt(input_2[0]);
            int b = Integer.parseInt(input_2[1]);
            // b가 존재안할때 미리 만들어야 하지 않나?
            if (!graph.containsKey(b)) {
                ArrayList<Integer> newArr = new ArrayList<>();
                newArr.add(a);
                graph.put(b, newArr);
            } else {
                graph.get(b).add(a);
            }
        }

        // bfs 시작
        int[] hacking = new int[n + 1];
        int maxCnt = 0;
        for (int i = 1; i <= n; i++) {
            boolean visited[] = new boolean[n + 1];
            if (visited[i] == true) {
                continue;
            }
            visited[i] = true;
            int cnt = 1;
            LinkedList<Integer> dq = new LinkedList<>();
            dq.add(i);
            while (!dq.isEmpty()) {
                int cur = dq.poll();
                if (graph.containsKey(cur)) {
                    for (int neighbor : graph.get(cur)) {
                        if (visited[neighbor] == true) {
                            continue;
                        }
                        visited[neighbor] = true;
                        cnt ++;
                        dq.add(neighbor);
                    }
                }
            }
            hacking[i] = cnt;
            maxCnt = max(maxCnt, cnt);
        }
        // 최대값들 뽑아냄
        for (int c = 1; c <= n; c++) {
            if (hacking[c] == maxCnt) {
                sb.append(c + " ");
            }
        }

        System.out.println(sb);
    }

}

