'''
Condition
    - TL : 1s (2000만)
      ML : 128 (32*100만)
    - 1<=N,M<= 500, 0<=높이<=100
    - 나무가 있으면 1, 없으면 0

Question
    - 위,앞,오른쪽에서 본 쌓기나무로 모양을 유추하여
      가장 많이 쌓아야 하는 경우를 출력하여라. 불가능하면 -1을 출력

Access
    try_1)
        - 예상분류 : 구현
          문제분류 : 구현, 그리디 알고리즘, 구성적

            1. 앞(N+2)에서 본 모양으로,
                해당하는 줄의 가능한 위치를(1의 위치) 모든 높이로 통일한다
                >>> 각 col의 최댓값이 높이가 된다
        
            2. 오른쪽(N+3)에서 본 모양으로,
                해당하는 줄의 가능한 위치를(1의 위치) 모든 높이를 통일한다
                >>> 각 row의 최댓값이 높이가 된다
        
        >>> Fail : 앞에서 본 모양과 오른쪽에서 본 모양에서 동일 위치의 최솟값으로 갱신해야한다
    
    try_2)
            1. 앞에서 본 모양으로 갱신
            2. 오른쪽에서 본 모양으로 갱신
               : aboveShape에서 0이 아닌 위치를, front와 right의 최솟값으로 갱신한다

        
'''
from sys import stdin
input = stdin.readline



#define function

def searchingRight():
    for r in range(n):
        rightStack = rightShape[r]
        for c in range(m):
            frontStack = frontShape[c]
            stack = aboveShape[r][c]
            if not stack:
                continue
            if rightStack <= frontStack:
                aboveShape[r][c] = rightStack
    return



def searchingFront():
    for c in range(m):
        frontStack = frontShape[c]
        for r in range(n):
            stack = aboveShape[r][c]
            if not stack:
                continue
            aboveShape[r][c] = frontStack
    return



def isPossible():
    #Front Shape을 검사
    for c in range(m):
        maxColumn = 0
        for r in range(n):
            currentColumn = aboveShape[r][c]
            maxColumn = max(maxColumn, currentColumn)
        if maxColumn != frontShape[c]:
            return False
    
    #Right Shape을 검사
    for r in range(n):
        maxRow = max(aboveShape[r])
        if maxRow != rightShape[r]:
            return False
    
    # 모두 동일하다면 True를 반환    
    return True
            
            

def solution():
    searchingFront()
    searchingRight()
    
    # 앞,옆에서 본 모습과 일치하는지 검사
    if not isPossible():
        print(-1)
        return

    # 일치한다면 정답 출력
    for shape in aboveShape:
        print(*shape,sep=' ')
    return




#main
n,m = map(int,input().split())
aboveShape = [list(map(int,input().split())) for _ in range(n)]
frontShape = list(map(int,input().split()))
rightShape = list(map(int,input().split()))
rightShape.reverse()

solution()
