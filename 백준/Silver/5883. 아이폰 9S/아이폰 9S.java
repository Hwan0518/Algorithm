
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] bites = new int[n];
		Set<Integer> types = new HashSet<>();
		for (int i=0; i<n; i++) {
			 int b = Integer.parseInt(br.readLine());
			 bites[i] = b;
			 types.add(b);
		}
		
		// find longest continuous section size
		int longest_size = 0;
		for (int t : types) {
			int before = -1;
			int cur_size = 1;
			for (int i=0; i<n; i++) {
				int cur = bites[i];
				// except t-type
				if (cur == t) {
					continue;
				}
				if (before == cur) {
					cur_size ++;
				} else {
					cur_size = 1;
				}
				longest_size = Math.max(longest_size, cur_size);
				before = cur;
			}
		}
	
		// result
		System.out.print(longest_size);
		
	}
		
}