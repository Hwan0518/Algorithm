'''
Condition
    - TL : 2s
    - 팀 능력치
        - i,j가 같은 팀일 때 : S_ij + S_ji

Goals
    - 두 팀 능력치 차이의 최솟값은?

Approach
    - 백트래킹
        - 최대 20_C_10 = 184756 * 20! = TLE
        - for문에서 중복을 막기 위해 i를 선택했다면, 그 다음번부터 탐색해야함
'''
import sys
input = sys.stdin.readline

def input_data():
    n = int(input())
    return n, [list(map(int,input().split())) for _ in range(n)]


def calc_diff(ability:list[list], n:int):
    s1 = 0
    s2 = 0
    for i in range(n):
        for j in range(n):
            if i == j:
                continue
            if team[i] and team[j]:
                s1 += ability[i][j]
            elif not team[i] and not team[j]:
                s2 += ability[i][j]
    return abs(s1-s2)


def dfs(size:int, idx, ability:list[list], n:int):
    global min_diff
    # 종료 조건
    if size == n//2:
        min_diff = min(min_diff, calc_diff(ability, n))
        return
    # 백트래킹
    for i in range(idx, n):
        if team[i]:
            continue
        team[i] = True
        dfs(size +1, i+1, ability, n)
        team[i] = False


def main(n:int, ability:list[list]):
    global min_diff, team
    min_diff = 1e9
    team = [False]*n
    dfs(0, 0, ability, n)
    return min_diff


print(main(*input_data()))