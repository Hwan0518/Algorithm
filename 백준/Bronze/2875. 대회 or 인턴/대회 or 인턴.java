
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
				int its_w = 2*i;
				int its_m = k-2*i;
				int cur_team = Math.min((n-2*i)/2, m-(k-2*i));
				if (n-its_w>=0 && m-its_m >=0) {
					cnt = Math.max(cnt, cur_team);
				}
			}
		} else {
			int its_m = k;
			int cur_team = Math.min(n, m-k);
			if (m-its_m>=0) {
				cnt = Math.max(cnt, cur_team);
			}
		}
		
		// result
		System.out.print(cnt);
		
    }
	
}