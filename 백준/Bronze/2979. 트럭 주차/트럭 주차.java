import java.io.*;
import java.util.*;

/**
 * 구현
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// abc
		int[] abc = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		// truck
		int[] t = new int[101];
		for (int i=0; i<3; i++) {
			int[] tInput = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();;
			for (int j=tInput[0]; j<tInput[1]; j++) {
				t[j] ++;
			}
		}
		// calc
		int fee = 0;
		for (int i=1; i<101; i++) {
			int cnt = t[i];
			switch (cnt) {
				case 1: {
					fee += abc[0];
					break;
				}
				case 2: {
					fee += abc[1]*cnt;
					break;
				}
				case 3: {
					fee += abc[2]*cnt;
					break;
				}
			}
		}
		System.out.println(fee);
		br.close();
	}
	
}