import java.io.*;
import java.util.*;

public class Main {

	/*
	선택/비선택 2지선다 문제
	N이 최대 20이므로 2^20 = 100만. 이건 완탐도 가능하다
	 */

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int[] l,j;

	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		l = new int[n];
		for (int i=0; i<n; i++) l[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		j = new int[n];
		for (int i=0; i<n; i++) j[i] = Integer.parseInt(st.nextToken());

		int max = dfs(0, 100);
		System.out.print(max);
	}


	static int dfs(int i, int hp) {

		// base-condition
		if (hp <= 0 || i == n) {
			if (hp <= 0) return -1_000_000;
			return 0;
		}

		// find max happy
		int happy = 0;

		// select
		happy = Integer.max(happy, dfs(i+1, hp-l[i]) + j[i]);

		// non-select
		happy = Integer.max(happy, dfs(i+1, hp));

		// result
		return happy;
	}

}
