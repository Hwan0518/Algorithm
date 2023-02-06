import sys

N=int(input())
positive_counting_array = []
nagative_counting_array = []
counting_array=[]
sorting_array = []



for _ in range(N):
    i = int(sys.stdin.readline()) #수의 범위가 -4000부터 4000

    # 들어온 i를 counting정렬
    if i >= 0:
        while len(positive_counting_array) <= i:
            positive_counting_array.append(0)
        positive_counting_array[i] += 1
    
    else:
        while len(nagative_counting_array) < abs(i):
            nagative_counting_array.insert(0,0)
        nagative_counting_array[i] += 1

i_number = -len(nagative_counting_array)
counting_array = nagative_counting_array + positive_counting_array
for k in counting_array:
    while k != 0:
        sorting_array.append(i_number)
        k -= 1
    i_number += 1 



#산술평균 계산,출력 : N개의 수들의 합을 N으로 나눈 값, 소수점 이하 첫째 자리에서 반올림한 값을 출력
sum_num = sum(sorting_array)
print(round(sum_num/N))



#중앙값 계산,출력 : N개의 수들을 증가하는 순서로 나열, 그 중앙에 위치하는 값
middle_num = sorting_array[len(sorting_array) // 2]
print(middle_num)

#최빈값 계산,출력 : N개의 수들중 가장 많이 나타난 값, 여러 개 있으면 두번째로 작은 값 출력
max_count = max(counting_array)
max_count_lst = []
j_number = -len(nagative_counting_array)
for j in counting_array:
    if j == max_count:
        max_count_lst.append(j_number)
    j_number += 1

if len(max_count_lst) == 1:
    print(max_count_lst[0])

else:
    print(max_count_lst[1])

#범위 계산,출력
num_range = max(sorting_array) - min(sorting_array)
print(num_range)