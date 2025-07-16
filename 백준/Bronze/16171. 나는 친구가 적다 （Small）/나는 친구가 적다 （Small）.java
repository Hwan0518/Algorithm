
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s_char = br.readLine().toCharArray();
		String k = br.readLine();
		
		// create new 's' without 'number' type
		StringBuilder sb = new StringBuilder();
		for (char c : s_char) {
			// c == alphabet -> add new 's'
			if ((65<=c && c<=90) || (97<=c && c<=122)) {
				sb.append(c);
			}
		}
		String s = sb.toString();
		
		// is contains?
		int result = 0;
		if (s.contains(k)) {
			result = 1;
		}
		System.out.print(result);
		
	}
		
}