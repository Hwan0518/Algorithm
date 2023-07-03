answer = 0
def solution(numbers, target):
    visited = set()
    def dfs(cnt, result, calc):
        global answer
        if cnt == len(numbers) and tuple(calc) not in visited:
            if result == target:
                answer +=1
                visited.add(tuple(calc))
            return
        
        # 덧셈과 뺼셈을 차례대로
        num = numbers[cnt]
        dfs(cnt+1, result+num, calc+[num])
        dfs(cnt+1, result-num, calc+[-num])
    
    dfs(0, 0, [])
    return answer

