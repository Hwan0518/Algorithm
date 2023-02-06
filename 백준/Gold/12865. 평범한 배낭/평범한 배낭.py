'''
knapsack algorithm

*중요한건 dp는 항상 최대 value로 유지되고 있다는 것이다
'''

from sys import stdin
input = stdin.readline

#input data
n,k = map(int,input().split())
stuff = [[0,0]] + [list(map(int,input().split())) for _ in range(n)]
knapsack = [[0 for _ in range(k+1)] for _ in range(n+1)]

#find max value
for i in range(1,n+1): # index=1번째 물건을 비교하기 위해서는 0번째 물건부터 봐야하므로 index는 1부터 시작
    for limit in range(1, k+1):
        w,v = stuff[i][0], stuff[i][1]

        # weight가 limit보다 크다면 담지 못함 = 이전 값을 유지 = i번째 물건을 제외하고 담은 최댓값
        if w > limit:
            knapsack[i][limit] = knapsack[i-1][limit]
        # 담을 수 있다면, 
        # (i번째 물건의 value + 담고나서 남은 limit에서, i번째 값을 빼고 담은 최댓값) vs (i번째를 담지 않았을 때의 최댓값) 중 큰값으로 갱신을 한다
        else:
            knapsack[i][limit] = max(v+knapsack[i-1][limit-w], knapsack[i-1][limit])

print(knapsack[n][k])