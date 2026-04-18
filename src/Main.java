//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

//Path DataBase = Paths.get("base", "kids-dataset.txt");
String DataBase = "base\\kids-dataset.txt";
Path DataCheck = Paths.get("log", "KidsCheck_");
//String DataCheck = "log/KidsCheck_";
String Cabecalho = "ID;Nome;DataDeNascimento;Responsavel(Afiliacao:Nome, Afiliacao:Nome, );Contato(numero, );Alergias/Proibicoes/Restricoes;Membro;ImgBase64, ;";
String CabecalhoCheck = "ID;Nome;Check-in;Check-out;";

// Apenas em modo desenvolvedor/administrador

    // Essa funcao DELETA TODA A BASE DE DADOS;
    public void Create(){
        Tools.gravarOuAdicionar(DataBase, Cabecalho, false);
    }


void main() throws Exception {
    /*
    // Dev
    Creat               OK

    // Tools

    // Func
    AddAluno            OK
    Editar              OK
    Add numero          OK
    Add responsavel     OK
    Pesquisar nome      OK
    Pesquisar ID        OK
    Excluir
    Check-in/Check-out
     */
    IO.println(String.format("Start!"));
    Scanner sc = new Scanner(System.in); // inicializa o Scanner

    // ====================== MAIN ======================
    SwingUtilities.invokeLater(() -> {
        new TelaPesquisa().setVisible(true);
    });

    /*

    List<String> resultados = null;
    try {
        resultados = buscarString(DataBase, "luiz f", true);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    System.out.println("||------------------------------------------------------------||");
    if (resultados.isEmpty()) {
        System.out.println("Nenhuma linha encontrada.");
    } else {
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println((i+1) + ": " + resultados.get(i));
        }
    }


    /*
    try {
        String arquivo = "exemplo.txt";

        String linha3 = getLinha(DataBase, 3);
        System.out.println("Linha 3: " + linha3);

        String linha1 = getLinha(DataBase, 1);
        System.out.println("Linha 1: " + linha1);

    } catch (IOException e) {
        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    } catch (IllegalArgumentException e) {
        System.err.println("Erro: " + e.getMessage());
    }

    /*


    try {
        alterarLinhaSimples(DataBase, 3, "essa é a nova linha 3");
    } catch (IOException e) {
        throw new RuntimeException(e);
    }*/
    sc.close();

}
