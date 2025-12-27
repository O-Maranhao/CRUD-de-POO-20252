package exceptions;
public class MsgException extends Exception{
    public MsgException(String mensagem){
        super(mensagem);
    }

    //Exemplo de implementação
    /*While(...){
        try{
            ...
        }
        catch(MeusErros e){
            IO.println(e.getMessage());
        }
    }
        class Exception{
        private(protected) String message;

            public Exception(String msg){
            this.message = message;
            }
        }
    */

}