import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int n,m,k;
	static PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2-o1);
	static int cnt;
	static StringBuilder sb;
	static List<Integer> satisfied;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int i=0; i<n; i++) q.offer(Integer.parseInt(br.readLine()));

		satisfied = new ArrayList<>();
		satisfied.add(0);

		// search
		sb = new StringBuilder();
		search();

		// result
		System.out.println(cnt);
		System.out.print(sb);

	}


	static void search() {

		int day = 1;

		while (!q.isEmpty()) {

			cnt ++;

			int curWork = q.poll();
			int curS = (int) Math.floor((double) satisfied.get(day-1) / 2) + curWork;
			
			satisfied.add(curS);
			curWork -= m;
			day++;

			sb.append(curS).append("\n");
			if (curWork > k) q.offer(curWork);
		}
	}

}
