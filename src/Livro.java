public class Livro{ 
    //Características do Livro
    private String titulo;
    private String autor;
    private String genero;
    private int quantidadeDisponivel;

    //Construtor do livro
    public Livro(String titulo, String autor, String genero, int quantidadeDisponivel){
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }
    //Métodos do Livro
    public boolean emprestar(){
        if(!estaDisponivel()){ //Se não tiver livro pra emprestar
            IO.println("Não emprestado! O livro está indisponível");
            return false;
        }
        this.quantidadeDisponivel--; //Subtrai um livro da quantidade total
        IO.println("Emprestado! O livro possui agora " +this.quantidadeDisponivel+" cópia(s)");
        return true;
    }
    public void devolver(){ //adiciona o livro
        this.quantidadeDisponivel++;
    }
    public boolean estaDisponivel(){ //verifica se o livro está disponível, é importante
        if(this.quantidadeDisponivel>0){
            IO.println("O Livro está disponível, há " +this.quantidadeDisponivel+ " cópia(s)");
            return true;
        }
        return false;
    }
    public void exibirInformacoes(){ //exibe as informações do livro
        IO.println("Título: " + titulo);
        IO.println("Autor: " + autor);
        IO.println("Gênero: " + genero);
        IO.println("Disponível: " + quantidadeDisponivel);   
    }


    //Métodos de Set (talvez seja importante para Edição)
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public void setGenero(String genero){
        this.genero = genero;
    }
    public void setQuantidadeDisponivel(int quantidadeDisponivel){
        this.quantidadeDisponivel = quantidadeDisponivel;
    }
    //Métodos de Get
    public String getTitulo(){
        return this.titulo;
    }
    public String getAutor(){
        return this.autor;
    }
    public String getGenero(){
        return this.genero;
    }
    public int getQuantidadeDisponivel(){
        return this.quantidadeDisponivel;
    }
}
