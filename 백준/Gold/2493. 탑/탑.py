'''
Condition
    - TL: 1.5s
    - 

Goals
    - 각 탑에서 발사한 레이저가, 몇 번째 탑에서 수신되는지 출력
    - 수신이 되지 않는 레이저라면 0을 출력

Approach
    - 스택 사용해야함
    - 왼쪽부터 시작
'''
n = int(input())
tops = list(map(int,input().split()))
# edge case
if n == 1:
    print(0)
    exit()
elif n == 2:
    if tops[0] >= tops[1]:
        print(0, 1)
    else:
        print(0, 0)
    exit()
# main
result = [0]*n
q = [0]
for i in range(1, n):
    while q:
        j = q[-1]
        if tops[j] >= tops[i]:
            result[i] = j+1
            break
        else:
            q.pop()
    q.append(i)

print(*result)