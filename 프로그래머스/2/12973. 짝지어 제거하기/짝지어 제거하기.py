def solution(s):
    # 스택
    st = list(s)
    temp = []
    while st:
        ss = st.pop()
        if not temp:
            temp.append(ss)
            continue
        if temp:
            if ss == temp[-1]:
                temp.pop()
            else:
                temp.append(ss)
            
    if not st and not temp:
        return 1
    else:
        return 0