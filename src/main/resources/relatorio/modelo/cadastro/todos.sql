SELECT col_nome, col_codigo, case when ifnull(col_ah_bio, "") <> "" then "S" else "N" end as col_futuro
from ||baseNome||.colaboradores c
where (col_ativo = 1
  and col_empresa=||emp||
  and ( col_nome like '%%'
   or col_nome is null ) )
  and (col_demissao is null
   or col_demissao = '')
order by col_nome

