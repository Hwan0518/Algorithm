import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m, max;
	static int[][] map;
	static int[][][] tet;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		max = 0;

		map = new int[n][m];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		// set tetromino
		setTetromino();

		// search
		search();

		// result
		System.out.print(max);
	}


	static void search() {

		for (int t=0; t<5; t++) {

			int[][] cur = arrCopy(t);
			for (int rev=0; rev<2; rev++) {

				// y축 대칭 -> x=-x
				if (rev == 1) reverse(cur);
				for (int rot=0; rot<4; rot++) {

					// 회전
					if (rot > 0) rotate(cur);

					// 최대 합 구하기
					calcSum(cur);
				}
			}
		}
	}


	static void calcSum(int[][] cur) {

		for (int r=0; r<n; r++) {
			for (int c=0; c<m; c++) {

				int curSum = 0;
				boolean isValid = true;
				for (int i=0; i<4; i++) {

					int nr = r + cur[i][0];
					int nc = c + cur[i][1];

					if (nr<0 || nr>=n || nc<0 || nc>=m) {
						isValid = false;
						break;
					}

					curSum += map[nr][nc];
				}

				// update max
				if (isValid) max = Integer.max(max, curSum);
			}
		}
	}


	static void rotate(int[][] cur) {
		for (int i=0; i<4; i ++) {
			int x = cur[i][0];
			int y = cur[i][1];
			cur[i][0] = y;
			cur[i][1] = -x;
		}
	}


	static void reverse(int[][] cur) {
		for (int i=0; i<4; i++) cur[i][0] = -cur[i][0];
	}


	static int[][] arrCopy(int t) {

		int[][] copy = new int[4][2];

		for (int i=0; i<4; i++) {
			for (int j=0; j<2; j++) copy[i][j] = tet[t][i][j];
		}

		return copy;
	}


	static void setTetromino() {

		tet = new int[5][4][2];

		// 첫번째
		tet[0][0] = new int[] { 0, 0 };
		tet[0][1] = new int[] { 0, 1 };
		tet[0][2] = new int[] { 0, 2 };
		tet[0][3] = new int[] { 0, 3 };

		// 두번째
		tet[1][0] = new int[] { 0, 0 };
		tet[1][1] = new int[] { 0, 1 };
		tet[1][2] = new int[] { 1, 0 };
		tet[1][3] = new int[] { 1, 1 };

		// 세번째
		tet[2][0] = new int[] { 0, 0 };
		tet[2][1] = new int[] { 1, 0 };
		tet[2][2] = new int[] { 2, 0 };
		tet[2][3] = new int[] { 2, 1 };

		// 네번째
		tet[3][0] = new int[] { 0, 0 };
		tet[3][1] = new int[] { 1, 0 };
		tet[3][2] = new int[] { 1, 1 };
		tet[3][3] = new int[] { 2, 1 };

		// 다섯번째
		tet[4][0] = new int[] { 0, 0 };
		tet[4][1] = new int[] { 0, 1 };
		tet[4][2] = new int[] { 0, 2 };
		tet[4][3] = new int[] { 1, 1 };

	}

}