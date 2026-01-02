import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n,m,max;
	static int[][] map;

	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			String row = br.readLine();
			for (int j = 0; j < m; j++) map[i][j] = row.charAt(j) - '0';
		}

		// find max perfect square number
		max = -1;

		// 1. 행/열의 차가 모두 0인 경우
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int v = map[i][j];
				int sqrt = (int) Math.sqrt(v);

				if (sqrt * sqrt == v) max = Integer.max(max, v);
			}
		}

		// 2. 행의 차가 0인 경우 (행 고정)
		for (int i = 0; i < n; i++) {
			for (int diff = 1; diff < m; diff++) {
				for (int stt=0; stt<m; stt++) {

					// 증가하는 방향
					sb = new StringBuilder();
					for (int j=stt; j<m; j+=diff) updateMax(i, j);

					// 감소하는 방향
					sb = new StringBuilder();
					for (int j=m-1-stt; j>=0; j-=diff) updateMax(i, j);
				}
			}
		}

		// 3. 열의 차가 0인 경우 (열 고정)
		for (int j = 0; j < m; j++) {
			for (int diff = 1; diff < n; diff++) {
				for (int stt=0; stt<n; stt++) {

					// 증가하는 방향
					sb = new StringBuilder();
					for (int i=stt; i<n; i+=diff) updateMax(i, j);

					// 감소하는 방향
					sb = new StringBuilder();
					for (int i=n-1-stt; i>=0; i-=diff) updateMax(i, j);
				}
			}
		}

		// 4. 행/열 모두 차가 1 이상인 경우
		for (int rDiff = 1; rDiff < n; rDiff++) {
			for (int cDiff = 1; cDiff < m; cDiff++) {
				for (int sttR = 0; sttR < n; sttR++) {
					for (int sttC = 0; sttC < m; sttC++) {

						int r = sttR;
						int c = sttC;

						// 행 증가, 열 증가
						sb = new StringBuilder();
						while (r<n && c<m) {
							updateMax(r, c);
							r += rDiff;
							c += cDiff;
						}

						// 행 증가, 열 감소
						r = sttR;
						c = m-1-sttC;
						sb = new StringBuilder();
						while (r<n && c>=0) {
							updateMax(r, c);
							r += rDiff;
							c -= cDiff;
						}

						// 행 감소, 열 증가
						r = n-1-sttR;
						c = sttC;
						sb = new StringBuilder();
						while (r>=0 && c<m) {
							updateMax(r, c);
							r -= rDiff;
							c += cDiff;
						}

						// 행 감소, 열 감소
						r = n-1-sttR;
						c = m-1-sttC;
						sb = new StringBuilder();
						while (r>=0 && c>=0) {
							updateMax(r, c);
							r -= rDiff;
							c -= cDiff;
						}
					}
				}
			}
		}

		// result
		System.out.print(max);
	}


	static void updateMax(int i, int j) {
		sb.append(map[i][j]);

		int v = Integer.parseInt(sb.toString());
		int sqrt = (int) Math.sqrt(v);

		if (sqrt * sqrt == v) max = Integer.max(max, v);
	}

}