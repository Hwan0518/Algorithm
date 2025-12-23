import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int[] arr;

	public static void main(String[] args) throws IOException {

		// input
		int test = Integer.parseInt(br.readLine());

		for (int t=0; t<test; t++) {

			String[] nums = br.readLine().split(" ");
			int n = nums.length;

			arr = new int[nums.length];
			for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(nums[i]);

			// gcd
			int max = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j) continue;
					max = Integer.max(max, calcGCD(arr[i], arr[j]));
				}
			}

			// ans
			sb.append(max).append("\n");
		}

		// result
		System.out.print(sb);
	}


	static int calcGCD(int i, int j) {

		int remain = i%j;

		if (remain == 0) {
			return j;
		}

		return calcGCD(j, remain);
	}

}