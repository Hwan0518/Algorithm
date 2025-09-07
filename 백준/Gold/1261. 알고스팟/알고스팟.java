import java.io.*;
	import java.util.*;

public class Main {

	static int m;
	static int n;
	static int[][] map;
	static int[][] visited;
	static int minCnt = Integer.MAX_VALUE;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };



	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i=0; i<n; i++) {
			map[i] = Arrays.stream(br.readLine().split(""))
				.mapToInt(Integer::parseInt)
				.toArray();
		}

		visited = new int[n][m];
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) visited[i][j] = Integer.MAX_VALUE;
		}

		// dfs
		bfs();

		// result
		System.out.print(minCnt);
	}



	static void bfs() {

		// set-up
		Deque<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0, 0));
		visited[0][0] = 0;

		// search
		while (!q.isEmpty()) {

			Node cur = q.pop();
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;

			if (r==n-1 && c==m-1) {
				minCnt = Math.min(minCnt, cnt);
				continue;
			}

			for (int i=0; i<4; i++) {

				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
				int v = visited[nr][nc];
				if (cnt >= visited[nr][nc]) continue;

				visited[nr][nc] = cnt;
				q.add(new Node(nr, nc, map[nr][nc] == 0 ? cnt : cnt+1));

			}
		}

	}



	static class Node {

		int r;
		int c;
		int cnt;

		Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

	}

}