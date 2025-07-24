
import java.io.*;
import java.util.*;


public class Main {
	
	static int n;
	static int m;
	static Set<String> seqDuplCheck = new HashSet<>();
	static int[] numDuplCheck;
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// Backtracking
		numDuplCheck = new int[n+1];
		dfs(0, "");
		
		// sort
		List<String> result = new ArrayList<>(seqDuplCheck);
		result.sort(Comparator.naturalOrder());
		
		// result
		StringBuilder sb = new StringBuilder();
		for (String r : result) {
			sb.append(r);
			sb.append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.print(sb);
		
	}
	
	
	// backtracking dfs
	public static void dfs(int cnt, String curSeq) {
		if (cnt == m) {
			if (seqDuplCheck.contains(curSeq)) {
				return;
			}
			seqDuplCheck.add(curSeq);
		}
		for (int i=1; i<=n; i++) {
			if (numDuplCheck[i] > 0) {
				continue;
			}
			numDuplCheck[i] ++;
			String newSeq = curSeq.length()>0 ? curSeq+" "+String.valueOf(i) : String.valueOf(i); 
			dfs(cnt+1, newSeq);
			numDuplCheck[i] --;
		}
	}
		
}