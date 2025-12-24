import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int k, c, r;
	static int[][] map;

	public static void main(String[] args) throws IOException {

		// input
		k = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		c = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		map = new int[r][c];
		for (int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<c; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		// bfs
		int min = bfs();

		// result
		System.out.print(min);
	}


	static int bfs() {

		// set-up
		Node stt = new Node(0, 0, 0, 0);

		boolean[][][] visited = new boolean[r][c][k+1];
		visited[0][0][0] = true;

		ArrayDeque<Node> q = new ArrayDeque<>();
		q.addLast(stt);

		int[] dr = { -1, 1, 0, 0, -2, -1, 1, 2, 2, 1, -1, -2 }; // 상,하,좌,우,대각1,대각2,...,대각8
		int[] dc = { 0, 0, -1, 1, 1, 2, 2, 1, -1, -2, -2, -1 };

		// search
		while(!q.isEmpty()) {

			Node cur = q.removeFirst();

			// end of map
			if (cur.r == r-1 && cur.c == c-1) return cur.totalMove;

			// search
			for (int i=0; i<12; i++) {

				// 말 움직임 검증 -> horseMove >= k 라면 더이상의 말 움직임은 못가져감
				if (i>=4 && cur.horseMove >= k) break;

				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				// validate
				if (nr<0 || nr>=r || nc<0 || nc>=c) continue;
				if (map[nr][nc] == 1) continue;
				if (visited[nr][nc][i>=4 ? cur.horseMove+1 : cur.horseMove]) continue;

				visited[nr][nc][i>=4 ? cur.horseMove+1 : cur.horseMove] = true;
				q.addLast(new Node(nr, nc, cur.totalMove+1, i>=4 ? cur.horseMove+1 : cur.horseMove));
			}
		}

		return -1; // 시작점에서 도착점까지 갈 수 없는 경우
	}



	static class Node {

		int r,c,totalMove,horseMove;

		Node(int r, int c, int totalMove, int horseMove) {
			this.r = r;
			this.c = c;
			this.totalMove = totalMove;
			this.horseMove = horseMove;
		}
	}

}