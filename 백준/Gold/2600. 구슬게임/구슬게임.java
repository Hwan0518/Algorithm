import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int[] pick;
	static int[][][] dp;

	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());

		pick = new int[3];
		pick[0] = Integer.parseInt(st.nextToken());
		pick[1] = Integer.parseInt(st.nextToken());
		pick[2] = Integer.parseInt(st.nextToken());

		sb = new StringBuilder();
		for (int t=0; t<5; t++) {

			st = new StringTokenizer(br.readLine());
			int bucket1 = Integer.parseInt(st.nextToken());
			int bucket2 = Integer.parseInt(st.nextToken());

			dp = new int[2][bucket1+1][bucket2+1];
			for (int i=0; i<2; i++) {
				for (int j=0; j<=bucket1; j++) {
					for (int k=0; k<=bucket2; k++) {
						dp[i][j][k] = -1;
					}
				}
			}

			int result = dfs2(0, bucket1, bucket2);
			sb.append(result == 0 ? "A" : "B").append("\n");
		}

		// result
		System.out.print(sb);
	}


	static int dfs2(int turn, int bucket1, int bucket2) {

		if ((bucket1 == 0 && (bucket2 == pick[0] || bucket2 == pick[1] || bucket2 == pick[2]))
			|| (bucket2 == 0 && (bucket1 == pick[0] || bucket1 == pick[1] || bucket1 == pick[2]))
		) {
			return turn;
		}

		// memoization
		if (dp[turn][bucket1][bucket2] == -1) {

			int res = 0;
			int[] temp = new int[6];
			for (int i=0; i<6; i++) temp[i] = -1;

			// A -> 하나라도 0이 나오면 0을 return
			if (turn == 0) {
				// bucket, pick 완탐
				search(turn, bucket1, bucket2, temp);

				boolean aWin = false;
				for (int v : temp) {
					if (v==0) {
						aWin = true;
						break;
					}
				}
				if (!aWin) res = 1;
			}

			// B -> 전부 다 0이 나와야 0을 return
			else {
				// bucket, pick 완탐
				search(turn, bucket1, bucket2, temp);

				for (int v : temp) {
					if (v==1) {
						res = 1;
						break;
					}
				}
			}

			dp[turn][bucket1][bucket2] = res;
		}

		// return
		return dp[turn][bucket1][bucket2];

	}


	static int dfs(int turn, int bucket1, int bucket2) {

		if ((bucket1 == 0 && (bucket2 == pick[0] || bucket2 == pick[1] || bucket2 == pick[2]))
			|| (bucket2 == 0 && (bucket1 == pick[0] || bucket1 == pick[1] || bucket1 == pick[2]))
		) {
			return turn;
		}

		int res = 0;
		int[] temp = new int[6];
		for (int i=0; i<6; i++) temp[i] = -1;

		// A -> 하나라도 0이 나오면 0을 return
		if (turn == 0) {
			// bucket, pick 완탐
			search(turn, bucket1, bucket2, temp);

			boolean aWin = false;
			for (int v : temp) {
				if (v==0) {
					aWin = true;
					break;
				}
			}
			if (!aWin) res = 1;
		}

		// B -> 전부 다 0이 나와야 0을 return
		else {
			// bucket, pick 완탐
			search(turn, bucket1, bucket2, temp);

			for (int v : temp) {
				if (v==1) {
					res = 1;
					break;
				}
			}
		}

		return res;
	}


	static void search(int turn, int bucket1, int bucket2, int[] temp) {

		// bucket 선택
		for (int i=0; i<2; i++) {
			// pick 선택
			for (int j=0; j<3; j++) {

				int curPick = pick[j];

				if (i==0) {
					if (bucket1 - curPick < 0) continue;
					temp[j] = dfs2((turn + 1) % 2, bucket1 - curPick, bucket2);
				}

				else {
					if (bucket2 - curPick < 0) continue;
					temp[3+j] = dfs2((turn + 1) % 2, bucket1, bucket2 - curPick);
				}
			}
		}

	}

}
