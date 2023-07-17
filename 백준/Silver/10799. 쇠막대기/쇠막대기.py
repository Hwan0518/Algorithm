'''
Approach
    - () : 레이저
    - n<=10만
    
>>> 스택
    : 막대기 하나에 몇 개의 레이저가 들어가있는지 확인한다
      막대기 하나에서 갈라지는 수 = 들어있는 레이저 +1
      
>>> 참고 : https://www.acmicpc.net/source/63646948

'''

from sys import stdin
input = stdin.readline
arr = input().strip()

answer = 0
stick = []
for i in range(len(arr)):
    cur = arr[i]
    # (면 막대기에 넣어준다
    if cur == "(":
        stick.append("(")
    # )라면 이전걸 확인해서 처리한다
    else:
        before = arr[i-1]
        # 레이저이므로 스틱을 하나 빼고, 갯수만큼 더해줌
        if before == "(":
            stick.pop()
            answer += len(stick)
        # 스틱의 마지막 분리지점이므로 스틱을 하나 빼고, 1을 더해줌
        else:
            stick.pop()
            answer +=1
    
print(answer)