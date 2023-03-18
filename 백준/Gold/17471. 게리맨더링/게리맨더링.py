'''
Condition
    - TL : 0.5s (1000만)
      ML : 512 (128*100만)
    - 2<=n<=10, 1<=구역 인구수<=100

Question
   - 조건에 맞게 선거구를 나눴을 때, 두 선거구에 포함된 인구 차이의 최소를 구하라

Access
   - 예상 분류 : 그래프
     실제 분류 : 그래프, BFS, DFS, 브루트포스

   try_1)
        - 두 선거구가 이룰 수 있는 조합들의 인구수 차이를 오름차순 정렬
        - 이후에 오름차순 정렬된 차이에 해당하는 조합을 순서대로 탐색해서
            1. 조건을 만족시킨다면 정답으로 출력
            2. 그렇지 않다면 다음 차이에 해당하는 조합으로 넘어감
        >>> fail
        
    try_2)
        1. 먼저 가능한 선거구를 모두 combination으로 찾아서 전체 탐색
        2. 첫번째 선거구에서 각 지역이 조건을 만족하는지 확인
            만족하지 못한다면 continue
        3. 두번째 선거구에서 각 지역이 조건을 만족하는지 확인
            만족하지 못한다면 continue
        4. 2,3번을 다 만족한다면 차이를 비교해서 최솟값을 갱신
'''

from collections import deque
from itertools import combinations
from sys import stdin
input = stdin.readline





#define function
def calcMin(dist1, dist2):
    sum_dist1 = 0
    sum_dist2 = 0
    # 각 구역에 포함된 지역들의 인구수를 더함
    for i in dist1:
        sum_dist1 += population[i]
    for j in dist2:
        sum_dist2 += population[j]
    # 두 구역의 인구수 차이를 구함
    return abs(sum_dist1 - sum_dist2)


def check(district,visited):
    # 만약 선거구의 지역중, visited에 존재하지 않는다면 연결되어있지 않는것이므로 False
    for area in district:
        if area not in visited:
            return False
    return True


def bfs(stt,visited,district):
    #district의 지역들중, stt에 연결된 지역들을 visited에 대입한다
    dq = deque()
    dq.append(stt)
    visited.add(stt)
    
    #bfs 시작
    while dq:
        current = dq.popleft()
        # district의 지역들을 탐색
        for area in district:
            # 자기 자신이거나 방문한적 있다면 continue
            if current == area or area in visited:
                continue
            # area가 current의 이웃이라면 연결되어 있으므로 visited에 추가
            if area in graph[current]:
                visited.add(area)
                dq.append(area)
    return visited
    

def solution():
    minDiff = 1e9
    #첫번째 선거구를 먼저 구한다 : 중복을 없애기 위해 절반까지만 뽑음
    for i in range(1,n//2+1):
        for district_1 in combinations(range(1,n+1),i):
            visited = set()
            stt = district_1[0]
            
            # 첫번째 선거구의 지역들중, 첫번째 지역과 연결된 지역을 visited에 저장한다
            visited = bfs(stt,visited,district_1)
            # 첫번째 선거구가 조건을 만족하는지 확인한다
            if not check(district_1,visited):
                continue
            
            # 두번째 선거구룰 구한다 : 첫번째 선거구에 포함되지 않아야 하고, 방문하지 않은곳(=연결되지 않은곳)만 가능하다
            district_2 = [k for k in range(1,n+1) if k not in district_1 and k not in visited]
            # 두번째 선거구 지역들중, 첫 번째 지역과 연결된 지역을 visited에 저장한다
            visited = bfs(district_2[0],visited,district_2)            
            # 두번째 선거구가 조건을 만족하는지 확인한다
            if not check(district_2,visited):
                continue

            # 모든 조건을 만족했다면 인구수 차이 계산
            curDiff = calcMin(district_1,district_2)
            minDiff = min(minDiff, curDiff)
    
    return -1 if minDiff ==1e9 else minDiff        






#main
n=int(input())
population = [0]+list(map(int,input().split()))
graph = [[] for _ in range(n+1)]
for i in range(1,n+1):
    data = list(map(int,input().split()))
    graph[i] = data[1:]

print(solution())

