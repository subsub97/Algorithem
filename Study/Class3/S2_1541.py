# 1541 Greedy try1
# 연산자가 +,- 두개 있는지 확인한다. 한개라면 그냥 진행
# 두개인 경우 - 값 뒤에 괄호를 이용하여 가장 큰 양수가 나오는 조합으로 만들자
# 1. 음수가 나오면 항상 그 뒤에있는 모든 덧셈까지는 모아서 괄호에 묶자
# 수식을 받자
op_line = input()
min_val = 0
sum_val = 0
flag = 0
num = ''

for i in range(len(op_line)):
    cur_char = op_line[i]
    if cur_char == '-' or cur_char == '+':
        if flag == 0 and cur_char == '-':
            flag = 1
            if i > 0:
                min_val += int(num)
            num =''
            continue


        if flag == 0 and i > 0:
            min_val += int(num)

        else:
            if flag == 1:
                min_val -= int(num)
            else:
                min_val += int(num)
        num = ''
    else:
        num = num + cur_char

if flag == 1:
    min_val -= int(num)
else:
    min_val += int(num)
print(min_val)

