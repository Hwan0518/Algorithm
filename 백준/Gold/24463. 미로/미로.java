import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n,m;
	static char[][] map;
	static Node entrance, exit;
	static List<Node> used;

	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		for (int i=0; i<n; i++) map[i] = br.readLine().toCharArray();

		// find entrance, exit
		for (int i=0; i<n; i++) {
			if (map[i][0] == '.') {
				if (entrance == null) entrance = new Node(i, 0, null);
				else if (exit == null) exit = new Node(i, 0, null);

			}
			if (map[i][m-1] == '.') {
				if (entrance == null) entrance = new Node(i, m-1, null);
				else if (exit == null) exit = new Node(i, m-1, null);
			}
		}

		for (int j=0; j<m; j++) {
			if (map[0][j] == '.') {
				if (entrance == null) entrance = new Node(0, j, null);
				else if (exit == null) exit = new Node(0, j, null);
			}

			if (map[n-1][j] == '.') {
				if (entrance == null) entrance = new Node(n-1, j, null);
				else if (exit == null) exit = new Node(n-1, j, null);
			}
		}

		// bfs
		bfs();

		// mark @
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (map[i][j] == '.') map[i][j] = '@';
			}
		}

		for (Node route : used) map[route.r][route.c] = '.';

		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) sb.append(map[i][j]);
			sb.append("\n");
		}

		// result
		System.out.print(sb);
	}


	static void bfs() {

		// set-up
		boolean[][] visited = new boolean[n][m];
		visited[entrance.r][entrance.c] = true;

		used = new ArrayList<>();
		used.add(entrance);

		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		ArrayDeque<Node> q = new ArrayDeque<>();
		q.addLast(entrance);

		// search
		while (!q.isEmpty()) {

			Node cur = q.removeFirst();

			// end - condition
			if (cur.r == exit.r && cur.c == exit.c) {

				Node route = cur;
				while (!route.equals(entrance)) {
					used.add(route);
					route = route.before;
				}

				return;
			}

			// up, down, left, right
			for (int i=0; i<4; i++) {

				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				// validate
				if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
				if (map[nr][nc] == '+') continue;
				if (visited[nr][nc]) continue;

				visited[nr][nc] = true;
				q.addLast(new Node(nr, nc, cur));
			}
		}
	}


	static class Node {

		int r,c;
		Node before;

		Node(int r, int c, Node before) {
			this.r = r;
			this.c = c;
			this.before = before;
		}
	}

}