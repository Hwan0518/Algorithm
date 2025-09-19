import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int m;
	static int k;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][][] visited;



	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i=0; i<n; i++) map[i] = Arrays.stream(br.readLine().split(""))
			.mapToInt(Integer::parseInt)
			.toArray();

		// bfs
		visited = new boolean[n][m][k+1];
		int ans = bfs(new Node(0, 0, 1, 0));

		// result
		System.out.print(ans);

	}


	static int bfs(Node stt) {

		Deque<Node> q = new ArrayDeque<>();
		q.add(stt);

		while (!q.isEmpty()) {

			Node cur = q.removeFirst();

			if (cur.r == n-1 && cur.c == m-1) {
				return cur.cnt;
			}

			for (int i=0; i<4; i++) {

				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
				if (visited[nr][nc][cur.wall]) continue;
				visited[nr][nc][cur.wall] = true;

				// destroy
				if (map[nr][nc] == 1 && cur.wall < k) q.add(new Node(nr, nc, cur.cnt+1, cur.wall +1));

				// not wall
				else if (map[nr][nc] == 0) q.add(new Node(nr, nc, cur.cnt + 1, cur.wall));
			}
		}

		return -1;
	}


	static class Node {

		int r;
		int c;
		int cnt;
		int wall;

		Node(int r, int c, int cnt, int wall) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.wall = wall;
		}

	}



}