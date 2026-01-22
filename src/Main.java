public class Main {
    public static void main(String[] arg) {
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
                else if (line[0].equals("cadastrarLivro")) { sis.cadastrarLivro(admin, line[1], line[2], line[3], IO.strToInt(line[4]));}
                else if (line[0].equals("cadastrarUsuario")){ sis.cadastrarUsuario(admin, line[1]);}
                else if (line[0].equals("removerLivro"))    {sis.removerLivro(admin, line[1]);}
                else if (line[0].equals("removerUsuario"))  {sis.removerUsuario(admin, line[1]);}
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
