import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();




	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		// search
		boolean possible = getUCPC(s);

		// result
		String result;
		if (possible) result = "I love UCPC";
		else result = "I hate UCPC";

		System.out.print(result);
	}

	
	static boolean getUCPC(String s) {
		
		char[] arr = s.toCharArray();
		char[] ucpc = { 'U', 'C', 'P', 'C' };

		int pointer = 0;
		for (char c : arr) {
			
			if (c == ucpc[pointer]) pointer ++;
			if (pointer == 4) return true;
		}

		return false;
	}
}