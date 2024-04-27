'''
빵-야채-고기-빵
상수가 포장하는 햄버거 개수는?
stack
'''
def solution(ingredient):
    answer = 0
    stack = []
    for i in ingredient:
        stack.append(i)
        if stack[-4:] == [1,2,3,1]:
            [stack.pop() for _ in range(4)]
            answer +=1
    return answer