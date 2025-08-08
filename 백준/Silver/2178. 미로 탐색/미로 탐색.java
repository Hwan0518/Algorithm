
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n;
	static int m;
	static int[][] map;
	static int cnt = 0;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		// input
		input();
		
		// bfs
		bfs(0,0);
		
		// result
		System.out.print(map[n-1][m-1]);
	}
	
	// bfs
	static void bfs(int sttX, int sttY) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(sttX, sttY));
		while (!q.isEmpty()) {
			Node node = q.remove();
			int x = node.getX();
			int y = node.getY();
			for (int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				// validate
				if (!(0<=nx && nx<n) || !(0<=ny && ny<m)) continue; // is out of range?
				if (nx == sttX && ny == sttY) continue; // is start point?
				if (map[nx][ny] == 0) continue; // is can't move point?
				if (map[nx][ny] > 1) continue; // is visited?
				// visit
				map[nx][ny] = map[x][y] +1;
				// push to queue
				q.add(new Node(nx,ny));
			}
		}
		
	}
	
	// node
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
	
	// input
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m]; 
		for (int i=0; i<n; i++) {
			String[] row = br.readLine().split("");
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(row[j]);
			}
		}
	}
	
}