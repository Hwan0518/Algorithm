'''
Condition
    - TL : 2s(4000만)
      ML : 128mb
    - n,m <=1000, x,y<=100만

Question
    - 

Access
    - 예상 분류 : 크루스칼, 프림
      실제 분류 : 최소신장트리

    try_1)
        - prim 알고리즘을 사용해본다
        >>> O(N^2logN) 가능
    
    try_2)
        - 전체 신들을 탐색해야한다. 그리고 각각에서 최소연결값을 찾아야한다.
        
    try_3)
        - 전체 node에서 최소를 이어본다
        >>> 모든 노드가 연결됐는지 확인하는 방법을 모르겠음 ㅜㅜ
        
    try_4)
        - kruskal을 사용해본다    
    
'''
from math import sqrt
from sys import stdin
input = stdin.readline


#define function
def connect():
    for i in range(1,n+1):
        for j in range(1,n+1):
            if i==j:
                continue
            cx,cy = god[i]
            nx,ny = god[j]
            cost = sqrt((cx-nx)**2 + (cy-ny)**2)
            graph.append((cost,i,j))
    graph.sort()
    return
    

def findParent(parent,node):
    if parent[node] != node:
        parent[node] = findParent(parent,parent[node])
    return parent[node]


def unionParent(parent,stt,end):
    sttParent = findParent(parent,stt)
    endParent = findParent(parent,end)
    # 더 작은쪽을 부모라고 설정
    if sttParent<endParent:
        parent[endParent] = sttParent
    else:
        parent[sttParent] = endParent


def kruskal():
    # 간선들을 전체탐색한다
    minCost = 0
    for cost,stt,end in graph:
        sttParent = findParent(parent,stt)
        endParent = findParent(parent,end)
        # 부모가 다르다면 연결된 상태가 아니므로 연결시켜준다
        if sttParent != endParent:
            unionParent(parent,stt,end)
            minCost += cost
    return minCost


def solution():
    # 연결정보가 완전하지 않으므로, 그래프를 완성시키고, cost순으로 정렬한다
    connect()
    
    # 이미 연결된 노드들의 부모를 통일시킨다
    for _ in range(m):
        stt,end = map(int,input().split())
        unionParent(parent,stt,end)
        
    # 크루스칼 알고리즘으로 구한 minCost를 출력한다
    minCost = kruskal()
    return minCost




#main
n,m = map(int,input().split())
god = [(0,0)] + [tuple(map(int,input().split())) for _ in range(n)] # 신들의 위치
graph = []  # 연결 정보 그래프
parent = [i for i in range(n+1)]
print(f'{solution():.2f}')
