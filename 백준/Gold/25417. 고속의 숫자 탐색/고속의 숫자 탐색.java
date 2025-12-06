import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;


	static int r;
	static int[][] map;
	static boolean[][] visited = new boolean[5][5];
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		// input
		map = new int[5][5];

		for (int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<5; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int sttR = Integer.parseInt(st.nextToken());
		int sttC = Integer.parseInt(st.nextToken());

		// bfs
		int min = bfs(sttR, sttC);

		// result
		System.out.print(min);
	}



	static int bfs(int sttR, int sttC) {

		// set-up
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.addLast(new Node(sttR, sttC, 0));
		visited[sttR][sttC] = true;

		// search
		while (!q.isEmpty()) {

			Node cur = q.removeFirst();

			// success condition
			if (map[cur.r][cur.c] == 1) {
				return cur.move;
			}

			for (int i=0; i<4; i++) {

				int nr, nc;

				for (int j=0; j<2; j++) {

					// run
					if (j == 0) {

						Node next = run(cur, i);

						// 움직일 수 없다면 continue
						if (next.r == cur.r && next.c == cur.c) continue;

						// 움직일 수 있다면 갱신
						nr = next.r;
						nc = next.c;
					}

					// walk
					else {
						nr = cur.r + dr[i];
						nc = cur.c + dc[i];
					}

					// validate
					if (nr < 0 || nr>= 5 || nc < 0 || nc>=5) continue;
					if (visited[nr][nc]) continue;
					if (map[nr][nc] == -1) continue;
					
					visited[nr][nc] = true;
					q.addLast(new Node(nr, nc, cur.move+1));
				}
			}
		}

		// impossible
		return -1;
	}



	static Node run(Node cur, int i) {

		int r = cur.r;
		int c = cur.c;

		while (true) {

			int nr = r + dr[i];
			int nc = c + dc[i];

			// map 벗어나기 직전까지 이동
			if (nr < 0 || nr>=5 || nc<0 || nc>=5) break;
			// -1 만나기 직전까지 이동
			if (map[nr][nc] == -1) break;

			r = nr;
			c = nc;

			// 7 만나면 해당 칸에서 멈춤
			if (map[r][c] == 7) break;
		}

		// next Node
		return new Node(r, c, cur.move);
	}



	static class Node {

		int r,c,move;

		Node(int r, int c, int move) {
			this.r = r;
			this.c = c;
			this.move = move;
		}
	}

}
