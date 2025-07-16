
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// check the secret key
		StringBuilder sb = new StringBuilder();
		loop: for (int i=0; i<n; i++) {
			// verify
			long s = Long.parseLong(br.readLine());
			for (int num=2; num<=1000000; num++) {
				if (s%num == 0) {
					sb.append("NO").append("\n");
					continue loop;
				}
			}
			sb.append("YES").append("\n");
		}
		
		// result
		System.out.print(sb);
		
    }
	
}