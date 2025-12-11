import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int[][] map;
	static int[] dr = { 1, 0 };
	static int[] dc = { 0, 1 };

	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		map = new int[n][n];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		// bfs
		boolean possible = bfs();

		// result
		System.out.print(possible ? "HaruHaru" : "Hing");
	}


	static boolean bfs() {

		// set-up
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.addLast(new Node(0,0));
		boolean[][] visited = new boolean[n][n];
		visited[0][0] = true;

		// search
		while (!q.isEmpty()) {

			Node cur = q.removeFirst();

			// base-condition
			if (cur.r==n-1 && cur.c==n-1) return true;

			// 숫자만큼 이동
			int val = map[cur.r][cur.c];
			for (int i=0; i<2; i++) {

				int nr = cur.r + dr[i]*val;
				int nc = cur.c + dc[i]*val;

				// validate
				if (nr<0 || nr>= n || nc<0 || nc>=n) continue;
				if (visited[nr][nc]) continue;
				
				// move
				visited[nr][nc] = true;
				q.addLast(new Node(nr, nc));
			}
		}

		// result
		return false;
	}


	static class Node {

		int r,c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
