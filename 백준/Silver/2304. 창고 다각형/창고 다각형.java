
import java.io.*;
import java.util.*;
import java.util.function.IntPredicate;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int max_l_1 = 0;
		int max_h_1 = -1;
		int max_l_2 = 0;
		int max_h_2 = -1;
		int[][] pillars = new int[n][2];
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			// find max pillar
			if (h > max_h_1) {
				max_h_1 = h;
				max_l_1 = l;
			} else if (h == max_h_1) {
				max_h_2 = h;
				max_l_2 = l;
			}
			// update pillars
			pillars[i][0] = l;
			pillars[i][1] = h;
		}
		
		// sort ascending based on 'l'
		Arrays.sort(pillars, (o1, o2) -> o1[0]-o2[0]);
		
		// find max_idx
		int max_idx_1 = -1;
		int max_idx_2 = n-1;
		for (int i=0; i<n; i++) {
			if (pillars[i][1] == max_h_1) {
				if (max_idx_1 == -1) {
					max_idx_1 = i;
				} else {
					max_idx_2 = i;
				}
			}
		}
		if (max_h_1 != max_h_2) {
			max_l_2 = max_l_1;
			max_h_2 = max_h_1;
			max_idx_2 = max_idx_1;
		}
		
		// sum left area
		int area = 0;
		final int final_max_idx_1 = max_idx_1;
		area = sumArea(pillars, area, 0, i -> i<=final_max_idx_1, 1);
		
		// sum middle area
		area += (Math.abs(max_l_2 - max_l_1) +1) * max_h_2;
		
		// sum right area
		final int final_max_idx_2 = max_idx_2;
		area = sumArea(pillars, area, n-1, i -> i>=final_max_idx_2, -1);
		
		// result
		System.out.print(area);
		
    }
	
	
	// sum area
	public static int sumArea(int[][] pillars, int area, int stt, IntPredicate condition, int increment) {
		int l_1 = pillars[stt][0];
		int h_1 = pillars[stt][1];
		for (int i=stt; condition.test(i); i+= increment) {
			int l_2 = pillars[i][0];
			int h_2 = pillars[i][1];
			if (h_1 <= h_2) {
				area += Math.abs(l_2-l_1) * h_1;
				l_1 = l_2;
				h_1 = h_2;
			}
		}
		return area;
	}
	
}