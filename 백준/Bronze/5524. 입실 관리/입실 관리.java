import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n
		int n = Integer.parseInt(br.readLine());
		// sentence
		for (int i=0; i<n; i++){
			String s = br.readLine();
			System.out.println(s.toLowerCase());
		}
		// br close
		br.close();
	}

}