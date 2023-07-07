def solution(numbers):
    # numbers의 원소들이 모두 0이라면 0을 return
    if set(numbers) == {0}:
        return '0'
    
    # numbers의 원소들이 최대 1000이므로, 각 원소*4를 [:4]로 슬라이싱 한 후 비교하면 된다!
    str_nums = list(map(str,numbers))
    str_nums.sort(key=lambda x:(x*4)[:4], reverse=True)
    answer = ''
    for i in str_nums:
        answer += i
    return answer