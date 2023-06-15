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
    dq.append(1)
    before = set()
    while dq:
        curNode = dq.popleft()
        before.add(curNode)
        child_Cnt = 0
        for child in graph[curNode]:
            if child in before:
                continue
            child_Cnt +=1
            dq.append(child)
        if not child_Cnt:
            leaf.append(curNode)
    return w/len(leaf)
        




# Main
n,w = map(int,input().split())
graph = [[] for _ in range(n+1)]
for _ in range(n-1):
    u,v = map(int,input().split())
    graph[u].append(v)
    graph[v].append(u)
print(solution())