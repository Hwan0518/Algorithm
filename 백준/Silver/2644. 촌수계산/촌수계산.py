'''
Approach
    - 부모-자식 관계를 보고 주어진 두 사람의 촌수를 계산하여라
    - 1<=n<=100

>>> 두 사람의 공통 부모 node를 찾는다
    : a,b의 촌수를 계산할 때, a의 부모들과 b의 부모들을 기억해두고 교집합을 찾는다
      이후 가장 가까운 node까지의 촌수를 계산해본다

>>> 플로이드 워셜쓰면 해결될거같은데?
'''
from collections import deque
from sys import stdin
input = stdin.readline

def inputData():
    n = int(input())
    p1,p2 = map(int,input().split())
    m = int(input())
    graph = [[int(1e9)]*(n+1) for _ in range(n+1)]
    for _ in range(m):
        a,b = map(int,input().split())
        graph[a][b] = 1
        graph[b][a] = 1
    for oneself in range(n+1):
        graph[oneself][oneself] = 0
    return n,p1,p2,m,graph

def solution(n,p1,p2,m,graph):
    for k in range(1,n+1):
        for a in range(1,n+1):
            for b in range(1,n+1):
                graph[a][b] = min(graph[a][b], graph[a][k]+graph[k][b])
    
    answer = graph[p1][p2]
    return answer if answer != int(1e9) else -1

print(solution(*inputData()))
