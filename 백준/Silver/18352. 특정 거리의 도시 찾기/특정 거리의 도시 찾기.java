
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int m;
	static int k;
	static int x;
	static int[] dist;
	static List<Node>[] edge;
	
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		dist = new int[n+1];
		for (int i=0; i<n+1; i++) dist[i] = Integer.MAX_VALUE;
		
		edge = new ArrayList[n+1];
		for (int i=0; i<n+1; i++) edge[i] = new ArrayList<>();
		for (int i=0; i<m; i++) {
			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edge[a].add(new Node(b, 1));
		}
		
		// dijkstra
		dijkstra();
		
		// result
		for (int i=1; i<n+1; i++) {	
			if (dist[i] == k) sb.append(i).append("\n");
		}
		System.out.print(sb.length()==0 ? -1 : sb);
	}
	
	
	static void dijkstra() {
		
		Queue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		q.offer(new Node(x, 0));
		dist[x] = 0;
		
		while (!q.isEmpty()) {
			
			Node cur = q.poll();
			int curIdx = cur.getIndex();
			int curCost = cur.getCost();
			
			if (curCost > dist[curIdx]) continue;
			
			
			for (Node next : edge[curIdx]) {
				
				int nextIdx = next.getIndex();
				int nextCost = next.getCost();
				int selectNextCost = curCost + nextCost;
				
				if (selectNextCost >= dist[nextIdx]) continue;
				dist[nextIdx] = selectNextCost;
				q.offer(new Node(nextIdx, dist[nextIdx]));
				
			}
			
		}
			
	}
		
	
	
	static class Node {
		
		int index, cost;
		
		Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}
		
		int getIndex() {return this.index;}
		int getCost() {return this.cost;}
		
	}
	
	
}