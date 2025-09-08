import java.io.*;
	import java.util.*;

public class Main {

	static int n;
	static int m;
	static int x;
	static List<Node>[] graph;
	static int[][] dist;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		dist = new int[n+1][n+1];
		for (int i=0; i<n+1; i++) {
			for (int j=0; j<n+1; j++) dist[i][j] = Integer.MAX_VALUE;
		}

		graph = new ArrayList[n+1];
		for (int i = 0; i < n+1; i++) graph[i] = new ArrayList<>();
		for (int i=0; i<m; i++) {

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b, t));
		}

		// dijkstra
		for (int i=1; i<n+1; i++) {
			dijkstra(i);
		}

		// result
		System.out.print(calcMaxTime());

	}



	static void dijkstra(int stt) {

		// set-up
		Queue<Node> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.t));
		q.offer(new Node(stt, 0));
		dist[stt][stt] = 0;

		// search
		while (!q.isEmpty()) {

			Node cur = q.poll();
			int curIdx = cur.idx;
			int curT = cur.t;

			if (dist[stt][curIdx] < curT) continue;

			for (Node next : graph[curIdx]) {

				int nextIdx = next.idx;
				int nextT = next.t;
				int goNextT = curT + nextT;

				if (dist[stt][nextIdx] < goNextT) continue;
				dist[stt][nextIdx] = goNextT;
				q.offer(new Node(nextIdx, goNextT));
			}
		}
	}




	static int calcMaxTime() {

		int maxTime = 0;

		for (int i = 1; i < n+1; i++) maxTime = Math.max(maxTime, dist[i][x] + dist[x][i]);

		return maxTime;
	}



	static class Node {

		int idx;
		int t;

		Node(int idx, int t) {
			this.idx = idx;
			this.t = t;
		}

	}

}