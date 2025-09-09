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
	static Deque<Node> q = new ArrayDeque<>();


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
		selectOutSide(0, 0, 0);
		bfs();

		// result
		System.out.println(maxTime);
		System.out.print(cnt);

	}





	static void bfs() {

		while (!q.isEmpty()) {

			Node cur = q.pop();
			int r = cur.r;
			int c = cur.c;
			int t = cur.t;

			if (t > maxTime) {
				maxTime = t;
				cnt = 1;
			}
			else if (t == maxTime) {
				cnt ++;
			}

			// 치즈 찾기
			for (int i=0; i<4; i++) {

				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
				if (visited[nr][nc]) continue;
				visited[nr][nc] = true;

				if (map[nr][nc] == 1) {
					map[nr][nc] = 0;
					q.add(new Node(nr, nc, t + 1)); // t+1 시간에 녹음
					continue;
				}

				// 열린 구멍 찾기
				for (int j=0; j<4; j++) {

					int nnr = nr + dr[j];
					int nnc = nc + dc[j];

					if (nnr<0 || nnr>=n || nnc<0 || nnc>=m) continue;
					if (visited[nnr][nnc]) continue;
					if (map[nnr][nnc] == 1) continue;

					visited[nnr][nnc] = true;
					selectOutSide(nnr, nnc, t); // 열린 구멍을 통해 바깥쪽 치즈 찾기
				}
			}
		}
	}





	static void selectOutSide(int ar, int ac, int at) {

		Deque<Node> airQ = new ArrayDeque<>();
		airQ.add(new Node(ar, ac, at));

		while (!airQ.isEmpty()) {

			Node cur = airQ.pop();
			int r = cur.r;
			int c = cur.c;
			int t = cur.t;

			for (int i=0; i<4; i++) {

				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
				if (visited[nr][nc]) continue;
				visited[nr][nc] = true;

				// cheese
				if (map[nr][nc] == 1) {
					map[nr][nc] = 0;
					q.add(new Node(nr, nc, t+1)); // t+1 시간에 녹음
				}

				// air
				else {
					airQ.add(new Node(nr, nc, t));
				}
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
