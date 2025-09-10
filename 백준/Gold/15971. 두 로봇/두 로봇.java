import java.io.*;
import java.util.*;




public class Main {

	static int n;
	static int stt;
	static int end;
	static List<Node>[] graph;
	static int minCost = -1;
	static boolean[] visited;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		stt = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		graph = new List[n+1];
		for (int i = 1; i < n + 1; i++) graph[i] = new ArrayList<>();
		for (int i=0; i<n-1; i++) {

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b, c, 0));
			graph[b].add(new Node(a, c, 0));
		}

		// dfs
		visited = new boolean[n+1];
		visited[stt] = true;
		dfs(new Node(stt, 0, 0));

		// result;
		System.out.print(minCost);
	}



	static void dfs(Node cur) {

		if (minCost > -1) return;

		if (cur.idx == end) {
			minCost = cur.cost - cur.maxCost;
			return;
		}

		for (Node next : graph[cur.idx]) {

			if (visited[next.idx]) continue;
			visited[next.idx] = true;

			dfs(new Node(next.idx, cur.cost + next.cost, Math.max(cur.maxCost, next.cost)));
			if (minCost > -1) return;

		}


	}



	static class Node {

		int idx;
		int cost;
		int maxCost;

		Node(int idx, int cost, int maxCost) {
			this.idx = idx;
			this.cost = cost;
			this.maxCost = maxCost;
		}

	}


}
