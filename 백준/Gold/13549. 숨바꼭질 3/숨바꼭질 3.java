import java.io.*;
import java.util.*;




public class Main {

	static int n;
	static int k;
	static int minTime;
	static BufferedReader br;
	static StringTokenizer st;
	static boolean[] visited;
	static int LIMIT = 100_000;
	static int[] before = new int[LIMIT+1];


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		// bfs
		visited = new boolean[LIMIT+1];
		bfs();

		// result
		System.out.print(minTime);
	}



	static void bfs() {

		Deque<Node> q = new ArrayDeque<>();
		q.add(new Node(n, 0, n));
		visited[n] = true;
		before[n] = n;

		while (!q.isEmpty()) {

			Node cur = q.pop();

			if (cur.x == k) {
				minTime = cur.t;
				return;
			}

			int[] next = { cur.x * 2, cur.x - 1, cur.x + 1 };
			for (int i=0; i<3; i++) {

				int nx = next[i];

				if (nx<0 || nx>LIMIT) continue;
				if (visited[nx]) continue;
				visited[nx] = true;
				before[nx] = cur.x;

				if (i > 0) q.add(new Node(nx, cur.t+1, cur.x));
				else q.addFirst(new Node(nx, cur.t, cur.x)); // 순간이동은 0초가 걸리기 때문에, 최단시간으로 찾기 위해 가장 먼저 넣어둔다
			}
		}
	}



	static class Node {

		int x;
		int t;
		int b;

		Node(int x, int t, int b) {
			this.x = x;
			this.t = t;
			this.b = b;
		}

	}


}
