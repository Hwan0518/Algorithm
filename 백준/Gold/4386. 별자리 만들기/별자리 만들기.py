'''
Condition
    - TL :
      ML :

Question
    - 

Access
    - 예상 분류 : 수학, 백트래킹
      실제 분류 : 그래프, mst

    try_1)
        - MST : 모든 별들을 연결시켜야 하므로 mst를 시도하면 된다!
        - 백트래킹 : 각 별이 연결될 수 있는곳은 (자신을 제외한 n-1개의 별 + 자신이 연결되지 않은 선분의 개수=(n*n-1)/2-1개) 이다
                   즉, O(n^2)라 볼 수 있는데, 총 n개의 별이 존재하므로 (n**2)**n으로 TLE가 발생한다
                   
        - 크루스칼 
                - 각 별들끼리의 edge는 쉽게 구할 수 있다
                - 선분과 별의 거리는 어떻게 구할것인가?
                    1. 기준 별과, 선분이 예각을 이룸
                        = 별과 선분을 수직으로 연결할 수 있음
                        = 수직으로 연결한 거리가 cost
                    2. 기준 별과, 선분이 둔각을 이룸
                        = 두 별중 가까운 쪽과 기준 별을 연결하는것이 최소 cost임
                        = 선분을 연결할 필요가 없음
            >>> 구하기 빡셈..
            
    try_2)
        - 모든 별들끼리만 연결..? why?
                    
'''
from sys import stdin
import math
input = stdin.readline


#define function
def calcEdge():
    edges = []
    stars = [tuple(map(float,input().split())) for _ in range(n)]
    # 별끼리의 간선을 구함
    for i in range(n-1):
        for j in range(i+1, n):
            x_i,y_i = stars[i]
            x_j,y_j = stars[j]
            # i에 대한 j의 cost를 구함
            cost = ((x_i-x_j)**2 + (y_i-y_j)**2)**0.5
            # 간선 정보를 저장
            edges.append((cost,i,j))
    edges.sort()
    return edges
    

def find_P(parents,node):
    if parents[node] != node:
        parents[node] = find_P(parents, parents[node])
    return parents[node]


def solution():
    minCost = 0
    for edge in edges:
        cost,stt,end = edge
        p_stt = find_P(parents,stt)
        p_end = find_P(parents,end)
        # union
        if p_stt != p_end:
            if p_stt < p_end:
                parents[p_end] = p_stt
            else:
                parents[p_stt] = p_end
            minCost += cost
    return round(minCost,2)
            




#main
n=int(input())
edges = calcEdge()
parents = [i for i in range(n+1)]
print(solution())