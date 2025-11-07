import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static int[] target;
	static int[] score;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		target = new int[m];
		for (int i=0; i<m; i++) target[i] = Integer.parseInt(st.nextToken());

		// game
		score = new int[n];
		for (int i=0; i<m; i++) {

			int curTarget = target[i];

			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				if (Integer.parseInt(st.nextToken()) == curTarget) score[j] ++;
				else score[curTarget-1] ++;
			}
		}

		// result
		for (int i = 0; i < n; i++) sb.append(score[i]).append("\n");
		System.out.print(sb);
	}

}
