'''
Condition
    - TL : 1s(2000만)
      ML : 256(64*100만)
    -
    - 연쇄는 여러 그룹이 터지더라도 동시에 일어났다면 1연쇄이다
    - r=12, c=6, .은 빈공간

Question
    - 연속 몇 연쇄가 일어나는가?

Access
    try_1)
        - 예상 분류 : 구현
          문제 분류 : 구현
        
        - 필요 기능 : 서칭, 중력
            - 서칭 : 뿌요들이 전부 아래로 떨어진 상황에서,
                    1. 동시에 터져야 하는 뿌요들의 위치를 확인
                    2-1. 터져야 하는 뿌요들이 존재한다면 cnt +1하고 계속 진행
                    2-2. 존재하지 않는다면 False를 반환
                    3. 뿌요들을 없앰
            
            - 중력 : 서칭 이후에,
                    1. 각 col별로 row를 11~0까지 탐색,
                       해당 col에서 모든 빈칸의 개수와 row를 찾음
                    2. 모든 빈칸을 list.remove(idx)로 제거하고,
                       list에 다시 갯수만큼 빈칸을 삽입
                       >>> 빈칸을 제일 뒤로 밀어주는 과정임
                    3. 다시 서칭으로 돌아감
                    

'''
from collections import deque
from sys import stdin
input = stdin.readline

#define function
def gravity():
    #전체 탐색
    for r in range(6):
        countBlank = 0
        # 해당 row에서 모든 빈칸 제거
        while '.' in field[r]:
            countBlank +=1
            field[r].remove('.')
        # 해당 row에 다시 빈칸을 삽입
        field[r] += ['.' for _ in range(countBlank)]
    return

def delete(locationDelete):
    for (r,c) in locationDelete:
        field[r][c] = '.'
    return
        

def searching():
    global cnt
    dr = [-1,1,0,0]
    dc = [0,0,-1,1]
    visited = [[False for _ in range(12)] for _ in range(6)]
    locationDelete = [] #이번 탐색에서 터뜨릴 뿌요들의 위치
    
    # 전체 탐색
    for r in range(6):
        for c in range(12):
            # (r,c)가 비어있거나 방문한적이 있다면 continue
            color = field[r][c]
            if color == '.' or visited[r][c]:
                continue
            dq = deque()
            dq.append((r,c,color))
            visited[r][c] = True
            locationSame = [(r,c)] # (r,c)와 상하좌우로 연결된 같은색의 뿌요들의 위치를 기록한 리스트
            
            # bfs 시작
            while dq:
                cr,cc,c_color = dq.popleft()
                for i in range(4):
                    nr,nc = cr+dr[i], cc+dc[i]
                    
                    # (nr,nc)가 범위를 벗어났거나 + 방문을 했거나 + 현재색상과 다르다면 continue
                    if not(0<=nr<6 and 0<=nc<12):
                        continue
                    n_color = field[nr][nc]
                    if visited[nr][nc] or c_color != n_color:
                        continue
                    
                    dq.append((nr,nc,n_color))
                    locationSame.append((nr,nc))
                    visited[nr][nc] = True
            
            # (r,c)와 상하좌우로 연결된 같은색의 뿌요가 4개 이상이라면, 터뜨릴 수 있으므로 delete 리스트에 추가   
            if len(locationSame) >=4:
                for loc in locationSame:
                    locationDelete.append(loc)
    
    # 이번 탐색에서 삭제할 뿌요들이 존재한다면 연쇄를 +1추가하고, 삭제 이후 True를 리턴
    if locationDelete:
        cnt +=1
        delete(locationDelete)
        return True
    # 삭제할 뿌요들이 존재하지 않는다면 False 리턴
    else:
        return False
    

def gameStart():
    while searching():
        gravity()
    # searching() == False라면 게임을 끝낸다
    return cnt


def changeField():
    changed = [[0 for _ in range(12)] for _ in range(6)]
    for r in range(11,-1,-1):
        for c in range(6):
            changed[c][11-r] = inputField[r][c]
    return changed

#main
inputField = [list(input().strip()) for _ in range(12)]
field = changeField()
cnt = 0
print(gameStart())
