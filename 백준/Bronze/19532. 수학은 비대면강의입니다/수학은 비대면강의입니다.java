
import java.io.*;
import java.util.*;

/*
 * Condition
 * 	- -999<=a,b,c,d,e,f<=999
 * 	- (x,y)는 유일하다
 * 	- -999<=x,y<=999
 * 	- TimeLimit = 1s
 * 
 * Question
 * 	- ax+by=c
 * 	- dx+ey=f
 * 	- a,b,c,d,e,f가 주어질 때, 위의 연립방정식을 만족하는 x,y를 구하여라
 * 
 * Approach
 * 	- 1999*1999 ~= 400만 -> 1s안에 충분히 들 수 있다
 * 	- 완탐으로 풀어보자
 */

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
		int d = input[3];
		int e = input[4];
		int f = input[5];
		
		// find x,y
		StringBuilder sb = new StringBuilder();
		for (int x=-999; x<=999; x++) {
			for (int y=-999; y<=999; y++) {
				if (a*x+b*y == c && d*x+e*y==f) {
					sb.append(x).append(" ").append(y);
					System.out.print(sb);
					return;
				}
			}
		}
		
    }
	
}