import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int t, m, n;
	static int[][] map;
	static int cnt = 0;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());

		// solution
		sb = new StringBuilder();

		for (int test = 0; test < t; test ++) {

			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());

			// set-up map
			map = new int[m][n];
			for (int i=0; i<m; i++) {

				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}

			int result = solution();
			sb.append(result).append("\n");
		}

		// result
		System.out.print(sb);
	}


	static int solution() {

		int cnt = 0;

		// r = m-2 ~ 0까지 while로 반복 탐색 진행 (row 하나 이동하면 다시 r=m-2로 이동)
		int r = m-2;
		while (r >= 0) {

			boolean moving = false;
			for (int c=0; c<n; c++) {

				// 현재가 0이면 continue
				int curV = map[r][c];
				if (curV == 0) continue;

				// 아래가 1이면 continue
				int underV = map[r+1][c];
				if (underV == 1) continue;

				// 아래로 이동
				map[r+1][c] = 1;
				map[r][c] = 0;
				cnt ++;
				moving = true;
			}

			if (moving) r = m-2; // 움직인게 있다면 다시 초기화
			else r --;
		}

		return cnt;
	}


}
