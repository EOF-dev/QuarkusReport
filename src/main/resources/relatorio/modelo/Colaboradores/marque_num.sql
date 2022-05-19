SELECT col_nome,col_codigo,CASE WHEN (ifnull(col_ah_codbar,"") <> "0" ) THEN col_ah_codbar WHEN (ifnull(c.col_ah_senha,"a") <> "a") THEN concat("S",col_ah_senha) ELSE col_ah_mifare end as col_futuro 
FROM ||baseNome||.colaboradores c 
WHERE (col_ativo = 1 and col_empresa=||emp|| and ( col_nome like '%%' or col_nome is null ) ) 
and ((ifnull(c.col_ah_codbar,"0") <> "0") or (ifnull(c.col_ah_mifare,"") <> "") or (ifnull(c.col_ah_senha,"a") <> "a")) AND (col_demissao is null or col_demissao = '') order by col_nome
