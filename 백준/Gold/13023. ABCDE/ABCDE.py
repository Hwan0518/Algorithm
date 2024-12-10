'''
Condition
- A-B-C-D-E 가 되는 node가 있는가?

Answer
- 존재하면 1, 없으면 0 출력

Approach
- 모든 node를 root삼아 한번씩 dfs 돌려보면 될 것 같음
    - 이때 depth가 5가 된다면 끝
'''
from sys import stdin
input = stdin.readline

# 입력
n,m = map(int,input().split())
graph = [[] for _ in range(n)]
for _ in range(m):
    a,b = map(int,input().split())
    graph[a].append(b)
    graph[b].append(a)

# dfs
visited = [False] * n
def dfs(node, cnt):
    if cnt == 5:
        print(1)
        exit()
    for neighbor in graph[node]:
        if visited[neighbor]:
            continue
        visited[neighbor] = True
        dfs(neighbor, cnt+1)
        visited[neighbor] = False

# 전체 탐색
for root in range(n):
    dfs(root, 0)
print(0)