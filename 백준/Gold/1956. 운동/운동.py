'''
Condition
    - TL : 2s(4000만)
      ML :
    - 2<=v<=400, 0<=e<=v(v-1)

Question
    - 최단거리 싸이클을 찾는다. 싸이클이 없다면 -1을 출력한다

Access
    - 예상 분류 : dfs
      실제 분류 : 플로이드 워셜

    try_1)
        - 플로이드 워셜로 graph를 완성시킨 후, 싸이클을 찾아본다
        - 싸이클을 찾을때는 stack을 사용한다
        
'''
import sys
input = sys.stdin.readline


#define function
def solution(): 
    # stt -> end로 가는 최소 cost를 계산한다
    for k in range(1, v+1):
        for stt in range(1, v+1):
            for end in range(1, v+1):
                graph[stt][end] = min(graph[stt][end], graph[stt][k] + graph[k][end])
    
    minCost = INF
    # 최소 싸이클을 찾는다
    for i in range(1,v+1):
        minCost = min(graph[i][i], minCost)
    return minCost if minCost != INF else -1
    
    
    

#main
v,e = map(int,input().split())
INF = 1e9
graph = [[INF]*(v+1) for _ in range(v+1)]
# 간선 정보로 그래프를 채움
for _ in range(e):
    a,b,c = map(int,input().split())
    # 단방향 그래프
    graph[a][b] = c
print(solution())