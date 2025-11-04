class Livro{ 
    //Características do Livro
    private String nome;
    private String autor;
    private String genero;
    private String quantidadeDisponivel;

    //Construtor do livro
    public Livro(String nome, String autor, String genero, String quantidadeDisponivel){
        this.nome = nome;
        this.autor = autor;
        this.genero = genero;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }
    //Métodos do Livro
    public boolean emprestar(){

    }
    public void devolver(){

    }
    public boolean estaDisponivel(){

    }
    public void exibirInformacoes(){
        
    }


    //Métodos de Set (talvez seja importante para Edição)
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public void setGenero(String genero){
        this.genero = genero;
    }
    public void setQuantidadeDisponivel(String quantidadeDisponivel){
        this.quantidadeDisponivel = quantidadeDisponivel;
    }
    //Métodos de Get
    public String getNome(){
        return this.nome;
    }
    public String getAutor(){
        return this.autor;
    }
    public String getGenero(){
        return this.genero;
    }
    public String getQuantidadeDisponivel(){
        return this.quantidadeDisponivel;
    }
}
