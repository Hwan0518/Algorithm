import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int r;
	static int c;
	static int max = 0;
	static int[][] board;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static boolean[] alphabet;



	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());


		board = new int[r][c];
		for (int i=0; i< r; i++) {
			char[] row = br.readLine().toCharArray();
			for (int j=0; j<c; j++) board[i][j] = row[j]-65;
		}

		// dfs
		visited = new boolean[r][c];
		alphabet = new boolean[26];
		visited[0][0] = true;
		alphabet[board[0][0]] = true;
		dfs(new Node(0, 0), 1);

		// result
		System.out.print(max);
	}



	static void dfs(Node cur, int cnt) {

		boolean moved = false;

		for (int i=0; i<4; i++) {

			int nr = cur.r + dr[i];
			int nc = cur.c + dc[i];

			if (nr<0 || nr>=r || nc<0 || nc>=c) continue;
			if (visited[nr][nc]) continue;
			if (alphabet[board[nr][nc]]) continue;

			moved = true;

			visited[nr][nc] = true;
			alphabet[board[nr][nc]] = true;
			dfs(new Node(nr, nc), cnt+1);
			visited[nr][nc] = false;
			alphabet[board[nr][nc]] = false;

		}

		if (!moved) max = Math.max(max, cnt);
	}


	static class Node {

		int r;
		int c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}



}