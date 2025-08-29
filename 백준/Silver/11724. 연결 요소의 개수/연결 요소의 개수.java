
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n;
	static int m;
	static List<Integer>[] edge;
	static boolean[] visited;
	static int cnt;
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		edge = new ArrayList[n+1];
		for (int i=0; i<=n; i++) edge[i] = new ArrayList<>();
		for (int i=0; i<m; i++) {
			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edge[a].add(b);
			edge[b].add(a);
			
		}
		
		// dfs
		visited = new boolean[n+1];
		for (int i=1; i<=n; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			
			cnt ++;
			dfs(i);
		}
		
		// result
		System.out.print(cnt);
	}
	
	
	
	static void dfs(int node) {
		
		for (int next : edge[node]) {
			
			if (visited[next]) continue;
			visited[next] =true;
			dfs(next);
			
		}
		
	}
	
	
}