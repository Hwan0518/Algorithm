import java.io.*;
import java.util.*;




public class Main {

	static int n;
	static int m;
	static int stt;
	static int target;
	static boolean[] visited;
	static List<Integer>[] edge;
	static int cnt = -1;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		stt = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());

		m = Integer.parseInt(br.readLine());

		edge = new List[n+1];
		for (int i = 0; i < n+1; i++) edge[i] = new ArrayList<>();
		for (int i=0; i<m; i++) {

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			edge[a].add(b);
			edge[b].add(a);
		}

		// dfs
		visited = new boolean[n + 1];
		visited[stt] = true;
		dfs(stt, 0);

		// result
		System.out.print(cnt);
	}


	static void dfs(int cur, int curCnt) {

		if (cur == target) {
			cnt = curCnt;
			return;
		}

		for (int next : edge[cur]) {

			if (visited[next]) continue;
			visited[next] = true;

			dfs(next, curCnt + 1);

		}
	}


}