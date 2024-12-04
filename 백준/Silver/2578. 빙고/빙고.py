'''
구현
- 숫자를 불렀을 때, 그 수의 상하좌우대각선 을 모두 확인해본다
'''
from sys import stdin
input = stdin.readline
VERTICAL = "v"
HORIZON = "h"
RDIGONAL = "rd"
LDIGONAL = "ld"


# num에 대해 수직, 수평, 오른대각, 왼대각 을 확인한다
def check(bingoVisited:set, visited:list, loc:dict, num:int):
    r,c = loc[num]
    visited[r][c] = True
    # 수직 확인
    if (c,VERTICAL) not in bingoVisited:
        for i in range(5):
            if not visited[i][c]:
                break
        else:
            bingoVisited.add((c,VERTICAL))
    # 수평 확인
    if (r,HORIZON) not in bingoVisited:
        for i in range(5):
            if not visited[r][i]:
                break
        else:
            bingoVisited.add((r,HORIZON))
    # 오른대각 확인
    if (RDIGONAL) not in bingoVisited:
        for i in range(5):
            if not visited[i][4-i]:
                break
        else:
            bingoVisited.add((RDIGONAL))
    # 왼대각 확인
    if (LDIGONAL) not in bingoVisited:
        for i in range(5):
            if not visited[i][i]:
                break
        else:
            bingoVisited.add((LDIGONAL))
    # 결과 리턴
    return bingoVisited, visited


def main():
    # num에 해당하는 r,c 매핑
    loc = {}
    for r in range(5):
        nums = list(map(int,input().split()))
        for c in range(5):
            loc[nums[c]] = [r,c]
    # 빙고 visit
    bingoVisited = set()
    # 숫자 visit
    visited = [[False]*5 for _ in range(5)]
    # 사회자가 부르는 수
    nums = []
    for _ in range(5):
        nums += list(map(int,input().split()))
    # 빙고 시작
    cnt = 0
    for num in nums:
        cnt +=1
        bingoVisited, visited = check(bingoVisited, visited, loc, num)
        if len(bingoVisited) >= 3:
            break
    return cnt


print(main())