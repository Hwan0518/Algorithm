'''
Condition
    - TL : 1s (2000만)
      ML : 1024mb (***)
    - 블록 : 검은색(-1),무지개(0),일반(M가지색)
    - 인접하다 : abs(r1-r2)+abs(c1-c2)=1 >>> 상하좌우
    - 블록그룹 : 일반이 포함된 연결된 블록들, 일반은 모두 같은색, 검은색 X, 무지개는 아무렇게나
                블록개수는 2이상
    - 블록그룹 기준블록 : 무지개 X, 행~열이 가장 작은 블록
    - 오토 플레이 (블록 그룹이 더이상 없을때까지 반복)
            1. 크기가 가장 큰 블록 그룹을 찾음
                : 여러개라면, 무지개가 가장 많은 블록그룹
                            >기준 블록 행이 가장 큰 그룹
                            >기준 블록 열이 가장 큰 그룹 순으로 찾는다
            2. 1에서 찾은 그룹의 크기를 size라 하면, (size^2)점을 획득함
            3. 검은색을 제외하고 모두 아래로 떨어진다(중간에 검은색에 막힐 수 있음)
            4. mapp이 90도 반시계로 회전한다
            5. 3번을 반복한다
            6. 1~5를 반복하는데, 1에서 그룹을 찾지 못한다면 플레이를 종료한다
    - 1<=N<=20, 1<=M<=5

Question
    - 가능한 점수의 최댓값은 ?

Access
    try_1)
        - key point : 블록그룹찾기, 중력작용, mapp회전
        - 블록그룹찾기 : 기준블럭(일반)에서 탐색시작, 다른 색의 일반블록, 검은색블록을 만나면 정지함
                        이때 이동한 횟수가 블록그룹의 size가됨(기억해야함)
                        >>> TC : O(N^2) = 400
        - 중력작용 : 각 col(0~n)에 대해 모든 row(0~n)를 탐색해서 -1을 찾아낸다
                    탐색도중 -1이 아닌 블럭을 non_black 리스트에 대입해준다
                    이후 -1이 있는 칸의 행을 b_row라 하면 (b_row-1)까지 차례로 블럭을 이동시킨다
                    차례로 블럭 이동은, for block in non_black를 사용하고, b_row-1부터 row를 -1씩 감소시키며 이동시킨다
                    >>> TC : O(N^2 *N^2) = 16000
        - mapp회전 : y=row, x=col일때 
                    # 반시계방향 90도 회전
                        new_x = (n-1) - old_y
                        new_y = old_x

                    # 시계방향 90도 회전
                        new_x = old_y
                        new_y = (n-1) - old_x
                    을 사용해서 전체를 회전시킨다
                    >>> TC : O(N^2) = 400
        >>> 예제2번 fail
    
    try_2)
        - 가장 큰 블록그룹이 하나가 아니라 여러개라는 조건을 빼먹었음
            >>> 추가하고 예제2는 맞았지만 예제3 fail
        - 조건 잘못돼서 수정
            >>> 예제3 통과
        
    try_3)
        - 0의 lotation을 플레이 전에 저장해두고,
          그 상태로 매번 visited를 False로 갱신해주었음
            >>> 0의 위치가 매번 바뀌기 때문에 유동적으로 바꿔주었음
'''
from copy import deepcopy
from collections import deque
from sys import stdin
input = stdin.readline

#define function
def find_group():
    visited = [[False for _ in range(n)] for _ in range(n)]
    max_size = 0
    max_group = []
    max_zero = 0
    max_standard = (0,0)
    for r in range(n):
        for c in range(n):
            if mapp[r][c] <=0 or visited[r][c]:
                continue
            change = False
            standard = (r,c)
            cnt_zero = 0
            group_lotation = []
            size = 1
            group_lotation.append((r,c))
            visited[r][c] = True
            dq=deque()
            dq.append((r,c))
            while dq:
                cr,cc = dq.popleft()
                for i in range(4):
                    nr,nc = cr+dr[i], cc+dc[i]
                    if not(0<=nr<n and 0<=nc<n) or visited[nr][nc] or mapp[nr][nc] <= -1:
                        continue
                    elif mapp[nr][nc] >0:
                        if mapp[nr][nc] != mapp[r][c]:
                            continue
                    if mapp[nr][nc] == 0: cnt_zero +=1
                    visited[nr][nc] = True
                    dq.append((nr,nc))
                    size +=1
                    group_lotation.append((nr,nc))
            # 가장 큰 그룹을 찾음
            group_lotation.sort()
            for (sr,sc) in group_lotation:
                if mapp[sr][sc] != 0:
                    standard = (sr,sc)
                    break
            if size > max_size:
                change = True
            elif size == max_size:
                if cnt_zero > max_zero:
                    change = True
                elif cnt_zero == max_zero:
                    if standard[0] > max_standard[0]:
                        change = True
                    elif standard[0] == max_standard[0]:
                        if standard[1] > max_standard[1]:
                            change = True
            # 가장 큰 그룹을 새로 발견했다면 갱신함
            if change:
                max_size = max(max_size,size)
                max_group = group_lotation[:]
                max_standard = standard
                max_zero = cnt_zero
            # 무지개 블록의 방문만 False로 초기화
            for zr in range(n):
                for zc in range(n):
                    if mapp[zr][zc]==0:
                        visited[zr][zc] = False
    return max_size,max_group

def gravity():
    for col in range(n):
        non_black=[] #각 열에 대해 검은색이 아닌 블록들
        for c_row in range(n):
            c_block = mapp[c_row][col]
            # 검은색 or 격자 끝을 만나면, 그 위에부터 쌓이기 시작
            if c_block == -1 or c_row == n-1:
                if c_block == -1:
                    c_row -=1
                else:
                    if c_block != -2:
                        non_black.append(c_block)
                while non_black:
                    block = non_black.pop()
                    mapp[c_row][col] = block
                    c_row -=1
                continue
            # 검은색과 빈칸이 아니라면 non_black 리스트에 대입
            if c_block != -2:
                non_black.append(c_block)
            mapp[c_row][col] = -2
    return

def rotate():
    temp = deepcopy(mapp)
    for r in range(n):
        for c in range(n):
            nr = (n-1)-c
            nc = r
            mapp[nr][nc] = temp[r][c]
    return

def solution(score):
    #1.블록 그룹 찾기
    max_size,max_group = find_group()
    if max_size>1: # 블록 그룹이 존재한다면 수행
        #2.그룹의 모든 블럭을 제거 후 점수 획득
        for lot in max_group:
            r,c = lot
            mapp[r][c] = -2 #빈칸을 -2로 설정
        score += max_size**2
        #3. 중력 작용
        gravity()
        #4. 반시계 방향으로 회전
        rotate()
        #5. 중력 작용
        gravity()
        #다시 반복
        score = solution(score)
    return score

#main
n,m = map(int,input().split())
mapp =[]
zero =[]
for input_r in range(n):
    rows = list(map(int,input().split()))
    mapp.append(rows)
    for input_c in range(n):
        if rows[input_c] == 0:
            zero.append((input_r,input_c))
dr = [-1,1,0,0]
dc = [0,0,-1,1]
print(solution(0))
