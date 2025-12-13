import java.io.*;
import java.util.*;

public class Main {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int[][] map;
	static List<Node> gooApple = new ArrayList<>();
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 12시부터 시계방향
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		// input
		n = 10;

		visited = new boolean[n][n];

		map = new int[n][n];
		for (int i=0; i<n; i++) {

			char[] input = br.readLine().toCharArray();

			for (int j=0; j<n; j++) {

				char c = input[j];
				int v = c=='X'
					? 1
					: c=='O' ? 2 : -1;

				map[i][j] = v;

				if (v==1) gooApple.add(new Node(i, j));
			}
		}

		// put
		boolean appleWin = false;
		loop: for (Node loc : gooApple) {

			visited[loc.r][loc.c] = true;

			for (int i=0; i<8; i++) {

				int nr = loc.r + dr[i];
				int nc = loc.c + dc[i];

				// validate
				if (nr<0 || nr>=n || nc<0 || nc>=n) continue;
				if (map[nr][nc] != -1) continue;
				if (visited[nr][nc]) continue;

				visited[nr][nc] = true;
				map[nr][nc] = 1;

				// search -> i방향과 i반대방향만 탐색하면 됨
				int cur = 1;
				cur += dfs(loc, i);
				cur += dfs(loc, (i+4)%8);
				
				if (cur >= 5) {
					appleWin = true;
					break loop;
				}
			
				// rollback
				map[nr][nc] = -1;
				visited[nr][nc] = false;
			}
		}

		// result
		System.out.print(appleWin ? 1 : 0);
	}


	static int dfs(Node cur, int dir) {

		int nr = cur.r + dr[dir];
		int nc = cur.c + dc[dir];

		// validate
		if (nr<0 || nr>=n || nc<0 || nc>=n) return 0;
		if (map[nr][nc] != 1) return 0;

		// next
		return dfs(new Node(nr, nc), dir)+1;
	}


	static class Node {

		int r,c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}