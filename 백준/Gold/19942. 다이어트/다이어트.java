import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int mp, mf, ms, mv;
	static boolean[] visited;
	static int[][] arr;
	static int min = Integer.MAX_VALUE;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		mp = Integer.parseInt(st.nextToken());
		mf = Integer.parseInt(st.nextToken());
		ms = Integer.parseInt(st.nextToken());
		mv = Integer.parseInt(st.nextToken());

		arr = new int[n][5];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}

		visited = new boolean[n];

		// dfs
		dfs(0, 0, 0, 0, 0, 0);

		// result
		System.out.println(sb.length() == 0 ? -1 : min);
		System.out.print(sb.toString());
	}


	// dfs
	static void dfs(int size, int cur, int cp, int cf, int cs, int cv) {

		// end condition
		if (size == n || (cp>=mp && cf>=mf && cs>=ms && cv>=mv)) {

			if ((cp>=mp && cf>=mf && cs>=ms && cv>=mv) && cur < min) {

				min = cur;
				sb.setLength(0);

				for (int i=0; i<n; i++) {
					if (visited[i]) sb.append(i+1).append(" ");
				}
			}

			return;
		}

		// select
		visited[size] = true;
		dfs(size+1,
			cur+arr[size][4],
			cp+arr[size][0],
			cf+arr[size][1],
			cs+arr[size][2],
			cv+arr[size][3]);
		visited[size] = false;

		// non-select
		dfs(size+1, cur, cp, cf, cs, cv);
	}


}
