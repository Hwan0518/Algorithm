'''
Condition
    - TL : 2s(4000만)
      ML :
    - 1<=n<=1000, 1<=a[i]<=10억

Question
    - 수열의 모든 수가 같아지는 add연산의 최소횟수는?

Access
    - 예상 분류 :
      실제 분류 : 그리디, 스택

    try_1)
        - 제일 작은 수부터 +1씩 add 시켜야한다
        >>> 시간초과
        
    try_2)
        - 같은 수의 그룹들을, 차이가 나는 만큼 한번에 더해준다. 이때 양쪽으로 만난 값중 더 작은 값과의 차이로 계산해야한다
        
    try_3)
        - 스택을 이용해서, 수가 커지기 시작하면 max-min을 해준다
        
    try_4)
        - 현재값과 이전값의 차이를 이용한다
'''
import sys
input = sys.stdin.readline


#define function
def solution():
    result = 0
    temp = []
    curMax = 0
    # 앞쪽부터 차례대로 탐색
    for cur in nums:
        curMax = max(curMax, cur)
        # 이전과 같다면 continue
        if temp and cur == temp[-1]:
            continue
        # 이전 수보다 커졌다면, 현재값과 이전값을 빼줌
        elif temp and cur > temp[-1]:
            result += cur - temp.pop()
            temp.append(cur)
        # 이전보다 작다면, 이전 수를 빼고 현재 수를 넣어줌
        else:
            if temp:
                temp.pop()
            temp.append(cur)
    # 만약 temp가 비워지지 않고 남았다면 가장 큰값과 가장 작은값의 차이를 더해줌
    result += curMax - max(temp)
    return result
    
    

#main
n = int(input())
nums = [int(input()) for _ in range(n)]
print(solution())

