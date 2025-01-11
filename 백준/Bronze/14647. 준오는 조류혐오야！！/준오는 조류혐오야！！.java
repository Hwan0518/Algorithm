import java.io.*;
import java.util.*;

/**
 * 구현
 * 각 행,열 별로 몇 개의 9가 존재하는지 기록
 * 이후 제일 많은 행/열의 9를 제외하고 출력
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		// num of 9
		int[] cnt = new int[n+m];
		// search
		int ans = 0;
		for (int r=0; r<n; r++) {
			String[] nums = br.readLine().split(" ");
			for (int c=0; c<m; c++) {
				char[] num = nums[c].toCharArray();
				for (char s:num) {
					if (s == '9') {
						ans ++;
						cnt[r] ++;
						cnt[n+c] ++;
					}
				}
			}
		}
		// ans
		System.out.print(ans - Arrays.stream(cnt).max().getAsInt());
	}
}