import java.io.*;
import java.util.*;

/**
 * 구현
 * - hashMap 사용하자
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Integer> map = new HashMap<>();
		// n
		int n = Integer.parseInt(br.readLine());
		// search
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String f = st.nextToken();
			int cnt = Integer.parseInt(st.nextToken());
			int cur = map.getOrDefault(f, 0);
			map.put(f, cur+cnt);
		}
		// check
		boolean trigger = false;
		for (int cnt:map.values()) {
			if (cnt==5) {
				trigger = true;
				break;
			}
		}
		System.out.println(trigger ? "YES" : "NO");
	}
	
}