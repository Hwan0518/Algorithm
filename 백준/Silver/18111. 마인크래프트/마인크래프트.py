'''
- binary search
    -> 조건을 만족하는 구간이, 주어진 구간 전체가 되어버린다면 탐색할 방법이 없음
- 브루트포스
    -> 높이 전체를 완탐하자
'''

from sys import stdin
input = stdin.readline

def input_data():
    n,m,b = map(int,input().split())
    mapp = [list(map(int,input().split())) for _ in range(n)]
    return n, m, b, mapp

def need_block(mapp:list[list], target:int, n:int, m:int):
    need = 0
    in_block = 0
    for r in range(n):
        for c in range(m):
            if target > mapp[r][c]:
                need += (target - mapp[r][c])
            else:
                in_block += (mapp[r][c] - target)
    return need, in_block

def need_time(mapp:list[list], target, n, m):
    t = 0
    in_block = 0
    for r in range(n):
        for c in range(m):
            ch = mapp[r][c]
            if ch < target:
                t += (target-ch)
            else:
                t += 2*(ch-target)
                in_block +=(ch-target)
    return t, in_block

def search(mapp:list[list], max_h:int, b:int, n:int, m:int): # 여기서 O(N)으로 끝내야함
    t = 1e9
    h = -1
    for ch in range(max_h+1):
        need, in_block = need_block(mapp, ch, n, m)
        if need > b + in_block:
            continue
        cur_time = 2*in_block + need
        if cur_time <= t:
            t = cur_time
            h = ch
    return t, h

def main(n:int, m:int, b:int, mapp:list[list]):
    max_h = 0
    for row in mapp:
        max_h = max(max_h, max(row))
    t, target = search(mapp, max_h, b, n, m)
    return t, target 

print(*main(*input_data()))