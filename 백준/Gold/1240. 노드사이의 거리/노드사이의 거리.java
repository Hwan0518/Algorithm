import java.io.*;
import java.util.*;

public class Main {

	/*
	이건 딱 봐도 다익스트라
	우선순위큐를 사용한 다익스트라를 구현하자
	필요한건 다음과 같다
	-
	 */

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n,m;
	static List<Node>[] graph;

	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new List[n+1];
		for (int i=0; i<=n; i++) graph[i] = new ArrayList<>();

		for (int i=0; i<n-1; i++) {

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}

		// bfs
		for (int i=0; i<m; i++) {

			st = new StringTokenizer(br.readLine());
			int stt = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			int dist = bfs(stt, end);
			sb.append(dist).append("\n");
		}

		// result
		System.out.print(sb);
	}


	static int bfs(int stt, int end) {

		// set-up
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(stt, 0));

		boolean[] visited = new boolean[n+1];
		visited[stt] = true;

		// search
		int min = 10_000;
		while (!q.isEmpty()) {

			Node cur = q.removeFirst();

			// base-condition
			if (cur.num == end) {
				return cur.cost;
			}

			for (Node neighbor : graph[cur.num]) {

				if (visited[neighbor.num]) continue;
				visited[neighbor.num] = true;
				q.addLast(new Node(neighbor.num, cur.cost + neighbor.cost));
			}
		}

		// result
		return min;
	}



	public static class Node {

		int num, cost;

		Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
	}


}
