'''
conditions
    - TL: 0.4s
    - 0<=n<=2**63
    
goals
    - q**2 >= n을 구하라

approach
    - 이분탐색
        - o(63)만에 가능
'''
def binary_search(n):
    l = 0
    r = n
    q = n
    while l<=r:
        m = (l+r)//2
        # 조건을 만족 -> m을 줄이는 방향으로 나아간다
        if m**2 >= n:
            if m<q:
                q = m
            r = m-1
        # 조건 불충족 -> m이 커져야한다
        elif m**2 < n:
            l = m+1
    print(q)

binary_search(int(input()))