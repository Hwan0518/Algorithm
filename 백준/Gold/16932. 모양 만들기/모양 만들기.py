'''
Condition
    - TL : 2s (4000만)
      ML : 512mb
    - 2<=n,m<=1000
        >>> O(n^2*logn) 까지는 가능
    - 0,1은 무조건 한개 이상 존재한다

Question
    - 인접 : 상하좌우로 연결된 경우
    - 모양 : 인접한 1끼리 연결한것
    - 한칸의 숫자를 바꿨을 떄, 가장 많이 연결된 개수는?

Access
    - 예상 분류 : 그래프 탐색
      실제 분류 : 그래프 탐색
 
    try_1)
        - 완전탐색
            : 모든 1 주위의 0 위치를 찾은 후, 그 0들을 1로 바꿔서 탐색해본다
            >>> fail, TLE
    
    try_2)        
        - 덩어리 찾아서 연결
            : 각각의 1 덩어리들을 구분해서 size를 기록해둔다
              이후에 0들을 전체탐색해서 상하좌우에 존재하는 모든 덩어리들을 찾는다
            
            - global
                zero(list) : 모든 0의 위치
                mapp(list) : 입력받은 map, 추후에 덩어리 number로 구분
                sizeOfLump(dict): 각 덩어리들의 size를 기록
                
                
            1. classify
            : bfs로 각각의 1덩어리들을 구분지음
              각 덩어리의 size를 기록
              연결된 덩어리들을 1,2,3,... 순으로 number를 부여하고,
              해당하는 위치의 mapp값을 number로 바꿈
                    > (r,c)가 2번 덩어리라면, mapp[r][c] = 2
              sizeOfLump[number] = size 로 저장
            
            2. createShape
            : 모든 0들의 위치를 탐색
              상하좌우를 탐색해서 해당하는 모든 덩어리들의 size를 더해서 max값을 계산
              
'''
from collections import deque
from sys import stdin
input = stdin.readline

#define function
def classify():
    numberOfLump = 1
    visited = [[False for _ in range(m)] for _ in range(n)]
    # 전체탐색
    for r in range(n):
        for c in range(m):
            # 방문했거나 값이 0이라면 continue
            if visited[r][c] or mapp[r][c]==0:
                continue
            mapp[r][c] = numberOfLump
            visited[r][c] = True
            dq = deque()
            dq.append((r,c))
            cnt = 1
            while dq:
                cr,cc = dq.popleft()
                for i in range(4):
                    nr,nc = cr+dr[i], cc+dc[i]
                    # 범위를 벗어나거나, 방문한적이 있거나, mapp에서 값이 0이라면 continue
                    if not(0<=nr<n and 0<=nc<m) or mapp[nr][nc]==0 or visited[nr][nc]:
                        continue
                    # 해당하는 number로 mapp을 갱신
                    mapp[nr][nc] = numberOfLump
                    visited[nr][nc] = True
                    dq.append((nr,nc))
                    cnt +=1
            # while이 끝나면, 해당 number 덩어리의 size를 기록
            sizeOfLump[numberOfLump] = cnt
            # numberOfLump를 +1해서 구분해줌
            numberOfLump +=1
    return


def createShape():
    maxSize = 0
    for (cr,cc) in zero:
        size = 1
        numVisited = set()
        for i in range(4):
            nr,nc = cr+dr[i], cc+dc[i]
            if not(0<=nr<n and 0<=nc<m) or mapp[nr][nc]==0:
                continue
            currentNum = mapp[nr][nc]
            if currentNum in numVisited:
                continue
            numVisited.add(currentNum)
            size += sizeOfLump[currentNum]
        maxSize = max(maxSize, size)
    return maxSize


def solution():
    classify()
    maxSize = createShape()    
    return maxSize


#main
n,m = map(int,input().split())
mapp = []
zero = []
for r in range(n):
    data = list(map(int,input().split()))
    mapp.append(data)
    for c in range(m):
        if mapp[r][c] == 0:
            zero.append((r,c))
sizeOfLump = {}
dr = [-1,1,0,0]
dc = [0,0,-1,1]
print(solution())