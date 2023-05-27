'''
Condition
    - TL : 2s(4000만)
      ML :
    - 2<=k<=5
    - 1<=v<= 2만
    - 1<=e<= 20만

Question
    - 주어진 그래프가 이분그래프인지 판별하라

Access
    - 예상 분류 : 그래프
      실제 분류 : 그래프, 이분그래프

    try_1)
        - root node : 연결된 노드가 2개 이상인 노드
          child node : root node에 연결된 노드
          >>> 이때 child의 연결된 노드는 단 하나만 존재해야한다
          >>> dfs - 메모리초과
          
    try_2)
        - hint : https://ji-gwang.tistory.com/293
        - 이웃 node들을 계속 다른색으로 칠할때, 같은 색이 연결되는지 확인해봄

    try_3)
        - clone coding
'''
from collections import deque
from sys import stdin
input = stdin.readline


#define function
def createGraph():
    graph = [[] for _ in range(v+1)]
    for _ in range(e):
        a,b = map(int,input().split())
        graph[a].append(b)
        graph[b].append(a)
    return graph

        
def solution():
    visited = [0]*(v+1)
    # 모든 노드에 대해서 탐색 시작
    for node in range(1,v+1):
        if visited[node]:
            continue
        dq = deque()
        dq.append(node)
        # node를 1번 색으로 설정
        visited[node] = 1
        while dq:
            cur = dq.popleft()
            for neighbor in graph[cur]:
                # 아직 방문한적이 없다면, cur 그룹과 다른 색으로 저장한다
                if not visited[neighbor]:
                    dq.append(neighbor)
                    visited[neighbor] = -1 * visited[cur]
                # 만약 방문했다면, cur과 neighbor의 색을 비교해서 같다면 false를 return
                elif visited[cur] == visited[neighbor]:
                    return "NO"
    return "YES"
            

#main
k=int(input())
for _ in range(k):
    v,e = map(int,input().split())
    graph = createGraph()
    print(solution())