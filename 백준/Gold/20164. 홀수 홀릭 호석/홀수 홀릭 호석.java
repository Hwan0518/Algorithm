import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		char[] charN = String.valueOf(n).toCharArray();

		// init odd count
		int total = 0;
		for (char num : charN) if ((int) num % 2 == 1) total ++;

		// dfs
		String cur = String.valueOf(n);
		dfs(cur, total);

		// result
		System.out.print(min + " " + max);
	}


	static void dfs(String cur, int total) {

		// 한자릿수
		if (cur.length() == 1) {
			min = Integer.min(min, total);
			max = Integer.max(max, total);
			return;
		}

		String next;
		int cntOdd;

		// 세 자릿수 이상인 경우
		if (cur.length() >= 3) {

			int size = cur.length();

			for (int idx1=1; idx1<size-1; idx1++) {
				for (int idx2=idx1+1; idx2<size; idx2++) {

					String c1 = cur.substring(0, idx1);
					String c2 = cur.substring(idx1, idx2);
					String c3 = cur.substring(idx2);
					
					next = String.valueOf(
						Integer.parseInt(c1)
							+Integer.parseInt(c2)
							+Integer.parseInt(c3));

					cntOdd = calcCntOdd(next);

					dfs(next, total + cntOdd);
				}
			}
		}

		// 두자릿수인 경우
		else {

			String c1 = cur.substring(0, 1);
			String c2 = cur.substring(1);

			next = String.valueOf(Integer.parseInt(c1) +Integer.parseInt(c2));

			cntOdd = calcCntOdd(next);

			dfs(next, total+cntOdd);
		}
	}


	static int calcCntOdd(String cur) {

		int cnt = 0;

		char[] arr = cur.toCharArray();
		for (char c : arr) if ((int) c%2 == 1) cnt++;

		return cnt;

	}

}
