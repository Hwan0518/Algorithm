
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
		for (int i=0; i<n+1; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[b].add(a);
		}
		
		// sort
		for (int i=0; i<n+1; i++) {
			Collections.sort(edges[i]);
		}
		
		
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
		result.append(v).append(" ");
		visited = new boolean[n+1];
		visited[v] = true;
	}
	
	// dfs
	static void dfs(int stt) {
		for(int node : edges[stt]) {
			if (visited[node]) continue;
			visited[node] = true;
			result.append(node).append(" ");
			dfs(node);
		}
	}
	
	// bfs
	static void bfs(int stt) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.addAll(edges[stt]);
		while (!q.isEmpty()) {
			int node = q.remove();
			if (visited[node]) continue;
			visited[node] = true;
			q.addAll(edges[node]);
			result.append(node).append(" ");
		}
	}
	
}