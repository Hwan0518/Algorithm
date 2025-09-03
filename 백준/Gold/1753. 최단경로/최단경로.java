
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int v;
	static int e;
	static int k;
	static int x;
	static int[] dist;
	static List<Node>[] graph;
	
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		k = Integer.parseInt(br.readLine());
		
		dist = new int[v+1];
		for (int i=1; i<v+1; i++) dist[i] = Integer.MAX_VALUE;
		
		graph = new ArrayList[v+1];
		for (int i=0; i<v+1; i++) graph[i] = new ArrayList<>();
		for (int i=0; i<e; i++) {
			
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[u].add(new Node(v, w));
		}
		
		// dijkstra
		dijkstra();
		
		// result
		for (int i=1; i<v+1; i++) {
			sb.append(dist[i] != Integer.MAX_VALUE ? dist[i] : "INF").append("\n");
		}
		System.out.print(sb);
		
	}
	
	
	
	static void dijkstra() {
		
		// 초기화
		Queue<Node> q = new PriorityQueue<Node>((o1, o2) -> o1.w - o2.w);
		q.offer(new Node(k, 0));
		dist[k] = 0;
		
		while (!q.isEmpty()) {
			
			Node curNode = q.poll();
			int curIdx = curNode.idx;
			int curW = curNode.w;
			
			// cur을 지나는게, 현재 등록된 dist보다 비싸면 pass
			if (dist[curIdx] < curW) continue;
			
			// 탐색
			for (int i=0; i<graph[curIdx].size(); i++) {
				
				Node next = graph[curIdx].get(i);
				int nextIdx = next.idx;
				int cost = curW + next.w;
				
				if (cost < dist[nextIdx]) {
					
					dist[nextIdx] = cost;
					q.offer(new Node(nextIdx, cost));
					
				}
				
			}	
		}	
	}
	
	
	
	static class Node {
		
		int idx;
		int w;
		
		Node(int idx, int w) {
			this.idx = idx;
			this.w = w;
		}
		
	}
	
	
	
}