import java.time.LocalDate; // biblioteca para usar Datas de maneira mais organizada, também captura a data do computador local
import java.time.temporal.ChronoUnit; // outro bagulho pra calcular a diferença de dias, demorei um monte pra achar isso também

public class Emprestimo { 

    // Atributos principais
    private Livro livro;
    private Usuario usuario; // tranquilo esse amarelinho, realmente não estamos usando ainda
    private LocalDate dataEmprestimo; // LocalDate é uma data, seria foda usar um Inteiro aqui né
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;

    // Constantes para melhorar manutenção 
    private static final int PRAZO_PADRAO = 30; // prazo padrão de devolução em dias
    private static final float MULTA_POR_DIA = 1000.0f; // valor da multa diária (R$ 1000,00 pra ferrar todo mundo hehehe)

    // Construtor
    public Emprestimo(Livro livro, Usuario usuario) {
        // Verificações básicas para evitar nullPointerException e manter consistência
        if (livro == null || usuario == null) {
            throw new IllegalArgumentException("Livro e Usuário não podem ser nulos, né pae");
        }

        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now(); // isso aqui tá pegando o tempo de exatamente agora
        this.dataDevolucaoPrevista = dataEmprestimo.plusDays(PRAZO_PADRAO); // já emplaca duas semanas, mas podemos mudar dependendo do tipo do usuário
        this.dataDevolucaoReal = null; // O cara ainda não devolveu né.
    }
    // MÉTODOS
    // verificar atraso
    public boolean verificarAtraso() {
        // Se o livro estiver atrasado, é verdadeiro, se não for, é falso
        if (this.dataDevolucaoReal != null && this.dataDevolucaoReal.isAfter(dataDevolucaoPrevista)) { 
            return true;
        }
        return false; // caso o livro não tenha sido devolvido ainda ou a devoluçãoReal vem antes da data de devolução prevista
    }

    // calcular multa (essa aqui deu raiva tá)
    public float calcularMulta() {
        if (!verificarAtraso()) {
            return 0.0f; // esse f é só pra dizer que é uma disgraça de float, o compilador interpreta como double caso não tenha
        }
        float diasAtraso; // um float pra contar a quantidade de dias, vamos usar isso pra descobrir quanto a pessoa vai pagar
        diasAtraso = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal); 
        return diasAtraso * MULTA_POR_DIA; // agora usando constante em vez de valor fixo, ficou mais elegante :)
    }

    // registrar Devolução
    public void registrarDevolucao() { 
        // isso aqui não envolve o Usuário, só vamos envolver ele nas classes de DevolverLivro da classe dele
        this.dataDevolucaoReal = LocalDate.now();
        this.livro.devolver(); // delega a ação para o livro respeitando encapsulamento
    }

    // Este método apenas monta a string de resumo.
    // esse sb.append é so pra facilitar a concatenação de strings, pq se usar o + direto fica meio lotado de informações
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empréstimo:\n");
        sb.append("Livro: ").append(livro.getTitulo()).append("\n");
        // sb.append("Usuário: ").append(usuario.getNome()).append(" (Matrícula: ").append(usuario.getMatricula()).append(")\n");
        sb.append("Data do Empréstimo: ").append(dataEmprestimo).append("\n");
        sb.append("Data Prevista de Devolução: ").append(dataDevolucaoPrevista).append("\n");

        if (dataDevolucaoReal != null) {
            sb.append("Data da Devolução Real: ").append(dataDevolucaoReal).append("\n");
            sb.append("Atrasado? ").append(verificarAtraso() ? "Sim" : "Não").append("\n");
            sb.append(String.format("Multa: R$ %.2f\n", calcularMulta()));
        } else {
            sb.append("Livro ainda não devolvido.\n");
        }

        return sb.toString();
    }
    // Getters - para acessar dados do empréstimo quando necessário. Se for necessario kk.
    public Livro getLivro() {
        return livro;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }
    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }
}
