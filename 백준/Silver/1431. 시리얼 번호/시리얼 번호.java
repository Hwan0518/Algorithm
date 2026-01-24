import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, m;


	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());
		
		Node[] arr = new Node[n];
		for (int i=0; i<n; i++) {
			
			String s = br.readLine();
			int len = s.length();
			int sum = sumNums(s);
			
			arr[i] = new Node(s, len, sum);
		}

		// sort - ver2
		Arrays.sort(arr, (o1, o2) -> {
			
			if (o1.len != o2.len) {
				return o1.len - o2.len;
			}
			else if (o1.sum != o2.sum) {
				return o1.sum - o2.sum;
			}
			else {
				String s1 = o1.s;
				return s1.compareTo(o2.s);
			}
		});
		
		// res
		for (Node node : arr) sb.append(node.s).append("\n");
		System.out.print(sb);
	}


	static int getSort(String s1, String s2, String target) {

		String[] ss = { s1, s2 };
		Arrays.sort(ss);

		if (ss[0].equals(target)) return 0;
		else return 1;
	}


	static int sumNums(String s) {

		int sum = 0;

		for (char c : s.toCharArray()) {
			if (c >= '0' && c <= '9') {
				sum += c-'0';
			}
		}

		return sum;
	}
	
	
	static class Node {
		
		String s;
		int len;
		int sum;
		
		Node(String s, int len, int sum) {
			this.s = s;
			this.len = len;
			this.sum = sum;
		}
	}

}
