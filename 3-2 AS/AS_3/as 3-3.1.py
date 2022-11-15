'''
3-1) 두 문자열(길이는 1 이상 2,000 이하 정수)의 가장 긴 공통의 부분문자열의 길이를 구하는 프로그램을 작성하시오.
'''
first_word = input()
second_word = input()

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
                if arr_2d[i][j] >= max_length:
                    max_length = arr_2d[i][j]

    return max_length

print(find_same_char_amount(first_word,second_word))

'''
완전 다른 단어를 입력받은경우 
max_length를 1로 했을때 잡아낼수없었다.
'''