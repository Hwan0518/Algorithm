import java.io.*;
import java.util.*;

/**
 * hashmap 사용하자
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Map<Character, Integer> m = new HashMap<>();
		String w1 = br.readLine();
		String w2 = br.readLine();
		boolean isSame = true;
		int cnt = 1;
		while (!w1.equals("END") || !w2.equals("END")) {
			char[] w11 = w1.toCharArray();
			char[] w22 = w2.toCharArray();
			for (int i=0; i<Math.max(w11.length, w22.length); i++) {
				if (i < w11.length) m.put(w11[i], m.getOrDefault(w11[i], 0) +1);
				if (i < w22.length) m.put(w22[i], m.getOrDefault(w22[i], 0) -1);
			}
			sb.append("Case ").append(cnt).append(": ");
			for (int v:m.values()) {;
				if (v != 0) {
					sb.append("different\n");
					isSame = false;
					break;
				}
			}
			sb.append(isSame ? "same\n" : "");
			// input again
			w1 = br.readLine();
			w2 = br.readLine();
			isSame = true;
			cnt++;
			m.clear();
		}
		System.out.print(sb);
	}
}