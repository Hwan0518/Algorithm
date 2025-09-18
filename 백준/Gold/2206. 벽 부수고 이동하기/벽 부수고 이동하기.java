import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int m;
	static int[][] visited;
	static int[][] map;
	static int[] dr = { 1, 0, -1, 0 }; // 하,우,상,좌 순서
	static int[] dc = { 0, 1, 0, -1 }; // 하,우,상,좌 순서
	static int wallCnt;
	static int[][] minMap;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i=0; i<n; i++) map[i] = Arrays.stream(br.readLine().split(""))
			.mapToInt(Integer::parseInt)
			.toArray();

		// bfs
		resetMinMap();
		resetVisited();
		int ans = bfs(new Node(0, 0, 1, 0, -1, -1));

		// result
		System.out.print(ans);

	}


	static int bfs(Node stt) {

		Deque<Node> q = new ArrayDeque<>();
		q.add(stt);
		visited[stt.r][stt.c] = stt.w;

		while (!q.isEmpty()) {

			Node cur = q.removeFirst();

			if (cur.r == n-1 && cur.c == m-1) {
				return cur.cnt;
			}

			for (int i=0; i<4; i++) {

				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
				if (visited[nr][nc] == cur.w || (nr==cur.br && nc==cur.bc)) continue;
				if (cur.w != 0 && cur.cnt+1 >= minMap[nr][nc]) continue;

				minMap[nr][nc] = Math.min(minMap[nr][nc], cur.cnt + 1);

				// destroy
				if (map[nr][nc] == 1 && cur.w == 0) {
					wallCnt ++;
					visited[nr][nc] = wallCnt;
					q.add(new Node(nr, nc, cur.cnt+1, wallCnt, cur.r, cur.c));
				}

				// not wall
				else if (map[nr][nc] == 0) {
					visited[nr][nc] = cur.w;
					q.add(new Node(nr, nc, cur.cnt + 1, cur.w, cur.r, cur.c));
				}
			}
		}

		return -1;
	}


	static void resetMinMap() {

		minMap = new int[n][m];
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) minMap[i][j] = Integer.MAX_VALUE;
		}
		
		minMap[0][0] = 0;


	}
	

	static void resetVisited() {

		visited = new int[n][m];

		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) visited[i][j] = -1;
		}

	}


	static class Node {

		int r;
		int c;
		int cnt;
		int w;
		int br;
		int bc;

		Node(int r, int c, int cnt, int w, int br, int bc) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.w = w;
			this.br = br;
			this.bc = bc;
		}

	}

	

}