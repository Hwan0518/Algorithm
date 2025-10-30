import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static int[] arr;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);

		// twoPointer
		int[] result = new int[2];
		result = twoPinter(result);

		// result
		int v1 = arr[result[0]];
		int v2 = arr[result[1]];
		System.out.print(v1 + " " + v2);
	}


	static int[] twoPinter(int[] result) {

		int p1 = 0;
		int p2 = n-1;
		int minDiff = Integer.MAX_VALUE;

		while (p1 < p2) {

			int sum = arr[p1] + arr[p2];
			int curDiff = Math.abs(arr[p1] + arr[p2]);

			if (sum == 0 || curDiff < minDiff) {
				minDiff = curDiff;
				result[0] = p1;
				result[1] = p2;
			}

			// 증가해야함
			if (sum < 0) p1 ++;

			// 감소해야함
			else p2 --;
		}

		return result;
	}


}
