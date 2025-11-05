import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int[][] map = new int[5][5];
	static boolean[][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i=0; i<5; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		visited = new boolean[5][5];

		// bfs
		Node start = new Node(r, c, 0, 1);
		int result = bfs(start);

		//result
		System.out.print(result);
	}


	static int bfs(Node start) {

		ArrayDeque<Node> q = new ArrayDeque<>();
		q.addLast(start);
		visited[start.r][start.c] = true;

		while (!q.isEmpty()) {

			Node cur = q.removeFirst();

			int nextDest = cur.dest;

			if (map[cur.r][cur.c] == cur.dest) {

				// dest가 6이라면 종료
				if (cur.dest == 6) return cur.cnt;

				// 그렇지 않다면 visited 초기화하고 계속 탐색 진행
				visited = new boolean[5][5];
				visited[cur.r][cur.c] = true;

				// queue 초기화
				q.clear();

				// dest 갱신
				nextDest = cur.dest +1;
			}

			for (int i=0; i<4; i++) {

				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr<0 || nr>=5 || nc<0 || nc>=5) continue;
				if (visited[nr][nc]) continue;
				if (map[nr][nc] == -1) continue;

				visited[nr][nc] = true;
				q.addLast(new Node(nr, nc, cur.cnt+1, nextDest));
			}
		}

		return -1;
	}


	static class Node {

		int r, c, cnt, dest;

		Node(int r, int c, int cnt, int dest) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dest = dest;
		}
	}




}