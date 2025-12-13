import java.io.*;
import java.util.*;

public class Main {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;

	public static void main(String[] args) throws IOException {

		n = Integer.parseInt(br.readLine());

		// dfs
		boolean samsam = false;
		if (n != 0) {
			int exp = findBiggestExp();
			samsam = dfs(exp, n);
		}

		// greedy
//		boolean samsam = greedy();

		// result
		System.out.print(samsam ? "YES" : "NO");
	}


	static boolean greedy() {

		// 제일 큰 exp를 구한다
		int exp = findBiggestExp();

		// n을 만들 수 있는지 확인
		while (n > 0 && exp >=0) {

			// 3**exp 뺀다
			int cur = (int) Math.pow(3, exp);
			if (n-cur >= 0) n -= cur;

			// exp 무조건 감소
			exp --;
		}

		// n이 0이라면 가능
		return n == 0;
	}


	static boolean dfs(int exponent, int cur) {

		// base-condition
		if (cur <= 0 || exponent < 0) {
			if (cur == 0) return true;
			return false;
		}

		// select
		boolean	res1 = dfs(exponent-1, cur - (int) Math.pow(3, exponent));

		// non-select
		boolean res2 = dfs(exponent-1, cur);

		// result
		return res1 || res2;
	}


	static int findBiggestExp() {

		// 제일 큰 exponent를 구한다
		long exp = 0;
		while (Math.pow(3, exp) <= n) exp ++;
		exp --;

		return (int) Long.max(exp, 0);
	}

}