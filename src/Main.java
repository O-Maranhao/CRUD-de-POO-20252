import java.io.IOException;

public class Main {
    public static void main(String[] arg) throws IOException, LerException {
        System.out.println("side_by_side=080");

        SistemaBiblioteca sistema = new SistemaBiblioteca();
        Bibliotecario admin = new Bibliotecario("Rubens", 123, true);

        while (true) {
            String[] line = IO.inputParts();

            try {
                if      (line[0].equals("end"))            { break; }
                else if (line[0].equals("init"))           { sistema = new SistemaBiblioteca(); }
                else if (line[0].equals("showLivros"))     {}
                else if (line[0].equals("showUsuarios"))   {}
                else if (line[0].equals("cadastrarLivro")) {}
                else if (line[0].equals("cadastrarUsuario")){}
                else if (line[0].equals("emprestar"))      {}
                else if (line[0].equals("devolver"))       {}
                else if (line[0].equals("pesquisarLivro")) {}
                else if (line[0].equals("pesquisarUsuario")) {}
                else if (line[0].equals("verificarAtrasos")) {}
                else                                       { throw new ComandoInvalido(); }
            } catch(Exception e) {
                String message = e.getMessage();
                if(message != null){
                    IO.println(message);
                }
            }
        }
    }
}
