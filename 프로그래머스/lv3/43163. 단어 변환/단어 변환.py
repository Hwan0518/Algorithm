'''
Approach
    - begin을 target으로 알파벳 하나씩만 바꿔서 변환할때, 가장 짧은 과정을 찾음
    - 한번에 한개의 알파벳만 바꿀 수 있고, 바꾼 단어는 words에 있어야함

>>> n이 작으니까 백트래킹을 사용해보자
    

'''
import sys
sys.setrecursionlimit(10**6)
def solution(begin, target, words):
    answer = 0
    
    def dfs(cnt, cur, visited):
        nonlocal target, words, answer
        # cur이 target과 같아지면 cnt와 answer를 비교
        if cur == target:
            answer = min(answer, cnt)
            return
        # 가능한 후보를 고름
        candidate = []
        for w in words:
            if w in visited:
                continue
            diff = 0
            for i in range(len(cur)):
                if cur[i] != w[i]:
                    diff +=1
            if diff == 1:
                candidate.append(w)     
        print(candidate)
        # 변경할 수 있는 후보가 없다면 return
        if not candidate:
            return
        # 후보중 하나를 골라서 계속 dfs
        for c in candidate:
            visited.add(c)
            dfs(cnt+1, c, visited)
            visited.remove(c)
            
    # target이 words안에 있다면 dfs 진행
    if target in words:
        answer = 1e9
        visited = set()
        dfs(0, begin, visited)
    return answer