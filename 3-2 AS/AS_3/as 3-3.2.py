'''
3-1) 두 문자열(길이는 1 이상 2,000 이하 정수)의 가장 긴 공통의 부분문자열을 구하는 프로그램을 작성하시오. 가장 긴 공통의 부분문자열이 여러개 일 경우, 이들 중 사전식으로 가장 앞에 나오는 문자열 하나를 출력한다.
'''
first_word = input()
second_word = input()
memo_arr = []

def find_same_char_amount(A,B):
    A_length = len(A) + 1
    B_length = len(B) + 1
    max_length = 0

    arr_2d = [
        [0 for _ in range(B_length)] # col
        for _ in range(A_length) # row
    ]

    for i in range(1,A_length):
        for j in range(1,B_length):
            if A[i-1] == B[j-1]:
                arr_2d[i][j] = arr_2d[i-1][j-1] + 1
                if arr_2d[i][j] > max_length:
                    memo_arr = []
                    max_length = arr_2d[i][j]
                    memo_arr.append(i)
                    # memo_arr.append(j)

                elif arr_2d[i][j] == max_length:
                     memo_arr.append(i)
                     # memo_arr.append(j)

    # 겹치는것이 없는경우
    if max_length < 1:
        return 0,0

    return max_length,memo_arr

max_length,memo_arr=find_same_char_amount(first_word,second_word)
# start_point = memo_arr[0] - max_length
if max_length > 1:
    print_arr = []
    for elem in memo_arr:
        start_point = elem - max_length
        for i in range(start_point,elem+1):
            print_arr.append(first_word[start_point:elem])
    if len(print_arr) > 1:
        print(min(print_arr))


