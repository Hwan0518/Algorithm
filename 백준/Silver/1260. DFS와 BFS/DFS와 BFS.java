import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        // n,m,v 입력
        int[] input1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input1[0];
        int m = input1[1];
        int v = input1[2];

        // 간선 정보로 그래프 생성
        ArrayList<Integer>[] graph = new ArrayList[n+1];
            // n+1개의 빈 리스트를 graph에 넣음 -> 각 node의 연결 정보가 담김
        for (int i = 1; i < n+1; i++) {
            graph[i] = new ArrayList<>();
        }
            // m개의 간선을 받아서 graph를 완성
        for (int i = 1; i < m+1; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = edge[0];
            int b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        // 각 그래프 정보를 정렬해야함 ** 안해줘서 틀림 ㅜㅜ
        for (ArrayList<Integer> info : graph) {
            if (info == null) {
                continue;
            }
            Collections.sort(info);
        }

        // dfs 수행결과 출력
        dfs(v, graph, new boolean[n+1]);
        System.out.println(sb);
        sb.setLength(0);
        // bfs 수행결과 출력
        bfs(v, graph, new boolean[n+1]);
        System.out.println(sb);
    }

    public static void dfs(int v, ArrayList<Integer>[] graph, boolean[] visited) {
        // 방문한적 있다면 바로 return
        if (visited[v] == true) {
            return;
        }
        // 그렇지 않다면 v를 방문체크하고, v에서 연결된 vertex를 차례대로 방문
        visited[v] = true;
        sb.append(v+" ");
        for (int next : graph[v]) {
            dfs(next, graph, visited);
        }
    }

    public static void bfs(int v, ArrayList<Integer>[] graph, boolean[] visited) {
        // v부터 방문 시작, visited 처리를 해준다, sb에도 넣는다
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(v);
        visited[v] = true;
        sb.append(v + " ");
        // bfs 시작, 같은 depth에 있는 vertex부터 방문한다, visited 처리는 뒤에서 진행한다
        while (!dq.isEmpty()) {
            int stt = dq.poll();
            // stt에 연결된 노드가 방문한적 있다면 pass
            for (int next : graph[stt]) {
                if (visited[next] == true) {
                    continue;
                }
                // 방문한적 없다면 sb와 deque에 추가 후 방문처리
                dq.add(next);
                visited[next] = true;
                sb.append(next + " ");
            }
        }
    }
}
