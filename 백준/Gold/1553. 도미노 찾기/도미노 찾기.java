import java.io.*;
import java.util.*;

/*
- 한칸에서 90도 회전 4번 가능
- 회전에서 숫자 순서 바꾸는거 2번씩 가능
	-> 근데 사실 얘네는.. 구딩 막 회전을 시킬 필요는 없고, 걍 연속된 숫자들이 사용되었는지만 체크
- visited는 걍 HashSet으로 하자 ('01', '66', '25', '33' 등)
- 백트 ㄱㄱ해보자
*/

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int[] cntLimit = { 8, 8, 8, 8, 8, 8, 8 };
	static HashSet<String> visited = new HashSet<>();
	static int[][] map;
	static int cnt;



	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		boolean flag = false;
		map = new int[8][7];
		loop: for (int i=0; i<8; i++) {

			String[] row = br.readLine().split("");

			for (int j = 0; j < 7; j++) {

				int v = Integer.parseInt(row[j]);

				cntLimit[v]--;
				if (cntLimit[v] < 0) {
					flag = true;
					break loop;
				}

				map[i][j] = v;
			}
		}

		// dfs
		if (!flag) dfs(0, 0);

		// result
		System.out.print(cnt);
	}


	static void dfs(int r, int c) {

		if (r == 8) {
			if (visited.size() == 28) cnt ++;
			return;
		}

		if (c == 7) {
			dfs(r+1, 0);
			return;
		}

		if (map[r][c] == -1) {
			dfs(r, c+1);
			return;
		}

		int int1, int2;

		// 가로: 왼쪽, 오른쪽
		if (c != 0 && map[r][c-1] != -1) {
			int1 = map[r][c-1];
			int2 = map[r][c];

			String value = getValue(r, c-1, r, c);

			if (!visited.contains(value)) {

				visited.add(value);
				map[r][c-1] = -1;
				map[r][c] = -1;

				dfs(r, c+1);
				visited.remove(value);
				map[r][c-1] = int1;
				map[r][c] = int2;
			}
		}

		if (c != 6 && map[r][c+1] != -1) {
			int1 = map[r][c];
			int2 = map[r][c+1];

			String value = getValue(r, c, r,c+1);

			if (!visited.contains(value)) {

				visited.add(value);
				map[r][c] = -1;
				map[r][c+1] = -1;

				dfs(r, c+1);
				visited.remove(value);
				map[r][c] = int1;
				map[r][c+1] = int2;
			}
		}

		// 세로: 위, 아래
		if (r != 0 && map[r-1][c] != -1) {
			int1 = map[r-1][c];
			int2 = map[r][c];

			String value = getValue(r-1, c, r, c);

			if (!visited.contains(value)) {

				visited.add(value);
				map[r-1][c] = -1;
				map[r][c] = -1;

				dfs(r, c+1);
				visited.remove(value);
				map[r-1][c] = int1;
				map[r][c] = int2;
			}
		}

		if (r != 7 && map[r+1][c] != -1) {
			int1 = map[r][c];
			int2 = map[r+1][c];

			String value = getValue(r, c, r+1, c);

			if (!visited.contains(value)) {

				visited.add(value);
				map[r][c] = -1;
				map[r+1][c] = -1;

				dfs(r, c+1);
				visited.remove(value);
				map[r][c] = int1;
				map[r+1][c] = int2;
			}
		}
	}


	static String getValue(int r1, int c1, int r2, int c2) {
		return String.valueOf(Integer.min(map[r1][c1], map[r2][c2])) + Integer.max(map[r1][c1], map[r2][c2]);
	}

}
