import java.io.*;
import java.util.*;

/**
 * 완탐으로 될지도?
 * - 모든 수에 대해서 int i=0; i++; 숫자*i에 모든 숫자를 나눠본다
 * - 나눠떨어지면 cnt++;
 * - 이때 cnt==3이 되면 min과 비교해서 갱신시켜준다
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// to int array
		int[] nums = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		Arrays.sort(nums);
		// search
		int min = Integer.MAX_VALUE;
		int cnt;
		int i = 1;
		Loop:
		while (true) {
			// get target
			for (int t:nums) {
				cnt = 0;
				int target = t*i;
				// get remainder
				for (int n:nums) {
					if (target%n==0) {
						cnt++;
					}
					if (cnt == 3) {
						min = Math.min(min, target);
					}
					if (target == nums[4]*nums[3]*nums[2]) {
						break Loop;
					}
				}
			}
			i++;
		}
		System.out.print(min);
	}
		
}