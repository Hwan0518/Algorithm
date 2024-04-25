def solution(players, callings):
    rank = {}
    names = {}
    n = len(players)
    for i in range(n):
        name = players[i]
        rank[i] = name
        names[name] = i
    for fst_name in callings:
        fst = names[fst_name]   # 순위
        sec = fst-1             # 순위
        sec_name = rank[sec]    # 이름
        # 순서 교체
        rank[sec], rank[fst] = rank[fst], rank[sec]
        names[sec_name], names[fst_name] = names[fst_name], names[sec_name] 
    answer = list(rank.values())
    return answer