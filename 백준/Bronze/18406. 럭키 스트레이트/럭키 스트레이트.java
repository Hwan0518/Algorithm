import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] num = br.readLine().toCharArray();

		// search
		int front = 0;
		int back = 0;

		for (int i = 0; i < num.length; i++) {
			if (i < num.length/2) front += num[i];
			else back += num[i];
		}

		// result
		System.out.print(front == back ? "LUCKY" : "READY");
	}


}