import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static BigInteger[] dp;

	public static void main(String[] args) throws IOException {

		// input
		String input = br.readLine();

		// set-up
		dp = new BigInteger[1 + 250];
		for (int i=0; i<= 250; i++) dp[i] = BigInteger.valueOf(-1);

		while (input!=null && !input.isBlank()) {
			n = Integer.parseInt(input);

			// dfs
//			sb.append(dfs(n)).append("\n");
			sb.append(dfs2(n)).append("\n");

			// next
			input = br.readLine();
		}

		// result
		System.out.print(sb);
	}


	// 2*cur의 직사각형을 2*1(가로,세로)과 2*2로 채우는 방법의 수
	static BigInteger dfs2(int cur) {

		// base-condition
		if (cur == 0) return BigInteger.ONE;
		if (cur == 1) return BigInteger.ONE;
		if (cur == 2) return BigInteger.valueOf(3);

		// search
		if (dp[cur].equals(BigInteger.valueOf(-1))) {

			BigInteger cnt = BigInteger.ZERO;

			// 2*1 세로 타입을 사용한 경우
			cnt = cnt.add(dfs2(cur - 1));

			// 2*1 가로 타입을 사용한 경우
			cnt = cnt.add(dfs2(cur - 2));

			// 2*2 정사각 타입을 사용한 경우
			cnt = cnt.add(dfs2(cur - 2));

			// assign
			dp[cur] = cnt;
		}

		// result
		return dp[cur];
	}


	// 2*cur의 직사각형을 2*1(가로,세로)과 2*2로 채우는 방법의 수
	static int dfs(int cur) {

		// base-condition
		if (cur == 1) return 1;
		if (cur == 2) return 3;

		// search
		int cnt = 0;

		// 2*1 세로 타입을 사용한 경우
		if (cur-1 > 0) cnt += dfs(cur-1);

		// 2*1 가로 타입을 사용한 경우
		if (cur-2 > 0) cnt += dfs(cur-2);

		// 2*2 정사각 타입을 사용한 경우
		if (cur-2 > 0) cnt += dfs(cur-2);

		// result
		return cnt;
	}


}
