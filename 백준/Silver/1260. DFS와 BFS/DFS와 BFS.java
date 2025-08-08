
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n, m, v;
	static ArrayList<Integer>[] edges;
	static boolean[] visited;
	static StringBuilder result;
	
	
	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		result = new StringBuilder();
		
		// edges
		edges = new ArrayList[n+1];
		for (int i=0; i<n+1; i++) edges[i] = new ArrayList<>();
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[b].add(a);
		}
		for (int i=0; i<n+1; i++) Collections.sort(edges[i]);
		
		// dfs
		init();
		dfs(v);
		result.append("\n");
		
		// bfs
		init();
		bfs(v);
		
		// result
		System.out.print(result);	
	}
	
	// init
	static void init() {
		visited = new boolean[n+1];
	}
	
	// dfs
	static void dfs(int node) {
		// visit
		visited[node] = true;
		// record visit node
		result.append(node).append(" ");
		// search edge
		for(int next : edges[node]) {
			// already visited.. so next dfs no effect. why don't works?
			if (visited[next]) continue;
			dfs(next);
		}
	}
	
	// bfs
	static void bfs(int stt) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(stt);
		visited[stt] = true;
		while (!q.isEmpty()) {
			// cur node
			int cur = q.remove();
			// record visit node
			result.append(cur).append(" ");
			for (int next : edges[cur]) {
				if (visited[next]) continue;
				// visit
				visited[next] = true;
				q.add(next);
			}
		}
	}
	
}