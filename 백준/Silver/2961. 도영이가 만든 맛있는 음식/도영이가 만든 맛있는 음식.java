import java.util.*;
import java.io.*;


public class Main {
	
	static StringTokenizer st;
	static int n;
	static int[][] ingredients;
	static int minDiff = Integer.MAX_VALUE;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		ingredients = new int[n][2];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			ingredients[i][0] = Integer.parseInt(st.nextToken());
			ingredients[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// dfs
		visited = new boolean[n];
		for (int i=1; i<n+1; i++) {
			dfs(0, i, 1, 0);
		}
		
		// result
		System.out.print(minDiff);
		
	}
	
	
	static void dfs(int size, int goal, int s, int b) {
		
		if (size == goal) {
			minDiff = Math.min(minDiff, Math.abs(s-b));
			return;
		}
		
		for (int i=0; i<n; i++) {
			
			// visited
			if (visited[i]) continue;
			
			// backtracking
			visited[i] = true;
			dfs(size+1, goal, s*ingredients[i][0], b+ingredients[i][1]);
			visited[i] = false;
			
		}
	}

}
