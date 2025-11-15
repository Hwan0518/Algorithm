import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static Set<String> opts = Set.of("+", "-", "x", "/", "=");



	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));
		String formula = br.readLine();

		// setup mapping table
		Map<String, Integer> map = setMappingTable();
		Map<String, String> map2 = setMappingTable2();

		// switch
		String newFormula = switchFormula(formula, map2);

		// calc
		String result = calc(newFormula, map2);

		// result
		StringBuilder sb = new StringBuilder();

		if (newFormula != null) sb.append(newFormula).append("\n");
		sb.append(result);

		System.out.print(sb);
	}


	private static Map<String, String> setMappingTable2() {

		Map<String, String> map = new HashMap<>();

		map.put("0", "ZERO");
		map.put("1", "ONE");
		map.put("2", "TWO");
		map.put("3", "THREE");
		map.put("4", "FOUR");
		map.put("5", "FIVE");
		map.put("6", "SIX");
		map.put("7", "SEVEN");
		map.put("8", "EIGHT");
		map.put("9", "NINE");

		return map;
	}


	static Map<String, Integer> setMappingTable() {

		Map<String, Integer> map = new HashMap<>();

		map.put("ZERO", 0);
		map.put("ONE", 1);
		map.put("TWO", 2);
		map.put("THREE", 3);
		map.put("FOUR", 4);
		map.put("FIVE", 5);
		map.put("SIX", 6);
		map.put("SEVEN", 7);
		map.put("EIGHT", 8);
		map.put("NINE", 9);

		return map;
	}


	static String switchFormula(String formula, Map<String, String> map) {

		if (formula.charAt(formula.length()-1) != '=') return null;

		StringBuilder sb = new StringBuilder();

		int i=0;
		while (i < formula.length()) {

			char val = formula.charAt(i);

			if (val == 'Z') {
				sb.append(0);
				i += 4;
			}

			else if (val == 'O') {
				sb.append(1);
				i += 3;
			}

			else if (val == 'T') {
				if (formula.charAt(i+1) == 'W') {
					sb.append(2);
					i += 3;
				}
				else {
					sb.append(3);
					i += 5;
				}
			}

			else if (val == 'F') {
				if (formula.charAt(i+1) == 'O') sb.append(4);
				else sb.append(5);
				i += 4;
			}

			else if (val == 'S') {
				if (formula.charAt(i+1) == 'I') {
					sb.append(6);
					i += 3;
				}
				else {
					sb.append(7);
					i += 5;
				}
			}

			else if (val == 'E') {
				sb.append(8);
				i += 5;
			}

			else if (val == 'N') {
				sb.append(9);
				i += 4;
			}

			else {

				// = 인 경우 끝을 나타냄
				if (val == '=') {
					sb.append(val);
					break;
				}

				// 연속으로 연산자면 null을 반환
				char nextC = formula.charAt(i+1);
				if (nextC < 'A' || nextC > 'Z') return null;

				// 그 외의 연산자라면 계속 진행
				sb.append(val);
				i ++;
			}
		}

		// 결과
		String result = sb.toString();

		// 검증
		int curIdx = 0;
		for (int idx=0; idx<result.length(); idx++) {

			String num = String.valueOf(result.charAt(idx));
			if (map.containsKey(num)) {

				String eng = map.get(num);

				for (int j=0; j<eng.length(); j++) {
					if (formula.charAt(curIdx + j) != eng.charAt(j)) return null;
				}
				curIdx += eng.length();
			}

			else curIdx ++;
		}

		// 검증됐다면 return
		return result;
	}


	static String calc(String f, Map<String, String> map) {

		// 예외
		if (f == null) return "Madness!";

		// 계산
		String[] arr = f.split("");

		String a = "";
		String b = "";
		String opt = "";
		for (int i=0; i<arr.length; i++) {

			String v = arr[i];

			// 연산자인 경우
			if (opts.contains(v)) {

				// 연산자가 아직 나오지 않은 경우는 계속 진행
				if (opt.isBlank()) opt = v;

				// 연산자가 나왔다면, 이전 계산을 끝내고 a, b, opt 갱신
				else {
					long intA = Long.parseLong(a);
					long intB = Long.parseLong(b);

					long res;
					if (opt.equals("+")) res = intA + intB;
					else if (opt.equals("-")) res = intA - intB;
					else if (opt.equals("x")) res = intA * intB;
					else if (opt.equals("/")) res = intA / intB;
					else break; // "="가 나왔다면 연산을 끝냄

					// update a, b, opt
					a = String.valueOf(res);
					b = "";
					opt = v;
				}
			}

			// 숫자인 경우
			else if (a.isBlank()) a += v; // a가 비어있다면 a먼저 채운다
			else if (opt.isBlank()) a += v; // 연산자가 아직 나오지 않았다면 a에 더한다
			else b += v;
		}

		// 결과를 다시 영어로 변경
		StringBuilder sb = new StringBuilder();
		for (String num : a.split("")) {
			if (map.containsKey(num)) sb.append(map.get(num));
			else sb.append(num);
		}

		return sb.toString();
	}

}
