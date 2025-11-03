import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int[][] map;
	static int[] cnt;
	static int min = 26;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int remain = 0;
		map = new int[11][11];
		for (int i=1; i<=10; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) remain ++;
			}
		}

		cnt = new int[1 +5];
		for (int i=1; i<=5; i++) cnt[i] = 5;

		// dfs
		int r = 1;
		int c = 1;
		int used = 0;
		dfs(r, c, used, remain);

		// result
		System.out.print(min==26 ? -1 : min);
	}


	static void dfs(int r, int c, int used, int remain) {

		if (r==11 || remain == 0) {
			if (remain == 0) min = Integer.min(min, used);
			return;
		}

		if (c == 11) {
			dfs(r+1, 1, used, remain);
			return;
		}

		if (map[r][c] == 0) {
			dfs(r, c+1, used, remain);
			return;
		}

		for (int size=1; size<=5; size++) {

			if (cnt[size] == 0) continue;
			if (!validate(r, c, size)) continue;

			cover(r, c, size, 0);
			cnt[size] --;
			dfs(r, c+size, used+1, remain-size*size);
			cover(r, c, size, 1);
			cnt[size] ++;
		}
	}


	static void cover(int r, int c, int size, int value) {

		for (int i=r; i<=r+size-1; i++) {
			for (int j=c; j<=c+size-1; j++) map[i][j] = value;
		}
	}


	static boolean validate(int r, int c, int size) {

		int nr = r+size-1;
		int nc = c+size-1;

		if (nr > 10 || nc > 10) return false;
		
		for (int i=r; i<=nr; i++) {
			for (int j=c; j<=nc; j++) {
				if (map[i][j] == 0) return false;
			}
		}

		return true;
	}


}
