'''
그냥 seq를 1씩 증가시키며 해당하는 값에 알맞게 나왔는지 확인하면 될듯
제대로 나온거라면 seq +=1
'''
def solution(skill, skill_trees):
    answer = 0
    sk = set(tuple(skill))
    for st in skill_trees:
        flag = True
        seq = 0
        for s in st:
            if s not in sk:
                continue
            if s == skill[seq]:
                seq +=1
            else:
                flag = False
                break
        if flag:
            answer +=1
    return answer