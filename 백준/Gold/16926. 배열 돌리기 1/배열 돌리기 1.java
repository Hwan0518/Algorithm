import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m, rCnt;
	static int[][] arr1;
	static int[][] arr2;
	static int[][][] arrs;
	static int lastTurn;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		rCnt = Integer.parseInt(st.nextToken());

		arr1 = new int[n][m];
		arr2 = new int[n][m];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());

			for (int j=0; j<m; j++) {

				int v = Integer.parseInt(st.nextToken());
				arr1[i][j] = v;
				arr2[i][j] = v;
			}
		}

		arrs = new int[2][n][m];
		arrs[0] = arr1;
		arrs[1] = arr2;

		// rotate
		rotate();

		// result
		sb = new StringBuilder();

		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) sb.append(arrs[lastTurn][i][j]).append(" ");
			sb.append("\n");
		}

		System.out.print(sb);
	}


	static void rotate() {

		int cnt = 0;
		int changeIdx = 0;
		int refIdx = 1;

		while (cnt < rCnt) {

			for (int r=0; r<n; r++) {
				for (int c=0; c<m; c++) {

					int curV = arrs[refIdx][r][c];

					// 아래로
					if (r >= c && r < n-1-c) {
						if ((m%2 == 1 && c <= m/2) || (m%2 == 0 && c < m/2)) {
							arrs[changeIdx][r + 1][c] = curV;
							continue;
						}
					}

					// 위로
					if (r > m-1-c) {
						if ((m%2 == 1 && r <= n-(m-c)) || (m%2 == 0 && r <= n-(m-c))) {
							arrs[changeIdx][r-1][c] = curV;
							continue;
						}
					}

					// 위쪽 절반 -> 왼쪽으로
					if (c > r && r < n/2) {
						arrs[changeIdx][r][c - 1] = curV;
						continue;
					}

					// 아래쪽 절반 -> 오른쪽으로
					if (c < m-(n-r)) {
						if ((n%2 == 1 && r > n/2) || (n%2 == 0 && r >= n/2)) {
							arrs[changeIdx][r][c + 1] = curV;
						}
					}
				}
			}

			lastTurn = changeIdx;
			changeIdx = (changeIdx +1) % 2;
			refIdx = (refIdx +1) % 2;
			cnt ++;
		}
	}


}
