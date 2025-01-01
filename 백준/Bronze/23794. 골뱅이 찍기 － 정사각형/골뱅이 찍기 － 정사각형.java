import java.io.*;

/**
 * 완탐 -> 60 * 60 * 24 = 86400, 최대 O(86400)으로 가능
 */
public class Main {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		String upDown = "@" + "@".repeat(n) + "@"; 
		String middle = "@" + " ".repeat(n) + "@" + "\n";
		bw.append(upDown + "\n");
		for (int i=0; i<n; i++) { 
			bw.append(middle);
		}
		bw.append(upDown);
		bw.flush();
		br.close();
		bw.close();
	}
	
}