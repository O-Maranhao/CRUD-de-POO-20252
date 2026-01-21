import java.util.*;

public class Bibliotecario extends Usuario{
    private boolean permissao_admin;

    public Bibliotecario(String nome, int matricula, boolean permissao_admin){
        super(nome, matricula);
        this.permissao_admin = permissao_admin;
    }
    @Override
    public String tipoUsuario(){
        return "Bibliotecario";
    }

    //Métodos foda que só o Bibliotecário tem
    //Cadastrar Livro
    public Livro cadastrarLivro(SistemaBiblioteca sistema, String titulo, String novoTitulo, String novoAutor, String novoGenero, int quantidadeDisponivel) throws Exception{
        if(!permissao_admin){ 
            throw new PermissaoNegada();
        }
        Livro novoLivro = new Livro(titulo, novoAutor, novoGenero, quantidadeDisponivel);
        sistema.livros.add(novoLivro); //Isso aqui é um uso de encapsulamento, espero que dê certo
        IO.println("Sucesso! Livro Cadastrado!");
        return novoLivro;
    }
    //Remover livro
    public void removerLivro(SistemaBiblioteca sistema, String titulo) throws Exception {
        if(!permissao_admin){ 
            throw new PermissaoNegada();
        }
        sistema.removerLivro(titulo); //Método do Sistema Biblioteca que vai exigir 
        IO.println("Sucesso! Livro removido!");
        return ;
    }
    //Editar Livro
    public void editarLivro(SistemaBiblioteca sistema, String titulo, String novoTitulo, String novoAutor, String novoGenero, int novaQuantidade) throws Exception {
        if(!permissao_admin){ 
            throw new PermissaoNegada();
        }
        sistema.editarLivro(titulo, novoTitulo, novoAutor, novoGenero, novaQuantidade);
        IO.println("Sucesso! Livro editado!");
        return ;
    }
    //Remover Usuario
    public void removerUsuario(SistemaBiblioteca sistema, String nome) throws Exception{
        if(!permissao_admin){ 
            throw new PermissaoNegada();
        }
        sistema.removerUsuario(nome);
        IO.println("Sucesso! Usuario removido!");
        return ;
    }
    //Editar Usuario
    public void editarUsuario(String nome)throws Exception{
        if(!permissao_admin){ 
            throw new PermissaoNegada();
        }
        return ;
    }
    //Listar Usuarios
    public ArrayList<Usuario> listarUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        return usuarios;
    }
    //Vizualizar Relatorios
    public void vizualizarRelatorio(String titulo){

        return ;
    }
}