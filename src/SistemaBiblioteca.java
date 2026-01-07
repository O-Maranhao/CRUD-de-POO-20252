import java.util.*;

public class SistemaBiblioteca {
    protected ArrayList<Livro> livros;
    protected ArrayList<Usuario> usuarios;
    protected ArrayList<Emprestimo> emprestimos;

    public SistemaBiblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    // Menu principal (simples para exemplo)
    public void menuPrincipal() {
        System.out.println("=== Sistema da Biblioteca ===");
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Listar Livros");
        System.out.println("3 - Cadastrar Usuário");
        System.out.println("4 - Realizar Empréstimo");
        System.out.println("5 - Realizar Devolução");
        System.out.println("6 - Pesquisar Livro");
        System.out.println("7 - Verificar Atrasos");
        System.out.println("8 - Sair");
    }

}