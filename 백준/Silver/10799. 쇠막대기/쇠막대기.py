'''
Approach
    - () : 레이저
    
>>> 스택
    : 막대기 하나에 몇 개의 레이저가 들어가있는지 확인한다
      막대기 하나에서 갈라지는 수 = 들어있는 레이저 +1
      

'''

from sys import stdin
input = stdin.readline
arr = input().strip()

answer = 0
stick = []
if arr[0] == "(" : stick.append(0)
for i in range(1,len(arr)):
    before = arr[i-1]
    cur = arr[i]
    
    if stick:
        if before == cur == "(":
            stick.append(0)
        elif before == "(" and cur == ")":
            stick.pop()
            for i in range(len(stick)):
                stick[i] += 1
        elif before == ")" and cur == "(":
            stick.append(0)
        else:
            answer += stick.pop()+1
    
    elif not stick and cur == "(":
        stick.append(0)
print(answer)