'''
Condition
    - TL: 2s

Goals
    - 

Approach
    - dp
        - dp[i] = i번째를 선택했을 때 최대 개수
    - hint: https://alpyrithm.tistory.com/91
'''
n = int(input())
box = list(map(int,input().split()))
# 자기 자신만 선택한 경우 -> 1이므로 1로 초기화
dp = [1]*n
for i in range(1,n):
    for j in range(i):
        # i가 더 큰 경우 -> dp[i](j를 선택하지 않은 경우)와 dp[j]+1(j를 선택한 경우)를 비교해서 업데이트
        if box[i] > box[j]:
            dp[i] = max(dp[i], dp[j]+1)
print(max(dp))