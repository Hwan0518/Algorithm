import java.io.*;
import java.util.*;

/**
 * 완탐 -> 60 * 60 * 24 = 86400, 최대 O(86400)으로 가능
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		// time pass
		int cnt = 0;
		for (int h=0; h<n+1; h++) {
			for (int m=0; m<60; m++) {
				for (int s=0; s<60; s++) {
					String time = String.format("%02d%02d%02d", h,m,s);
					if (check(k, time)) cnt++;
				}
			}
		}
		// result
		System.out.println(cnt);
	}

	public static boolean check(int k, String time) {
		// check
		if (String.valueOf(time).contains(String.valueOf(k))) {
			return true;
		}
		return false;
	}
	
}