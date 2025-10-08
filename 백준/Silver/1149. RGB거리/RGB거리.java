import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int[][] arr;
	static int min;
	static int[][] dp;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		arr = new int[n][3];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());

		}

		dp = new int[n][3];
		for (int i=0; i<n; i++) {
			for (int j=0; j<3; j++) dp[i][j] = -1;
		}

		// 재귀
//		backtrackingVersion(0, -1, 0);
//		min = returnVersion(0, -1);
		int min1 = topDown(0, 0);
		int min2 = topDown(0, 1);
		int min3 = topDown(0, 2);

		// result
		System.out.print(Integer.min(min1, Integer.min(min2, min3)));

	}


	// 메모이제이션 사용 -> size와 before일 때 어떤 값이었는지 상태를 저장 -> O(n^2)으로 해결 가능
	static int topDown(int size, int before) {

		if (size == n) return 0; // 기저조건

		// 메모이제이션 확인
		if (dp[size][before] != -1) return dp[size][before];

		// 탐색
		int minV = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			if (i != before) minV = Math.min(minV, arr[size][i] + topDown(size + 1, i));
		}

		// 최솟값
		dp[size][before] = minV;
		return dp[size][before];
	}


	// return 형태로 변환 -> size == n에서의 최솟값을 구한다, 매개변수 int total을 제거한다
	static int returnVersion(int size, int before) {

		if (size == n) return 0; // 0~n-1까지 모두 구했으므로, n에서 끝냄

		int total1 = before != 0 ? arr[size][0] + returnVersion(size+1, 0) : Integer.MAX_VALUE;
		int total2 = before != 1 ? arr[size][1] + returnVersion(size+1, 1) : Integer.MAX_VALUE;
		int total3 = before != 2 ? arr[size][2] + returnVersion(size+1, 2) : Integer.MAX_VALUE;

		return Integer.min(Integer.min(total1, total2), total3);

	}


	// 단순 백트 -> O(2^n) -> 최대 O(2^1000)
	static void backtrackingVersion(int size, int before, int total) {

		if (size == n) {
			min = Math.min(min, total);
			return;
		}

		for (int i=0; i<3; i++) {

			if (i == before) continue;
			backtrackingVersion(size+1, i, total+arr[size][i]);
		}
	}

}
