import java.io.*;
import java.util.*;




public class Main {

	static int n;
	static int m;
	static int[][] map;
	static boolean[][] visited;
	static int cnt = 0;
	static int maxTime = 0;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i=0; i<n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		}

		// bfs
		visited = new boolean[n][m];
		bfs();

		// result
		System.out.println(maxTime);
		System.out.print(cnt);

	}





	static void bfs() {

		Deque<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0, 0));
		visited[0][0] = true;

		while (!q.isEmpty()) {

			Node cur = q.pop();
			int r = cur.r;
			int c = cur.c;
			int t = cur.t;

			if (map[r][c] == 1) {
				if (t > maxTime) {
					maxTime = t;
					cnt = 1;
				}
				else if (t == maxTime) {
					cnt ++;
				}
			}

			// 치즈 찾기
			for (int i=0; i<4; i++) {

				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
				if (visited[nr][nc]) continue;

				visited[nr][nc] = true;

				if (map[nr][nc] == 1) q.add(new Node(nr, nc, t + 1)); // t+1시간 후에 녹는다
				else q.addFirst(new Node(nr, nc, t)); // 공기에 맞닿는 바깥쪽 치즈를 먼저 찾기 위해 addFirst를 사용한다
			}
		}
	}


	static class Node {

		int r;
		int c;
		int t;

		Node (int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}

	}


}