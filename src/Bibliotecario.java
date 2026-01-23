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
    /* //Cadastrar Livro
    public Livro cadastrarLivro(SistemaBiblioteca sistema, String novoTitulo, String novoAutor, String novoGenero, int quantidadeDisponivel) throws Exception{
        if(!permissao_admin){ 
            throw new PermissaoNegada();
        }
        Livro novoLivro = new Livro(novoTitulo, novoAutor, novoGenero, quantidadeDisponivel);
        sistema.cadastrarLivro(novoLivro);//espero que dê certo
        IO.println("Sucesso! Livro Cadastrado!");
        return novoLivro;
    }
    //Remover livro
    public void removerLivro(SistemaBiblioteca sistema, String titulo) throws Exception {
        if(!permissao_admin){ 
            throw new PermissaoNegada();
        }
        sistema.removerLivro(titulo);
    }
    //Editar Livro
    public void editarLivro(SistemaBiblioteca sistema, String titulo, String novoTitulo, String novoAutor, String novoGenero, int novaQuantidade) throws Exception {
        if(!permissao_admin){ 
            throw new PermissaoNegada();
        }
        // sistema.editarLivro(titulo, novoTitulo, novoAutor, novoGenero, novaQuantidade);
        IO.println("Sucesso! Livro editado!");
        return ;
    } */
    //Cadastrar Usuario
    // public Usuario cadastrarUsuario(SistemaBiblioteca sistema, String tipo, String nome, int matricula) throws Exception{ //Classe que cadastra usuário, a instancia criada depende do tipo do Usuário
    //     if(!permissao_admin){ 
    //         throw new PermissaoNegada();
    //     }
    //     switch(tipo){
    //         case "a":
    //             Usuario a = new Discente(nome, matricula);
    //             sistema.usuarios.add(a);
    //             return a;
    //         case "b":
    //             Usuario b = new Docente(nome, matricula);
    //             sistema.usuarios.add(b);
    //             return b;
    //         case "c":
    //             Usuario c = new Bibliotecario(nome, matricula, true);
    //             sistema.usuarios.add(c);
    //             return c;
    //         default:
    //             throw new ComandoInvalido();
    //     }
    // }
    //Remover Usuario
    // public void removerUsuario(SistemaBiblioteca sistema, String titulo) throws Exception {
    //     if(!permissao_admin){ 
    //         throw new PermissaoNegada();
    //     }
    //     for(Livro le : sistema.getUsuarios()){
    //         if(le.getTitulo().equalsIgnoreCase(titulo)){
    //             sistema.removerLivro(titulo);
    //             IO.println("Sucesso! Livro removido!");
    //             return;
    //         }
    //     }
    //     // sistema.removerLivro(titulo); //Método do Sistema Biblioteca que vai exigir 
    //     throw new NaoEncontrado("Fail: Livro nao encontrado");
    // }
    //Editar Usuario
    // public void editarUsuario(String nome)throws Exception{
    //     if(!permissao_admin){ 
    //         throw new PermissaoNegada();
    //     }
    //     return ;
    // }
    //Listar Usuarios
    // public ArrayList<Usuario> listarUsuarios(){
    //     ArrayList<Usuario> usuarios = new ArrayList<Usuario>(   );
    //     return usuarios;
    // }
}