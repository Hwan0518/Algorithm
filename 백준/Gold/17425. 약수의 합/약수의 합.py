'''
Condition
    - TL : 1s(2000만)
      ML : 512mb(128*100만)
    - 1<=t<=10만, 1<=n<=100만

Question
    - f(A) = A의 모든 약수를 더한 값
      g(x) = x이하의 모든 자연수 y에 대해, f(y)값을 더한 값
      >>> g(N)을 구하여라

Access
    - 예상 분류 : 수학, dp
      실제 분류 : 수학, 누적 합, 에라토스테네스의 체

    try_1)
        - 완탐 실패
    
    try_2)
        - 클론코딩 : https://enhjh.tistory.com/38
'''
from sys import stdin
input = stdin.readline

#define function
def solution():
    # MAX보다 작은 i의 배수에 i를 더해줌
    for i in range(2,MAX+1):
        for j in range(1,MAX+1):
            if i*j > MAX:
                break
            func_F[i*j] += i
    
    for i in range(1, MAX+1):
        func_G[i] = func_G[i-1] + func_F[i]
    return


#main
MAX = 1_000_000
func_F = [1] * (MAX+1)
func_G = [0] * (MAX+1)
solution()

t = int(input())
for _ in range(t):
    n = int(input())
    print(func_G[n])