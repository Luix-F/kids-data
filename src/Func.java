import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Func
{
    static String DataBase = "base\\kids-dataset.txt";
    static Path DataCheck = Paths.get("log", "KidsCheck_");
    static String Cabecalho = "ID;Nome;DataDeNascimento;Responsavel(Afiliacao:Nome, Afiliacao:Nome, );Contato(numero, );Alergias/Proibicoes/Restricoes;Membro;ImgBase64, ;";



// --Funcoes

    // Check-in e Check-out
    public static void addCheckKid(String ID, boolean entrando) throws Exception {
        StringBuilder check = new StringBuilder();
        if (entrando) {
            check.append("ID:").append(ID).append(";");
            check.append(Tools.getNome(DataBase, Tools.idParaLinha(DataBase, ID))).append(";");
            check.append(Tools.hora()).append(";");
            Tools.gravarOuAdicionar(DataCheck + Tools.horarioDaEscala(), check.toString(), true);
        }else if (Tools.idParaLinhaBottomUp(DataCheck + Tools.horarioDaEscala(),ID) != -1){
            check.append(Tools.hora()).append(";");
            Tools.editarLinha(DataCheck + Tools.horarioDaEscala(),Tools.idParaLinhaBottomUp(DataCheck + Tools.horarioDaEscala(),ID),Tools.getLinha(DataCheck + Tools.horarioDaEscala(),Tools.idParaLinhaBottomUp(DataCheck + Tools.horarioDaEscala(),ID)) + check);
        }else {
            throw new Exception("Erro, ID da Criança não foi registrada no Check-In");
        }
    }

    // Pesquisa Nome ou semelhantes
    public static List<String> buscarNomes(String caminhoArquivo, String palavra, boolean ignorarMaiusculas) throws IOException {

        if (palavra == null || palavra.trim().isEmpty()) {
            //throw new IllegalArgumentException("A palavra de busca não pode ser vazia");
            System.out.println("A palavra de busca não pode ser vazia");
        }else {

            List<String> linhasEncontradas = new ArrayList<>();
            Path caminho = Paths.get(caminhoArquivo);

            String palavraBusca = ignorarMaiusculas ? palavra.toLowerCase() : palavra;

            try (BufferedReader reader = Files.newBufferedReader(caminho)) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String linhaComparacao = ignorarMaiusculas ? linha.toLowerCase() : linha;

                    if (linhaComparacao.contains(palavraBusca)) {
                        linhasEncontradas.add(linha);   // retorna a linha original
                    }
                }
            }


            return linhasEncontradas;
        }
        return null;
    }

    // Add novo Responsavel
    public static String addResp(int numeroLinha,String novoResp) throws IOException {
        String linha = Tools.getLinha(DataBase, numeroLinha);
        String saida = "-1";
        for (int i = 0, cdp = -1; i < linha.length() && cdp < 4; i++) {
            if (cdp == 3){
                String parte1 = linha.substring(0, i-1);
                String parte2 = linha.substring(i-1);
                saida = parte1 + novoResp + ", " + parte2;
                //IO.println(String.format("Flag"));
                IO.println(String.format(saida));
                Tools.editarLinha(DataBase, numeroLinha, saida);
                return saida;
            } else if (linha.charAt(i) == ';'){
                cdp++;
            }

        }
        return saida;
    }

    // Add novo contato
    public static String addCont(int numeroLinha,String novoResp) throws IOException {
        String linha = Tools.getLinha(DataBase, numeroLinha);
        String saida = "-1";
        for (int i = 0, cdp = -1; i < linha.length() && cdp < 5; i++) {
            if (cdp == 4){
                String parte1 = linha.substring(0, i-1);
                String parte2 = linha.substring(i-1);
                saida = parte1 + novoResp + ", " + parte2;
                IO.println(String.format(saida));
                Tools.editarLinha(DataBase, numeroLinha, saida);
                return saida;
            } else if (linha.charAt(i) == ';'){
                cdp++;
            }

        }
        return saida;
    }

    // Add nova restricao
    public static String addRestricao(int numeroLinha,String novoResp) throws IOException {
        String linha = Tools.getLinha(DataBase, numeroLinha);
        String saida = "-1";
        for (int i = 0, cdp = -1; i < linha.length() && cdp < 6; i++) {
            if (cdp == 7){
                String parte1 = linha.substring(0, i-1);
                String parte2 = linha.substring(i-1);
                saida = parte1 + novoResp + ", " + parte2;
                IO.println(String.format(saida));
                Tools.editarLinha(DataBase, numeroLinha, saida);
                return saida;
            } else if (linha.charAt(i) == ';'){
                cdp++;
            }

        }
        return saida;
    }

    // Add aluno
    public static void AddKid(){
        Scanner scadd = new Scanner(System.in); // inicializa o Scanner

        IO.println(String.format("Nome;DataDeNascimento;Responsavel(Afiliacao:Nome, Afiliacao:Nome, );Contato(numero, );Alergias/Proibicoes/Restricoes;Membro;"));
        String Nome = scadd.nextLine();
        String DataDeNascimento = "";
        do {
            DataDeNascimento = scadd.nextLine();
            if (!DataDeNascimento.matches("\\d+")){ System.out.println("Entrada inválida! Digite apenas números.");}
        }while (!DataDeNascimento.matches("\\d+"));


        String ResponsavelAfiliacao = scadd.nextLine();
        String ResponsavelNome = scadd.nextLine();
        String Contato = "";
        do {
            Contato = scadd.nextLine();
            if (!Contato.matches("\\d+")){ System.out.println("Entrada inválida! Digite apenas números.");}
        }while (!Contato.matches("\\d+"));
        String Restricoes = scadd.nextLine();
        boolean membro = Tools.paraBoolean(scadd.nextLine());

        int ID = -1;
        if (Objects.equals(Tools.ReadID(DataBase), "")){
            ID = 0;
        }else {
            ID = Integer.parseInt(Tools.ReadID(DataBase));
            //ID = Long.parseLong(ReadID(DataBase));
            ID = ID + 1;
        }





        scadd.close();
        Tools.gravarOuAdicionar(DataBase, "ID:"+ID+";" + Nome + ";" + DataDeNascimento +";"+ResponsavelAfiliacao+":"+ResponsavelNome+", "+";"+Contato+", "+";"+Restricoes+";"  + membro + ";", true);
    }

}
