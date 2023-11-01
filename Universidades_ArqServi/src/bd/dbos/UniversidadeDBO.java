package bd.dbos;

import javax.lang.model.util.ElementScanner14;

public class UniversidadeDBO implements Cloneable {

    private String CNPJ;
    private String Nome; 
    private int Ranking;
    private String CEP;
    private int Numero;
    private String Complemento;
 
    

    public void setCEP (String CEP) throws Exception
    {
        if (CEP == null || CEP.equals(""))
            throw new Exception ("CEP inválido");

        this.CEP = CEP;
    }   
    
    public void setNumero (int Numero) throws Exception
    {
        try {
            if (Numero <= 0) {
                throw new Exception("Número inválido");
            }
            this.Numero = Numero;
        } catch (NumberFormatException e) {
            throw new Exception("Valor inválido. Digite um número inteiro positivo.");
        }
    }   
    
    public void setComplemento(String Complemento) throws Exception {

         if(Complemento == null || Complemento== " ") {
                    this.Complemento = Complemento;
           
       }}

    
    public void setCNPJ(String CNPJ) throws Exception {
        
        try {
            Long.parseLong(CNPJ);
        } catch (NumberFormatException e) {
            throw new Exception("Erro: o CNPJ digitado contém caracteres inválidos.");
        }
    }
    
    

     public void setNome (String Nome) throws Exception
    {
        if (Nome==null || Nome.equals(""))
            throw new Exception ("Digite o nome da Universidade");

        this.Nome = Nome;
    }

     public void setRanking (int Ranking) throws Exception
    {
        if (Ranking <= 0)
            throw new Exception ("digite a posição no Ranking");

        this.Ranking = Ranking;
    }   




    public String getCEP ()
    {
        return this.CEP;
    }

    public String getNome ()
    {
        return this.Nome;
    }

    public int getNumero ()
    {
        return this.Numero;
    }

    public String getComplemento ()
    {
        return this.Complemento;
    }


    public String getCNPJ ()
    {
        return this.CNPJ;
    }

    public int getRanking ()
    {
        return this.Ranking;
    }



    public UniversidadeDBO (String CNPJ, String Nome, int Ranking, String CEP, int Numero, String Complemento ) throws Exception
    {
         this.setCNPJ  (CNPJ);
         this.setNome  (Nome); 
         this.setRanking (Ranking);
         this.setCEP   (CEP);
         this.setNumero (Numero);
         if (Complemento != null && Complemento.equals("")) {
            this.setComplemento(null);
        } else {
            this.setComplemento(Complemento);
        }
      
    }

    public String toString ()
    {
        String ret="";

        ret+="CEP: "+this.CEP+"\n";
        ret+="Nome: "+this.Nome  +"\n";
        ret+="CNPJ: "+this.CNPJ +  "\n";
        ret+="Ranking: "+this.Ranking +  "\n";
        ret+="Numero: "+this.Numero +  "\n";
        ret+="Complemento: "+this.Complemento  +"\n";
        

        return ret;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof UniversidadeDBO))
            return false;

        UniversidadeDBO liv = (UniversidadeDBO)obj;

        if (this.CEP.equals(liv.CEP))
        return false;

        if (this.Complemento.equals(liv.Complemento))
        return false;

        if (this.Nome.equals(liv.Nome))
            return false;

        if (this.CNPJ.equals(liv.CNPJ))
            return false;

        if (this.Numero!=liv.Numero)
        return false;

        if (this.Ranking!=liv.Ranking)
        return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + this.CNPJ.hashCode();
        ret = 7*ret + new Integer(this.Numero).hashCode();
        ret = 7*ret + this.Nome.hashCode();
        ret = 7*ret + this.CEP.hashCode();
        ret = 7*ret + this.Complemento.hashCode();
        ret = 7*ret + new Integer(this.Ranking).hashCode();

        return ret;
    }


    public UniversidadeDBO (UniversidadeDBO modelo) throws Exception
    {
        this.CEP = modelo.CEP; // nao clono, pq nao eh objeto
        this.Complemento = modelo.Complemento;
        this.Numero = modelo.Numero;
        this.Nome   = modelo.Nome;   // nao clono, pq nao eh clonavel
        this.CNPJ = modelo.CNPJ; 
        this.Ranking = modelo.Ranking; // nao clono, pq nao eh objeto
    }

    public Object clone ()
    {
        UniversidadeDBO ret=null;

        try
        {
            ret = new UniversidadeDBO(this);
        }
        catch (Exception erro)
        {} // nao trato, pq this nunca � null e construtor de
           // copia da excecao qdo seu parametro for null

        return ret;
    }
}