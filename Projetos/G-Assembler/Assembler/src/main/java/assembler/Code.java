/**
 * Curso: Elementos de Sistemas
 * Arquivo: Code.java
 */

package assembler;

/**
 * Traduz mnemônicos da linguagem assembly para códigos binários da arquitetura Z0.
 */
public class Code {

    /**
     * Retorna o código binário do(s) registrador(es) que vão receber o valor da instrução.
     * @param  mnemnonic vetor de mnemônicos "instrução" a ser analisada.
     * @return Opcode (String de 4 bits) com código em linguagem de máquina para a instrução.
     */
    public static String dest(String[] mnemnonic) {
        int size = mnemnonic.length;
        switch (mnemnonic[0]){
            case "movw":
                if (size==4) {
                    switch (mnemnonic[2]) {
                        case "%A":
                            switch (mnemnonic[3]) {
                                /*quando movw %X,%A,(%A)*/
                                case "(%A)":
                                    return "0101";
                                /*quando movw %X,%A,%D*/
                                default:
                                    return "0011";
                            }
                        case "%D":
                            switch (mnemnonic[3]) {
                                /*quando movw %X,%D,%A*/
                                case "%A":
                                    return "0011";
                                /*quando movw %X,%D,(%A)*/
                                default:
                                    return "0110";
                            }
                        default:
                            switch (mnemnonic[3]) {
                                /*quando movw %X,(%A),%A*/
                                case "%A":
                                    return "0101";
                                /*quando movw %X,(%A),%D*/
                                default:
                                    return "0110";
                            }
                    }
                }
                else{
                    switch(mnemnonic[2]){
                        /*quando movw %X,%A*/
                        case "%A":
                            return "0001";
                        /*quando movw %X,(%A)*/
                        case "(%A)":
                            return "0100";
                        /*quando movw %X,%D*/
                        default:
                            return "0010";
                    }
                }
            case "addw":
            case "subw":
            case "rsubw":
            case "andw":
            case "orw":
                switch(mnemnonic[3]){
                    /*quando addw %X,%X,%A*/
                    /*quando subw %X,%X,%A*/
                    /*quando rsubw %X,%X,%A*/
                    case "%A":
                        return "0001";
                    /*quando addw %X,%X,(%A)*/
                    /*quando subw %X,%X,(%A)*/
                    /*quando rsubw %X,%X,(%A)*/
                    case "(%A)":
                        return "0100";
                    /*quando addw %X,%X,%D*/
                    /*quando subw %X,%X,%D*/
                    /*quando rsubw %X,%X,%D*/
                    default:
                        return "0010";
                }
            case "incw":
            case "decw":
            case "negw":
            case "notw":
                switch(mnemnonic[1]){
                    case "%A":
                        return "0001";
                    case "(%A)":
                        return "0100";
                    default:
                        return "0010";
                }
            default:
                return "0000";
        }
    }

    /**
     * Retorna o código binário do mnemônico para realizar uma operação de cálculo.
     * @param  mnemnonic vetor de mnemônicos "instrução" a ser analisada.
     * @return Opcode (String de 7 bits) com código em linguagem de máquina para a instrução.
     */
    public static String comp(String[] mnemnonic) {
        /* TODO: implementar */
    	switch (mnemnonic[0]){
            case "movw" : switch (mnemnonic[1]) {
                case "%A":
                    return "000110000";
                case "%D":
                    return "000001100";
                case "(%A)":
                    return "001110000";
                case "$1":
                    return "000111111";
                case "$0":
                    return "000101010";
            }
            case "addw" :  switch (mnemnonic[1]) {
                case "%D": switch (mnemnonic[2]){
                    case "%A":return "000000010";
                    case "(%A)":return "001000010";
                    case "$1":return "00";
                }
                case "%A": switch (mnemnonic[2]){
                    case "%D":return "000000010";
                    case "$1":return "000110111";
                }
                case "(%A)":switch (mnemnonic[2]){
                    case "%D":return "001000010";
                    case "$1":return "001110111";
                }
            }
            case "incw" :  switch (mnemnonic[1]) {
                case "%A" : return "000110111";
                case "%D" : return "000011111";
            }
            case "subw" :  switch (mnemnonic[1]) {
                case "%D":switch (mnemnonic[2]){
                    case "%A":return "000010011";
                    case "(%A)":return "001010011";
                    case "$1":return "000001110";
                }
                case "%A":switch (mnemnonic[2]){
                    case "$1":return "000110010";
                    case "%D":return "000000111";
                }
                case "(%A)":switch (mnemnonic[2]){
                    case "$1":return "001110010";
                    case "%D":return "001000111";
                }
            }
            case "rsubw" : switch (mnemnonic[1]){
                case "%D":switch (mnemnonic[2]){
                    case "%A":return "000000111";
                    case "(%A)":return "001000111";
                }
                case "$1":switch (mnemnonic[2]){
                    case "%D":return "000001110";
                    case "%A":return "000110010";
                    case "(%A)":return "001110010";
                }
                case "%A":
                    if ("%D".equals(mnemnonic[2])) {
                        return "000010011";
                    }
                case "(%A)":
                    if ("%D".equals(mnemnonic[2])) {
                        return "001010011";
                    }
            }
            case "decw" :  switch (mnemnonic[1]) {
                case "%D" : return "000001110";
                case "%A" : return "000110010";
            }
            case "notw" :  switch (mnemnonic[1]) {
                case "%D" : return "000001101";
                case "%A" : return "000110001";
            }
            case "negw" :  switch (mnemnonic[1]) {
                case "%D" : return "000001111";
                case "%A" : return "000110011";
            }
            case "andw" :  switch (mnemnonic[1]) {
                case "%D": switch (mnemnonic[2]){
                    /*andw %D,%A,Z*/
                    case "%A": return "000000000";
                    /*andw %D,(%A),Z*/
                    case "(%A)": return "001000000";
                }
                case "%A": return "000000000"; /*andw %A,%D,Z*/
                case "(%A)": return "001000000"; /*andw %(%A),%D,Z*/
            }
            case "orw" :  switch (mnemnonic[1]) {
                case "%D": switch (mnemnonic[2]){
                    /*orw %D,%A,Z*/
                    case "%A": return "000010101";
                    /*andw %D,(%A),Z*/
                    case "(%A)": return "001010101";
                }
                case "%A": return "000010101"; /*andw %A,%D,Z*/
                case "(%A)": return "001010101"; /*andw %(%A),%D,Z*/
            }
            default : return "000001100";
        }
    }

    /**
     * Retorna o código binário do mnemônico para realizar uma operação de jump (salto).
     * @param  mnemnonic vetor de mnemônicos "instrução" a ser analisada.
     * @return Opcode (String de 3 bits) com código em linguagem de máquina para a instrução.
     */
    public static String jump(String[] mnemnonic) {
        switch (mnemnonic[0]){
            case "jmp" : return "111";
            case "je" : return "010";
            case "jne" : return "101";
            case "jg" : return "001";
            case "jge" : return "011";
            case "jl" : return "100";
            case "jle" : return "110";

            default    : return "000";
        }
    }

    /**
     * Retorna o código binário de um valor decimal armazenado numa String.
     * @param  symbol valor numérico decimal armazenado em uma String.
     * @return Valor em binário (String de 15 bits) representado com 0s e 1s.
     */
    public static String toBinary(String symbol) {
        int num = Integer.valueOf(symbol);
        String binario = "";
        int resto = 0;

        while (num > 0){
            resto = num%2;
            binario=resto+binario;
            num = num/2;
        }
        while (binario.length() != 16){
            binario = '0' + binario;
        }
        return binario;
    }

}
