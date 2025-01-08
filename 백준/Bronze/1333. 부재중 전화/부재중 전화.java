import java.io.*;
import java.util.*;

/**
 * 완탐
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int n = input[0];
		int l = input[1];
		int d = input[2];
		int dcnt = 1;
		// search
		int cur = 0;
		boolean trigger = false;
		Loop:
		for (int j=0; j<n ; j++) {
			for (int i=0; i<l; i++) {
				if (cur == d*dcnt) {
					dcnt ++;
				}
				cur ++;
			}
			for (int i=0; i<5; i++) {
				if (cur == d*dcnt) {
					trigger = true;
					break Loop;
				}
				cur ++;
			}
		}
		if (!trigger) {
			while (cur != d*dcnt) {
				cur ++;
			}
		}
		System.out.print(cur);
	}
}