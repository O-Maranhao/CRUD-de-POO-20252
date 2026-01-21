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
class NaoEncontrado extends Exception{
    protected NaoEncontrado(String mensagem){
        super(mensagem);
    }
}

class LimiteEmprestimo extends Exception{
    protected LimiteEmprestimo(String mensagem){
        super(mensagem);
    }
}

class UsuarioException extends Exception{
    protected UsuarioException(String mensagem){
        super(mensagem);
    }
}
class PermissaoNegada extends Exception{
    public PermissaoNegada(){
        super("Fail: Permissão negada pois apenas Admins podem acessar");
    }
}
class ComandoInvalido extends Exception{
    public ComandoInvalido(){
        super("Fail: comando invalido ne patrao");
    }
}

class LivroException extends Exception{
    public LivroException(String message){
        super(message);
    }
}

class JeniferCaloteira extends Exception{
    public JeniferCaloteira(){
        super("Fail: devolveu mas nao pagou");
    }
}

