import java.util.ArrayList;

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
    public Livro cadastrarLivro(Livro livro){
        return null;
    }
    //Remover livro
    public void removerLivro(Livro livro){
        return ;
    }
    //Editar Livro
    public void editarLivro(Livro livro){
        return ;
    }
    //Remover Usuario
    public void removerUsuario(Usuario usuario){
        return ;
    }
    //Editar Usuario
    public void editarUsuario(Usuario usuario){
        return ;
    }
    //Listar Usuarios
    public ArrayList<Usuario> listarUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        return usuarios;
    }
    //Vizualizar Relatorios
    public void vizualizarRelatorio(Livro livro){
        return ;
    }
}