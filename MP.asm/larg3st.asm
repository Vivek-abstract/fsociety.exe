data segment
A DB 5 DUP(?)
msg DB "Enter 5 elements: $"
msg1 DB 0ah, "The largest number is $"
data ends

code segment
assume CS: code, DS: data
start:
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

inputLoop:
        ; Input values into array
        MOV AH, 01h
        INT 21h
        MOV A[BX], AL

        MOV DL, 0ah
        MOV AH, 02h
        INT 21h

        INC BX
        LOOP inputLoop

        MOV CX, 5

        ; Assume BL is max. Initialize max to A[0]
        MOV BL, A[0]
        LEA SI, A

compare:
        CMP BL, [SI]
        JG continue

setMax:
        ; max < a[i] so set max = a[i]
        MOV BL, [SI]

continue:
        ; max > a[i] so continue
        INC SI
        LOOP compare

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
