import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int m;
	static int k;
	static List<Road>[] graph;
	static List<Integer> ans = new ArrayList<>();
	static int[] dist;
	static List<Node> followerList;

	static String FOLLOWER = "follower";
	static String BCW = "bcw";


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		graph = new List[n + 1];
		for (int i = 1; i < n + 1; i++) graph[i] = new ArrayList<>();
		for (int i=0; i<m; i++) {

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new Road(b, c));
			graph[b].add(new Road(a, c));

		}

		st = new StringTokenizer(br.readLine());
		followerList = new ArrayList<>();
		for (int i = 1; i < k + 1; i++) {

			int loc = Integer.parseInt(st.nextToken());
			followerList.add(new Node(loc, 0));

		}

		dist = new int[n+1];
		for (int i = 0; i < n + 1; i++) dist[i] = Integer.MAX_VALUE;

		// bfs
		bfs(FOLLOWER);
		bfs(BCW);

		// result
		Collections.sort(ans);

		StringBuilder sb = new StringBuilder();
		if (ans.isEmpty()) { sb.append(0); }
		else { for (int a : ans) sb.append(a).append("\n"); }

		System.out.print(sb);
	}


	static void bfs(String turn) {

		// set-up
		PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.t - o2.t);

		if (turn.equals(FOLLOWER)) followerSetting(q);
		else bcwSetting(q);

		// search
		while (!q.isEmpty()) {

			Node cur = q.poll();

			if (dist[cur.loc] < cur.t) continue;

			for (Road r : graph[cur.loc]) {

				int time = cur.t + r.cost;

				if (time < dist[r.dest]) {
					dist[r.dest] = time;
					q.add(new Node(r.dest, time));
					if (turn.equals(BCW)) ans.add(r.dest);
				}

			}
		}
//			System.out.println(turn+" -> "+ Arrays.toString(dist));
	}


	static void bcwSetting(PriorityQueue<Node> q) {

		q.offer(new Node(1, 0));
		dist[1] = 0;

	}


	static void followerSetting(PriorityQueue<Node> q) {

		for (Node f : followerList) {
			q.offer(f);
			dist[f.loc] = 0;
		}

	}


	static class Road {

		int dest;
		int cost;

		Road(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

	}


	static class Node {

		int loc;
		int t;

		Node(int loc, int t) {
			this.loc = loc;
			this.t = t;
		}

	}

}
