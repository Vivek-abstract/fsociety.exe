data segment
A DB 5 DUP(?)
msg DB "Enter 5 elements: $"
msg1 DB 0ah, "The array is $"
data ends

code segment
assume CS: code, DS: data
start:
        ; Program to input, store and output single digit numbers in array
        MOV AX, data
        MOV DS, AX

        MOV DX, offset msg
        MOV AH, 09h
        INT 21h

        MOV CX, 5
        MOV BX, 0

        ; Print newline
        MOV DL, 0ah
        MOV AH, 02h
        INT 21h

l2:
        ; Input values into array
        MOV AH, 01h
        INT 21h
        MOV A[BX], AL

        ; Print newline after each digit
        MOV DL, 0ah
        MOV AH, 02h
        INT 21h

        INC BX
        LOOP l2

        MOV CX, 5
        LEA SI, A

l1:
        MOV DL, [SI]
        MOV AH, 02h
        INT 21h

        MOV DL, ' '
        MOV AH, 02h
        INT 21h

        INC SI
        LOOP l1

        ; LOOP Instruction decrements CX and loops if not 0

        MOV AH, 4ch
        INT 21h
code ends
end start
