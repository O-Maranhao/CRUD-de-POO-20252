public class MsgException extends Exception{
    protected MsgException(String mensagem){
        super(mensagem);
    }
}
class LivroIndisponivel extends Exception{
    protected LivroIndisponivel(String mensagem){
        super(mensagem);
    }
}
class LimiteEmprestimo extends Exception{
    protected LimiteEmprestimo(String mensagem){
        super(mensagem);
    }
}
