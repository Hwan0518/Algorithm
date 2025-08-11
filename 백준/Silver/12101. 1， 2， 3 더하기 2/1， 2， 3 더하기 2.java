import java.util.*;
import java.io.*;


/*
 * - n을 1,2,3의 합으로 나타내는 방법
 * - 1<= n <= 10
 * - k는 int 범위 양수
 * - n을 큰 순서대로 (3,2,1) 나눠서 모든 경우의 수를 찾아본다
 * 	-> 완탐 -> n의 최대가 10이기에 충분히 완탐 가능할 것으로 예상됨
 *  -> 3*a + 2b + c = n 을 만족시키는 (a,b,c) 순서쌍을 모두 구하면 된다
 *  -> a의 최대는 n/3, b의 최대는 n/2, c의 최대는 n
 * - k번째가 없다면 -1을 출력한다
 * 	-> 전체 경우의 수가 k보다 작은 경우와 큰 경우로 나누어서 계산한다
 */

public class Main {
		
	static int n;
	static int k;
	static ArrayList<Integer> cur;
	static ArrayList<String> result;
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		// find all
		result = new ArrayList<>();
		cur = new ArrayList<>();
		dfs(0);
		
		// result
		if (result.size() >= k) System.out.print(result.get(k-1));
		else System.out.print(-1);
		
	}
	
	static void dfs(int sum) {
		
		if (sum >= n) {
			StringBuilder sb = new StringBuilder();
			for (int s : cur) sb.append(s).append("+");
			sb.setLength(sb.length()-1);
			if (sum == n) result.add(sb.toString());
			return;
		}
		
		for (int i=1; i<=3; i++) {
			cur.add(i);
			dfs(sum+i);
			cur.remove(cur.size()-1);
		}
		
	}

}
