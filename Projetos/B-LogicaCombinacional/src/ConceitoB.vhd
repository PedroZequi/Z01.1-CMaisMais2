----------------------------
-- Bibliotecas ieee       --
----------------------------
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
use work.all;

----------------------------
-- Entrada e saidas do bloco
----------------------------
entity ConceitoB is
	port(
		CLOCK_50 : in  std_logic;
		SW       : in  std_logic_vector(9 downto 0);
        HEX0     : out std_logic_vector(6 downto 0); -- 7seg0
        HEX1     : out std_logic_vector(6 downto 0); -- 7seg0
        HEX2     : out std_logic_vector(6 downto 0); -- 7seg0
		LEDR     : out std_logic_vector(9 downto 0)
	);
end entity;

----------------------------
-- Implementacao do bloco --
----------------------------
architecture rtl of ConceitoB is

--------------
-- signals
--------------

---------------
-- implementacao
---------------
begin

	with SW(3 downto 0) select
		HEX0 <= "1000000" when "0000", -- 0 
			"1111001" when "0001", -- 1
			"0100100" when "0010", -- 2
			"0110000" when "0011", -- 3
			"0001101" when "0100", -- 4
			"0001010" when "0101", -- 5
			"0000010" when "0110", -- 6
			"1110000" when "0111", -- 7
			"0000000" when "1000", -- 8
			"0001100" when "1001", -- 9
			"0001000" when "1010", -- A
			"0000011" when "1011", -- B
			"1000110" when "1100", -- C
			"0100001" when "1101", -- D
			"0000110" when "1110", -- E
			"0001110" when others; -- F			
				
	with SW(7 downto 4) select
		HEX1 <= "1000000" when "0000", -- 0 
			"1111001" when "0001", -- 1
			"0100100" when "0010", -- 2
			"0110000" when "0011", -- 3
			"0001101" when "0100", -- 4
			"0001010" when "0101", -- 5
			"0000010" when "0110", -- 6
			"0000111" when "0111", -- 7
			"0000000" when "1000", -- 8
			"0001100" when "1001", -- 9
			"0001000" when "1010", -- A
			"0000011" when "1011", -- B
			"1000110" when "1100", -- C
			"0100001" when "1101", -- D
			"0000110" when "1110", -- E
			"0001110" when others; -- F			
			
	with SW(9 downto 8) select
		HEX2 <= "1000000" when "00", -- 0 
			"1111001" when "01", -- 1
			"0100100" when "10", -- 2
			"0110000" when others; -- 3
end rtl;
