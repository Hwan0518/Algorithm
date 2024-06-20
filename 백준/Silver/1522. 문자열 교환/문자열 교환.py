'''
슬라이딩 윈도우

- 윈도우 안에서 교환해야할 b의 개수 = 교환 개수가 된다
'''
s = input()
# 모든 a가 연속이 되는지 확인 = a의 개수만큼 윈도우 크기
a_cnt = s.count('a')
# 원형 구조를 위해 두 배로 만들어줌
ss = 2*s
# 윈도우 움직이면서 확인
min_cnt = 1e9
for i in range(len(s)+1): # 한 번만 더 확인하면 됨
    window = ss[i:i+a_cnt]
    min_cnt = min(min_cnt, window.count('b'))
# 정답 출력
print(min_cnt)