import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;



	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));


		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][m];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int[] commands = new int[r];
		for (int i=0; i<r; i++) commands[i] = Integer.parseInt(st.nextToken());

		// create Array
		Array array = new Array(arr, n, m);

		// do operation
		for (int opt : commands) {
			switch (opt) {
				case 1 : array.opt1(); break;
				case 2 : array.opt2(); break;
				case 3 : array.opt3(); break;
				case 4 : array.opt4(); break;
				case 5 : array.opt5(); break;
				case 6 : array.opt6(); break;
			}
		}

		// result
		for (int i=0; i<array.n; i++) {
			for (int j=0; j<array.m; j++) sb.append(array.arr[i][j]).append(" ");
			sb.append("\n");
		}

		System.out.print(sb);
	}



	static class Array {

		int[][] arr, res;
		int n,m;


		public Array(int[][] arr, int n, int m) {
			this.arr = arr;
			this.n = n;
			this.m = m;
		}


		// 상하 반전
		void opt1() {

			res = new int[n][m];

			// 상하반전 -> c를 순차증가, r을 거꾸로 읽음
			for (int c=0; c<m; c++) {
				for (int r=0; r<n; r++) {
					res[r][c] = arr[n-1-r][c];
				}
			}

			// switch
			arr = res;
		}


		// 좌우 반전
		void opt2() {

			res = new int[n][m];

			// 좌우반전 -> r을 순차증가, c를 거꾸로 읽음
			for (int r=0; r<n; r++) {
				for (int c=0; c<m; c++) {
					res[r][c] = arr[r][m-1-c];
				}
			}

			// switch
			arr = res;
		}


		// 오른쪽 90도 회전
		void opt3() {

			// 초기화
			res = new int[arr[0].length][arr.length];

			// 오른쪽 90도 회전 -> r은 c가되고, c는 n-1-r이 된다
			for (int r=0; r<n; r++) {
				for (int c=0; c<m; c++) {
					// nr = c, nc = n-1-r
					res[c][n-1-r] = arr[r][c];
				}
			}

			// switch
			switchArr();
		}


		// 왼쪽 90도 회전
		void opt4() {

			// 초기화
			res = new int[arr[0].length][arr.length];

			// 왼쪽 90도 회전 -> r은 m-1-c가되고, c는 r이 된다
			for (int r=0; r<n; r++) {
				for (int c=0; c<m; c++) {
					// nr = c, nc = r
					res[m-1-c][r] = arr[r][c];
				}
			}

			// switch
			switchArr();
		}


		// 부분배열 시계방향 회전
		void opt5() {

			res = new int[n][m];

			int[] dr = { 0, 0, n/2, n/2 };
			int[] dc = { 0, m/2, m/2, 0 };

			// 부분배열 위치 이동
			for (int i=0; i<4; i++) {

				int r = dr[i];
				int c = dc[i];
				int nr = dr[(i+1)%4];
				int nc = dc[(i+1)%4];

				for (int rCnt=0; rCnt<n/2; rCnt ++) {
					for (int cCnt=0; cCnt<m/2; cCnt++) {
						res[nr][nc] = arr[r][c];
						c ++;
						nc ++;
					}
					c = dc[i];
					nc = dc[(i+1)%4];

					r ++;
					nr ++;
				}
			}

			// switch
			arr = res;
		}


		// 부분배열 반시계방향 회전
		void opt6() {

			res = new int[n][m];

			int[] dr = { 0, n/2, n/2, 0 };
			int[] dc = { 0, 0, m/2, m/2 };

			// 부분배열 위치 이동
			for (int i=0; i<4; i++) {

				int r = dr[i];
				int c = dc[i];
				int nr = dr[(i+1)%4];
				int nc = dc[(i+1)%4];

				for (int rCnt=0; rCnt<n/2; rCnt ++) {
					for (int cCnt=0; cCnt<m/2; cCnt++) {
						res[nr][nc] = arr[r][c];
						c ++;
						nc ++;
					}
					c = dc[i];
					nc = dc[(i+1)%4];

					r ++;
					nr ++;
				}
			}

			// switch
			arr = res;
		}


		void switchArr() {
			this.arr = res;
			int temp = n;
			n = m;
			m = temp;
		}
	}

}