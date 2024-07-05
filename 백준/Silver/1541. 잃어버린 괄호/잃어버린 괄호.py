inputs = list(input().split("-"))
num_list = []
for exp in inputs:
    nums = map(int,exp.split("+"))
    num_list.append(sum(nums))
result = num_list[0]
for i in range(1, len(num_list)):
    result -= num_list[i]
print(result)