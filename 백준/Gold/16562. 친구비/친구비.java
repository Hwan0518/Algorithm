import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int m;
	static int k;
	static boolean[] visited;
	static Set<Integer>[] graph;
	static int[] cost;



	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		cost = new int[n + 1];
		PriorityQueue<Node> ascSeq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n + 1; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			ascSeq.offer(new Node(i, cost[i]));
		}

		graph = new Set[n+1];
		for (int i = 0; i < n + 1; i++) graph[i] = new HashSet<>();
		for (int i=0; i<m; i++) {

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		// dfs
		int curCost = 0;
		visited = new boolean[n + 1];

		// cost가 작은 순서대로 탐색한다
		while (!ascSeq.isEmpty()) {

			Node cur = ascSeq.poll();

			if (visited[cur.idx]) continue;
			if (cost[cur.idx] > k) continue;

			visited[cur.idx] = true;
			k -= cost[cur.idx];
			curCost += dfs(cur.idx, cost[cur.idx]);
		}

		// result
		System.out.print(validate() ? curCost : "Oh no");
	}




	static int dfs(int cur, int curCost) {

		for (int next : graph[cur]) {

			if (visited[next]) continue;
			visited[next] = true;
			curCost = dfs(next, curCost);

		}

		return curCost;

	}




	static boolean validate() {

		for (int i=1; i<n+1; i++) if (!visited[i]) return false;
		return true;

	}



	static class Node {

		int idx;
		int cost;

		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

}