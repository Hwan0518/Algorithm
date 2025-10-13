import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int m;
	static int[] arr;
	static int[] prefixSum;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

		// create prefix
		prefixSum = new int[n + 1];
		createPrefixSum();

		// find a to b sum
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			sb.append(prefixSum[b] - prefixSum[a-1]);
			sb.append("\n");
		}

		// result
		System.out.print(sb);
	}


	static void createPrefixSum() {

		for (int i=1; i<n+1; i++) prefixSum[i] = prefixSum[i-1] + arr[i-1];

	}

}
