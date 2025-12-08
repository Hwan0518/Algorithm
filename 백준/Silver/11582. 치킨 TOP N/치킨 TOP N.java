import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws IOException {

		// input
		int n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for (int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

		int k = Integer.parseInt(br.readLine());

		// sort -> n/k개로 수열을 나눠서 정렬
		int base = n/k;
		int[] cur = new int[base];
		for (int i=0; i<n; i+=base) {

			for (int j=0; j<base; j++) cur[j] = arr[i+j];

			Arrays.sort(cur);
			for (int j=0; j<base; j++) sb.append(cur[j]).append(" ");
		}

		// result
		System.out.print(sb);
	}

}
