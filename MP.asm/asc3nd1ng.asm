data segment
A DB 5 DUP(?)
msg DB 0ah, 0dh, "Enter numbers: $"
msg2 DB 0ah, 0dh, "The numbers in ascending order are: $"
data ends

code segment
assume CS: code, DS: data
start:
        MOV AX, data
        MOV DS, AX
        MOV DX, offset msg
        MOV AH, 09h
        INT 21h

        MOV CX, 5
        MOV BX, 0

        MOV DL, 0ah
        MOV AH, 02h
        INT 21h

inputLoop:
        ; Input the numbers in the array
        MOV AH, 01h
        INT 21h
        MOV A[BX], AL

        MOV DL, 0ah
        MOV AH, 02h
        INT 21h

        INC BX
        LOOP inputLoop

        MOV CX, 5
        MOV SI, 0         ; Initialize i to 0

startOuter:
        MOV BX, SI        ; Initialize j to  i + 1
        INC BX
        CMP BX, 5         ; j < n?
        JE printMessage   ; Array is sorted

startInner:
        MOV AL, A[SI]
        CMP AL, A[BX]     ; a[i] > a[j]?
        JLE continue

swap:
        ; a[i] is greater than a[j] so swap
        MOV AL, A[SI]
        XCHG AL, A[BX]    ; Swap AL and A[BX]
        MOV A[SI], AL

continue:
        ; Continue
        INC BX            ; j++
        CMP BX, 5         ; j < n?
        JL startInner

endInner:
        INC SI            ; i++
        CMP SI, 5         ; i < n?
        JL startOuter

printMessage:
        MOV DX, offset msg2
        MOV AH, 09h
        INT 21h

        MOV DL, 0ah
        MOV AH, 02h
        INT 21h

        MOV CX, 5
        MOV BX, 0

displayLoop:
        ; Display the array
        MOV DL, A[BX]
        MOV AH, 02h
        INT 21h

        MOV DL, ' '
        MOV AH, 02h
        INT 21h

        INC BX
        LOOP displayLoop

        MOV AH, 4ch
        INT 21h
code ends
end start
