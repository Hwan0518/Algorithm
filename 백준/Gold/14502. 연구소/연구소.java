import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m, max;
	static int[][] map;
	static List<Node> virusList = new ArrayList<>();
	static int originSafety = 0;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());

			for (int j=0; j<m; j++) {

				int v = Integer.parseInt(st.nextToken());
				map[i][j] = v;

				if (v == 2) virusList.add(new Node(i, j));
				else if (v == 0) originSafety ++;
			}
		}

		// find max safety cnt
		int r = 0;
		int c = 0;
		int addCnt = 0;
		dfs(r, c, addCnt);

		// result
		System.out.print(max);
	}


	static void dfs(int r, int c, int addCnt) {

		if (r == n || addCnt == 3) {
			if (addCnt == 3) max = Integer.max(max, bfs());
			return;
		}

		// validate
		if (c == m) {
			dfs(r+1, 0, addCnt);
			return;
		}

		if (map[r][c] != 0) {
			dfs(r, c+1, addCnt);
			return;
		}

		// 선택
		map[r][c] = 1;
		dfs(r, c+1, addCnt+1);
		map[r][c] = 0;

		// 비선택
		dfs(r, c+1, addCnt);
	}


	static int bfs() {

		// set-up
		ArrayDeque<Node> q = new ArrayDeque<>();
		for (Node virus : virusList) q.addLast(virus);

		boolean[][] visited = new boolean[n][m];

		// search
		int addVirusCnt = 0;
		while (!q.isEmpty()) {

			Node cur = q.removeFirst();

			for (int i=0; i<4; i++) {

				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				// validate
				if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
				if (visited[nr][nc]) continue;
				if (map[nr][nc] != 0) continue;

				visited[nr][nc] = true;
				addVirusCnt ++;
				q.addLast(new Node(nr, nc));
			}
		}

		return originSafety - addVirusCnt -3; //벽 개수도 뺴야함
	}


	static class Node {

		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}


}


