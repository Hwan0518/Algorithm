'''
Approach
    - 1~n까지의 사람이 원형으로 앉아있을 떄, k번째 사람을 계속해서 빼냈을때의 수열을 반환해라
    
>>> 큐
    : 큐를 loate시키면서 k번째에 맨앞의 수를 제거한다

'''
from collections import deque
def inputData():
    n,k = map(int,input().split())
    return n,k

def solution(n:int, k:int):
    arr = []
    dq = deque([i for i in range(1,n+1)])
    
    i = 1
    while dq:
        if i%k == 0:
            arr.append(dq.popleft())
        else:
            dq.rotate(-1)
        i +=1
        
    answer = '<'
    for num in arr:
        answer += str(num)+', '
    answer = answer[:len(answer)-2]+'>'
    return answer

print(solution(*inputData()))