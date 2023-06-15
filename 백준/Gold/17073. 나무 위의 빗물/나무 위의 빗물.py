'''
Condition
    - TL : 1s
      ML :
    - 2<=n<=50만, 1<=w<=10^9

Question
    - 

Access
    - 예상 분류 : 트리
      실제 분류 :

    try_1)
        - 물이 움직이지 않는 상태에서, p_i>0인 정점들 = leaf node
            >>> 그렇다면 leaf_node들의 p_i 평균은?
        - ex) 예제1
            leaf node : 2,4,5
            2 : w=10
            4 : w=5
            5 : w=5     
            따라서 20/3 = 6.666667
            >>> 결국 전체 물/leaf node의 수 를 계산하면 됨
'''
from collections import deque
from sys import stdin
input = stdin.readline



# Define function
def solution():
    leaf = []
    dq = deque()
    # root부터 탐색 시작, root는 무조건 1
    dq.append(1)
    # 노드 방문여부
    visited = set()
    while dq:
        curNode = dq.popleft()
        # 방문처리
        visited.add(curNode)
        check = 0
        # 자식 노드를 탐색
        for child in graph[curNode]:
            if child in visited:
                continue
            # 자식 node가 있다면 체크
            check = 1
            dq.append(child)
        # 자식 node가 없다면 leaf에 추가
        if not check:
            leaf.append(curNode)
    # w/leaf노드 수가 정답이 됨
    return w/len(leaf)
        




# Main
n,w = map(int,input().split())
graph = [[] for _ in range(n+1)]
for _ in range(n-1):
    u,v = map(int,input().split())
    # 무방향 그래프
    graph[u].append(v)
    graph[v].append(u)
print(solution())