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
        
    for k in range(1,n+1):
        for a in range(1,n+1):
            for b in range(1,n+1):
                if graph[a][k] == graph[k][b] == 2:
                    graph[a][b] = 2
                elif graph[a][k] == graph[k][b] == 1:
                    graph[a][b] = 1
    
    # 랭크를 확인
    ranking = [True] + [False]*n + [True]
    visited = [False]*(n+1)
    for p in range(1,n+1):
        print(graph[p])
        flag = True
        lose = 0
        for i in range(1,n+1):
            r = graph[p][i]
            if r == 0 :
                flag = False
                break
            elif r == 1:
                lose +=1
        if flag:
            ranking[lose+1] = True
            visited[lose+1] = True
            answer +=1
    print(ranking)
    
    # 랭크를 확인할 수 있으면 True로 변경
    check = True
    while check:
        check = False
        for i in range(1,n+1):
            before = ranking[i-1]
            after = ranking[i+1]
            if before and after and not visited[i]:
                answer +=1
                ranking[i] = True
                visited[i] = True
                check = True        
                break
                
    return answer