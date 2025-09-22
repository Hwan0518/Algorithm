import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int n;
	static int m;
	static int[][] dist;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		dist = new int[n+1][n+1];
		for (int i=1; i<n+1; i++) {
			for (int j=1; j<n+1; j++) dist[i][j] = n;
			dist[i][i] = 0;
		}

		for (int i=0; i<m; i++) {

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			dist[a][b] = 1;
			dist[b][a] = 1;

		}

		// bfs
		fw();

		// result
		int ans = 1;
		int min = Integer.MAX_VALUE;
		for (int i=1; i<n+1; i++) {

			int sum = Arrays.stream(dist[i]).sum();
			if (sum < min) {
				min = sum;
				ans = i;
			}

		}
		System.out.print(ans);

	}



	static void fw() {

		for (int k=1; k<n+1; k++) {
			for (int i=1; i<n+1; i++) {
				for (int j=1; j<n+1; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

	}

}