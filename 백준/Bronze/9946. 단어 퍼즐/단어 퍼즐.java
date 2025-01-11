import java.io.*;
import java.util.*;

/**
 * hashmap 사용하자
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Map<Character, Integer> m1 = new HashMap<>();
		Map<Character, Integer> m2 = new HashMap<>();
		String w1 = br.readLine();
		String w2 = br.readLine();
		int cnt = 1;
		while (!w1.equals("END") || !w2.equals("END")) {
			for (char w:w1.toCharArray()) {
				m1.put(w, m1.getOrDefault(w, 0) +1);
			}
			for (char w:w2.toCharArray()) {
				m2.put(w, m2.getOrDefault(w, 0) +1);
			}
			sb.append("Case ").append(cnt).append(": ")
			.append(m1.equals(m2) ? "same" : "different")
			.append("\n");
			// input again
			w1 = br.readLine();
			w2 = br.readLine();
			cnt++;
			m1.clear();
			m2.clear();
		}
		System.out.print(sb);
	}
}