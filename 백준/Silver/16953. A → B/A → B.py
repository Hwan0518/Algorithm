'''
- TL: 2s
- possible operation
    - x2
    - +1 in far right

Goals
    - A를 B로 바꾸는데 필요한 연산의 최솟값

Approach
    - 백트래킹 or DP
    - 백트래킹 먼저 해보자
'''
def dfs(num:int, cnt:int):
    global min_cnt
    # 종료 조건
    if num > b:
        return
    elif num == b:
        min_cnt = min(min_cnt, cnt)
    # 백트래킹
    dfs(num*2, cnt+1)
    dfs(int(str(num)+'1'), cnt+1)
    return

def main():
    global min_cnt, b
    min_cnt = 1e9
    a, b = map(int,input().split())
    dfs(a, 0)
    return min_cnt+1 if min_cnt != 1e9 else -1

print(main())