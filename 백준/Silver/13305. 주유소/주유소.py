'''
Approach
    - N개의 도시
    - 제일 왼쪽에서 제일 오른쪽으로 이동
    - 기름값의 최소비용은?
    
>>> 그리디
    : 첫번째에서 다음 도시까지 충전함
      이후, 다음도시가 더 싸다면 그다음 도시까지 충전,
           그렇지않다면 그다음 도시까지 이전 가격으로 충전
'''
from sys import stdin
input = stdin.readline

n=int(input())
dist = list(map(int,input().split()))
cost = list(map(int,input().split()))

answer = cost[0]*dist[0]
curCost = cost[0]
for i in range(1,n-1):
    nextCost = cost[i]
    if nextCost < curCost:
        curCost = nextCost
    answer += curCost * dist[i]
print(answer)