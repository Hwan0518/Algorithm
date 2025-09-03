
import java.io.*;
import java.util.*;


public class Main {

	static StringBuilder sb;
	static StringTokenizer st;
	static int n;
	static int k;
	static boolean[] visited;
	static int minTime = Integer.MAX_VALUE;
	static int[] befores;
	
	
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
				
		visited = new boolean[100_001];
		befores = new int[100_001];
		
		// bfs
		bfs();
		
		// result
		sb = new StringBuilder();
		sb.append(minTime).append("\n");
		
		ArrayList<Integer> result = new ArrayList<>();
		result.add(k);
		
		int b = befores[k];
		while (b != n) {	
			result.add(b);
			b = befores[b];
		}
		if (k != n) result.add(n);
		Collections.reverse(result);
		
		for(int x : result) sb.append(x).append(" ");
		System.out.print(sb);
		
	}
	
	
	
	static void bfs() {
		
		Deque<Node> q = new ArrayDeque<>();
		q.add(new Node(n, 0, n));
		visited[n] = true;
		
		while (!q.isEmpty()) {
			
			Node cur = q.pop();
			befores[cur.x] = cur.before;
			
			// 갱신
			if (cur.x == k) {
				minTime = cur.t;
				break;
			}
			
			// 탐색
				
			int x1 = cur.x + 1;
			int x2 = cur.x - 1;
			int x3 = 2*cur.x;
			int[] xArr = {x1, x2, x3};
			
			for (int nx : xArr) {
				
				if (!(0<=nx && nx<=100_000)) continue;
				if (visited[nx]) continue;
				
				visited[nx] = true;
				q.add(new Node(nx, cur.t+1, cur.x));
			}
		}	
	}
	
	
	
	static class Node {
		
		int x;
		int t;
		int before;
		
		Node(int x, int t, int before) {
			this.x = x;
			this.t = t;
			this.before = before;
		}
		
	}
	
	
	
}