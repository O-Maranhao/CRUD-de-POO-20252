import java.io.*; //FileReader, BufferedReader
import java.time.LocalDate;
import java.util.*;

public class SistemaBiblioteca {
    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;
    
    public SistemaBiblioteca() throws LerException, IOException {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
            
        livrosArq();
        emprestimosArq();
        usuariosArq();
    }

    //metodo de busca
    protected Livro pesquisarLivro(String titulo){
        for (Livro l : livros){
            if(l.getTitulo().equalsIgnoreCase(titulo)) return l;
        }
        return null; //se nao achar retorna nada
    }
    protected Usuario pesquisarUsuario(int matricula){
        for (Usuario u : usuarios){
            if (u.getMatricula()==matricula) { return u; }// achou
        }
        return null; //se nao achar retorna nada
    }
    
    //Cadastro de livro
    public void adicionarLivro(Livro novoLivro){
        this.livros.add(novoLivro);
        IO.println("Sistema: Livro '" + novoLivro.getTitulo() + "' guardado com sucesso.");
    }
    
    public void cadastrarLivro(Livro livro){
        livros.add(livro); 
    }
    public void cadastrarUsuario(String tipo, String nome, int matricula) throws Exception{ 
        switch(tipo){
            case "a":
                Usuario a = new Discente(nome, matricula);
                this.usuarios.add(a);
                break;
            case "b":
                Usuario b = new Docente(nome, matricula);
                this.usuarios.add(b);
                break;
            case "c":
                Usuario c = new Bibliotecario(nome, matricula, true);
                this.usuarios.add(c);
                break;
            default:
                throw new ComandoInvalido();
        }
        //usuarios.add(usuario);
    }

    public void adicionarEmprestimo(String titulo, int matricula) throws Exception{
        Livro livro = pesquisarLivro(titulo);
        Usuario usuario = pesquisarUsuario(matricula);
        if(usuario!=null && livro !=null ){
            Emprestimo emprestimo = new Emprestimo(livro, usuario);
            emprestimos.add(emprestimo);
            usuario.realizarEmprestimo(livro);
            livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel()-1);
            IO.println("Sucesso, Livro emprestado!");
        }
    }
    //devoluçao
    public void realizarDevolucao(String nomeUsuario, String tituloLivro) throws Exception {
    for (Emprestimo e : emprestimos) {
        if (e.getUsuario().getNome().equalsIgnoreCase(nomeUsuario) && 
            e.getLivro().getTitulo().equalsIgnoreCase(tituloLivro) && 
            e.getDataDevolucaoReal() == null) {
            
            e.setDataDevolucaoReal(LocalDate.now()); // Data de hoje
            e.getLivro().setQuantidadeDisponivel(e.getLivro().getQuantidadeDisponivel() + 1);
            IO.println("Sucesso: Livro devolvido!");
            return;
        }
    }
    throw new Exception("fail: emprestimo nao encontrado");
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
    //Devolução
    public void devolverLivro(String titulo, int matricula) throws Exception{
        Emprestimo encontrado = null;
        Usuario usuario = pesquisarUsuario(matricula);
        if(usuario!=null){
            for(Emprestimo e : usuario.getEmprestimos()){
                if(e.getLivro().getTitulo().equalsIgnoreCase(titulo)){
                    encontrado = e;
                    usuario.devolverLivro(e);
                    e.getLivro().setQuantidadeDisponivel(e.getLivro().getQuantidadeDisponivel()+1); //adiciona de novo
                    IO.println("Livro retornado!");
                }
            }
            throw new NaoEncontrado("Fail: Livro nao encontrado");
        }
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
    //editar usuario
    public void editarUsuario(int matricula, String novoNome, int novaMatricula) throws UsuarioException {
        Usuario u = pesquisarUsuario(matricula);
        if (u == null) throw new UsuarioException("fail: usuario nao encontrado");
        
        u.setNome(novoNome);
        u.setMatricula(novaMatricula);
        System.out.println("Sucesso: Usuario editado!");
    }

    ////ler linha por linha e adicionar na lista do arquivo. cada linha, um objeto
    private List<String> lerArquivo(String nomeArq) throws LerException, IOException{
        List<String> linhasArq = new ArrayList<>();
        File arquivo = new File(nomeArq); // uso do file para apontar o arquivo a ser lido
        //abrir o arquivo, acessar os dados (lê caractere por caractere);
        FileReader leitorArq = null;
        BufferedReader leitorLinha = null; // lê linha a linha do arquivo



        try{
            leitorArq = new FileReader(arquivo);//tentar abrir o arquivo
            leitorLinha = new BufferedReader(leitorArq);// conseguindo abrir, continua
            String linha = leitorLinha.readLine();
            
            while(linha != null){
                linhasArq.add(linha);//enquanto tiver linha, vai adicionando à lista de linhas
                linha = leitorLinha.readLine(); //vai sempre pra próxima
            }
 
        }catch(FileNotFoundException e){
            throw new LerException("fail: arquivo não encontrado");
        }finally{
            try{
                if(leitorLinha != null){
                    leitorLinha.close();
                }
    
                if(leitorArq != null){
                    leitorArq.close();
                }

            }catch(IOException e){
                IO.println("fail: erro ao fechar o arquivo");
            }
        }

        return linhasArq;

    }

    private void livrosArq() throws LerException, IOException{
        String nome = "livros.txt"; // nome do arquivo a ser lido
        List<String> linhas = null;
    
        linhas = lerArquivo(nome);

        //percorrer as linhas
        for(int i = 0; i< linhas.size();i++){
            String linhaAtual = linhas.get(i);
            //separar campos de cada linha
            String campos[] = linhaAtual.split(", ");
            
            if(campos.length < 4){
                IO.println("fail: linha inválida.");
                continue; // pula pra próxima linha
            }

            //titulo, autor, genero, quantidade
            String titulo = campos[0].trim(); //.trim() remove espaços;
            String autor = campos[1].trim();
            String genero = campos[2]. trim();
            int quantidade = Integer.parseInt(campos[3].trim());

            Livro livroAtual = new Livro(titulo, autor, genero, quantidade);
            this.livros.add(livroAtual);
        }
        
    }

    
    private void usuariosArq() throws LerException, IOException{
        String nome = "usuarios.txt"; // nome do arquivo a ser lido
        List<String> linhas = null;
        
        linhas = lerArquivo(nome);
        
        //percorrer as linhas
        for(int i = 0; i< linhas.size();i++){
            String linhaAtual = linhas.get(i);
            //separar campos de cada linha
            String campos[] = linhaAtual.split(", ");
            
            if(campos.length < 2){
                IO.println("fail: linha inválida.");
                continue; // pula pra próxima linha
            }
            
            //titulo, autor, genero, quantidade
            // String nome;
            // int matricula;
            // ArrayList<Emprestimo> emprestimos;
            String nomeUsuario = campos[0].trim(); //.trim() remove espaços;
            int matricula = Integer.parseInt(campos[1].trim());
            
            Usuario usuarioAtual = new Discente(nomeUsuario, matricula);
            this.usuarios.add(usuarioAtual);
        }
        
    }
    
    private void emprestimosArq() throws LerException, IOException{
        String nome = "emprestimos.txt"; // nome do arquivo a ser lido
        List<String> linhas = null;
    
        linhas = lerArquivo(nome);

        //percorrer as linhas
        for(int i = 0; i< linhas.size();i++){
            String linhaAtual = linhas.get(i);
            //separar campos de cada linha
            String campos[] = linhaAtual.split(", ");
            
            if(campos.length < 5){
                IO.println("fail: linha inválida.");
                continue; // pula pra próxima linha
            }
    // private Usuario usuario; // tranquilo esse amarelinho, realmente não estamos usando ainda
    // private LocalDate dataEmprestimo; // LocalDate é uma data, seria foda usar um Inteiro aqui né
    // private LocalDate dataDevolucaoPrevista;
    // private LocalDate dataDevolucaoReal;
            String titulo = campos[0].trim(); //.trim() remove espaços;
            String nomeU = campos[1].trim();
            LocalDate dataEemp = LocalDate.parse(campos[2].trim());
            LocalDate dataDev =LocalDate.parse(campos[3].trim());
            LocalDate dataReal = LocalDate.parse(campos[4].trim());

            
            Livro livroAtual = new Livro(titulo, "", "", 0);//associar livro ao emprestimo
            Usuario usuarioAtual = new Discente(nomeU, 0);
            Emprestimo emp = new Emprestimo(livroAtual,usuarioAtual, dataEemp,dataDev,dataReal);

            this.emprestimos.add(emp);
        }
        
    }
    // Menu principal (simples para exemplo)
    public void menuPrincipal() {
        System.out.println("=== Sistema da Biblioteca ===");
        System.out.println("cadastrarLivro - Cadastrar Livro");
        System.out.println("showLivros - Listar Livros");
        System.out.println("cadastrarUsuario - Cadastrar Usuário");
        System.out.println(" - Realizar Empréstimo");
        System.out.println("5 - Realizar Devolução");
        System.out.println("6 - Pesquisar Livro");
        System.out.println("7 - Verificar Atrasos");
        System.out.println("8 - Sair");
    }
    //GETTERS  GAY LOL
    public ArrayList<Livro> getLivros(){
        return livros;
    }
    public ArrayList<Usuario> getUsuarios(){
        return usuarios;
    }
    public ArrayList<Emprestimo> getEmprestimos(){
        return emprestimos;
    }

    //LISTAS
    public String listarLivros(){
        String ss="";
        ss+="Lista de Livros:\n";
        for(Livro l : livros){
            ss += l.exibirInformacoes();
            ss+="\n";
            ss+="\\\\\\\\\\n";
        }
        return ss;
    }

    //Lista de Usuarios
    public String listarUsuario(){
        String ss="";
        ss+="Lista de Usuarios:\n";
        for(Usuario u : usuarios){
            ss += "Nome: " +u.getNome();
            ss += ";Matricula: " +u.getMatricula();
            ss += ";Tipo de Usuario: " +u.tipoUsuario()+"\n";
            ss+="\\\\\\\\\\n";
        } //cornakkkkkkkk seria mais facil criar um toString la em usuario, mas só pensei nisso dps
        return ss;
    }

    //Lista de Emprestimos
    public String listarEmprestimos(){
        String ss="";
        ss+="Lista de Emprestimos:\n";
        for(Emprestimo e : emprestimos){
            ss += "Livro: " +e.getLivro();
            ss += ";Usuario: " +e.getUsuario();
            ss += ";Data de Emprestimo" + e.getDataEmprestimo();
            ss += ";Data de Devolucao Prevista" + e.getDataDevolucaoPrevista()+"\n";
            ss+="\\\\\\\\\\n";
        } //cornakkkkkkkk seria mais facil criar um toString la em usuario, mas só pensei nisso dps
        return ss;
    }

}