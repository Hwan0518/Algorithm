import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> hash = new HashMap<>();

        // 개수 파악
        for (String people : participant){
            if (hash.containsKey(people)) {
                Integer val = hash.get(people);
                hash.put(people, val+1);
            } else {
                hash.put(people, 1);
            }
        }
        // 완주한 사람의 개수를 빼줌
        for (String people : completion) {
            if (hash.containsKey(people)) {
                Integer val = hash.get(people);
                hash.put(people, val - 1);
            } else {
                return people;
            }
        }
        // 값이 1인것을 찾아내서 return
        for (String people : hash.keySet()) {
            if (hash.get(people) == 1) {
                return people;
            }
        }
        return "";
    }
}