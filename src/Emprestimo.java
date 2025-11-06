import java.time.LocalDate; // biblioteca para usar Datas de maneira mais organizada, também captura a data do computador local
import java.time.temporal.ChronoUnit; // outro bagulho pra calcular a diferença de dias, demorei um monte pra achar isso também

public class Emprestimo{ //Atributos principais
    private Livro livro;
    private Usuario usuario; //tranquilo esse amarelinho, realmente não estamos usando ainda
    private LocalDate dataEmprestimo; //LocalDate é uma data, seria foda usar um Inteiro aqui né
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;

    //Construtor
    public Emprestimo(Livro livro, Usuario usuario){
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now(); //isso aqui tá pegando o tempo de exatamente agora
        this.dataDevolucaoPrevista = dataEmprestimo.plusDays(14); //já emplaca duas semanas, mas podemos mudar dependendo do tipo do usuário
        this.dataDevolucaoReal = null; //O cara ainda não devolveu né.
    }

    //MÉTODOS
    //verificar atraso
    public boolean verificarAtraso(){//Se o livro estiver atrasado, é verdadeiro, se não for, dizemos que é falso
        if(this.dataDevolucaoReal!=null && this.dataDevolucaoReal.isAfter(dataDevolucaoPrevista)){ //se a data de devolução for diferente de nula e for depois da data de devolução prevista
        return true;
        }
        return false; //caso o livro não tenha sido devolvido ainda ou a devoluçãoReal vem antes da data de devolução prevista
    }
    //calcular multa (essa aqui deu raiva tá)
    public float calcularMulta(){
        if(!verificarAtraso()){
            return 0.0f; //esse f é só pra dizer que é uma disgraça de float, o compilador por algum motivo interpreta que é double caso não tenha
        }
        float diasAtraso; //um float pra contar a quantidade de dias, vamos usar isso pra descobrir quanto a pessoa vai pagar
        diasAtraso = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal); //esse código comicamente grande calcula a diferença do valor inicial pro final
        return diasAtraso*100; //to nem ai, essa sessão demorou tanto tempo que agora a multa vai subindo de 100 em 100 dias.
    }
    //registrar Devolução
    public void registrarDevolucao(){ //isso aqui não envolve o Usuário, só vamos envolver ele nas classes de DevolverLivro da classe dele
        this.dataDevolucaoReal = LocalDate.now();
        this.livro.devolver();
    }
    //exibir Resumo
    public void exibirResumo(){ //Exibir TODAS as informações... TODASSSSS ATÉ AQUELAS QUE NÃO TEMOS
        IO.println("Empréstimo:");
        IO.println("Livro: " + livro.getTitulo());
        //IO.println("Usuário: " +usuario.getNome()+ " (Matrícula: " +usuario.getMatricula()+ ")"); //vamos ter que acessar informações do usuário
        IO.println("Data do Empréstimo: " + dataEmprestimo);
        IO.println("Data Prevista de Devolução: " + dataDevolucaoPrevista);

        if (dataDevolucaoReal != null){
            IO.println("Data da Devolução Real: " + dataDevolucaoReal);
            IO.println("Atrasado? " +(verificarAtraso() ? "Sim" : "Não")); //aquele if e else engraçado
            IO.printf("Multa: R$ %.2f\n", calcularMulta()); //só sei fazer com printfkkk
        }else{
            IO.println("Livro ainda não devolvido.");
        }
    }
}