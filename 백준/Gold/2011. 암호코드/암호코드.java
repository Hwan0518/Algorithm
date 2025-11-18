import java.io.*;
import java.util.*;


/**
 * 26 이하의 숫자중 어떤걸 고를지 선택하면 됨
 * dfs to dp로 가자
 * return에 %1_000_000 해주면 됨
 */
public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, cnt;
	static String pw;
	static int[] dp;
	static boolean wrong = false;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		pw = br.readLine().strip();
		n = pw.length();

		// dfs2
//		int i = 0;
//		cnt = dfs2(i);

		// dfs3
		dp = new int[n];
		for (int j=0; j<n; j++) dp[j] = -1;
		int i = 0;
		cnt = dfs3(i);

		// result
		System.out.print(cnt);
	}


	// i번째에서 나올 수 있는 전체 가짓수
	static int dfs3(int i) {

		// base condition
		if (i == n) return 1;

		// 만약 현재 암호가 0이라면 안되는 경우임
		if (pw.charAt(i) == '0') {
			return 0;
		}

		// memoization
		if (dp[i] == -1) {

			// 각 case별 cnt
			int cnt1 = 0;
			int cnt2 = 0;
			int cnt3 = 0;
			int cnt4 = 0;
			int cnt5 = 0;

			// i가 마지막 문자라면 무조건 선택. 선택 안하는 경우 없음
			if (i==n-1) {
				cnt1 = dfs3(i+1);
			}

			// 마지막 문자가 아닌 경우
			else {

				int n1 = pw.charAt(i) - '0';
				int n2 = pw.charAt(i+1) - '0';

				// (i번째) + (i+1)번째가 26보다 크다면, 무조건 현재'만' 선택. 선택 안하는 경우 없음
				if (n1*10 + n2 > 26) {
					cnt2 = dfs3(i+1);
				}

				// i+1번째가 0이라면 무조건 두 개 선택해야한다
				else if (n2 == 0) {
					cnt3 = dfs3(i+2);
				}

				// 그 외의 경우는 현재만 선택해도 되고, 두 개 연속으로 선택해도 됨
				else {
					// 현재만 선택
					cnt4 = dfs3(i+1);

					// 두개 연속으로 선택
					cnt5 = dfs3(i+2);
				}
			}

			// 갱신
			dp[i] = (cnt1+cnt2+cnt3+cnt4+cnt5) % 1_000_000;
		}

		// dp
		return dp[i];
	}


	static int dfs2(int i) {

		// base condition
		if (i == n) return 1;

		// 각 case별 cnt
		int cnt1 = 0;
		int cnt2 = 0;
		int cnt3 = 0;
		int cnt4 = 0;

		// i가 마지막 문자라면 무조건 선택. 선택 안하는 경우 없음
		if (i==n-1) {
			cnt1 = dfs2(i+1);
		}

		// 마지막 문자가 아닌 경우
		else {

			int n1 = pw.charAt(i) - '0';
			int n2 = pw.charAt(i+1) - '0';

			// (i번째) + (i+1)번째가 26보다 크다면, 무조건 현재'만' 선택. 선택 안하는 경우 없음
			if (n1*10 + n2 > 26) {
				cnt2 = dfs2(i+1);
			}

			// 그 외의 경우는 현재만 선택해도 되고, 두 개 연속으로 선택해도 됨
			else {
				// 현재만 선택
				cnt3 = dfs2(i+1);

				// 두개 연속으로 선택
				cnt4 = dfs2(i+2);
			}
		}

		return cnt1 + cnt2 + cnt3 + cnt4;
	}


}
