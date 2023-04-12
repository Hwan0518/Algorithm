'''
Condition
    - TL : 1s(2000만)
      ML : 512(128*100만)
    - 2<=A,B<=2000

Question
    - 이름 궁합의 결과를 두 자리 숫자로 출력하여라

Access
    - 분류 : dp, 구현, 문자열

    try_1)
        -
'''
from string import ascii_uppercase

#define function
def solution():    
    # 알파벳을 대문자 획수로 전환 후 dp에 대입
    for i in range(size):
        idx_A = alph.index(A[i])
        idx_B = alph.index(B[i])
        dp[0].append(data[idx_A])
        dp[0].append(data[idx_B])

    # dp 시작    
    for i in range(2*size -2):
        for j in range(len(dp[i])-1):
            new = dp[i][j] + dp[i][j+1]
            dp[i+1].append(new%10)
    k = map(str,dp[-2])
    return ''.join(k)



#main
alph = ascii_uppercase
A = input().strip()
B = input().strip()
size = len(A)
data = [3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1]
dp = [[] for _ in range(2*size)]
print(f'{solution():02}')