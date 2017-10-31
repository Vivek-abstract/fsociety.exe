data segment
msg DB "Enter string: $"
msg1 DB "The string is a palindrome$"
msg2 DB "The string is not a palindrome$"
data ends

code segment
assume CS: code, DS: data
start:
        MOV AX, data
        MOV DS, AX
        MOV DX, offset msg
        MOV AH, 09h
        INT 21h

        MOV SI, 2000h
        MOV DI, 3000h
        MOV CL, 00h

up:
        ; Input the string
        MOV AH, 01h
        INT 21h
        MOV [SI], AL
        MOV [DI], AL
        INC SI
        INC DI
        INC CL
        CMP AL, 0dh
        JNZ up

        ; Move SI to start of string and DI to end of string, Ignore 0dh in CL
        MOV SI, 2000h
        SUB DI, 02h
        DEC CL

up1:
        ; Compare char by char
        MOV AL, [SI]
        CMP AL, [DI]
        JNZ down
        INC SI
        DEC DI
        DEC CL
        JNZ up1

        ; String is palindrome
        MOV DX, offset msg1
        MOV AH, 09h
        INT 21h
        JMP fin

down:
        ; String is not a palindrome
        MOV DX, offset msg2
        MOV AH, 09h
        INT 21h

fin:
        MOV AH, 4ch
        INT 21h

code ends
end start
