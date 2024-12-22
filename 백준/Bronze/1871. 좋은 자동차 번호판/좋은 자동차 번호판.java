import java.io.*;
import java.util.*;

/**
 * condition
 * - 좋은번호판: abs(첫번째가치 - 두번쨰가치) < 100
 * - 26진법: 알파벳순서*(n-자릿수), 알파벳 순서는 입력값-65로 계산하자
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] alphabet = createAlphabet();
		// n
		int n = Integer.parseInt(br.readLine());		
		// n번 반복
		for (int i=0; i<n; i++) {
			String[] input = br.readLine().split("-");
			char[] fst = input[0].toCharArray();
			int secValue = Integer.parseInt(input[1]);
			// 계산
			int fstValue = calcFst(fst);
			System.out.println(Math.abs(fstValue-secValue) <= 100 
					? "nice"
					: "not nice");
		}
	}
	
	public static int calcFst(char[] fst) {
		int size = fst.length;
		int result = 0;
		for (int i=0; i<size; i++) {
			char cValue = fst[i];
			int position = cValue-65;
			result += (position * Math.pow(26,size-i-1));
		}
		return result;
	}
	
	
	public static char[] createAlphabet() {
		char[] result = new char[26];
		char a = 'A';
		for (int i=0; i<26; i++) {
			result[i] = a;
			a +=1;
		}
		return result;
	}

}