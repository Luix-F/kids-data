import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class TelaPesquisa extends JFrame {

    private JTextField campoTexto;
    private JButton btnPesquisar;
    private DefaultListModel<String> listModel;
    private JList<String> listaElementos;

    // Labels que vão receber o primeiro item da lista
    private final JLabel[] infoLabels = new JLabel[6];



    private void acaoBotao1() {
        JOptionPane.showMessageDialog(this,
                "Ação do Botão 1 executada!\n\nVocê pode colocar aqui sua lógica específica.",
                "Botão 1", JOptionPane.INFORMATION_MESSAGE);

        // Exemplo: limpar a lista
        // listModel.clear();
    }

    private void acaoBotao2() {
        String texto = campoTexto.getText().trim();
        if (texto.isEmpty()) texto = "nada digitado";

        JOptionPane.showMessageDialog(this,
                "Botão 2: Texto atual no campo = " + texto,
                "Botão 2", JOptionPane.INFORMATION_MESSAGE);
    }

    private void acaoBotao3() {
        if (listaElementos.getSelectedValue() != null) {
            JOptionPane.showMessageDialog(this,
                    "Item selecionado: " + listaElementos.getSelectedValue(),
                    "Botão 3", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum item selecionado na lista!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void acaoBotao4() {
        // Exemplo: atualizar as informações com algo diferente
        for (int i = 0; i < infoLabels.length; i++) {
            infoLabels[i].setText("Atualizado pelo Botão 4 (" + (i+1) + ")");
        }
        JOptionPane.showMessageDialog(this, "Informações atualizadas pelo Botão 4",
                "Botão 4", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Executa ação diferente para cada botão do painel direito
     */
    private void acaoBotao(int indice) {
        switch (indice) {
            case 0:  // Botão 1
                acaoBotao1();
                break;

            case 1:  // Botão 2
                acaoBotao2();
                break;

            case 2:  // Botão 3
                acaoBotao3();
                break;

            case 3:  // Botão 4
                acaoBotao4();
                break;

            default:
                JOptionPane.showMessageDialog(this, "Botão não implementado");
        }
    }



    public TelaPesquisa() {
        setTitle("Children's management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 620);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ==================== TOP BAR ====================
        //JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 12));
        JPanel topBar = new JPanel();
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.X_AXIS));
        topBar.setBackground(new Color(245, 245, 245));
        topBar.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        String[] titulos = {"Informação1", "Informação2", "Informação3",
                "Informação4", "Informação5", "Informação6"};

        for (int i = 0; i < 6; i++) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

            JLabel titulo = new JLabel(titulos[i] + ": ");
            titulo.setFont(new Font("Arial", Font.BOLD, 12));

            infoLabels[i] = new JLabel("—");
            infoLabels[i].setFont(new Font("Arial", Font.PLAIN, 13));
            infoLabels[i].setForeground(new Color(50, 50, 50));

            panel.add(titulo, BorderLayout.NORTH);
            panel.add(infoLabels[i], BorderLayout.CENTER);
            topBar.add(panel);
        }

        // Botões Imagem e Logo (mantidos como botões)
        //topBar.add(Box.createHorizontalStrut(20));

// Caminho das imagens (ajuste para o seu projeto)
        ImageIcon iconImagem = new ImageIcon("IMGSys/profile-1.jpg");
        ImageIcon iconLogo = new ImageIcon("IMGSys/logoNeon.png");

// (Opcional) Redimensionar imagem
        Image img1 = iconImagem.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image img2 = iconLogo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        JLabel lblImagem = new JLabel(new ImageIcon(img1));
        JLabel lblLogo = new JLabel(new ImageIcon(img2));

        topBar.add(Box.createHorizontalGlue()); // empurra tudo pra direita
        topBar.add(lblImagem);
        topBar.add(Box.createHorizontalStrut(10)); // espacinho entre elas
        topBar.add(lblLogo);

        lblImagem.setAlignmentY(Component.CENTER_ALIGNMENT);
        lblLogo.setAlignmentY(Component.CENTER_ALIGNMENT);

        add(topBar, BorderLayout.NORTH);

        // ==================== CENTER PANEL ====================
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de Pesquisa
        JPanel searchPanel = new JPanel(new BorderLayout(8, 0));

        campoTexto = new JTextField();
        campoTexto.setPreferredSize(new Dimension(650, 38));
        campoTexto.setFont(new Font("Arial", Font.PLAIN, 14));

        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setPreferredSize(new Dimension(130, 38));
        btnPesquisar.setFont(new Font("Arial", Font.BOLD, 13));

        searchPanel.add(campoTexto, BorderLayout.CENTER);
        searchPanel.add(btnPesquisar, BorderLayout.EAST);

        centerPanel.add(searchPanel, BorderLayout.NORTH);

        // Lista de Elementos
        listModel = new DefaultListModel<>();
        listaElementos = new JList<>(listModel);
        listaElementos.setFont(new Font("Arial", Font.PLAIN, 14));
        listaElementos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaElementos);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Lista de elementos",
                0, 0,
                new Font("Arial", Font.BOLD, 13)
        ));

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // ==================== RIGHT PANEL ====================
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        rightPanel.setPreferredSize(new Dimension(160, 0));

        String[] nomesBotoes = {
                "Check-In",
                "Check-Out",
                "AddContato",
                "AddResponsavel",
                "AddRestrições",
                "AddKid"
        };

// Cria os 4 botões com funções diferentes
        for (int i = 0; i < nomesBotoes.length; i++) {
            JButton btn = new JButton(nomesBotoes[i]);
            btn.setMaximumSize(new Dimension(140, 45));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setFocusable(false);

            // Define ação diferente para cada botão
            final int indice = i; // necessário por causa do lambda

            btn.addActionListener(e -> acaoBotao(indice));

            rightPanel.add(btn);
            rightPanel.add(Box.createRigidArea(new Dimension(0, 12)));
            if (i == 1 || i == 4){
                rightPanel.add(Box.createVerticalStrut(75));
            }
        }

        add(rightPanel, BorderLayout.EAST);
        getRootPane().setDefaultButton(btnPesquisar);

        // ==================== ACTION ====================
        btnPesquisar.addActionListener(this::realizarPesquisa);
    }

    private void realizarPesquisa(ActionEvent e) {
        String termo = campoTexto.getText().trim();

        if (termo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Digite um termo para pesquisar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Limpa lista
        listModel.clear();

        // Busca os resultados
        List<String> resultados = buscarElementos(termo);

        // Preenche a lista
        for (String item : resultados) {
            listModel.addElement(item);
        }

        if (resultados.isEmpty()) {
            listModel.addElement("Nenhum resultado encontrado para: " + termo);
        }

        // ==================== Atualiza os textos das Informações ====================
        atualizarInformacoes(resultados);
    }

    /**
     * Atualiza cada "InformaçãoX" com o primeiro item da lista
     */
    private void atualizarInformacoes(List<String> resultados) {
        if (resultados == null || resultados.isEmpty()) {
            for (JLabel label : infoLabels) {
                label.setText("Sem resultado");
            }
            return;
        }

        String primeiroItem = resultados.get(0);

        for (JLabel label : infoLabels) {
            label.setText(primeiroItem);   // Todos recebem o mesmo primeiro item
        }
    }

    /**
     * Função de busca - pode ser substituída pela sua lógica real
     */
    private List<String> buscarElementos(String termo) {
        List<String> resultados = new ArrayList<>();

        // Simulação de dados
        String[] exemplos = {
                "Cliente: " + termo.toUpperCase(),
                "Produto encontrado: " + termo,
                "Registro ID-2026-" + termo.hashCode() % 10000,
                "Categoria: Tecnologia - " + termo,
                "Documento: Contrato_" + termo.replace(" ", "_")
        };

        for (int i = 0; i < 6; i++) {
            if (Math.random() > 0.25) {
                resultados.add(exemplos[i % exemplos.length]);
            }
        }

        return resultados;
    }
}