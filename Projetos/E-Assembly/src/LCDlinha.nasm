leaw $19, %A
movw %A, %D
leaw $1, %A
movw %D, (%A)

leaw $16384, %A
movw %A, %D
leaw $0, %A
movw %D, (%A)

LOOP:
leaw $0, %A
movw (%A), %D
addw $1, %D, (%A)
movw %D, %A
movw $-1, (%A)
leaw $1, %A
movw (%A), %D
subw %D, $1, (%A)
leaw $LOOP, %A
jne %D
nop










































































































































































































































































































































































































































































































































































































































































































































































































































































