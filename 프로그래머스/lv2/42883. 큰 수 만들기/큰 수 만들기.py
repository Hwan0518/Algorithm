def solution(number, k):
    stack = []
    for cur in number:
        while k>0 and stack:
            if cur <= stack[-1]:
                break
            stack.pop()
            k -=1
        stack.append(cur)
    stack = stack[:-k] if k else stack
    answer = ''.join(stack)
    return answer