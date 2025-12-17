import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int[][] map;
	static int remain = 0;
	static boolean[][][] visited = new boolean[3][9][10];

	public static void main(String[] args) throws IOException {

		// input
		map = new int[9][9];
		for (int i=0; i<9; i++) {

			char[] rows = br.readLine().toCharArray();

			for (int j=0; j<9; j++) {

				int v = rows[j] - '0';

				// map에 대입
				map[i][j] = v;

				// visit처리
				visited[0][i][v] = true; // i번째 row의 v는 사용됨
				visited[1][j][v] = true; // j번째 column의 v는 사용됨

				int squareIdx = (i/3*3) + j/3;
				visited[2][squareIdx][v] = true; // sIdx번째 square의 v는 사용됨

				// 0이라면 remain 증가
				if (map[i][j] == 0) remain ++;
			}
		}

		// dfs
		dfs(0, 0);

		// result
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) sb.append(map[i][j]);
			sb.append("\n");
		}

		System.out.print(sb);
	}


	static void dfs(int r, int c) {

		// 모두 채운 경우 종료
		if (remain == 0) {
			return;
		}

		// r==9이지만 remain이 남은 경우
		if (r==9) {
			return;
		}

		// c가 끝에 도달한 경우 -> r 증가
		if (c==9) {
			dfs(r+1, 0);
			return;
		}

		// 현재칸이 0이 아닌 경우
		if (map[r][c] != 0) {
			dfs(r, c+1);
			return;
		}

		// 현재칸이 0인 경우
		for (int v=1; v<=9; v++) {

			// r행 확인
			if (visited[0][r][v]) continue;

			// c열 확인
			if (visited[1][c][v]) continue;

			// squareIdx사각형 확인
			int squareIdx = (r/3*3) + c/3;
			if (visited[2][squareIdx][v]) continue;

			// 사용되지 않았다면, 할당하고 다음칸 탐색
			visited[0][r][v] = true;
			visited[1][c][v] = true;
			visited[2][squareIdx][v] = true;
			map[r][c] = v;
			remain --;
			dfs(r, c+1);
			if (remain == 0) return; // remain == 0이라면 바로 return

			// 롤백
			visited[0][r][v] = false;
			visited[1][c][v] = false;
			visited[2][squareIdx][v] = false;
			map[r][c] = 0;
			remain ++;
		}
	}

}