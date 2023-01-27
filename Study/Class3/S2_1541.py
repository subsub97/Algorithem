# 1541 Greedy try1
# 연산자가 +,- 두개 있는지 확인한다. 한개라면 그냥 진행
# 두개인 경우 - 값 뒤에 괄호를 이용하여 가장 큰 양수가 나오는 조합으로 만들자
# 1. 음수가 나오면 항상 그 뒤에있는 모든 덧셈까지는 모아서 괄호에 묶자
# 수식을 받자
op_line = input()
sum_val = 0
min_val = 0
flag = 0
# 두개의 연산자가 모두 존재하는 경우
if '+'  in (op_line):
    if '-' in(op_line):
        split_Mnum = op_line.split('-') # - 를 기준으로 나누자
        for num in split_Mnum:
            if '+' in num:
               split_Pnum = num.split('+')
               for val in split_Pnum:
                   sum_val += int(val)
               sum_val = -sum_val
            else:
                sum_val = int(num)
            min_val += sum_val
            sum_val = 0
    else: # + 연산자로만 이루어진 수식
        split_Pnum = op_line.split('+')
        for num in split_Pnum:
            sum_val += int(num)
        min_val += sum_val
else: # -로 만 연산자가 이루어진 경우
    split_Mnum = op_line.split('-')
    for num in split_Mnum:
        if num == '':
            flag = 1
            continue
        if flag == 1:
            sum_val -= int(num)
        if flag == 0:
            flag = 1
            sum_val += int(num)
    min_val = sum_val

print(min_val)

'''
20 - 30 + 20  - 190 +200 +100 -12
-20 - 30 - 40 - 50 이라면 ? 20 ,30 ,40 ,50 일텐데? 
'''
