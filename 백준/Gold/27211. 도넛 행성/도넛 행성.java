import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 0, 1, 0 }; // 상,우,하,좌 (시계방향)
	static int[] dc = { 0, 1, 0, -1 };


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		visited = new boolean[n][m];

		// bfs
		int cnt = 0;

		for (int r=0; r<n; r++) {
			for (int c=0; c<m; c++) {

				if (map[r][c] == 1) continue;
				if (visited[r][c]) continue;

				visited[r][c] = true;
				cnt ++;
				bfs(new Node(r, c));
			}
		}

		// result
		System.out.print(cnt);
	}


	static void bfs(Node stt) {

		// set-up
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.addLast(stt);

		// search
		while (!q.isEmpty()) {

			Node cur = q.removeFirst();

			for (int i=0; i<4; i++) {

				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				// 연결됨
				if (nr < 0) nr = (nr+n) %n;
				if (nr >= n) nr = nr %n;
				if (nc < 0) nc = (nc+m) %m;
				if (nc >= m) nc = nc %m;

				// validate
				if (map[nr][nc] == 1) continue;
				if (visited[nr][nc]) continue;

				visited[nr][nc] = true;
				q.addLast(new Node(nr, nc));
			}
		}
	}


	static class Node {

		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}


}
