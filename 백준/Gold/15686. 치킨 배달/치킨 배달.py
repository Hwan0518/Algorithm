'''
Condition
    - TL : 1s
    - 2<=n<=50, 1<=m<=13
    - 도시: 0빈칸, 2치킨집, 1집
    - 치킨거리: 집과 가장 가까운 치킨집 사이의 거리
    - 도시 치킨거리: 모든집 치킨거리의 합
    - r,c는 1부터 시작
    - (r1,c1), (r2,c2) 사이 거리 : |r1-r2|+|c1-c2|

Question
    - m개의 치킨집을 골랐을 때, 도시 치킨거리의 최솟값은?

Approach
    - 분류 : 구현

try_1)BFS 
    - 모든 집마다 BFS를 시행해서 가장 짧은 치킨거리를 구함
    - 가장 짧은 치킨거리를 구하면, 바로 다음 집의 치킨거리를 구함
    - 그렇게 구해진 치킨거리들을 하나의 list에 넣어서 sort한 후, 가장 앞의 3개의 치킨집에 대한 모든 치킨 거리를 더함
    - fail: 잘못된 접근

try_2)BFS
    - 모든 집마다 bfs를 시전해서 가장 가까운 치킨집 위치를 체크함
    - 체크수가 가장 많은 상위 m개의 치킨집을 선정
    - 각 집에서 m개의 치킨집에 대한 최소거리를 계산
    - 모든 최소거리를 합함
    - fail: 구현 실패
    
try_3)Combination
    - reference: https://codesyun.tistory.com/185
'''
from itertools import combinations
from sys import stdin
input = stdin.readline

def input_data():
    n,m = map(int,input().split())
    home = []
    store = []
    # mapp을 만들면서 집과 치킨집의 위치도 구함
    for r in range(n):
        rows = list(map(int,input().split()))
        for c in range(n):
            if rows[c] == 1:
                home.append((r,c))
            elif rows[c] == 2:
                store.append((r,c))
    # 반환
    return m,home,store

def solution(m,home,store):
    min_d = 1e9
    # m개의 치킨집을 선택함
    for s in combinations(store, m): # s = [(r1,c1), (r2,c2), ... (rm,cm)]
        sum_d = 0
        # 각 집마다 가장 가까운 치킨집과의 거리를 구한다
        for h in home: # h = (r,c)
            min_dist = 1e9
            for i in range(m):
                dist = abs(h[0]-s[i][0]) + abs(h[1]-s[i][1])
                min_dist = min(min_dist, dist)
            sum_d += min_dist
        min_d = min(min_d, sum_d)
    return min_d

print(solution(*input_data()))
