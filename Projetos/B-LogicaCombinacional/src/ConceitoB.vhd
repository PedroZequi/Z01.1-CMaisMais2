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
		HEX2 <= "0000001" when "0000", -- 0 
				"1001111" when "0001", -- 1
				"0010010" when "0010", -- 2
				"0000110" when "0011", -- 3
				"1001100" when "0100", -- 4
				"0100100" when "0101", -- 5
				"0100000" when "0110", -- 6
				"0001111" when "0111", -- 7
				"1111111" when "1000", -- 8
				"0000100" when "1001", -- 9
				"0001000" when "1010", -- A
				"1100000" when "1011", -- B
				"0110001" when "1100", -- C
				"1000010" when "1101", -- D
				"0110000" when "1110", -- E
				"0111000" when others; -- F
				
	with SW(7 downto 4) select
		HEX1 <= "0000001" when "0000", -- 0 
				"1001111" when "0001", -- 1
				"0010010" when "0010", -- 2
				"0000110" when "0011", -- 3
				"1001100" when "0100", -- 4
				"0100100" when "0101", -- 5
				"0100000" when "0110", -- 6
				"0001111" when "0111", -- 7
				"1111111" when "1000", -- 8
				"0000100" when "1001", -- 9
				"0001000" when "1010", -- A
				"1100000" when "1011", -- B
				"0110001" when "1100", -- C
				"1000010" when "1101", -- D
				"0110000" when "1110", -- E
				"0111000" when others; -- F
			
	with SW(9 downto 8) select
		HEX0 <= "0000001" when "00", -- 0 
				"1001111" when "01", -- 1
				"0010010" when "10", -- 2
				"0000110" when others; -- 3

end rtl;
