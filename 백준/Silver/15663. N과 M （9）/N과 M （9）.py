'''
Condition
    - TL : 1s
    - 수열 길이 = M
    - N개 자연수에서 M개를 고른 수열

Goals
    - 조건을 만족하는 수열을 출력
    - 중복되면 안됨
    - 사전 순으로 증가하도록 출력

Approach
    - 백트래킹 해보자

'''
from heapq import *

def dfs(length:int, sq:list, n:int, m:int):
    # 종료 조건
    if length == m:
        if not(tuple(sq) in sequence_visit):
            heappush(sequence, tuple(sq))
            sequence_visit.add(tuple(sq))
        return
    # 백트래킹
    for i in range(n):
        num = nums[i]
        if visited[i]:
            continue
        visited[i] = True
        sq.append(num)
        dfs(length+1, sq, n, m)
        visited[i] = False
        sq.pop()

def main():
    global sequence, sequence_visit, nums, visited
    n,m=map(int,input().split())
    nums = list(map(int,input().split()))
    sequence = []
    sequence_visit = set()
    visited = [False]*n
    dfs(0, [], n, m)
    for _ in range(len(sequence)):
        print(*heappop(sequence))
        

main()