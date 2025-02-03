import java.io.*;
import java.util.*;

/**
 * 구현
 * 
 * approach
 * - for문 돌려가면서 비교하자
 */

public class Main {
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// input
		int n = Integer.parseInt(br.readLine());
		int[][] data = new int[n][5];
		for (int r=0; r<n; r++) {
			data[r] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		// searching
		int[] students = new int[n];
		for (int r1=0; r1<n-1; r1++) {
			for (int r2=r1+1; r2<n; r2++) {
				for (int c=0; c<5; c++) {
					if (data[r1][c] == data[r2][c]) {
						students[r1] ++;
						students[r2] ++;
						break;
					}
				}
			}
		}
		// result
		int maxCnt = 0;
		int maxStudent = 0;
		for (int i=0; i<n; i++) {
			if (students[i] > maxCnt) {
				maxCnt = students[i];
				maxStudent = i;
			}
		}
		System.out.print(maxStudent+1);
	}
		
}