import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int[] arr;
	static boolean[][] visited;


	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		int[][] map = new int[n][n];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		visited = new boolean[n][n];

		// dfs
		int max = dfs(map, 0);

		// result
		System.out.print(max);
	}


	static int dfs(int[][] map, int cnt) {

		if (cnt == 5) {

			// 가장 큰 수 찾기
			int max = 0;
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) max = Integer.max(max, map[i][j]);
			}

			return max;
		}

		// 상하좌우 이동
		int max = 0;
		for (int i=0; i<4; i++) {

			// set-up
			int[][] nextMap = new int[n][n];
			visited = new boolean[n][n]; // 매번 초기화 (현재 세션에서만 사용됨)

			for (int r=0; r<n; r++) {
				for (int c=0; c<n; c++) nextMap[r][c] = map[r][c];
			}

			// move top
			if (i==0) {
				// r=0을 동기화
				for (int c=0; c<n; c++) nextMap[0][c] = map[0][c];

				// 위로 이동
				for (int r=1; r<n; r++) {
					for (int c=0; c<n; c++) {

						// 블럭 정보
						int target = nextMap[r-1][c]; // 이동할 위치의 블럭
						int targetR = r-1;
						int cur = nextMap[r][c];

						// cur이 비어있는경우
						if (cur == 0) continue;

						// 블럭이 없는 경우 -> 블럭이 있거나 끝에 도달할때까지 움직인다
						if (target == 0) {
							while (targetR > 0 && target == 0) {
								targetR --;
								target = nextMap[targetR][c];
							}
						}

						// 같은 수이고, 합쳐지지 않았다면 합친다
						if (target == cur && !visited[targetR][c]) {
							nextMap[targetR][c] = target+cur;
							visited[targetR][c] = true;
							nextMap[r][c] = 0;
						}

						// 이외의 경우라면 직전까지 움직인다
						else {
							int destR = (target == 0) ? targetR : (targetR + 1);
							if (destR != r) {
								nextMap[destR][c] = cur;
								nextMap[r][c] = 0;
							}
						}
					}
				}
			}

			// move bottom
			if (i==1) {
				// r=n-1을 동기화
				for (int c=0; c<n; c++) nextMap[n-1][c] = map[n-1][c];

				// 아래로 이동
				for (int r=n-2; r>=0; r--) {
					for (int c=0; c<n; c++) {

						// 블럭 정보
						int target = nextMap[r+1][c]; // 이동할 위치의 블럭
						int targetR = r+1;
						int cur = nextMap[r][c];

						// cur이 비어있는경우
						if (cur == 0) continue;

						// 블럭이 없는 경우 -> 블럭이 있거나 끝에 도달할때까지 움직인다
						if (target == 0) {
							while (targetR < n-1 && target == 0) {
								targetR ++;
								target = nextMap[targetR][c];
							}
						}

						// 같은 수이고, 합쳐지지 않았다면 합친다
						if (target == cur && !visited[targetR][c]) {
							nextMap[targetR][c] = target+cur;
							visited[targetR][c] = true;
							nextMap[r][c] = 0;
						}

						// 이외의 경우라면 직전까지 움직인다
						else {
							int destR = (target == 0) ? targetR : (targetR - 1);
							if (destR != r) {
								nextMap[destR][c] = cur;
								nextMap[r][c] = 0;
							}
						}
					}
				}
			}

			// move left
			if (i==2) {
				// c==0을 동기화
				for (int r=0; r<n; r++) nextMap[r][0] = map[r][0];

				// 왼쪽으로 이동
				for (int c=1; c<n; c++) {
					for (int r=0; r<n; r++) {

						// 블럭 정보
						int target = nextMap[r][c-1]; // 이동할 위치의 블럭
						int targetC = c-1;
						int cur = nextMap[r][c];

						// cur이 비어있는경우
						if (cur == 0) continue;

						// 블럭이 없는 경우 -> 블럭이 있거나 끝에 도달할때까지 움직인다
						if (target == 0) {
							while (targetC > 0 && target == 0) {
								targetC --;
								target = nextMap[r][targetC];
							}
						}

						// 같은 수이고, 합쳐지지 않았다면 합친다
						if (target == cur && !visited[r][targetC]) {
							nextMap[r][targetC] = target+cur;
							visited[r][targetC] = true;
							nextMap[r][c] = 0;
						}

						// 이외의 경우라면 직전까지 움직인다
						else {
							int destC = (target == 0) ? targetC : (targetC + 1);
							if (destC != c) {
								nextMap[r][destC] = cur;
								nextMap[r][c] = 0;
							}
						}
					}
				}
			}

			// move right
			else if (i == 3) {
				// c==n-1을 동기화
				for (int r=0; r<n; r++) nextMap[r][n-1] = map[r][n-1];

				// 오른쪽으로 이동
				for (int c=n-2; c>=0; c--) {
					for (int r=0; r<n; r++) {

						// 블럭 정보
						int target = nextMap[r][c+1]; // 이동할 위치의 블럭
						int targetC = c+1;
						int cur = nextMap[r][c];

						// cur이 비어있는경우
						if (cur == 0) continue;

						// 블럭이 없는 경우 -> 블럭이 있거나 끝에 도달할때까지 움직인다
						if (target == 0) {
							while (targetC < n-1 && target == 0) {
								targetC ++;
								target = nextMap[r][targetC];
							}
						}

						// 같은 수이고, 합쳐지지 않았다면 합친다
						if (target == cur && !visited[r][targetC]) {
							nextMap[r][targetC] = target+cur;
							visited[r][targetC] = true;
							nextMap[r][c] = 0;
						}

						// 이외의 경우라면 직전까지 움직인다
						else {
							int destC = (target == 0) ? targetC : (targetC - 1);
							if (destC != c) {
								nextMap[r][destC] = cur;
								nextMap[r][c] = 0;
							}
						}
					}
				}
			}

			// update Max
			max = Integer.max(max, dfs(nextMap, cnt+1));
		}

		// 최댓값
		return max;
	}

}
