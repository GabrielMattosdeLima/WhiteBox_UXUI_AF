package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    //  Não há documentação explicando o que o método faz.
   
    public Connection conectarBD(){
        Connection conn = null;
        try{ 
            /*
            Classe do driver incorreta. O correto seria "com.mysql.cj.jdbc.Driver"
            Erro de arquitetura: 
            responsabilidade de conexão e regra de negócio estão na mesma classe
            */ 
            Class.forName("com.mysql.Driver.Manager").newInstance();

             //  Constantes ausentes — credenciais e URL estão fixas no código (má prática)
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";

            conn = DriverManager.getConnection(url);
            
        /* Os campos abaixo não utilizam boas práticas em JAVA, 
        utilizando as {} na mesma linha, atrapalhando a legibilidade do código
        */ 
        //  Tratamento de exceção ausente — erro é ignorado completamente
        }catch (Exception e) { }

        return conn;} // Pode retornar null

    public String nome="";
    public boolean result = false;

    //  Falta de documentação explicando a função do método, parâmetros e retorno
    public boolean verificarUsuario(String login, String senha){
        String sql = "";
        Connection conn = conectarBD(); //  Pode retornar null, risco de NullPointer

        //INSTRUÇÃO SQL
        // SQL montado com concatenação → vulnerável a SQL Injection
        sql += "select nome from usuarios ";
        sql += "where login = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "'";

        try{
             // Se conn == null, essa linha lança NullPointer Exception
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                result = true;
                nome = rs.getString("nome");}

                // Conexão, Statement e ResultSet não são fechados, podendo travar o banco

        }catch (Exception e) { } // Catch vazio, não informa nem trata o erro
        return result; }
    }//fim da class