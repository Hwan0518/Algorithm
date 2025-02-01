import java.io.*;
import java.util.*;

/**
 * L 길이의 케이크
 * N명의 방청객
 * P부터 K까지를 원함
 * 이미 선점되면 주지 못하고 넘어감
 * 가장 많은 조각을 받을 것으로 기대한 방청객 번호와, 실제로 가장 많이 받은 방청객 번호는?
 * => 차례대로 완탐 돌리면 될 것 같음
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// input
		int l = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		// calc
		int expectedMaxVal = 0, expectedMaxVisit = 0;
		int realMaxVal = 0, realMaxVisit = 0;
		int[] selected = new int[l+1];
		int[] visitor = new int[n+1];
		for (int i=1; i<n+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			// calc expected
			int len = e-s+1; 
			if (len > expectedMaxVal) {
				expectedMaxVal = len;
				expectedMaxVisit = i;
			}
			// calc real
			for (int j=s; j<=e; j++) {
				if (selected[j] == 0) {
					selected[j] = i;
					visitor[i] ++;
				}
			}
			if (visitor[i] > realMaxVal) {
				realMaxVal = visitor[i];
				realMaxVisit = i;
			}
		}
		// answer
		sb.append(expectedMaxVisit).append("\n").append(realMaxVisit);
		System.out.print(sb);
	}
		
}