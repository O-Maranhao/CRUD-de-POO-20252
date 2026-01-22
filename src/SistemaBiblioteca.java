import java.io.*; //FileReader, BufferedReader
import java.time.LocalDate;
import java.util.*;

public class SistemaBiblioteca {
    protected ArrayList<Livro> livros;
    protected ArrayList<Usuario> usuarios;
    protected ArrayList<Emprestimo> emprestimos;


    
    public SistemaBiblioteca() throws LerException, IOException {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();

        livrosArq();
        emprestimosArq();
        usuariosArq();
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


