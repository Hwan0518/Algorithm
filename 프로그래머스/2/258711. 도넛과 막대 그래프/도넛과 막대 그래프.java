import java.util.*;

class Solution {
    
    static int addV, n;
    static boolean[] visited;
    static Set<Integer> vertex = new HashSet<>();
    
    public int[] solution(int[][] edges) {
        
        // create graph
        Set<Integer>[][] graph = createGraph(edges);
        
        // find donut
        int[] answer = findDonut(graph);
        
        // result
        return answer;
    }
    
    
    
    int[] findDonut(Set<Integer>[][] graph) {
        
        int[] result = new int[4];
        result[0] = addV;
        
        // 존재하는 모든 정점 확인
        for (int i : vertex) {
            
            // validate
            if (i == addV) continue;
            if (visited[i]) continue;
            visited[i] = true;
            
            // search
            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.addLast(i);
            
            int last = i;
            boolean eightShape = false;
            
            while (!q.isEmpty()) {
                
                int cur = q.removeFirst();
                last = cur;
                
                // 8자 확인
                if (graph[cur][1].size() == 2) eightShape = true;
                
                // 탐색
                for (int dir=0; dir<=1; dir++) {
                    for (int nV : graph[cur][dir]) {
                        
                        // validate
                        if (nV == addV) continue;
                        if (visited[nV]) continue;

                        visited[nV] = true;
                        q.addLast(nV);
                    }
                }
            }
                        
            // 마지막 정점으로 종류 파악 (1:도넛, 2:막대, 3:8자)
            int type = findType(graph, last, eightShape);
            
            // 결과에 추가
            result[type] ++;
        }
                
        return result;
    }
    
    
    
    int findType(Set<Integer>[][] graph, int last, boolean eightShape) {
        
        // 혹시모를 추가 정점 삭제
        graph[last][0].remove(addV);
        graph[last][1].remove(addV);
        
        // in, out 갯수 확인
        int in = graph[last][0].size();
        int out = graph[last][1].size();
        
        // 8자인 경우
        if (eightShape) {
            return 3;
        }

        // 도넛인 경우
        if (in == 1 && out == 1) return 1;
        
        // 막대인 경우
        else if (in == 0 && out == 1) return 2;
        else if (in == 1 && out == 0) return 2;
        else if (in == 0 && out == 0) return 2;
        
        return 0;
    }
    
    
    
    boolean checkAddV(Set<Integer>[][] graph, int nextV) {
        
        // nextV로 들어오는게 없는데, 나가는게 2개 이상이면 추가된 정점임
        int in = graph[nextV][0].size();
        int out = graph[nextV][1].size();
        
        if (in == 0 && out >= 2) return true;
        return false;
    }
    
    
    
    Set<Integer>[][] createGraph(int[][] edges) {
        
        // set n
        n = -1;
        for (int[] e : edges) n = Integer.max(n, Integer.max(e[0], e[1]));
        
        // [i][0] -> i로 들어오는 정점번호, [i][1] -> i에서 나가는 정점번호
        Set<Integer>[][] graph = new Set[n+1][2];
        for (int i=1; i<=n; i++) {
            graph[i][0] = new HashSet<>();
            graph[i][1] = new HashSet<>();
        }
        
        for (int[] e : edges) {
            
            int a = e[0];
            int b = e[1];
            
            graph[a][1].add(b); // 나가는 정점번호
            graph[b][0].add(a); // 들어오는 정점번호
            
            vertex.add(a);
            vertex.add(b);
        }
                
        // set visited
        visited = new boolean[n+1];
        
        // check addV
        for (int i=1; i<=n; i++) {
            if (checkAddV(graph, i)) {
                addV = i;
                break;
            }
        }
        
        // return graph
        return graph;
    }
    
    
}