
import java.io.*;
import java.util.*;

/*
 * Condition
 * 	- TimeLimit = 2s
 * 	- 1<=n<=100
 * 	- 0<=t<=1000
 * 
 * Question
 * 	- life guard를 1명 해고했을 때, 커버할 수 있는 최대 시간은? 
 * 
 * Approach
 * 	- 최대 n=100이므로, 하나씩 차례대로 제외하고 계산한다면?
 * 		-> 매번 99개 더하기(O(99)) * 100번반복(O(100)) = 9900
 * 		-> 완탐가능
 * 	- 입력 저장해두고, 차례대로 제외하면서 모두 더해서 max time을 구해보자
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] lifeguards = new int[n][2];
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			lifeguards[i][0] = Integer.parseInt(st.nextToken());
			lifeguards[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// find max-time
		int maxTime = 0;
		for (int i=0; i<n; i++) {
			Set<Integer> covers = new HashSet<>();
			for (int j=0; j<n; j++) {
				// except 'i'st life guard
				if (i==j) continue;
				for (int time=lifeguards[j][0]; time<lifeguards[j][1]; time++) {
					covers.add(time);
				}
			}
			// update max-time
			maxTime = Math.max(maxTime, covers.size());
		}
		
		// result
		System.out.print(maxTime);
		
    }
	
}