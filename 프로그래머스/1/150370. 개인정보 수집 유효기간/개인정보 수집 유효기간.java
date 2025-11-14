import java.util.*;


class Solution {
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        // create due arr
        int[] dueArr = createDueArr(terms);
        
        // find del idx
        int[] answer = findDelIdx(today, privacies, dueArr);
        
        // result
        return answer;
    }
    
    
    
    int[] findDelIdx(String today, String[] privacies, int[] dueArr) {
        
        List<Integer> result = new ArrayList<>();
        
        // split Today
        String[] todays = today.split("\\.");
        int todayY = Integer.parseInt(todays[0]);
        int todayM = Integer.parseInt(todays[1]);
        int todayD = Integer.parseInt(todays[2]);
        
        // find del privacies
        for (int i=0; i<privacies.length; i++) {
            
            String[] data = privacies[i].split(" ");
            String[] sttDate = data[0].split("\\.");
            int term = data[1].charAt(0) - 'A';
            
            int sttY = Integer.parseInt(sttDate[0]);
            int sttM = Integer.parseInt(sttDate[1]);
            int sttD = Integer.parseInt(sttDate[2]);
            
            int delY = sttY;
            int delM = sttM + dueArr[term];
            int delD = sttD;
                        
            if (delM > 12) {
                delY += delM/12;
                delM %= 12;
                
                if (delM == 0) {
                    delY -= 1;
                    delM = 12;      
                }
            }
            
            // validate
            if (delY < todayY) result.add(i +1);
            
            else if (delY == todayY && delM < todayM) result.add(i +1);
            
            else if (delY == todayY && delM == todayM && delD <= todayD) result.add(i +1);   
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    
    
                                                                                
    int[] createDueArr(String[] terms) {
        
        int[] dueArr = new int[26];
        
        for (int i=0; i<terms.length; i++) {
            
            String[] data = terms[i].split(" ");
            
            int term = terms[i].charAt(0) - 'A';
            int due = Integer.parseInt(data[1]);
            
            dueArr[term] = due;
        }
        
        return dueArr;
    }
    
    
}