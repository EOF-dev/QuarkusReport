package br.com.proinddy.helper;

import io.quarkus.test.junit.QuarkusTest;
import org.gradle.internal.impldep.javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class GenericSQLTest {

    private GenericSQL genericSQL;

    @BeforeEach
    void setUp() {
        this.genericSQL = new GenericSQL();
    }

    @Test
    public void shouldBeReadTodos(){
        try {
            String s = this.genericSQL.parse("cadastro/todos.sql","proinddy_proinddy","filial_01");
            System.out.println(s);
            assertTrue(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}