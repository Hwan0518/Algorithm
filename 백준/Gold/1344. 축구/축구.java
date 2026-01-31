import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static double a,b;
	static double[][][] dp;
	static Set<Integer> primeNum = new HashSet<>();

	/*
	90분을 5분간격으로 -> 18구간
	각 구간별로 각팀이 한 골을 득점할 확률은 a,b이다
	여기서 말하는 소수는 Prime Number를 말한다

	dp[i][j][k] -> i번째 구간에서 a팀이 j골, b팀이 k골을 유지할 확률
	-> a,b 각각 최대 18골 가능하다

	넣었다 -> 확률은 (a or b)/100
	못넣었다 -> 확률은 (100- (a or b))/100

	각 구간에서 (0,0), (a/0), (0/b), (a/b) 일때의 확률을 구하고,
	최종 결과에서 prime number로 득점한 팀이 있다면 확률값을 더한다
	 */

	public static void main(String[] args) throws IOException {

		// input
		a = (double) Integer.parseInt(br.readLine()) /100;
		b = (double) Integer.parseInt(br.readLine()) /100;

		// set-up
		primeNum.addAll(List.of(2,3,5,7,11,13,17));

		dp = new double[19][19][19];
		for (int i=0; i<=18; i++) {
			for (int j=0; j<=18; j++) {
				for (int k=0; k<=18; k++) dp[i][j][k] = -1;
			}
		}

		// dfs
		double probability = 0D;
		int interval = 0;

		for (int aGoal=0; aGoal<=18; aGoal++) {
			for (int bGoal=0; bGoal<=18; bGoal++) {

				// 소수인 경우가 없다면 continue
				if (!primeNum.contains(aGoal) && !primeNum.contains(bGoal)) continue;

				// 소수인 경우만 확률 더하기
				probability += dfs(interval, aGoal, bGoal);
			}
		}

		// result
		System.out.println(probability);
	}


	// i번째 구간에서 a팀이 j골, b팀이 k골을 유지할 확률
	static double dfs(int i, int aGoal, int bGoal) {

		// base-condition
		if (aGoal < 0 || bGoal < 0) return 0D;
		if (i==18) {
			if (aGoal!=0 || bGoal!=0) return 0D;
			else return 1D;
		}

		// memoization
		if (dp[i][aGoal][bGoal] == -1) {

			double possibleP = 0D;

			// 둘 다 골을 못넣는 경우의 확률
			possibleP += (1-a) * (1-b) * dfs(i+1, aGoal, bGoal);

			// a만 골을 넣는 경우의 확률
			possibleP += a * (1-b) * dfs(i+1, aGoal-1, bGoal);

			// b만 골을 넣는 경우의 확률
			possibleP += (1-a) * b * dfs(i+1, aGoal, bGoal-1);

			// a와 b가 둘 다 골을 넣는 경우의 확률
			possibleP += a * b * dfs(i+1, aGoal-1, bGoal-1);

			// assign
			dp[i][aGoal][bGoal] = possibleP;
		}

		// result
		return dp[i][aGoal][bGoal];
	}
}
