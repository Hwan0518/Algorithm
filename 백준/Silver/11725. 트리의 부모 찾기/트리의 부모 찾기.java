
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n;
	static List<Integer>[] edge;
	static int[] parent;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		n = Integer.parseInt(br.readLine());
		
		edge = new ArrayList[n+1];
		for (int i=1; i<n+1; i++) edge[i] = new ArrayList<>();
		for (int i=0; i<n-1; i++) {
			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edge[a].add(b);
			edge[b].add(a);
			
		}
		
		// bfs
		parent = new int[n+1];
		visited = new boolean[n+1];
		bfs();
		
		// result
		StringBuilder sb = new StringBuilder();
		for (int i=2; i<n+1; i++) sb.append(parent[i]).append("\n");
		
		System.out.print(sb);
	}
	
	
	
	static void bfs() {
		
		Deque<Integer> q = new ArrayDeque<>();
		q.add(1);
		
		while (!q.isEmpty()) {
			
			int cur = q.pop();
			visited[cur] = true;
			
			for (int child : edge[cur]) {
				
				if (visited[child]) continue;
				
				parent[child] = cur;
				q.add(child);
				
			}
		}
	}
	
	
}