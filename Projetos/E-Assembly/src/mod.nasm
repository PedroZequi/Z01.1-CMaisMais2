; ------------------------------------------------------------
; Arquivo: Mod.nasm
; Curso: Elementos de Sistemas
; Criado por: Luciano Soares
; Data: 27/03/2017
;
; Calcula o resto da divisão (modulus) entre RAM[0] por RAM[1]
; e armazena o resultado na RAM[2].
;
; 4  % 3 = 1
; 10 % 7 = 3
; ------------------------------------------------------------

leaw $1, %A
movw (%A), %D

while:

leaw $5, %A
movw %D, (%A)

leaw $1, %A
movw (%A), %D

leaw $0, %A
subw (%A), %D, %D

leaw $0, %A
movw %D, (%A)

leaw $equal, %A
je %D
nop

leaw $end, %A
jl %D
nop

leaw $while, %A
jmp
nop

end:
leaw $5, %A
movw (%A), %D
leaw $2, %A
movw %D, (%A)

equal:
leaw $2, %A
movw %D, (%A)