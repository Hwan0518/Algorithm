'''
Condition
    - TL : 1s(2000만)
      ML : 512(128*100만)
    - 6<=r,c<=50, 1<=t<=1000
    - 공기청정기는 1열에 설치되어있고, 2행을 차지하며, 값은 -1이다

Question
    - t초가 지난 후, 방에 남아있는 미세먼지의 양은?

Access
    - 예상 분류 : 구현
      실제 분류 : 구현

    try_1)
        - 두 기능을 구현한다
        1. 미세먼지 확산(동시에 일어남) 
        >>> (r,c)에 남아있는 미세먼지 양과 확산되는 양을 계산만 먼저하고 따로 저장해둔 후 나중에 한번에 적용
            1-1. (r,c)가 5이상이라면,
                 (r,c)의 상하좌우에 공기청정기가 없고 방을 벗어나지 않는다면 확산
            1-2. 확산되는 양은 (r,c//5)
            1-3. (r,c)에 남아있는 양은 (r,c) - (r,c//5)*(확산된 방향개수)
        
        2. 공기청정기 작동(공기청정기가 있는 행과 모서리에만 작용)
        >>> 한칸씩 밀기보다는 당겨온다는 느낌으로 진행해야한다!!
            - 위쪽에 해당하는 공기청정기(행 = r1)
            2-1. i=r1-1, j=1로 시작 
            while (i,j) != (r1,1):
                # 아래로 당겨옴
                if j==1:
                    # 공기청정기에 들어오는게 아니라면 값을 당겨옴
                    if i+1 !=r1:   
                        mapp[i+1][j] += mapp[i][j]
                    mapp[i][j] = 0
                    i +=1     
                # 왼쪽으로 당겨옴
                elif i==1 and j!=1:
                    mapp[i][j-1] += mapp[i][j]
                    mapp[i][j] = 0
                    j += 1
                # 위로 당겨옴
                elif i!=1 and j==c:
                    mapp[i-1][j] += mapp[i][j]
                    mapp[i][j] = 0
                    i += 1
                # 오른쪽으로 당겨옴
                elif i==r1 and j!=c:
                    # 공기청정기에서 당겨오는게 아니라면 값을 그대로 당겨옴
                    if 
                    mapp[i][j+1] += mapp[i][j]
                    mapp[i][j] = 0
                    j -= 1
                    
            2-2. i=r2+1, j=1로 시작
            while (i,j) != (r2,1):
                # 위로 당겨옴
                if j==c:
                    # 공기청정기에 들어오는게 아니라면 값을 당겨옴
                    if i-1 !=r2:
                        mapp[i-1][j] += mapp[i][j]
                    mapp[i][j] = 0
                    i += 1
                # 왼쪽으로 당겨옴
                elif i==1 and j!=1:
                    mapp[i][j-1] += mapp[i][j]
                    mapp[i][j] = 0
                    j += 1
                # 아래로 당겨옴
                elif j==1:
                    mapp[i+1][j] += mapp[i][j]
                    mapp[i][j] = 0
                    i +=1     
                # 오른쪽으로 당겨옴
                    elif i==r2 and j!=c:
                        mapp[i][j+1] += mapp[i][j]
                        mapp[i][j] = 0
                        j -= 1
            
        3. sum으로 더하는데, 공기청정기가-1이므로 +2를 해주어서 출력한다
        
'''
from typing import List
from sys import stdin
input = stdin.readline


#define function
def doitClean(r1:int, r2:int, r:int, c:int, mapp:List)->None:
    # 공기청정기 위쪽 먼저 시작
    i,j = r1,0
    while (i,j) != (r1,1):
        # 아래로 당겨옴
        if i!=0 and j==0:
            # 공기청정기로 들어올때는 값을 당겨오지 않음,
            # 공기청정기가 아니라면 값을 당겨옴
            if i!=r1:    
                mapp[i][j] += mapp[i-1][j]
            mapp[i-1][j] = 0
            i -=1     
        # 왼쪽으로 당겨옴
        elif i==0 and j!=c-1:
            mapp[i][j] += mapp[i][j+1]
            mapp[i][j+1] = 0
            j +=1
        # 위로 당겨옴
        elif i<r1 and j==c-1:
            mapp[i][j] += mapp[i+1][j]
            mapp[i+1][j] = 0
            i +=1
        # 오른쪽으로 당겨옴
        elif i==r1:
            mapp[i][j] += mapp[i][j-1]
            mapp[i][j-1] = 0
            j -=1
                    
    # 공기청정기 아래쪽 시작                
    i,j = r2,0
    while (i,j) != (r2,1):
        # 위로 당겨옴
        if i<r-1 and j==0:
            if i!=r2:
                mapp[i][j] += mapp[i+1][j]
            mapp[i+1][j] = 0
            i +=1
        # 왼쪽으로 당겨옴
        elif i==r-1 and j!=c-1:
            mapp[i][j] += mapp[i][j+1]
            mapp[i][j+1] = 0
            j +=1
        # 아래로 당겨옴
        elif i>r2 and j==c-1:
            mapp[i][j] += mapp[i-1][j]
            mapp[i-1][j] = 0
            i -=1     
        # 오른쪽으로 당겨옴
        elif i==r2:
            mapp[i][j] += mapp[i][j-1]
            mapp[i][j-1] = 0
            j -=1
    return



def diffusion(r:int, c:int, mapp:List)->None:
    possibleInfo = [] # 확산 정보
    # 전체탐색으로 확산 정보를 입력
    for cr in range(r):
        for cc in range(c):
            # 먼지 양이 5이상일때만 확산가능
            if mapp[cr][cc]<5:
                continue
            spread = mapp[cr][cc]//5 #확산되는 양
            spreadCnt = 0
            # 상하좌우 탐색
            spreadLoc = []
            for i in range(4):
                nr,nc = cr+dr[i], cc+dc[i]
                if not(0<=nr<r and 0<=nc<c) or mapp[nr][nc]==-1:
                    continue
                # 조건을 만족한다면, 확산되는 위치 파악
                spreadLoc.append((nr,nc))
                spreadCnt +=1
            # 확산 정보 업데이트
            possibleInfo.append(((cr,cc),spread,spreadCnt,spreadLoc))
                
    # 확산 정보를 바탕으로 확산 시작
    while possibleInfo:
        (cr,cc),spread,spreadCnt,spreadLoc = possibleInfo.pop()
        # 상하좌우로 확산
        for (nr,nc) in spreadLoc:
            mapp[nr][nc] += spread
        # 확산되고 남은 먼지를 갱신
        mapp[cr][cc] -= (spread*spreadCnt)
        
    return



def solution(r1:int, r2:int, r:int, c:int, t:int, mapp:List)->int:
    dust = 0
    # t초동안 반복
    for _ in range(t):
        # 먼지 확산
        diffusion(r,c,mapp)
        # 공기청정기 가동
        doitClean(r1,r2,r,c,mapp)
    
    # 방에 남아있는 먼지의 양을 구해서 리턴
    for cr in range(r):
        dust += sum(mapp[cr])
    return dust +2



def readInput():
    r,c,t = map(int,input().split())
    mapp = []
    # 공기청정기 위치 확인
    airCleaner = []
    for cr in range(r):
        mapp.append(list(map(int,input().split())))
        if mapp[cr][0] == -1:
            airCleaner.append(cr)
    # 공기청정기의 윗부분이 r1, 아랫부분이 r2
    r1 = min(airCleaner)
    r2 = max(airCleaner)
            
    return r1,r2,r,c,t,mapp





#main
dr = [-1,1,0,0]
dc = [0,0,-1,1]
print(solution(*readInput()))

