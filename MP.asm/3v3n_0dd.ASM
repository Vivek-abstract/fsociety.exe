data segment
msg DB "Enter a number: $"
msg2 DB 0ah, "The number is even$"
msg3 DB 0ah, "The number is odd$"
data ends

code segment
assume DS: data, CS: code
start:
        MOV AX, DATA
        MOV DS, AX
        MOV DX, offset msg
        MOV AH, 09h
        INT 21h

inputLoop:
        ; Input number
        MOV AH, 01h
        INT 21h
        MOV [SI], AL
        INC SI
        CMP AL, 0dh
        JNZ inputLoop

        SUB SI, 02h
        MOV AL, [SI]

        ; Rotate right, check if carry = 1 then odd else even
        ROR AL, 01h
        JNC evenMessage

oddMessage:
        ; Print odd
        MOV DX, offset msg3
        MOV AH, 09h
        INT 21h
        JMP last

evenMessage:
        ; Print even
        MOV DX, offset msg2
        MOV AH, 09h
        INT 21h

last:
        MOV AH, 4ch
        INT 21h

code ends
end start
