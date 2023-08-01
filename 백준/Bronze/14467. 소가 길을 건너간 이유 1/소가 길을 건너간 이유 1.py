'''
Approach
    - n<=100
    - 소의 위치 : 0은 왼쪽, 1은 오른쪽
    - 소는 10마리

>>> dict를 사용해보자
'''
from sys import stdin
input = stdin.readline

n=int(input())
dct = {}
cnt = 0
for _ in range(n):
    num, posit = map(int,input().split())
    if num in dct and dct[num] != posit:
        cnt +=1
        dct[num] = posit
    else:
        dct[num] = posit 
        
print(cnt)