'''
Condition
    - TL: 1s
    - <x':y'>
        - x < M: x'=x+1
        - x >= M: x'=1
        - y < N: y'=y+1
        - y >= N: y'=1 

Goals
    - <x:y>가 몇 번째 해를 나타내는지 출력
    - <x:y>가 유효하지 않다면 -1을 출력

Approach
    - <x:y>
        - x = k - a*m, k = x+ a*m, k-x = a*m, (k-x)%m = 0 
        - y = k - b*n, k = y+ b*n, k-y = b*n, (k-y)%n = 0
        - 따라서 k = x(혹은 y) or x+a*m(혹은 y+a*n) 을 만족하면 된다
        - k를 x,y중 하나로 선택하자(m,n을 비교 후 더 큰쪽을 선택)

m=10, n=12
<10:10>, <1:11>, <2:12>, <3:1>, <10:8>, <10:6>
10      11      12      13      20      30
'''
def calc(m:int, n:int, x:int, y:int):
    if m > n: 
        k = x
    else: 
        k = y
    add = max(m,n)
    while k <= m*n: #최대 크기가 m과n의 곱
        if not (k-x)%m and not (k-y)%n:
            return k
        k += add
    return -1

for _ in range(int(input())):
    m,n,x,y = map(int,input().split())
    print(calc(m,n,x,y))