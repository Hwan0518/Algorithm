
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n;
	static int k;
	static int[] map;
	static boolean[] visited;
	static int time = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[100_001];
		
		
		// bfs
		visited = new boolean[100_001];
		int stt = n;
		bfs(stt);

		
		
		// result
		System.out.println(time);
	}
	
	

	
	static void bfs(int stt) {
		
		Deque<Node> q = new ArrayDeque<>();
		q.add(new Node(stt, 0));
		visited[stt] = true;
		
		while (!q.isEmpty()) {
			
			Node node = q.pop();
			
			// check
			if (node.getX() == k) {
				time = Math.min(time, node.getT());
				continue;
			}
			
			// move
			int wL = node.getX()-1;
			int wR = node.getX()+1;
			int warp = node.getX() *2;
			int[] next = {wL, wR, warp};
			
			for (int nx : next) {
				
				// validate
				if (nx<0 || nx>100_000 || visited[nx]) continue;
				visited[nx] = true;
				
				// add q
				q.add(new Node(nx, node.getT()+1));				
			}
		}
	}
	
	
	
	static class Node {
		
		int x;
		int t;
		
		Node(int x, int t) {
			this.x = x;
			this.t = t;
		}
		
		int getX() {
			return this.x;
		}
		
		int getT() {
			return this.t;
		}
		
	}
	
	
}