
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int n = input[0];
		int m = input[1];
		int k = input[2];
		
		// find max-team-count
		int cnt = 0;
		if (k>=2) {
			for (int i=0; 2*i<=k; i++) {
				cnt = Math.max(cnt, Math.min((n-2*i)/2, m-(k-2*i)));
			}
		} else {
			int its_m = k;
			cnt = Math.max(cnt, Math.min(n, m-k));
		}
		
		// result
		System.out.print(cnt);
		
    }
	
}