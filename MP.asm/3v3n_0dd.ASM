data segment
msg db "Enter a number: $"
msg2 db 0ah, "The number is even$"
msg3 db 0ah, "The number is odd$"
data ends

code segment
assume ds:data, cs:code
start:
        MOV AX, DATA
        MOV DS, AX
        MOV DX, offset msg
        MOV AH, 09h
        INT 21h

up:
        ; Input number
        MOV AH, 01h
        INT 21h
        MOV [SI], AL
        INC SI
        CMP AL, 0dh
        JNZ up

        SUB SI, 02h
        MOV AL, [SI]

        ;Rotate right if carry then odd else even
        ROR AL, 01h
        JNC eva

        ;Print odd
        MOV DX, offset msg3
        MOV AH, 09h
        INT 21h
        JMP last

eva:
        ;Print even
        MOV DX, offset msg2
        MOV AH, 09h
        INT 21h

last:
        MOV AH, 4ch
        INT 21h

code ends
end start
