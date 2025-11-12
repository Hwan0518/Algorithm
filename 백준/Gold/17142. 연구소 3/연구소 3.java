import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m, blankCnt;
	static boolean[][] visited;
	static int[][] map;
	static List<Node> virusList;
	static boolean[] selected;
	static int min = Integer.MAX_VALUE;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		virusList = new ArrayList<>();
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());

			for (int j=0; j<n; j++) {

				int v = Integer.parseInt(st.nextToken());

				map[i][j] = v;

				if (v == 0) blankCnt++;
				else if (v == 2) virusList.add(new Node(i, j, 0));
			}
		}

		selected = new boolean[virusList.size()];

		// dfs + bfs
		int cnt = 0;
		int idx = 0;
		dfs(cnt, idx);

		// result
		System.out.print(min == Integer.MAX_VALUE ? -1 : min);
	}


	static void dfs(int activeCnt, int idx) {

		if (activeCnt == m || idx == virusList.size()) {

			if (activeCnt == m) min = Integer.min(min, bfs());
			return;
		}

		// 선택
		selected[idx] = true;
		dfs(activeCnt+1, idx+1);
		selected[idx] = false;

		// 비선택
		dfs(activeCnt, idx+1);
	}


	static int bfs() {

		// set-up
		ArrayDeque<Node> q = new ArrayDeque<>();

		visited = new boolean[n][n]; // 매번 탐색마다 초기화
		for (int i=0; i<selected.length; i++) {
			if (selected[i]) {

				Node active = virusList.get(i);
				q.addLast(active);
				visited[active.r][active.c] = true;
			}
		}

		int maxT = 0;
		int infected = 0;

		// search
		while (!q.isEmpty()) {

			Node cur = q.removeFirst();

			// update maxTime
			if (map[cur.r][cur.c] != 2) maxT = Integer.max(maxT, cur.t);

			// infecting
			for (int i=0; i<4; i++) {

				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				// validate
				if (nr<0 || nr>=n || nc<0 || nc>=n) continue;
				if (map[nr][nc] == 1) continue;
				if (visited[nr][nc]) continue;

				visited[nr][nc] = true;

				if (map[nr][nc] == 0) infected ++;

				q.addLast(new Node(nr, nc, cur.t+1));
			}
		}

		// return max T
		int diff = blankCnt - infected;
		return diff == 0 ? maxT : Integer.MAX_VALUE;
	}


	static class Node {

		int r,c,t;

		Node(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}

		@Override
		public String toString() {
			return r+","+c+","+t;
		}
	}


}