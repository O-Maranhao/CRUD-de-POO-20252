import java.time.LocalDate;
import java.util.*;

abstract class Usuario {
    //Vamo lá
    String nome;
    int matricula;
    ArrayList<Emprestimo> emprestimos;

    //Construtor
    public Usuario(String nome, int matricula){ //quando se cria um usuario
        this.nome = nome;
        this.matricula = matricula;
        this.emprestimos = new ArrayList<Emprestimo>();
    }

    //Métodos
    public abstract String tipoUsuario();
    public Emprestimo realizarEmprestimo(Livro livro) throws MsgException{
        //Vamo lá, primeiro verificar quantos empréstimos cada um pode fazer
        int limite;
        switch(this.tipoUsuario()){
            case "Discente":
                limite = 3;
                break;
            case "Docente":
                limite = 6;
                break;
            case "Bibliotecario":
                limite = 10;
                break;
            default:
                limite = 2;
                break;
        }

        //Verficando se o limite foi atingido
        if(limite<=this.emprestimos.size()){
            throw new MsgException("Fail:"+this.nome+"atingiu o limite de Empréstimos para "+tipoUsuario());
        }
        if(livro.estaDisponivel()){
            livro.emprestar();
            Emprestimo e = new Emprestimo(livro, this);
            emprestimos.add(e);
            return e;
        } else{
            throw new MsgException("Fail: Livro não Disponível");
        }
    }
    public void devolverLivro(Emprestimo emprestimo){
        Livro livro = emprestimo.getLivro();
        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel()+1); //vai somar mais um a quantidade disponível
        emprestimo.registrarDevolucao();
        emprestimos.remove(emprestimo); //tira da lista
    }
    public void listarEmprestimos(){
        if(emprestimos.isEmpty()){
            IO.println("Aviso: Nenhum Empréstimo Encontrado Para" + this.nome);
        } else{
            IO.println("Empréstimos de "+this.nome);
            for(Emprestimo e : this.emprestimos){ //For each, vai percorrer cada elemento da lista, é tipo um (int i; i<this.emprestimos.size();i++){Emprestimo e = emprestimos.get(i)} ou algo do tipo
                e.toString();
            } //Vai imprimir tudo, e depois só colocar aqui o
            IO.println("Fim da Lista");
        }
    }
    public ArrayList<Emprestimo> verificarAtrasos(){ //Função pra listar os livros atrasados
        ArrayList<Emprestimo> atrasados = new ArrayList<Emprestimo>(); //Vamos comparar a data prevista com a data atual num for each
        LocalDate hoje = LocalDate.now();
        for(Emprestimo e : this.emprestimos){
            if(e.getDataDevolucaoReal() == null && e.getDataDevolucaoPrevista().isBefore(hoje)){ //Se ainda não foi entregue && e se a data de Devolução é antes de hoje
                atrasados.add(e);
            }
        }

        return atrasados;
    }
}


//CLASSES
class Discente extends Usuario{ //É Aluno
    public Discente(String nome, int matricula){
        super(nome, matricula); //chamando o construtor do pai
    }

    @Override
    public String tipoUsuario(){
        return "Discente";
    }
}
class Docente extends Usuario{ //É Professor
    public Docente(String nome, int matricula){
        super(nome, matricula); //chamando o construtor do pai
    }

    @Override
    public String tipoUsuario(){
        return "Docente";
    }
}
