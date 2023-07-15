'''
Approach
    - 경기 결과를 보고 정확히 순위를 매길 수 있는 선수의 수를 리턴
    - 1<=n<=100, 1<=edge<=4500
    
>>> 그래프에 연관된 알고리즘 확인
    : 위상정렬, 다익스트라, mst, 플로이드 워셜
      모든 선수와 순위를 비교할 수 있는지 확인
    = 한 노드에서 다른 모든 노드들의 연결상태를 확인
    = 플로이드 워셜로 가능할듯?
      O(N^3) == O(100^3) == 100만이라 가능

>>> 순위를 구해야 하므로 플로이드 워셜만으로는 불가능
    : 말그대로 구현을 해야할듯?

>>> 플로이드 워셜로 가능함!
'''
import sys
sys.setrecursionlimit(10**7)

def solution(n, results):
    answer = 0
    graph = [[0]*(n+1) for _ in range(n+1)]
    # 그래프 완성
    for r in results:
        a,b = r
        graph[a][b] = 2 # 이겼을때
        graph[b][a] = 1 # 졌을때
    for p in range(n+1):
        graph[p][p] = 3
        
    # 플로이드워셜
    for k in range(1,n+1):
        for a in range(1,n+1):
            for b in range(1,n+1):
                if graph[a][k] == graph[k][b] == 2:
                    graph[a][b] = 2
                elif graph[a][k] == graph[k][b] == 1:
                    graph[a][b] = 1
            
    
    for a in range(1,n+1):
        flag = True
        for b in range(1,n+1):
            if graph[a][b] == 0:
                flag = False
                break
        if flag: answer +=1
                
    return answer