SELECT col_nome,col_codigo,case when ifnull(col_ah_bio,"") <> "" then "S" else "N" end as col_futuro from proinddy_icn.colaboradores c where (col_ativo = 1 and col_empresa=07 and ( col_nome like '%%'  or col_nome is null ) ) and
	ifnull(col_ah_bio,"") <> ""
and (col_demissao is null or col_demissao = '') order by col_nome

