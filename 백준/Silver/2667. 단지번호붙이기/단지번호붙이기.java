
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int apt = 0;
	static int cnt;
	static List<Integer> result;
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		for (int i=0; i<n; i++) {
			String[] row = br.readLine().split("");
			for (int j=0; j<n; j++) map[i][j] = Integer.parseInt(row[j]);
		}
		
		
		// dfs, bfs
		result = new ArrayList<>();
		visited = new boolean[n][n];
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
			
				if (visited[i][j] || map[i][j]==0) continue;
				visited[i][j] = true;
				
				cnt = 1;
//				dfs(i, j);
				bfs(i, j);
				
				apt ++;
				result.add(cnt);
				
			}
		}
		
		
		// result
		System.out.println(apt);
		
		Collections.sort(result);
		for (Integer res : result) System.out.println(res);
		
		
	}
	
	
	
	static void dfs(int r, int c) {
				
		for (int i=0; i<4; i++) {
			
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (!(0<=nr && nr<n && 0<=nc && nc<n)) continue;
			if (visited[nr][nc]) continue;	
			if (map[nr][nc] == 0) continue;
			
			visited[nr][nc] = true;
			cnt ++;
			
			dfs(nr, nc);
		}
		
	}
		
	
	static void bfs(int r, int c) {
		
		Deque<Node> q = new ArrayDeque<>();
		q.add(new Node(r, c));
		
		while (!q.isEmpty()) {
			
			Node node = q.pop();
			
			for (int i=0; i<4; i++) {
				
				int nr = node.getX() + dr[i];
				int nc = node.getY() + dc[i];
				
				if (!(0<=nr && nr<n && 0<=nc && nc<n)) continue;
				if (map[nr][nc] == 0) continue;
				if (visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				q.add(new Node(nr, nc));
				cnt ++;
				
			}
		}
	}
	
	
	
	static class Node {
		
		int x;
		int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		int getX() {
			return this.x;
		}
		
		int getY() {
			return this.y;
		}
		
	}
	
	
}