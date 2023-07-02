'''
- 모든 명함을 수납할 수 있는 가장 작은 지갑의 크기를 정함
- 1<= sizes <= 1만
- 1<= w,h <= 1000

>>> 명함을 돌릴 수 있으므로, w나 h 한쪽에 큰 수들을 몰아넣고 계산하면 됨
'''
def solution(sizes):
    w_list = []
    h_list = []
    
    # w에 더 큰 수를 몰아넣어줌
    for data in sizes:
        w,h = data
        w_list.append(max(w,h))
        h_list.append(min(w,h))
    
    # w와 h에서 가장 큰 수들을 곱한 값이 지갑의 크기가 됨
    answer = max(w_list) * max(h_list)
    
    return answer