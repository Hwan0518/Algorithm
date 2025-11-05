import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int f, s, g, u, d;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		f = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		u = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		visited = new boolean[f + 1];

		// bfs
		Node start = new Node(s, 0);
		int result = bfs(start);

		// result
		System.out.print(result >= 0 ? result : "use the stairs");
	}


	static int bfs(Node start) {

		ArrayDeque<Node> q = new ArrayDeque<>();
		q.addLast(start);
		visited[start.floor] = true;

		while (!q.isEmpty()) {

			Node cur = q.removeFirst();

			if (cur.floor == g) return cur.cnt;

			// up
			int upFloor = cur.floor + u;
			if (upFloor <= f && !visited[upFloor]) {
				q.addLast(new Node(upFloor, cur.cnt+1));
				visited[upFloor] = true;
			}

			// down
			int downFloor = cur.floor - d;
			if (downFloor >= 1 && !visited[downFloor]) {
				q.addLast(new Node(downFloor, cur.cnt+1));
				visited[downFloor] = true;
			}
		}

		return -1;
	}


	static class Node {

		int floor, cnt;

		Node(int floor, int cnt) {
			this.floor = floor;
			this.cnt = cnt;
		}
	}




}
