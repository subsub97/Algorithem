import sys
sys.setrecursionlimit(10**6)
find_number = sys.stdin.readline().strip()
wrong_cnt =  int(sys.stdin.readline())

pos_key_list = []

if wrong_cnt != 0:
    imp_key_list = list(map(int,sys.stdin.readline().split()))
else:
    imp_key_list = []

for num in range(10): # 사용가능한 키 리스트 정리
    if num not in imp_key_list:
        pos_key_list.append(num)

cur_num_list = []
min_press = abs(int(find_number) - 100)

def find_less_n(n): # 인자를 n-1로 받아와야한다
    if n >= 1 and wrong_cnt != 10:
        char_num =''
        for _ in range(n):
            char_num = char_num + str(pos_key_list[-1])
        int_num = int(char_num)
        return int(find_number) - int_num + n
    else:
        return 10000000000

def select_num(cur_num_list):
    global  min_press
    char_num = ''
    for num in cur_num_list:
        char_num = char_num + str(num)
    if min_press > abs(int(find_number) - int(char_num)) + len(find_number):
        min_press = abs(int(find_number) - int(char_num)) + len(find_number)
    return

def find_same_n(cnt,n):
    if cnt == n + 1:
        select_num(cur_num_list)
        return

    for num in pos_key_list:
        cur_num_list.append(num)
        find_same_n(cnt+1,n)
        cur_num_list.pop()
    return

def find_more_n(n):
    if wrong_cnt == 10:
        return 10000000
    char_num = ''
    if pos_key_list[0] == 0 and len(pos_key_list) > 1:
        for i in range(n):
            if i == 0:
                char_num = char_num + str(pos_key_list[1])
            else:
                char_num = char_num + str(pos_key_list[0])
        int_num = int(char_num)
    else:
        if len(pos_key_list) == 1:
            return 100
        else:
            for _ in range(n):
                char_num = char_num + str(pos_key_list[0])
            int_num = int(char_num)
    return int_num - int(find_number) + n



less_gap =find_less_n(len(find_number)- 1)
find_same_n(1,len(find_number))
more_gap = find_more_n(len(find_number)+1)
min_press = min((min_press,less_gap,more_gap))
print(min_press)


