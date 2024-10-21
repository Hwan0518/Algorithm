'''
확신의 스택 문제
- prices를 들고있다가, 가격이 떨어지면 pop해버리자
- stack에 price를 차례대로 넣는다
- stack[-1]보다 price[i]가 작다면? -> answer[stack.pop()]=i-stack.pop()로 업데이트
'''
def solution(prices):
    n = len(prices)
    answer = [0] * n
    stack = []
    for i in range(n):
        cur = prices[i]
        # stack[-1]은 바로 직전 price의 index다
        while stack and prices[stack[-1]] > prices[i]:
            idx = stack.pop()
            answer[idx] = i - idx
        stack.append(i)
    while stack:
        idx = stack.pop()
        answer[idx] = n-1-idx
    
    return answer