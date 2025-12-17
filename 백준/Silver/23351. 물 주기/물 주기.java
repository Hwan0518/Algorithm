import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;


	static int[] arr;
	static int n, k, a, b;
	static int die = -1;

	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		arr = new int[n];
		for (int i=0; i<n; i++) arr[i] = k;

		// dfs
		dfs(1, -a);

		// result
		System.out.print(die);
	}


	static void dfs(int day, int before) {

		if (die != -1) return;

		if (before == n-a) before = -a;
		else before = Integer.min(before, n-a);

		// select
		for (int i=before+a; i<=n-a; i++) {

			// water
			for (int j=0; j<a; j++) arr[i+j] += b;

			// decrease
			for (int j=0; j<n; j++) {

				arr[j] --;

				// if die?
				if (arr[j] == 0) {
					die = day;
					return;
				}
			}

			// next Day
			dfs(day+1, i);
			if (die != -1) return;

			// rollBack
			for (int j=0; j<a; j++) arr[i+j] -= b;
			for (int j=0; j<n; j++) arr[j] ++;
		}
	}

}