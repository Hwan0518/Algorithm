import java.io.*;
import java.util.*;

/**
 * 문자열 구현
 * - for문 돌려서 새로 생성
 * - 이후 동일한지 비교
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n,m
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		// 이미지 입력 후 두 배로 늘리기
		StringBuilder sb1 = new StringBuilder();
		for (int i=0; i<n; i++) {			
			char[] img = br.readLine().toCharArray();
			// 두 배로 늘리기
			for (int j=0; j<m; j++) {
				sb1.append(img[j]).append(img[j]);
			}
		}
		// 입력2
		StringBuilder sb2 = new StringBuilder();
		for (int i=0; i<n; i++) {
			sb2.append(br.readLine());
		}
		// 결과
		System.out.println(sb1.toString().equals(sb2.toString())
				?"Eyfa"
				:"Not Eyfa");
	}

}