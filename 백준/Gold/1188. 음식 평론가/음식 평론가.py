'''
Condition
    - TL : 1s (2000만)
      ML : 128 (32*100만)
    - 1<=N,M<=100

Question
    - 모든 평론가에게 동일한 양을 주기 위해 필요한 칼질 횟수의 최솟값은?


Access
    - 예상 분류 : 수학
      실제 분류 : 수학, 정수론, 유클리드 호제법

    try_1)
        - n%m을 다시 m개로 나눌때 필요한 칼질 수를 구한다
        >>> fail
        
    try_2)
        - m-gcd(n,m)이 정답이다
        
'''
from math import gcd

n,m=map(int,input().split())

print(m-gcd(n,m))

"""
2 6 > k= 0.333 , result=4 ,gcd = 2
3 4 > k= 0.75  , result=3 ,gcd = 1
3 5 > k= 0.6   , result=4 ,gcd = 1

6 2 > k= 3     , result=0 ,gcd = 2
7 2 > k= 3.5   , result=1 ,gcd = 1


m-gcd가 정답이다

"""
