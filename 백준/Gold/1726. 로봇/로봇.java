import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int m;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dr = { 0, 0, 0, 1, -1 };
	static int[] dc = { 0, 1, -1, 0, 0 };
	static Map<String, int[]> changeDir = Map.of(
		"left", new int[] { 0, 4, 3, 1, 2 },
		"right", new int[] { 0, 3, 4, 2, 1 }
	);
	static int er, ec, ed;
	static String[] command = { "go", "dir" };
	static String[] dirCommand = { "straight", "left", "right" };


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		visited = new boolean[n][m][5];

		map = new int[n][m];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) map[i][j] = Integer.parseInt(st.nextToken());

		}

		st = new StringTokenizer(br.readLine());
		int sr, sc, sd;
		sr = Integer.parseInt(st.nextToken()) -1;
		sc = Integer.parseInt(st.nextToken()) -1;
		sd = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		er = Integer.parseInt(st.nextToken()) -1;
		ec = Integer.parseInt(st.nextToken()) -1;
		ed = Integer.parseInt(st.nextToken());

		// bfs
		Node stt = new Node(sr, sc, sd, 0);
		int result = bfs(stt);

		// result
		System.out.print(result);

	}


	static int bfs(Node stt) {

		// set
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.addLast(stt);
		visited[stt.r][stt.c][stt.d] = true;

		// search
		while (!q.isEmpty()) {

			Node cur = q.removeFirst();

			if (cur.r == er && cur.c == ec && cur.d == ed) {
				return cur.cnt;
			}

			// command
			for (String c : command ) {

				int move1R, move1C, nr, nc, nd;

				// move
				if (c.equals("go")) {
					nd = cur.d;

					for (int j = 1; j <= 3; j++) { // 이동 범위 1~3까지

						// 실제 이동
						nr = cur.r + dr[nd] * j;
						nc = cur.c + dc[nd] * j;

						// validate1
						if (!validate(nr, nc, nd)) continue;

						// validate2 -> 벽있으면 더이상 못감. 아예 끝내야함
						if (map[nr][nc] == 1) break;

						visited[nr][nc][nd] = true;
						q.addLast(new Node(nr, nc, nd, cur.cnt + 1));

					}
				}

				// change dir
				else {
					nr = cur.r;
					nc = cur.c;

					for (String change : dirCommand) { // 0:그대로, 1:왼쪽 회전, 2:오른쪽 회전

						nd = change.equals("straight") ? cur.d : changeDir.get(change)[cur.d];

						// validate
						if (!validate(nr, nc, nd)) continue;

						visited[nr][nc][nd] = true;
						q.addLast(new Node(nr, nc, nd, cur.cnt + 1));

					}
				}
			}
		}

		return -1;

	}


	static boolean validate(int nr, int nc, int nd) {

		if (nr < 0 || nr >= n || nc < 0 || nc >= m) return false;
		if (visited[nr][nc][nd]) return false;

		return true;

	}


	static class Node {

		int r;
		int c;
		int d;
		int cnt;

		Node(int r, int c, int d, int cnt) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.cnt = cnt;
		}

	}


}
