
import java.io.*;
import java.util.*;


public class Main {
	
	static int n;
	static int m;
	static boolean[] visited;
	static int[] selected;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// Backtracking
		visited = new boolean[n+1];
		selected = new int[n+1];
		sb = new StringBuilder();
		dfs(0);
		
		// result
		sb.setLength(sb.length()-1);
		System.out.print(sb);
		
	}
	
	
	// backtracking dfs
	public static void dfs(int cnt) {
		if (cnt == m) {
			for (int i=1; i<=m; i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i=1; i<=n; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			selected[cnt+1] = i;
			dfs(cnt+1);
			visited[i] = false;
		}
	}
		
}