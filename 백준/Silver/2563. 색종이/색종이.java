
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// find total area
		int totalCount = 0;
		int[][] area = new int[100][100];
		for (int i=0; i<n; i++) {
			// input r,c
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// push confetti loc to set
			for (int x=r; x<r+10; x++) {
				for (int y=c; y<c+10; y++) {
					if (area[x][y] == 0) {
						totalCount ++;
					}
					area[x][y] ++;
				}
			}
		}
		
		// result
		System.out.print(totalCount);
		
    }
	
}