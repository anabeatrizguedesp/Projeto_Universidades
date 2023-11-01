package bd.daos;
import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class UniversidadesDAO {



//ESTA CADASTRADO???
    public static boolean cadastrado (String CNPJ) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM UNIVERSIDADES1 " +
                  "WHERE CNPJ = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString(1, CNPJ); 
           

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); 
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar universidade");
        }

        return retorno;
    }



    //CADASTRAR

    public static void incluir (UniversidadeDBO universidade) throws Exception
    {
        if (universidade==null)
            throw new Exception ("Universidade nao fornecida");

        try
        {
            String sql;

            sql = "INSERT INTO UNIVERSIDADES1 " +
                  "(CNPJ, NOME, CEP, NUMERO, COMPLEMENTO, RANKING) " +
                  "VALUES " +
                  "(?,?,?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, universidade.getCNPJ ());
             BDSQLServer.COMANDO.setString (2, universidade.getNome ());
            BDSQLServer.COMANDO.setString  (3,  universidade.getCEP ());
            BDSQLServer.COMANDO.setInt (4,  universidade.getNumero ());
            BDSQLServer.COMANDO.setString (5, universidade.getComplemento ());
            BDSQLServer.COMANDO.setInt (6,  universidade.getRanking());


            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
			BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao inserir Universidade");
        }
    }


    
     //EXCLUIR

    public static void excluir (String CNPJ) throws Exception
    {
        if (!cadastrado (CNPJ))
            throw new Exception ("Não cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM UNIVERSIDADES1" +
                  "WHERE CNPJ=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString(1, CNPJ);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
			BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao excluir a universidade");
        }
    }


//ATUALIZAR
    public static void alterar (UniversidadeDBO universidade) throws Exception
    {
        if (universidade==null)
            throw new Exception ("Universidade nao fornecida");

        if (!cadastrado (universidade.getCNPJ()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE UNIVERSIDADES1 " +
                  "SET NOME=? " +
                  "SET CEP=? " +
                  "SET RANKING=?"+
                  "WHERE CNPJ = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, universidade.getNome ());
            BDSQLServer.COMANDO.setString (2, universidade.getCEP());
            BDSQLServer.COMANDO.setInt  (3, universidade.getRanking());
            BDSQLServer.COMANDO.setString(4, universidade.getCNPJ ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
			BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados da Universidade");
        }
    }

    public static void alterarNome (String cnpj, String nome) throws Exception
    {
         if (cnpj == null)
            throw new Exception ("Universidade não fornecida");

        if (!cadastrado (cnpj))
            throw new Exception ("Universidade não cadastrado");

        try
        {
            String sql;

            sql = "UPDATE UNIVERSIDADES1 " +
                  "SET NOME=? "          +                    
                  "WHERE CNPJ=?";

            BDSQLServer.COMANDO.prepareStatement (sql);
        
            BDSQLServer.COMANDO.setString (1, nome);
            BDSQLServer.COMANDO.setString   (2, cnpj);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar nome da universidade");
        }
    }
    //cep
    public static void alterarCep (String cnpj, String cep) throws Exception
    {
        if (cnpj == " " || cnpj == null)
        throw new Exception ("Universidade não fornecida");

        if (!cadastrado (cnpj))
            throw new Exception ("Universidade não cadastrada");

        try
        {
            String sql;

            sql = "UPDATE UNIVERSIDADES1" +
                  "SET CEP=? "          +                    
                  "WHERE CNPJ=?";

            BDSQLServer.COMANDO.prepareStatement (sql);
        
            BDSQLServer.COMANDO.setString (1, cep);
            BDSQLServer.COMANDO.setString (2, cnpj);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar CEP da universidade");
        }
    }
    //compl
    public static void alterarComplemento (String cnpj, String complemento) throws Exception
    {
        if (cnpj == " " || cnpj == null)
            throw new Exception ("Universidade não fornecida");

        if (!cadastrado (cnpj))
            throw new Exception ("Universidade não cadastrada");

        try
        {
            String sql;

            sql = "UPDATE UNIVERSIDADES1" +
                  "SET COMPLEMENTO=? "          +                    
                  "WHERE CNPJ=?";

            BDSQLServer.COMANDO.prepareStatement (sql);
        
            BDSQLServer.COMANDO.setString (1, complemento);
            BDSQLServer.COMANDO.setString   (2, cnpj);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar complemento da universidade");
        }
    }
    //alterar ranking
    public static void alterarRanking (String cnpj, int ranking) throws Exception
    {
         if (cnpj == " " || cnpj == null)
            throw new Exception ("Universidade não fornecida");

        if (!cadastrado (cnpj))
            throw new Exception ("Universidade não cadastrada");

        try
        {
            String sql;

            sql = "UPDATE UNIVERSIDADES1 " +
                  "SET RANKING=? "          +                    
                  "WHERE CNPJ=?";

            BDSQLServer.COMANDO.prepareStatement (sql);
        
            BDSQLServer.COMANDO.setInt    (1, ranking);
            BDSQLServer.COMANDO.setString    (2, cnpj);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar ranking da universidade");
        }
    }

    //alterar nome
    public static void alterarNumero (String cnpj, int numero) throws Exception
    {
         if (cnpj == " " || cnpj == null)
            throw new Exception ("\nUniversidade não fornecido");

        if (!cadastrado (cnpj))
            throw new Exception ("\nUniversidade não cadastrada");

        try
        {
            String sql;

            sql = "UPDATE UNIVERSIDADES1 " +
                  "SET NUMERO=? "          +                    
                  "WHERE CNPJ=?";

            BDSQLServer.COMANDO.prepareStatement (sql);
        
            BDSQLServer.COMANDO.setInt    (1, numero);
            BDSQLServer.COMANDO.setString    (2, cnpj);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar numero da universidade");
        }
    }



//PROCURAR PELO NOME
    public static UniversidadeDBO getUniversidadebyname (String nome) throws Exception
    {
       UniversidadeDBO universidade = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                 "FROM UNIVERSIDADES1" +
                 " WHERE Nome = '?'";

                

                 BDSQLServer.COMANDO.prepareStatement(sql);
                 BDSQLServer.COMANDO.setString(1, nome);
 

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");
            

           
        universidade = new UniversidadeDBO (resultado.getString("CNPJ"),
                                         resultado.getString("Nome"), 
                                         resultado.getInt("Ranking"),
                                         resultado.getString ("CEP"),
                                         resultado.getInt("Numero"),
                                         resultado.getString("Complemento"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro: Universidade inexistente no Banco de Dados ");
        }

        return universidade;
    }


//PROCURAR PELO CNPJ

public static UniversidadeDBO getUniversidadebyRanking(int Ranking) throws Exception
{
   UniversidadeDBO universidade = null;

    try
    {
        String sql;

        sql = "SELECT * " +
             "FROM UNIVERSIDADES1" +
             " WHERE Ranking = ?";

        

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, Ranking);


        MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();

        if (!resultado.first())
            throw new Exception ("Nao cadastrado");
        

        universidade = new UniversidadeDBO (resultado.getString("CNPJ"),
                                           resultado.getString("Nome"), 
                                            resultado.getInt("Ranking"),
                                           resultado.getString ("CEP"),
                                           resultado.getInt("Numero"),
                                            resultado.getString("Complemento")
                                          );
    }
    catch (SQLException erro)
    {
        throw new Exception ("Erro: Universidade inexistente no Banco de Dados ");
    }

    return universidade;
}


public static UniversidadeDBO getUniversidade (String cnpj) throws Exception
{
   UniversidadeDBO universidade = null;

    try
    {
        String sql;

        sql = "SELECT * " +
              "FROM UNIVERSIDADES1 " +
              "WHERE CNPJ = ?";

        BDSQLServer.COMANDO.prepareStatement (sql);

        BDSQLServer.COMANDO.setString(1, cnpj);

        MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

        if (!resultado.first())
            throw new Exception ("\nUniversidade nao cadastrada");

        universidade = new UniversidadeDBO  (resultado.getString("CNPJ"),
                                            resultado.getString("NOME"),
                                            resultado.getInt   ("RANKING"),
                                            resultado.getString("CEP"),
                                            resultado.getInt   ("NUMERO"),
                                            resultado.getString("COMPLEMENTO"));
    }
    catch (SQLException erro)
    {
        throw new Exception ("Erro: Universidade inexistente no Banco de Dados");
    }

    return universidade;
}



    public static MeuResultSet getUniversidades () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;
            sql = "SELECT * " +
                  "FROM UNIVERSIDADES1";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar Universidades");
        }

        return resultado;
    }




    public static boolean existeCnpj(String CNPJ) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            // Abre a conexão com o banco de dados
            conn =  BDSQLServer.COMANDO.getConnection();

            // Cria a consulta SQL para verificar se o CNPJ existe
            String sql = "SELECT COUNT(*) " +
                        "AS total FROM UNIVERSIDADES1"+
                        " WHERE CNPJ = ?";


            // Prepara o comando SQL
           stmt = conn.prepareStatement(sql);

            // Atribui o parâmetro CNPJ ao comando SQL
            stmt.setString(1, CNPJ);
            // Executa o comando SQL e recupera o resultado
            rs = stmt.executeQuery();
            if (rs.next()) {
                // Se o resultado da consulta for maior que zero, o CNPJ existe no banco de dados
                existe = rs.getInt("total") > 0;
            }
        } finally {
            // Fecha os recursos utilizados (ResultSet, PreparedStatement e Connection)
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return existe;
    }
    
    
    
    
    
    
    
    

  
}




