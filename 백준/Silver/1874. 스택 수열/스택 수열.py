'''
Approach
    - push는 오름차순으로
    - 수열을 만들수 있는지 없는지를 출력

>>> 스택
'''
from sys import stdin
input = stdin.readline
answer = []
n = int(input())

target = [int(input()) for _ in range(n)]
stack = []


i = 0
num = 1
while num<=n:
    stack.append(num)
    answer.append('+')
    while stack and stack[-1] == target[i]:
        stack.pop()
        answer.append('-')
        i+=1
    num+=1

if i==n:
    for ans in answer:
        print(ans)
else:
    print("NO")