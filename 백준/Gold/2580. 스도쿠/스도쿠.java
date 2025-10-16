import java.io.*;
import java.sql.Array;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static boolean[][] row = new boolean[10][10];
	static boolean[][] col = new boolean[10][10];
	static boolean[][] square = new boolean[10][10];
	static int[][] squareIdx = { { 1,2,3 }, { 4,5,6 }, { 7,8,9 } };
	static int[][] map = new int[10][10];
	static List<Node> emptyLoc = new ArrayList<>();
	static boolean end = false;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		for (int r=1; r<=9; r++) {

			st = new StringTokenizer(br.readLine());

			for (int c=1; c<=9; c++) {

				int v = Integer.parseInt(st.nextToken());

				// empty
				if (v == 0) {
					emptyLoc.add(new Node(r, c));
					continue;
				}

				// non-empty
				row[r][v] = true;

				col[c][v] = true;

				int sIdx = squareIdx[(r-1)/3][(c-1)/3];
				square[sIdx][v] = true;

				map[r][c] = v;
			}
		}

		// dfs
		dfs(0);

		// result
		System.out.print(sb);
	}


	static void dfs(int idx) {

		if (end) return;

		// end condition
		if (idx == emptyLoc.size()) {

			for (int i=1; i<=9; i++) {
				for (int j=1; j<=9; j++) sb.append(map[i][j]).append(" ");
				sb.append("\n");
			}

			end = true;
			return;
		}

		// search
		Node cur = emptyLoc.get(idx);
		int r = cur.r;
		int c = cur.c;

		for (int num = 1; num <= 9; num++) {

			if (row[r][num] || col[c][num]) continue;

			int sIdx = squareIdx[(r-1)/3][(c-1)/3];
			if (square[sIdx][num]) continue;

			row[r][num] = true;
			col[c][num] = true;
			square[sIdx][num] = true;
			map[r][c] = num;
			dfs(idx+1);
			row[r][num] = false;
			col[c][num] = false;
			square[sIdx][num] = false;
			map[r][c] = 0;
		}
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
