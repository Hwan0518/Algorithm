import java.io.*;
import java.util.*;

/**
 * 문자
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Set<String> set = new HashSet<>();
		for (int i=0; i<n; i++) {
			set.add(br.readLine());
		}
		for (String s:set) {
			StringBuilder sb = new StringBuilder(s);
			if (set.contains(sb.reverse().toString())) {
				int size = sb.length();
				System.out.print(size+" "+sb.charAt(size/2));
				return;
			}
		}
	}
	
}