import java.util.*;
import java.io.*;

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	static int t;
	static int m;
	static int n;
	static int k;
	static int[][] map;
	static Node[] loc;
	static int cnt;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static StringBuilder sb;

	
	public static void main(String[] args) throws IOException {

		// setUp
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		
		// do test
		for (int i=0; i<t; i++) {
			doit();
		}
		
		// result
		System.out.print(sb);
		
	}
	
	
	static void doit() throws IOException {

		// input
		input();
		
		// bfs
		for (Node node : loc) {
			
			// visit
			if (map[node.getX()][node.getY()] > 1) continue;
			
			// do bfs
			bfs(node);
			
			// count
			cnt++;
			
		}
		
		// result
		sb.append(cnt).append("\n");
	}
	
	
	static void bfs(Node sttNode) {
		
		// set-up
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(sttNode);
		
		// search
		while (!q.isEmpty()) {
			
			// get cur node
			Node cur = q.remove();
			int x = cur.getX();
			int y = cur.getY();
			
			// search up, down, left, right
			for (int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				// validate
				if (!(0<=nx && nx<m) || !(0<=ny && ny<n)) continue; // is out of range?
				if (nx == sttNode.getX() && ny == sttNode.getY()) continue; // is start point?
				if (map[nx][ny] == 0) continue; // is no bechoo?
				if (map[nx][ny] > 1) continue; // is visited?
				
				// visit & push to queue
				map[nx][ny] ++;
				q.add(new Node(nx, ny));
	
			}
		}
		
		
	}
	
	
	static class Node {
		
		// field
		int x;
		int y;
		
		// constructor
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		// getter
		int getX() {
			return this.x;
		}
		
		int getY() {
			return this.y;
		}
		
	}
	
	
	static void input() throws IOException {
		
		// set-up
		st = new StringTokenizer(br.readLine());
		
		// input m,n,k,map,loc,visited,cnt
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		loc = new Node[k];
		cnt = 0;
		
		// filling loc
		for (int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			loc[i] = new Node(x,y);
			map[x][y] = 1;
		}
	}
	

}
