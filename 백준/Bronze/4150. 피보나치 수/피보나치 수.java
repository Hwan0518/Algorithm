import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * 피보나치
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		BigInteger[] fibonacci = new BigInteger[n];
		fibonacci[0]=new BigInteger("1");
		if (n >=2) {
			fibonacci[1]=new BigInteger("1");	
		}
		for (int i=2; i<n; i++) {
			fibonacci[i] = fibonacci[i-1].add(fibonacci[i-2]);
		}
		System.out.print(fibonacci[n-1]);
	}
	
}