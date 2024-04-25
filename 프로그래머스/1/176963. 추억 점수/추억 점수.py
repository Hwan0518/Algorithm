def solution(name, yearning, photo):
    score = {}
    n = len(name)
    for i in range(n):
        score[name[i]] = yearning[i]
    
    answer = []
    for p in photo:
        p_score = 0
        for name in p:
            if name in score:
                p_score += score[name]
        answer.append(p_score)
    return answer