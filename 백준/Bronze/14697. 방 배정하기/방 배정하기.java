
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int a = input[0];
		int b = input[1];
		int c = input[2];
		int n = input[3];
		
		// calc -> Ax+By+Cz=N
		int cur;
		int remain_except_c;
		int remain_except_b;
		if (n%c == 0) {
			System.out.print(1);
			return;
		} else {
			for (int z=n/c; z>0; z--) {
				remain_except_c = n-c*z;
				if (remain_except_c%b == 0) {
					System.out.print(1);
					return;
				} else {
					for (int y=remain_except_c/b; y>0; y--) {
						remain_except_b = n-b*y-c*z;
						if (remain_except_b%a == 0) {
							System.out.print(1);
							return;
						}
					}
				}
			}
		}
		
		// result
		System.out.print(0);
		
    }
	
}