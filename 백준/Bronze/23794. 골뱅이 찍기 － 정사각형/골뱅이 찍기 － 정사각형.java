import java.io.*;

/**
 * 
 */
public class Main {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine()); 
		String upDown = "@".repeat(n+2); 
		sb.append(upDown).append("\n");
		for (int i=0; i<n; i++) { 
			sb.append("@").append(" ".repeat(n)).append("@").append("\n");
		}
		sb.append(upDown);
		System.out.print(sb);
	}
	
}