import java.util.*;

public class SistemaBiblioteca {
    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;

    public SistemaBiblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
}
    public void cadastrarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo){
        emprestimos.add(emprestimo);
    }

    public void removerLivro(String titulo) throws NaoEncontrado{
        //procura - acha - remove
        //n achou, lança erro
        //percorrer livros cadastrados com o for
        for(int i = 0; i < this.livros.size(); i++){
            Livro l = livros.get(i); // pegar o livro do array
            if(l.getTitulo().equals(titulo)){ // verificar se o livro digitado pelo usuario é o livro que tá no sistema
                this.livros.remove(i); // remover o livro da lista
                IO.println("Livro removido com sucessoo");
                return;
            }
        }
        throw new NaoEncontrado("fail: livro não encontrado no sistema");
    }

    public void removerUsuario(String nome) throws UsuarioException{
        for(int i = 0; i < this.usuarios.size(); i++){
            Usuario u = usuarios.get(i); // pegar o livro do array
            if(u.getNome().equals(nome)){ // verificar se o livro digitado pelo usuario é o livro que tá no sistema
                this.usuarios.remove(i); // remover o livro da lista
                IO.println("Usuario removido com sucessoo.");
                return;
            }
        }
        throw new UsuarioException("fail: usuario não encontrado no sistema.");
    }

    public void editarLivro(String titulo, String novoTitulo, String novoAutor, String novoGenero, int novaQuantidade) throws LivroException{
        //String titulo é pra acessar o livro a ser editado pelo titulo dele
        for(int i = 0; i<this.livros.size(); i++){
            Livro l = livros.get(i);
            if(l.getTitulo().equals(titulo)){
                l.setTitulo(novoTitulo);
                l.setAutor(novoAutor);
                l.setGenero(novoGenero);
                l.setQuantidadeDisponivel(novaQuantidade);
                IO.println("Livro editado com sucesso.");
                return;
            }
        }

        throw new LivroException("fail: livro nao encontrado.");
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