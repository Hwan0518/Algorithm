import java.util.*;
import java.io.*;


public class Main {
	
	static StringTokenizer st;
	static int n;
	static int[] nums;
	static int[] ops;
	static StringBuilder sb;
	static int maxV;
	static int minV;
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		nums = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		ops = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		
		// dfs
		maxV = -1 * Integer.MAX_VALUE;
		minV = Integer.MAX_VALUE;
		dfs(1, nums[0]);
		
		// result
		sb = new StringBuilder();
		sb.append(maxV).append("\n");
		sb.append(minV);
		System.out.print(sb);
		
	}
	
	
	static void dfs(int size, int curV) {
		
		if (size == n) {
			maxV = Math.max(maxV, curV);
			minV = Math.min(minV, curV);
			return;
		}
		
		for (int i=0; i<4; i++) {
			if (ops[i] == 0) continue;
			ops[i] --;
			dfs(size+1, calc(curV, i, size));
			ops[i] ++;
		}
		
	}
	
	
	static int calc(int curV, int i, int size) {
		
		int result;

		if (i == 0) result = curV + nums[size];
		else if (i == 1) result = curV - nums[size];
		else if (i == 2) result = curV * nums[size];
		else result = curV > 0 ? curV / nums[size] : -1 * (Math.abs(curV) / nums[size]);
		
		return result;
		
	}

}
