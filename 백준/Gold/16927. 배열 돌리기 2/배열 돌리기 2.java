import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m, rCnt;
	static int[][] arr;
	static List<int[]> layers;
	static int layerCnt;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		rCnt = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}

		// set One-D arr
		layerCnt = Integer.min(n, m) / 2;
		layers = new ArrayList<>();
		setOneD();

		// rotate
		int[][] result = rotate();

		// result
		sb = new StringBuilder();
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) sb.append(result[i][j]).append(" ");
			sb.append("\n");
		}

		System.out.print(sb);
	}


	static int[][] rotate() {

		int[][] result = new int[n][m];

		int upLimit = 1;
		int downLimit = n-1;
		int rightLimit = m-1;
		int leftLimit = 0;

		for (int i=0; i<layerCnt; i++) {

			int[] oneD = layers.get(i);
			int size = oneD.length;
			int repeat = rCnt % size;

			int r = i;
			int c = i;
			int dir = 0;

			int cnt = 0;
			while (cnt < size) {

				result[r][c] = oneD[(repeat + cnt) % size];
				cnt ++;

				// 오른쪽으로
				if (dir == 0) {
					// 끝에 도달하면 아래로
					if (c == rightLimit) {
						dir = (dir + 1) % 4;
						r++;
					} else c++;
				}

				// 아래로
				else if (dir == 1) {
					// 끝에 도달하면 왼쪽으로
					if (r == downLimit) {
						dir = (dir + 1) % 4;
						c--;
					} else r++;
				}

				// 왼쪽으로
				else if (dir == 2) {
					// 끝에 도달하면 위로
					if (c == leftLimit) {
						dir = (dir + 1) % 4;
						r--;
					} else c--;
				}

				// 위로
				else {
					// 끝에 도달하면 오른쪽으로
					if (r == upLimit) {
						dir = (dir + 1) % 4;
						c++;
					} else r--;
				}
			}

			// limit update
			rightLimit--;
			downLimit--;
			leftLimit++;
			upLimit++;
		}
		
		return result;
	}


	static void setOneD() {

		int upLimit = 1;
		int downLimit = n-1;
		int rightLimit = m-1;
		int leftLimit = 0;

		int r = 0;
		int c = 0;
		int dir = 0;

		for (int i=0; i<layerCnt; i++) {

			int size = (n-(2*i) + m-(2*i) -3) *2 +2;
			layers.add(new int[size]);

			int cnt = 0;
			while (cnt < size) {

				layers.get(i)[cnt] = arr[r][c];
				cnt ++;

				// 오른쪽으로
				if (dir == 0) {
					// 끝에 도달하면 아래로
					if (c == rightLimit) {
						dir = (dir + 1) % 4;
						r++;
					} else c++;
				}

				// 아래로
				else if (dir == 1) {
					// 끝에 도달하면 왼쪽으로
					if (r == downLimit) {
						dir = (dir + 1) % 4;
						c--;
					} else r++;
				}

				// 왼쪽으로
				else if (dir == 2) {
					// 끝에 도달하면 위로
					if (c == leftLimit) {
						dir = (dir + 1) % 4;
						r--;
					} else c--;
				}

				// 위로
				else {
					// 끝에 도달하면 오른쪽으로
					if (r == upLimit) {
						dir = (dir + 1) % 4;
						c++;
					} else r--;
				}
			}

			// limit update
			rightLimit--;
			downLimit--;
			leftLimit++;
			upLimit++;
		}
	}


}
