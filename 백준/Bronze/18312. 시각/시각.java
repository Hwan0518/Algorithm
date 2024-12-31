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
					if (check(k, h, m, s)) cnt++;
				}
			}
		}
		// result
		System.out.println(cnt);
	}

	public static boolean check(int k, int h, int m, int s) {
		String sh = h<10 ? "0"+String.valueOf(h) : String.valueOf(h);
		String sm = m<10 ? "0"+String.valueOf(m) : String.valueOf(m);
		String ss = s<10 ? "0"+String.valueOf(s) : String.valueOf(s);
		// check
		if (sh.contains(String.valueOf(k))) {
			return true;
		} else if (sm.contains(String.valueOf(k))) {
			return true;
		} else if (ss.contains(String.valueOf(k))) {
			return true;
		}
		return false;
	}
	
}