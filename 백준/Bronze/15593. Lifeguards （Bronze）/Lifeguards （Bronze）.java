
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
 * 	- 두 번째 풀이 (feat. lms)
 * 		-> 처음에 모든 covers를 구한 후, 하나씩 빼주면 됨
 * 		-> int[]로 체크하는 방법과 set 사용하는 방법이 있음. 어떤거할까
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// sum all times
		int[][] lifeguards = new int[n][2];
		int[] covers = new int[1000];
		int allTimes = 0;
		for (int i=0; i<n; i++) {
			// input
			StringTokenizer st = new StringTokenizer(br.readLine());
			int stt = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			// set
			lifeguards[i][0] = stt;
			lifeguards[i][1] = end;
			// add times
			for (int t=stt; t<end; t++) {
				if (covers[t] == 0) {
					allTimes ++;
				}
				covers[t] ++;
			}
		}
		
		// find max-time
		int maxTime=0;
		for (int i=0; i<n; i++) {
			int stt = lifeguards[i][0];
			int end = lifeguards[i][1];
			// calc
			int exceptTime = 0;
			for (int t=stt; t<end; t++) {
				if (covers[t] == 1) {
					exceptTime++;
				}
			}
			// update
			maxTime = Math.max(maxTime, allTimes-exceptTime);
		}
		
		// result
		System.out.print(maxTime);
		
    }
	
}