SELECT col_nome,col_codigo,

CONCAT(
case when ifnull(col_ah_bio,"") <> "" then "B (ah10)" 
else " " 
end,
case when ifnull(col_ah_bio30,"") <> "" then "B (ah30)"
else ""
end,
case when ifnull(col_ah_mifare,"") <> "" then "M" 
else " " 
end,
case when ifnull(col_ah_codbar,"0") <> "0" then "C" 
else " " 
end,
case when ifnull(col_ah_senha,"a") <> "a" then "S" 
else " " 
end
)
as col_futuro 

from ||baseNome||.colaboradores c where (col_ativo = 1 and col_empresa=||emp|| and ( col_nome like '%%'  or col_nome is null ) ) and
	ifnull(col_ah_bio,"")  <> ""
and (col_demissao is null or col_demissao = '') order by col_nome

