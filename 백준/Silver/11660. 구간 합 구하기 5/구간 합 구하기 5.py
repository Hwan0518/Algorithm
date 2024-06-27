'''
Condition
    - TL : 1s

Goals
    - (x1,y1)부터 (x2,y2)까지 합을 구해서 출력하라

Approach
    - 단순 브루트포스: O(N^2 * M) = TLE
    - 구간합을 사용하자
        - (x1,y1 ~ x2,y2) 구간합 = (x2,y2 구간합) - (x1,y1) 구간합
'''
from sys import stdin
input = stdin.readline

def input_data():
    n,m = map(int,input().split())
    mapp = [list(map(int,input().split())) for _ in range(n)]
    target_list = [list(map(int,input().split())) for _ in range(m)]
    return n,m,mapp,target_list

def main(n:int, m:int, mapp:list[list], target_list:list[list]):
    # 구간합 생성
    prefix_sum = [[0]*(n+1)] + [[0] for _ in range(n)]
    # 구간합 계산
    for i in range(1, n+1):
        for j in range(1, n+1):
            prefix_sum[i].append(prefix_sum[i][j-1] + prefix_sum[i-1][j] - prefix_sum[i-1][j-1] + mapp[i-1][j-1])
    # 목표 계산
    for x1,y1,x2,y2 in target_list:
        print(prefix_sum[x2][y2] - prefix_sum[x1-1][y2] - prefix_sum[x2][y1-1] + prefix_sum[x1-1][y1-1])
    
main(*input_data())