
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n;
	static int m;
	static List<Integer>[] edge;
	static boolean[] visited;
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		edge = new ArrayList[n+1];
		for (int i=1; i<n+1; i++) edge[i] = new ArrayList<>();
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edge[a].add(b);
			edge[b].add(a);
		}
		
		
		// dfs
		visited = new boolean[n+1];
		visited[1] = true;
//		dfs(1);
		bfs(1);
		
		// result
		System.out.print(cnt);
			
	}
	
	
	
	static void dfs(int node) {
		
		for (int next : edge[node]) {
		
			if (visited[next]) continue;
			visited[next] = true;
			cnt ++;
			
			dfs(next);
		
		}
	}
	
	
	static void bfs(int stt) {
		
		Deque<Integer> q = new ArrayDeque<>();
		q.add(stt);
		
		while(!q.isEmpty()) {
			
			int node = q.pop();
			
			for (int next : edge[node]) {
				
				if (visited[next]) continue;
				visited[next] = true;
				cnt ++;
				
				q.add(next);
				
			}
			
		}
		
	}
		
	
	
}