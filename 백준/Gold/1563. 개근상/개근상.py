'''
Condition
    - TL : 2s(4000만)
      ML : 128(32*10만)
    - 1<=N<=1000
    - O는 출석, L은 지각, A는 결석
    - 개근상 못받음 : L>=2 or 3연속A

Question
    - 개근상을 받을 수 있는 경우의 수는?

Access
    try_1)
        - 전체 경우의 수 - 못받는 경우의 수로 계산해본다
        - 전체 경우의 수 = 3^n
          못받는 경우의 수
                        - L>=2인경우 : 
                            n개의 자리에서, L이 들어갈 2자리를 뽑고 나머지에 아무거나
                            >>> n_C_2 * 3^(n-2)
                        - 3연속 이상 A인경우: 
                            n개 자리에서 AAA를 하나로 보면 총 n-2개의 자리 됨
                            AAA가 들어갈 자리를 하나 고르고, 나머지는 아무거나 대입
                            >>> n-2_C_1 * 3^(n-3)
        >>> 중복되는 경우의 수가 존재해서 fail
    
    try_2)
        - L>=2인경우와 A>=3인경우
            L과 A를 1개씩 늘려가며 경우의 수를 구한다. 이때 L과 A의 개수는 고정해놓는다
            L : n_C_i * (2^n-i)
            A : (n-i+1)_C_1 * (2^n-i)
        >>> fail..

    try_3)
        - clone coding : https://velog.io/@ckstn0778/%EB%B0%B1%EC%A4%80-1563%EB%B2%88-%EA%B0%9C%EA%B7%BC%EC%83%81-X-1-Python
            dp[day][late][absent] = day에서 지각과 결석이 late와 absent일 때, 개근상을 받을 수 있는 경우의 수
'''

import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

#define function
def case(day, late, absent):
    if late==2 or absent ==3: # 이 조건을 만족하면 개근상을 받지 못하므로 더 이상 진행할 필요가 없다
        return 0
    if day == n: # 이 조건을 만족하려면, late가 2보다 작고 absent가 3보다 작은 경우이다
        return 1
    # dp가 비어있다면
    if dp[day][late][absent] == -1:
        # 경우의 수 = (지각X, 결석X인 경우) + (지각O,결석X인 경우) + (지각X,결석O인 경우)
        attend = (case(day+1, late, 0) + case(day+1, late+1, 0) + case(day+1, late, absent+1))
        dp[day][late][absent] = attend
        return attend
    else:
        return  dp[day][late][absent]
    
#main
n=int(input())
dp = [[[-1 for absent in range(3)] for late in range(2)] for day in range(n)]
print(case(0,0,0)%1_000_000)