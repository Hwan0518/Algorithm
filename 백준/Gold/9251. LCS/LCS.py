'''
Condition
    - TL: 2s
    - 

Goals
    - 

Approach
    - LCS
    - dp로 해야함
        - dp[i][j]: 첫 번째 문자열의 i번째와, 두 번째 문자열의 j번째에서 lcs의 최댓값
            - str1[i] == str2[i]인 경우
                dp[i][j]    = (첫 번째 문자열의 i-1번째와, 두 번째 문자열의 j번째에서 lcs의 최댓값) +1
                            = dp[i-1][j-1] +1
            - str1[i] != str2[i]인 경우
                dp[i][j]    = max(
                                (첫 번째 문자열의 i-1번째와, 두 번째 문자열의 j번째에서 lcs의 최댓값),
                                (첫 번째 문자열의 i번째와, 두 번째 문자열의 j-1번째에서 lcs의 최댓값)
                                )
                            = max(dp[i-1][j], dp[i][j-1])
'''
str1 = ' '+input().strip()
str2 = ' '+input().strip()
n1 = len(str1)
n2 = len(str2)

dp = [[0]*(n2) for _ in range(n1)]
for i in range(1, n1):
    for j in range(1, n2):
        if str1[i] == str2[j]:        
            dp[i][j] = dp[i-1][j-1] + 1
        else:
            dp[i][j] = max(dp[i-1][j], dp[i][j-1])

print(dp[-1][-1])