import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, e, a, b;
	static int[] dist;
	static int[] aDists;
	static int[] bDists;
	static List<Node>[] graph;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		graph = new List[n+1];
		for (int i=0; i<=n; i++) graph[i] = new ArrayList<>();
		for (int i=0; i<e; i++) {

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		dist = new int[n + 1];
		aDists = new int[n + 1];
		bDists = new int[n + 1];
		for (int i=0; i<=n; i++) {
			dist[i] = Integer.MAX_VALUE;
			aDists[i] = Integer.MAX_VALUE;
			bDists[i] = Integer.MAX_VALUE;
		}

		// dijkstra1 -> 1 to ?
		dijkstra(0);
		// dijkstra1 -> a to ?
		dijkstra(a);
		// dijkstra1 -> b to ?
		dijkstra(b);

		// result
		int min = findMinVal();
		System.out.print(min);
	}


	static int findMinVal() {

		// a <-> b 거리를 기본값으로
		int min = aDists[b];

		// 거리1 (a먼저)
		int aDist1 = dist[a]; // 1에서 a까지
		int aDist2 = bDists[n]; // b에서 n까지

		// 거리2(b먼저)
		int bDist1 = dist[b]; // 1에서 b까지
		int bDist2 = aDists[n]; // a에서 n까지

		// 총 거리
		int aTotal = aDist1==Integer.MAX_VALUE || aDist2==Integer.MAX_VALUE ? -1 : aDist1+aDist2;
		int bTotal = bDist1==Integer.MAX_VALUE || bDist2==Integer.MAX_VALUE ? -1 : bDist1+bDist2;

		// 최단거리
		int minDist = Integer.min(aTotal, bTotal);

		// result
		return minDist==-1 ? -1 : min + minDist;
	}


	static void dijkstra(int stt) {

		PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		q.offer(new Node(stt == 0 ? 1 : stt, 0));

		// set dist arr
		int[] arr;
		if (stt == 0) arr = dist;
		else if (stt == a) arr = aDists;
		else arr = bDists;

		// search
		while (!q.isEmpty()) {

			Node cur = q.poll();

			// 거리 비교 & 갱신
			if (arr[cur.idx] < cur.cost) continue;
			arr[cur.idx] = cur.cost;

			// 연결된 노드 확인
			for (Node next : graph[cur.idx]) {

				int newCost = cur.cost + next.cost;

				if (newCost < arr[next.idx]) {
					arr[next.idx] = newCost;
					q.offer(new Node(next.idx, newCost));
				}
			}
		}

	}


	static class Node {

		int idx, cost;

		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

}
