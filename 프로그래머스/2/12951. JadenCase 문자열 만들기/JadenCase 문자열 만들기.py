def solution(s):
    answer = ''
    flag = True # 단어의 첫 번째 문자인지 여부
    for ss in s:
        if ss == ' ':
            answer += ss
            flag = True
            continue
        # 첫 번째 문자일 때
        if flag:
            # 첫 번째 문자가 숫자
            if ss.isdigit():
                answer += ss
            # 첫 번째 문자가 문자
            else:
                answer += ss.upper()
            flag = False
        else:
            answer += ss.lower()
    return answer