import java.io.*;
import java.util.*;

/**
 * 슬라이딩 윈도우로 가잣
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		for (int i=0; i<n; i++) {
			Map<String, Integer> cntMap = new LinkedHashMap<>();
			cntMap.put("TTT", 0);
			cntMap.put("TTH", 0);
			cntMap.put("THT", 0);
			cntMap.put("THH", 0);
			cntMap.put("HTT", 0);
			cntMap.put("HTH", 0);
			cntMap.put("HHT", 0);
			cntMap.put("HHH", 0);
			String input = br.readLine();
			for (int stt=0; stt<38; stt++) {
				String key = input.substring(stt, stt+3);
				cntMap.put(key, cntMap.get(key)+1);
				sb.setLength(0);
			}
			// answer
			for (Map.Entry<String, Integer> ans:cntMap.entrySet()) {
				sb2.append(ans.getValue()).append(" ");
			}
			sb2.append("\n");
		}
		System.out.print(sb2);
	}
	
}