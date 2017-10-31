data segment
A DB 5 DUP(?)
msg DB "Enter 5 elements: $"
msg1 DB 0ah, "The largest number is $"
data ends

code segment
assume CS: code, DS: data
start:
        ; Program to input, store and output single digit numbers in array
        MOV AX, data
        MOV DS, AX
        MOV SI, 2000h

        MOV DX, offset msg
        MOV AH, 09h
        INT 21h

        MOV CX, 5
        MOV BX, 0

        ; Print newline
        MOV DL, 0ah
        MOV AH, 02h
        INT 21h

l1:
        ; Input values into array
        MOV AH, 01h
        INT 21h
        MOV A[BX], AL

        MOV DL, 0ah
        MOV AH, 02h
        INT 21h

        INC BX
        LOOP l1

        MOV CX, 5

        ; Assume BL is max. Initialize max to A[0]
        MOV BL, A[0]
        LEA SI, A

l2:
        CMP BL, [SI]
        JL less
        JMP great

less:
        ; BL is less than SI so SI is max
        MOV BL, [SI]

great:
        INC SI
        LOOP l2

        ; Now we print BL
        MOV DX, offset msg1
        MOV AH, 09h
        INT 21h

        MOV DL, BL
        MOV AH, 02h
        INT 21h

        MOV AH, 4ch
        INT 21h
code ends
end start
