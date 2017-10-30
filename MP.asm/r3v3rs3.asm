data segment
msg DB "Enter a string: $"
msg2 DB "Reverse: $"
data ends

code segment
assume CS: code, DS: data
start:
        MOV AX, data
        MOV DS, AX
        MOV DX, offset msg
        MOV AH, 09h
        INT 21h

        ; Initialize SI
        MOV SI, 2000h
        MOV CL, 0h
up:
        ; Input string and store in SI
        MOV AH, 01h
        INT 21h
        MOV [SI], AL
        INC CL
        INC SI
        CMP AL, 0dh
        JNZ up

        ; Ignore the 0dh(enter)
        SUB SI, 02h
        DEC CL

        MOV DX, offset msg2
        MOV AH, 09h
        INT 21h

up2:
        ; Keep printing char by char in reverse
        MOV DL, [SI]
        MOV AH, 02h
        INT 21h
        DEC SI
        DEC CL
        JNZ up2

        MOV AH, 4ch
        INT 21h

code ends
end start
