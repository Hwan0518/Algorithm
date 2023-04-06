'''
Condition
    - TL : 1s(2000만)
      ML : 256(64*100만)

Question
    - 빙산이 두 덩어리 이상으로 분리되는 최초 시간(년)을 구하라
      전부 다 녹을 때까지 계속 한덩어리면 0을 출력한다

Access
    - 예상 분류 : 구현, bfs
      실제 분류 : 구현, dfs, bfs

    try_1)
        - 필요한 기능 : 빙산녹음, 덩어리 확인
            melting : 빙산위치 전체탐색, 해당칸의 상하좌우로 0 개수 확인,
                      해당칸 -= 0개수, 0밑으로 안내려감 = max(0,칸-0개수)
                      해당칸이 1이상이라면 빙산위치 업데이트
                      전체탐색이 끝나면 year +1해줌
            check : bfs를 사용하여 빙산위치만 확인하여 덩어리 개수 확인
            출력 : 마지막 check 이후 덩어리가 1이면 0을 출력
                  덩어리가 1이 아니라면 year를 출력
'''
from collections import deque
from sys import stdin
input = stdin.readline


#define function
def check():
    chunk = 0
    visited = set()
    for (r,c) in iceberg:
        if (r,c) in visited:
            continue
        # (r,c)를 방문한적이 없다면 bfs 시작
        dq = deque()
        dq.append((r,c))
        visited.add((r,c))
        while dq:
            cr,cc = dq.popleft()
            for i in range(4):
                nr,nc = cr+dr[i], cc+dc[i]
                if not(0<=nr<n and 0<=nc<m) or (nr,nc) in visited or mapp[nr][nc]==0:
                    continue
                dq.append((nr,nc))
                visited.add((nr,nc))
        # while문이 끝났다는것은, 한 덩어리에 대해 탐색이 끝났다는 의미이므로 chunk +=1
        chunk +=1
    return chunk


def melting():
    temp = deque()
    zero = []
    # 빙산의 위치를 모두 탐색
    while iceberg:
        (r,c) = iceberg.popleft()
        cnt_0 = 0
        for i in range(4):
            nr = r+dr[i]
            nc = c+dc[i]
            if not(0<=nr<n and 0<=nc<m):
                continue
            if mapp[nr][nc] == 0:
                cnt_0 +=1
        
        # 높이가 0 이하가 되는 부분은 나중에 한꺼번에 처리하기 위해 zero에 추가
        value = mapp[r][c]
        if value-cnt_0 <=0:
            zero.append((r,c))
        # 높이가 0보다 크다면 값을 갱신하고 temp에 위치 저장
        else:
            mapp[r][c] = value-cnt_0
            temp.append((r,c))

    # while문 이후에 높이가 0인 부분을 mapp에 모두 반영
    for (r_0,c_0) in zero:
        mapp[r_0][c_0] = 0
            
    # temp를 반환해서 iceberg를 갱신
    return temp


def solution():
    global iceberg
    # 빙산 위치 정보 저장
    for r in range(n):
        data = list(map(int,input().split()))
        mapp.append(data)
        for c in range(m):
            if data[c] !=0:
                iceberg.append((r,c))
    
    year = 0
    chunk = 1
    # 빙산이 모두 녹거나, 덩어리가 두개 이상이 된다면 종료
    while iceberg and chunk ==1:
        # 빙산 녹기 시작
        iceberg = melting()
        chunk = check()
        year +=1
    
    # 덩어리가 두개 이상이면 최초 분리 시간을, 아니라면 0을 출력
    if chunk >=2:
        return year
    return 0





#main
n,m = map(int,input().split())
mapp=[]
iceberg = deque()
dr = [-1,1,0,0]
dc = [0,0,-1,1]
print(solution())
