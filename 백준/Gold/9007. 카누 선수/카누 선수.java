import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, k;
	static int[][] arr;
	static int aValue = 1_000_000_000;

	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());


		for (int i=0; i<t; i++) {

			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());

			arr = new int[4][n];
			for (int j=0; j<4; j++) {

				st = new StringTokenizer(br.readLine());
				for (int num=0; num<n; num++) arr[j][num] = Integer.parseInt(st.nextToken());
			}

			solution();
		}

		// result
		System.out.print(sb);
	}


	static void solution() {

		// dfs
		aValue = twoPointer();

		// result
		sb.append(aValue).append("\n");
	}


	static int twoPointer() {

		// set-up
		int[] ab = new int[n*n];
		int[] cd = new int[n*n];

		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {

				ab[i*n +j] = arr[0][i] + arr[1][j];
				cd[i*n +j] = arr[2][i] + arr[3][j];
			}
		}

		Arrays.sort(ab);
		Arrays.sort(cd);

		// search
		int p1 = 0;
		int p2 = n*n-1;

		int ans = ab[p1] + cd[p2];
		int minDiff = Math.abs(k - ans);

		while (p1 < n*n && p2 >= 0) {

			int sum = ab[p1] + cd[p2];
			int curDiff = Math.abs(k - sum);

			if (curDiff == 0) return sum;

			if (curDiff < minDiff || (curDiff == minDiff && sum < ans)) {
				ans = sum;
				minDiff = curDiff;
			}

			if (sum < k) p1 ++; // k에 가까워져야 하므로 커지는 방향
			else p2 --; // 작아지는 방향
		}

		return ans;
	}
}