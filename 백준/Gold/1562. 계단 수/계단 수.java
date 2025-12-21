import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int bit = (1 << 10); // 2**10자리만 1인 '10000000000', 1~9가 모두 0으로 꺼져있는 상태
	static int allVisit = (1 << 10) - 1; // bit에서 1을뺀 수. 따라서 '0111111111'가 된다. 즉, 0~9가 모두 켜져있는 상태
	static long[][][] dp;

	public static void main(String[] args) throws IOException {

		// input
		int n = Integer.parseInt(br.readLine());

		// dp
		dp = new long[10][n+1][bit];
		for (int i=0; i<10; i++) {
			for (int j=0; j<=n; j++) {
				for (int k=0; k<bit; k++) dp[i][j][k] = -1;
			}
		}

		long cnt = dfs2(-1, n, 0);

		// result
		System.out.print(cnt);
	}


	// dfs : 현재 방문상태에서, i로 시작했을 때, 만들 수 있는 j(n-size)자릿수 계단수 중, 0~9가 모두 등장하는 계단 수의 개수
	static long dfs2(int i, int j, int visitBit) {


		// base-condition : j==0일때 visitCnt를 확인
		if (j == 0) {
			if(visitBit == allVisit) return 1;
			return 0;
		}

		// cnt
		long cnt = 0;

		// 첫 탐색이라면 i=1~9로 만들 수 있는 j자리수를 전부 탐색
		if (i == -1) {

			for (int num=1; num<=9; num++) {
				cnt += (dfs2(num, j-1, 1 << num) % 1_000_000_000); // num만 1로 켜져있는 '0000num00000'을 넘김
			}

			return cnt % 1_000_000_000;
		}

		// memoization
		if (dp[i][j][visitBit] == -1) {

			int num1 = i-1;
			int num2 = i+1;

			if (num1 >= 0) {
				cnt += (dfs2(num1, j-1, visitBit | (1 << num1)) % 1_000_000_000);
			}

			if (num2 <= 9) {
				cnt += (dfs2(num2, j-1, visitBit | (1 << num2)) % 1_000_000_000);
			}

			dp[i][j][visitBit] = cnt % 1_000_000_000;
		}

		// result
		return dp[i][j][visitBit] % 1_000_000_000;
	}


}