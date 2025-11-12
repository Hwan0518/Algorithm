import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static int[] arr;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

		// twoPointer
		int max = twoPointer();

		// result
		System.out.print(max);
	}


	static int twoPointer() {

		int p1 = 0;
		int p2 = n-1;
		int max = calc(p1, p2);

		while (p1 < p2) {

			if (arr[p1] < arr[p2]) p1 ++;
			else p2 --;

			max = Integer.max(max, calc(p1, p2));
		}

		return max;
	}


	static int calc(int p1, int p2) {
		return (p2-p1-1) * Integer.min(arr[p1], arr[p2]);
	}


}