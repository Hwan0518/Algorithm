
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] heights = new int[9];
		for (int i=0; i<9; i++) {
			heights[i] = Integer.parseInt(br.readLine());
		}
		
		// find heights
		int totalSum = Arrays.stream(heights).sum();
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (i==j) continue;
				if (totalSum - heights[i] - heights[j] == 100) {
					heights[i] = 0;
					heights[j] = 0;
					Arrays.sort(heights);
					for (int h: heights) {
						if (h==0) continue;
						System.out.println(h);
					}
					return;
				}
			}
		}
		
		
    }
	
}