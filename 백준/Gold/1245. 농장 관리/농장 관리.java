import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n,m;
	static int[][] map;
	static boolean[][] visited, top;
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dc = {0, 0, -1, 1, 1, -1, 1, -1 };

	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		// bfs
		int cnt = 0;

		top = new boolean[n][m];

		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (top[i][j]) continue;
				if (bfs(new Node(i, j))) cnt ++;
			}
		}

		// result
		System.out.print(cnt);
	}


	// stt가 봉우리인지 판단
	static boolean bfs(Node stt) {

		// set-up
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.addLast(stt);
		int limit = map[stt.r][stt.c];

		visited = new boolean[n][m];
		visited[stt.r][stt.c] = true;

		List<Node> topList = new ArrayList<>();
		topList.add(stt);

		// search
		while (!q.isEmpty()) {

			Node cur = q.removeFirst();
			boolean isTop = true;

			// 8-d
			for (int i=0; i<8; i++) {

				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
				if (visited[nr][nc]) continue;

				if (map[nr][nc] < limit) {
					continue;
				}
				else if (map[nr][nc] > limit) {
					isTop = false;
					break;
				}

				Node next = new Node(nr, nc);
				visited[nr][nc] = true;
				topList.add(next);
				q.addLast(next);
			}

			if (!isTop) return false;
		}

		for (Node t : topList) top[t.r][t.c] = true;
		return true;
	}


	static class Node {

		int r, c;

		Node(int r, int m) {
			this.r = r;
			this.c = m;
		}
	}

}