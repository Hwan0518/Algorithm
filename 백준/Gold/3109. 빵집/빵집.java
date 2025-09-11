import java.io.*;
import java.util.*;




public class Main {

	static int n;
	static int m;
	static int[][] map;
	static int maxCnt = 0;
	static BufferedReader br;
	static StringTokenizer st;
	static boolean[][] visited;
	static int[] dr = { -1, 0, 1 };
	static int[] dc = { 1, 1, 1 };
	static boolean isArrived;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i=0; i<n; i++) {

			String[] row = br.readLine().split("");

			for (int j=0; j<m; j++) {
				map[i][j] = row[j].equals(".") ? 0 : 1;
			}

		}

		// dfs
		visited = new boolean[n][m];
		for (int i=0; i<n; i++) {
			visited[i][0] = true;
			isArrived = false;
			dfs(i, 0);
		}

		// result
		System.out.print(maxCnt);
	}


	static void dfs(int r, int c) {

		if (isArrived) return;

		if (c == m-1) {
			isArrived = true;
			maxCnt ++;
			return;
		}

		for (int i=0; i<3; i++) {

			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
			if (visited[nr][nc]) continue;
			if (map[nr][nc] == 1) continue;

			visited[nr][nc] = true; // 위를 고르는게 최적의 선택임. 따라서 visited를 다시 false로 해줄 필요가 없음
			dfs(nr, nc);

			if (isArrived) return;
		}

	}

}