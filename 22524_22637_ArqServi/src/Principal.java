import java.io.BufferedReader;
import java.util.Scanner;
import bd.daos.UniversidadesDAO;
import bd.dbos.UniversidadeDBO;

public class Principal 
{
    public static void main (String[]args)
    {
       int passo = 0;
       Scanner scanner = new Scanner(System.in);
    //    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

       do{
        System.out.println("                                                            ");
        System.out.println("                                                            ");
        System.out.println("::::::::::::::  Digite o numero correspondente ao método a ser realizado  ::::::::::::::::");
        System.out.println("                                                            ");
   
        System.out.println("1. Cadastrar Universidade");
        System.out.println("2. Atualizar Universidade");
        System.out.println("3. Excluir Universidade");
        System.out.println("4. Pesquisar Universidade");  
        System.out.println("0. Sair");
        System.out.println("                                                            ");
        System.out.println("                                                            ");

        passo = scanner.nextInt();

        switch (passo) {
           
           //CADASTRAR
            case 1:
                try {

                    System.out.println("                                                            ");
                    System.out.println(" ::::::::::::  Você escolheu a opção 1 - CADASTRAR UNIVERSIDADE  ::::::::::::");
                    System.out.println(" ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                    System.out.println("                                                            ");
                    System.out.println("                                                            ");
                
                scanner.nextLine();
                System.out.print("Nome da Universidade: ");
                String Nome =  scanner.nextLine(); // scanner.next()+" "+ 
               
                String CNPJ= null;
                boolean Valido = false;
                
                while (!Valido) {
                                try {
                                    System.out.print("\nCNPJ: ");
                                    CNPJ = scanner.nextLine();
                                    Long.parseLong(CNPJ);
                                    Valido = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Erro: o CNPJ digitado contém caracteres inválidos.");
                                }}

                System.out.print("\nCEP: ");
                String CEP = scanner.nextLine();
                
                System.out.print("\nNúmero: ");
                int Numero = Integer.valueOf(scanner.nextLine()); 

                System.out.print("\nComplemento (opcional): ");
                String Complemento = scanner.nextLine();

                System.out.print("\nPosição no Ranking Brasileiro: ");
                int Ranking = Integer.valueOf(scanner.nextLine()); 


              UniversidadeDBO universidadeincluir = new UniversidadeDBO(CNPJ, Nome, Ranking, CEP, Numero, Complemento);
             
              UniversidadesDAO.incluir(universidadeincluir);
                System.out.println("                                                            ");
                System.out.println("Universidade incluída com sucesso!");
                System.out.println("                                                            ");
                
                    
                } catch (Exception e) {
                   System.out.println(e.getMessage());
                }
                break;
                


            //ALTERAR
            case 2:

            System.out.println("                                                                  ");
            System.out.println(" ::::::::::::  Você escolheu a opção 2 - ALTERAR DADOS DA UNIVERSIDADE  ::::::::::::");
            System.out.println(" :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("                                                            ");
            System.out.println("\nDigite o CNPJ da universidade que você deseja alterar: ");
            String altporcnpj = scanner.next()+" "+ scanner.nextLine();
            try{
                int opcaoAlteracao = 0;
                //Scanner Alteracao = new Scanner(System.in);

                if (UniversidadesDAO.existeCnpj(altporcnpj)) {
        
                
                // mostra todos os dados incluindo rua, bairro, cidade e estado
                System.out.println("\nListagem dos dados:");
                System.out.println("                                                            ");

                UniversidadeDBO universidade =  UniversidadesDAO.getUniversidade(altporcnpj);
                Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(Logradouro.class, 
                    "https://api.postmon.com.br/v1/cep", universidade.getCEP());

                System.out.println("\n:::::::::: LISTAGEM DE DADOS ::::::::::\n");
                System.out.println("Nome:.................. " + universidade.getNome());
                System.out.println("Ranking:............... " + universidade.getRanking());
                System.out.println("CNPJ:.................. " + universidade.getCNPJ());
                System.out.println("CEP:................... " + universidade.getCEP());                
                System.out.println("Número:................ " + universidade.getNumero());
                System.out.println("Complemento:........... " + universidade.getComplemento());
                System.out.println("Rua:................... " + logradouro.getLogradouro());
                System.out.println("Bairro:................ " + logradouro.getBairro());
                System.out.println("Cidade:................ " + logradouro.getCidade());
                System.out.println("Estado:................ " + logradouro.getEstado()+ "\n");} 
                else {
                    // Lança uma exceção personalizada caso a universidade não exista
                    throw new Exception("A universidade com o CNPJ " + altporcnpj + " não existe no banco de dados.");
                }

                System.out.println("\nDigite o número para confirmar: ");
                System.out.println("\n1. Alterar.");
                System.out.println("2. Retornar ao menu.");
                opcaoAlteracao = scanner.nextInt();
                
                
                switch (opcaoAlteracao) {
                

                case 1:
                    int qualAlterar = 0;
                    System.out.println("\n :::::: Qual dado você deseja alterar? ::::::");
                    System.out.println("\n1. Nome da universidade");
                    System.out.println("2. Ranking");
                    System.out.println("3. CEP");
                    System.out.println("4. Número");
                    System.out.println("5. Complemento");
                    System.out.println("0. Retornar ao menu");
                    
                    qualAlterar = scanner.nextInt();

                    
    
                        switch(qualAlterar){
                           
                                case 0:
                                    System.out.println("\nVocê retornou ao menu!");
                                break;
        
                                case 1:                                      
                                    System.out.println("\nDigite o novo nome pelo qual você gostaria de atualizar: \n");
                                    String nomeUpdate = scanner.next()+ "" + scanner.nextLine();
        
                                    try{    
                                        UniversidadesDAO.alterarNome(altporcnpj, nomeUpdate); 
                                        System.out.println("\nNome atualizado com sucesso");
        
                                    } catch (Exception e) {
                                        System.out.println( e.getMessage());
                                    }
                                break;
        
                                case 2:
                                    System.out.println("\nDigite o novo ranking: ");
                                    int rankingUp = scanner.nextInt();
        
                                    try {
                                        UniversidadesDAO.alterarRanking(altporcnpj, rankingUp);
                                        System.out.println("\nRanking atualizado com sucesso");
        
                                    } catch (Exception e) {
                                        System.out.println( e.getMessage());
                                    }
                                break;
        
                                case 3:
                                    System.out.println("\nDigite o novo CEP (sem hífen, espaço, ponto ou vírgula): ");
                                    String cepUpdate = scanner.next();
        
                                    try {
                                        UniversidadesDAO.alterarCep(altporcnpj, cepUpdate);
                                        System.out.println("\nCEP atualizado com sucesso");
        
                                    } catch (Exception e) {
                                        System.out.println( e.getMessage());
                                    }                    
                                break;
        
                                case 4:
                                    System.out.println("\nDigite o novo número residencial sua universidade: ");
                                    int numeroUpdate = scanner.nextInt();
        
                                    try {
                                        UniversidadesDAO.alterarNumero(altporcnpj, numeroUpdate);
                                        System.out.println("\nNumero atualizado com sucesso");
        
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                break;
        
                                case 5:
                                    System.out.println("\nDigite o novo complemento para atualizar: \n");
                                    String complUpdate = scanner.next()+ "" + scanner.nextLine();
                                    try{    
                                        UniversidadesDAO.alterarComplemento(altporcnpj, complUpdate); 
                                        System.out.println("\ncomplemento atualizado com sucesso");
        
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                break;
        
                                    default: 
                                    System.out.println("\nOpção inválida!");
                                break;
                                }

                                try {
                                } catch (Exception e) {
                                    System.out.println( e.getMessage());
                                }
                            break;
                    

                                default:
                                        System.out.println("\nOpção inválida, você será encaminhado ao Menu.");
                                break;
                                }
                            
                    }
                    catch (Exception e){
                        System.out.println( e.getMessage());
                    }
                    break;
                         
        
                    
            //DELETAR
            case 3:

            System.out.println("                                                            ");
            System.out.println(" ::::::::::::  Você escolheu a opção 3 - EXCLUIR UNIVERSIDADE  ::::::::::::");
            System.out.println(" ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("                                                            ");
            try{
                
                String CNPJ= null;
                boolean Valido = false;
                
while (!Valido) {
                try {
                    System.out.println("\nDigite o CNPJ da Universidade que deseja excluir: ");
                    CNPJ = scanner.nextLine();
                    Long.parseLong(CNPJ);
                } catch (NumberFormatException e) {
                    System.out.println("Erro: o CNPJ digitado contém caracteres inválidos.");
                }}

                String cnpjExclusao = scanner.next()+" "+ scanner.nextLine();
                int opcaoExclusao = 0;

                UniversidadeDBO universidadeExclusao =  UniversidadesDAO.getUniversidade(cnpjExclusao);
                Logradouro logradouroExclusao = (Logradouro)ClienteWS.getObjeto(Logradouro.class, 
                    "https://api.postmon.com.br/v1/cep", universidadeExclusao.getCEP());

                System.out.println("\n:::::::::: LISTAGEM DE DADOS ::::::::::\n");
                System.out.println("Nome:.................. " + universidadeExclusao.getNome());
                System.out.println("Ranking:............... " + universidadeExclusao.getRanking());
                System.out.println("CNPJ:.................. " + universidadeExclusao.getCNPJ());
                System.out.println("CEP:................... " + universidadeExclusao.getCEP());                
                System.out.println("Numero:................ " + universidadeExclusao.getNumero());
                System.out.println("Complemento:........... " + universidadeExclusao.getComplemento());
                System.out.println("Rua:................... " + logradouroExclusao.getLogradouro());
                System.out.println("Bairro:................ " + logradouroExclusao.getBairro());
                System.out.println("Cidade:................ " + logradouroExclusao.getCidade());
                System.out.println("Estado:................ " + logradouroExclusao.getEstado()); 

                System.out.println("\nAcima estão os dados do CNPJ selecionado. "+
                                    "\n\nDigite o número correspondente para confirmar a EXCLUSÂO:\n" +
                                    "\n1.EXCLUIR."+
                                    "\n2.CANCELAR.");
                opcaoExclusao = scanner.nextInt();

                switch (opcaoExclusao){
                    case 1: 
                        try {
                            UniversidadesDAO.excluir(cnpjExclusao);
                            System.out.println("\nUniversidade excluída com sucesso");
                    
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    break;

                    case 2: 
                        System.out.println("\nExclusão cancelada.");
                    break;

                }  
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }  
            break;


        //PESQUISAR
           case 4:
        
            System.out.println("                                                            ");
            System.out.println(" :::::::::::  Você escolheu a opção 4 - PESQUISAR UNIVERSIDADE  ::::::::::::");
            System.out.println(" :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            
        
            try {
                int opcaoPesquisa = 0;
                System.out.println("\nEscolha por qual meio deseja pesquisar a Universidade: "+
                                    "\n           " +
                                    "\n1.Pelo Nome"+
                                    "\n2.Pelo CNPJ."+
                                    "\n3.Pelo Ranking."+
                                    "\n          ");

                opcaoPesquisa = scanner.nextInt();

                switch (opcaoPesquisa){
                    case 1: 
                    String nomeUniversidade;
                    try {

                    System.out.println("                                                            ");
                   System.out.println("Digite o nome da Universidade:");
                   nomeUniversidade = scanner.next()+" "+ scanner.nextLine();
                   UniversidadeDBO universidade = UniversidadesDAO.getUniversidadebyname(nomeUniversidade);
                   Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(Logradouro.class,
                   "https://api.postmon.com.br/v1/cep",universidade.getCEP());

    System.out.println("                                                            ");
            System.out.println("Listagem de dados:");
            System.out.println("                         ");

                System.out.println("Nome: " + universidade.getNome());
                System.out.println("Ranking: " + universidade.getRanking());
                System.out.println("CNPJ: " + universidade.getCNPJ());
                System.out.println("CEP: " + universidade.getCEP());
                System.out.println("Numero: " + universidade.getNumero());
                System.out.println("Complemento " + universidade.getComplemento());
                System.out.println("Rua: "+ logradouro.getLogradouro());
                System.out.println("Bairro: "+ logradouro.getBairro());
                System.out.println("Cidade: "+ logradouro.getCidade());
                System.out.println("Estado: "+ logradouro.getEstado());
                System.out.println("                                                            ");

            } 
             catch (Exception e) {
                System.out.println(e.getMessage());
            }
                break;


                case 2: 
                String cnpjuniv = null;
            try{ 

                 System.out.println("Digite o CNPJ da Universidade:");
                 try {
                    System.out.print("\nCNPJ: ");
                    cnpjuniv = scanner.nextLine();
                    Long.parseLong(cnpjuniv);
                } catch (NumberFormatException e) {
                    System.out.println("Erro: o CNPJ digitado contém caracteres inválidos.");
                }
                
                UniversidadeDBO universidade = UniversidadesDAO.getUniversidade(cnpjuniv);
                Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(Logradouro.class,
                "https://api.postmon.com.br/v1/cep",universidade.getCEP());

                System.out.println("                                                            ");
            System.out.println("Listagem de dados:");
            System.out.println("                         ");

             System.out.println("Nome: " + universidade.getNome());
             System.out.println("Ranking: " + universidade.getRanking());
             System.out.println("CNPJ: " + universidade.getCNPJ());
             System.out.println("CEP: " + universidade.getCEP());
             System.out.println("Numero: " + universidade.getNumero());
             System.out.println("Complemento " + universidade.getComplemento());
             System.out.println("Rua: "+ logradouro.getLogradouro());
             System.out.println("Bairro: "+ logradouro.getBairro());
             System.out.println("Cidade: "+ logradouro.getCidade());
             System.out.println("Estado: "+ logradouro.getEstado());
             System.out.println("     ");
            } 
            catch (Exception e) {
               System.out.println(e.getMessage());
           }
               break;

               case 3: 
            try{  
                 System.out.println("Digite o Ranking da Universidade:");
                String rankinguniv = scanner.next()+" "+ scanner.nextLine();
                UniversidadeDBO universidade = UniversidadesDAO.getUniversidade(rankinguniv);
                Logradouro logradouro = (Logradouro)ClienteWS.getObjeto(Logradouro.class,
                "https://api.postmon.com.br/v1/cep",universidade.getCEP());

                System.out.println("                                                            ");
            System.out.println("Listagem de dados:");
            System.out.println("                         ");

             System.out.println("Nome: " + universidade.getNome());
             System.out.println("Ranking: " + universidade.getRanking());
             System.out.println("CNPJ: " + universidade.getCNPJ());
             System.out.println("CEP: " + universidade.getCEP());
             System.out.println("Numero: " + universidade.getNumero());
             System.out.println("Complemento " + universidade.getComplemento());
             System.out.println("Rua: "+ logradouro.getLogradouro());
             System.out.println("Bairro: "+ logradouro.getBairro());
             System.out.println("Cidade: "+ logradouro.getCidade());
             System.out.println("Estado: "+ logradouro.getEstado());
             System.out.println("     ");
            
            } 
             catch (Exception e) {
                System.out.println(e.getMessage());
            }
             break;
        

            case 0:
                try{
                System.out.println("\n\n::::::::::::  O programa está encerrado  :::::::::::: \n\n");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
                
            break;
        
            default:
                System.out.println("Opção inválida! Tente novamente.");   
            break;
        } 
       } catch (Exception e) {
        System.out.println(e.getMessage());
    }
     break;
            
}
   } while (passo != 5);
     }     
}
    
