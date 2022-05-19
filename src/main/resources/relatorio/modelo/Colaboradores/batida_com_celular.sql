select col_nome,col_codigo,col_fone_cel as col_futuro 
from ||baseNome||.colaboradores c where (col_ativo = 1 and col_empresa=||emp|| and ( col_nome like '%%'  or col_nome is null ) ) and
 ifnull(col_fone_cel,"")  <> ""
and (col_demissao is null or col_demissao = '') order by col_nome

