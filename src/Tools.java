import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Tools {
    // --Tools

    // define o dia e hora para o check
    public static String horarioDaEscala() {
        int hora = java.time.LocalTime.now().getHour();
        int dia = java.time.LocalDate.now().getDayOfMonth();
        int mes = java.time.LocalDate.now().getMonthValue();
        int ano = java.time.LocalDate.now().getYear();

        if (hora < 13) {
            return "dia_" + dia + "-" + mes + "-" + ano + ".txt";
        } else {
            return "noite_" + dia + "-" + mes + "-" + ano + ".txt";
        }
    }

    // Le ultimo ID
    public static String ReadID(String caminhoArquivo) {
        String ultimaLinha = null;
        String linhaAtual;
        StringBuilder IDString = new StringBuilder("");

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {

            while ((linhaAtual = br.readLine()) != null) {
                ultimaLinha = linhaAtual;   // sempre atualiza
            }

            char c = 'n';
            for (int i = 0; c != ';'; i++) {
                c = ultimaLinha.charAt(i);
                if (i >2 && c != ';'){
                    IDString.append(ultimaLinha.charAt(i)); // ID
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
        }

        return IDString.toString();
    }

    // Responsavel por escrever nos arquivos de texto
    public static void gravarOuAdicionar(String caminho, String texto, Boolean append) {
        try {
            // true = modo append (adiciona ao final do arquivo)
            FileWriter fw = new FileWriter(caminho, append);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(texto); // escreve com quebra de linha

            pw.close();
            fw.close();

            System.out.println("Texto gravado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo: " + e.getMessage());
        }
    }

    // ID --> linha
    public static int idParaLinha(String caminhoArquivo, String palavraProcurada) {
        int linhaNumero = 0;
        int id = -1;
        palavraProcurada = "ID:" + palavraProcurada + ";";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {

                linhaNumero++;

                // Ignora diferença entre maiúsculas e minúsculas
                if (linha.toLowerCase().contains(palavraProcurada.toLowerCase())) {
                    //System.out.printf("Linha %d: %s%n", linhaNumero, linha);
                    id = linhaNumero;
                    return id;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado! Verifique o caminho.");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return id;
    }

    // id para Linhas de Baixo para Cima
    public static int idParaLinhaBottomUp(String caminhoArquivo, String palavraProcurada) {
        int id = -1;
        palavraProcurada = "ID:" + palavraProcurada + ";";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            List<String> linhas = new ArrayList<>();
            String linha;

            // Lê todas as linhas
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }

            // Percorre de trás para frente
            for (int i = linhas.size() - 1; i >= 0; i--) {
                String linhaAtual = linhas.get(i);

                if (linhaAtual.toLowerCase().contains(palavraProcurada.toLowerCase())) {
                    return i + 1; // +1 para manter numeração de linhas começando em 1
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado! Verifique o caminho.");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return id;
    }

    // Editor de linhas de arquivo
    public static void editarLinha(String caminhoArquivo, int numeroLinha, String novaFrase) throws IOException {
        Path path = Paths.get(caminhoArquivo);
        List<String> linhas = Files.readAllLines(path);

        if (numeroLinha < 1 || numeroLinha > linhas.size()) {
            throw new IllegalArgumentException("Linha inválida!");
        }

        linhas.set(numeroLinha - 1, novaFrase);
        Files.write(path, linhas);
    }

    // Recebe o numero da linha e retorna a frase da linhas
    public static String getLinha(String caminhoArquivo, int numeroLinha) throws IOException {
        List<String> linhas = Files.readAllLines(Paths.get(caminhoArquivo));

        if (numeroLinha < 1 || numeroLinha > linhas.size()) {
            throw new IllegalArgumentException("Linha inválida! O arquivo tem " + linhas.size() + " linhas.");
        }

        return linhas.get(numeroLinha - 1);
    }

    // Recebe o numero da linha retorna nome
    public static String getNome(String caminhoArquivo, int numeroLinha) throws IOException{
        String linha = getLinha(caminhoArquivo, numeroLinha);
        StringBuilder nome = new StringBuilder();
        for (int i = 0, cdp = 0; i < linha.length() && cdp < 2; i++) {
            if (linha.charAt(i) == ';'){
                cdp++;
            } else if (cdp > 0) {
                nome.append(linha.charAt(i));
            }
        }
        return nome.toString();
    }

    // Recebe bolenado e retonar string CheckIn ou CheckOut
    public static  String entrandoOuSaindo(boolean entrando){
        if (entrando){
            return "Check-In";
        }else {
            return  "Check-Out";
        }
    }

    // Horario atual
    public static String hora(){
        int hora = java.time.LocalTime.now().getHour();
        int min = java.time.LocalTime.now().getMinute();
        return hora + ":" + min;
    }

    // Recebe Sim/Nao e retorna true/false
    public static boolean paraBoolean(String resposta) {
        if (resposta == null) {
            return false;
        }

        // Normaliza a string: remove espaços, converte para minúsculo e trim
        String r = resposta.trim().toLowerCase();

        // Português
        if (r.equals("sim") || r.equals("s") || r.equals("verdadeiro") || r.equals("true") || r.equals("1") || r.equals("ok") || r.equals("positivo")) {
            return true;

            // Inglês
        } else if (r.equals("yes") || r.equals("y") || r.equals("no") || r.equals("n") || r.equals("false") || r.equals("0")) {
            return false;
        }// Se não reconhecer, você pode escolher o comportamento:
        return false;                    // opção mais segura
        // ou lançar exceção:
        // throw new IllegalArgumentException("Resposta inválida: " + resposta);
    }

}
