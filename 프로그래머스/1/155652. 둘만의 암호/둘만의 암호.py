from string import ascii_lowercase

def solution(s, skip, index):
    alphabet = set(ascii_lowercase)
    a_skip = sorted(alphabet - set(skip))
    answer = ''
    for w in s:
        idx = a_skip.index(w)
        new_idx = (idx+index) % len(a_skip)
        print(idx, new_idx)
        answer += a_skip[new_idx]
    
    return answer