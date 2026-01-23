import java.io.IOException;

public class Main {
    public static void main(String[] arg) throws IOException, LerException {
        System.out.println("side_by_side=080");

        SistemaBiblioteca sis = new SistemaBiblioteca();
        Bibliotecario admin = new Bibliotecario("Rubens", 123, true);

        while (true) {
            String[] line = IO.inputParts();

            try {
                if      (line[0].equals("end"))            { break; }
                else if (line[0].equals("init"))           { sis = new SistemaBiblioteca(); sis.menuPrincipal();}
                // else if (line[0].equals("showLivros"))     { sis.listarLivros();}
                else if (line[0].equals("showUsuarios"))   {}
                else if (line[0].equals("cadastrarLivro")) { Livro livro = new Livro(line[1], line[2], line[3], IO.strToInt(line[4])); sis.cadastrarLivro(livro);}
                else if (line[0].equals("cadastrarUsuario")){ sis.cadastrarUsuario(line[1], line[2], IO.strToInt(line[3]));}
                else if (line[0].equals("removerLivro"))    {sis.removerLivro(line[1]);}
                else if (line[0].equals("removerUsuario"))  {sis.removerUsuario(line[1]);}
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
