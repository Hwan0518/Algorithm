
import java.io.*;
import java.util.*;


public class Main {

	static int n;
	static String[] signs;
	static boolean isFirst = true;
	static int[] minNums;
	static int[] maxNums;		
	static boolean[] visit = new boolean[10];
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		signs = br.readLine().split(" ");
		
		// backtracking
		dfs(0, new int[n+1]);
		
		// result
		StringBuilder sb = new StringBuilder();
		for (int num: maxNums) {
			sb.append(num);
		}
		sb.append("\n");
		for (int num: minNums) {
			sb.append(num);
		}
		System.out.print(sb);
		
	}
	
	static void dfs(int cnt, int[] nums) {
		if (cnt == n+1) {
			if (!validate(nums)) {
				return;
			}
			if (isFirst) {
				minNums = Arrays.copyOf(nums, n+1);
				isFirst = false;
			}
			maxNums = Arrays.copyOf(nums, n+1);
			return;
		}
		for (int i=0; i<=9; i++) {
			if (visit[i]) {
				continue;
			}
			visit[i] = true;
			nums[cnt] = i;
			dfs(cnt+1, nums);
			visit[i] = false;
		}
		
	}
	
	static boolean validate(int[] nums) {
		for (int i=0; i<n; i++) {
			String sign = signs[i];
			int curNum = nums[i];
			int nextNum = nums[i+1];
			if (sign.equals(">") && !(curNum > nextNum)) {
				return false;
			} else if (sign.equals("<") && !(curNum < nextNum)) {
				return false;
			}
		}
		return true;
	}
		
}