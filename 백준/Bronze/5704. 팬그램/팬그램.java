import java.io.*;

/**
 * 
 */
public class Main {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = br.readLine();
		while (!input.equals("*")) {
			char[] arr = input.toCharArray();
			boolean[] exist = new boolean[26];
			boolean pangram = true;
			for (char c:arr) {
				if (c>=97) exist[c-97] = true;
			}
			for (boolean e:exist) {
				if (!e) {
					pangram = false;
					break;
				}
			}
			sb.append(pangram ? "Y\n" : "N\n");
			input = br.readLine();
		}
		System.out.print(sb);
		br.close();
	}
	
}