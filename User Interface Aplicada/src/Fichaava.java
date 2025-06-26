import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Fichaava extends JFrame {

    // JTEXTFIELDS
    JTextField codigoField;
    JTextField nomeField;
    JTextArea txtCurriculo;

    // RADIOBUTTONS , checkboxes
    JRadioButton checkboxFeminino;
    JRadioButton checkboxMasculino;
    ButtonGroup sexoGroup;

    // ComboBoxes
    JComboBox<String> interesseComboBox;
    JComboBox<String> atuacaoComboBox;

    // Botões
    JButton salvarButton;
    JButton novoButton;
    JButton cancelarButton;
    JButton anteriorButton;
    JButton proximoButton;

    // Itens de Menu
    JMenuItem salvarMenuItem;
    JMenuItem sairMenuItem;


    public Fichaava() {
        //frame
        super("Arquivo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 650);
        setLocationRelativeTo(null);

        // Menubar
        JMenuBar menuBar = new JMenuBar();
        JMenu arquivoMenu = new JMenu("Arquivo");
        JMenu enviarSubMenu = new JMenu("Enviar");
        enviarSubMenu.add(new JMenuItem("email"));//email e nem impressora não vão ser usados então sem variáveis pra eles
        enviarSubMenu.add(new JMenuItem("impressora"));
        //adicionar o submenu enviar no menu
        arquivoMenu.add(enviarSubMenu);
        salvarMenuItem = new JMenuItem("Salvar");
        sairMenuItem = new JMenuItem("Sair");
        arquivoMenu.add(salvarMenuItem);
        arquivoMenu.add(sairMenuItem);
        //adicionamos tudo que tinha que colocar, podemos finalmente adicionar no menuBar
        menuBar.add(arquivoMenu);
        setJMenuBar(menuBar);
        //Painel principal
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel labelTitulo = new JLabel("Ficha de Avaliação");//Criando o titulo
        labelTitulo.setFont(new Font("Arial", Font.ITALIC, 16)); // fonte bonitinha
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);//alinhamento do boxlayout
        formContainer.add(labelTitulo);//adiciona no painel principal
        formContainer.add(Box.createVerticalStrut(15));

        // Campo "Código" (Feito do jeito dificil)
        JLabel codigoLabel = new JLabel("Código:");
        codigoField = new JTextField(15);
        JPanel painelLinhaCodigo = new JPanel();
        painelLinhaCodigo.setLayout(new FlowLayout(FlowLayout.LEFT));//pra ele ficar a esquerda da barra espero eu
        painelLinhaCodigo.add(codigoLabel);
        painelLinhaCodigo.add(codigoField);
        formContainer.add(painelLinhaCodigo);

        // Campo "Nome" como o campo padrão
        nomeField = new JTextField();
        formContainer.add(createLabelAndFieldPanel("Nome:", nomeField));

        // Campo "Sexo"
        JPanel sexoPanel = new JPanel(new BorderLayout(5, 5));
        sexoPanel.add(new JLabel("Sexo:"), BorderLayout.WEST);
        //crio os botao radio
        checkboxFeminino = new JRadioButton("Feminino");
        checkboxMasculino = new JRadioButton("Masculino");
        //o grupo onde eles vao ficar
        sexoGroup = new ButtonGroup();
        sexoGroup.add(checkboxFeminino);
        sexoGroup.add(checkboxMasculino);
        //crio o painel menor pra colocar eles
        JPanel checkBox = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        checkBox.add(checkboxFeminino);
        checkBox.add(checkboxMasculino);
        //adiciono no maior
        sexoPanel.add(checkBox, BorderLayout.CENTER);
        sexoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, sexoPanel.getPreferredSize().height));
        formContainer.add(sexoPanel);
        formContainer.add(Box.createVerticalStrut(10));

        // Campo "Curriculo"
        JPanel curriculoPanel = new JPanel(new BorderLayout());
        //titulozinho
        curriculoPanel.add(new JLabel("Curriculo"), BorderLayout.NORTH);
        txtCurriculo = new JTextArea(10, 20);//caixa de texto
        curriculoPanel.add(new JScrollPane(txtCurriculo), BorderLayout.CENTER);//scroll
        formContainer.add(curriculoPanel);
        formContainer.add(Box.createVerticalStrut(10));

        // Seção "Áreas"
        JPanel areasSectionPanel = new JPanel(new BorderLayout());
        areasSectionPanel.add(new JLabel("Áreas"), BorderLayout.NORTH);
        JPanel areasFieldsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        areasFieldsPanel.add(new JLabel("Interesse:"));
        interesseComboBox = new JComboBox<>(new String[]{"Desenvolvedor", "Analista de Sistemas", "Gerente de Projetos"});
        areasFieldsPanel.add(interesseComboBox);
        areasFieldsPanel.add(new JLabel("Atuação:"));
        atuacaoComboBox = new JComboBox<>(new String[]{"Programação", "Banco de Dados", "Redes", "Segurança da Informação"});
        areasFieldsPanel.add(atuacaoComboBox);
        areasSectionPanel.add(areasFieldsPanel, BorderLayout.CENTER);
        areasSectionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, areasSectionPanel.getPreferredSize().height));
        formContainer.add(areasSectionPanel);

        // --- Painel de Botões ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        novoButton = new JButton("Novo");
        cancelarButton = new JButton("Cancelar");
        salvarButton = new JButton("Salvar");
        anteriorButton = new JButton("Anterior");
        proximoButton = new JButton("Proximo");

        buttonPanel.add(novoButton);
        buttonPanel.add(cancelarButton);
        buttonPanel.add(salvarButton);
        buttonPanel.add(anteriorButton);
        buttonPanel.add(proximoButton);

        // action listeners
        // expressão direta menos o salvarButton que fiz do jeito mais padrão já que a IntelliJ completa

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarFicha();
            }
        });

        salvarMenuItem.addActionListener(e -> salvarFicha());

        novoButton.addActionListener(e -> limparFormulario());

        cancelarButton.addActionListener(e -> sairDoPrograma());
        sairMenuItem.addActionListener(e -> sairDoPrograma());

        anteriorButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Função 'Anterior' não implementada."));
        proximoButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Função 'Próximo' não implementada."));


        // Adição dos Painéis ao Frame Principal
        getContentPane().add(formContainer, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    //Frame padrão
    private JPanel createLabelAndFieldPanel(String labelText, JComponent component) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(new JLabel(labelText), BorderLayout.WEST);
        panel.add(component, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel.getPreferredSize().height));
        return panel;
    }
    //salvar os dados do campo em variaveis pra trabalhar depois
    private void salvarFicha() {
        // Leitura dos valores dos componentes
        String codigo = codigoField.getText();
        String nome = nomeField.getText();
        String curriculum = txtCurriculo.getText();
        String interesse = (String) interesseComboBox.getSelectedItem();
        String atuacao = (String) atuacaoComboBox.getSelectedItem();

        // Validação simples para o campo nome
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo 'Nome' é obrigatório.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return; // Interrompe a execução do método
        }

        String sexo;
        if (checkboxFeminino.isSelected()) {
            sexo = "Feminino";
        } else if (checkboxMasculino.isSelected()) {
            sexo = "Masculino";
        } else {
            sexo = "Não selecionado";
        }

        // Monta a string com os dados para exibição
        StringBuilder dados = new StringBuilder();
        dados.append("--- DADOS DA FICHA SALVA ---\n\n");
        dados.append("Código: ").append(codigo).append("\n");
        dados.append("Nome: ").append(nome).append("\n");
        dados.append("Sexo: ").append(sexo).append("\n");
        dados.append("Área de Interesse: ").append(interesse).append("\n");
        dados.append("Área de Atuação: ").append(atuacao).append("\n\n");
        dados.append("Currículo:\n").append(curriculum);

        // Exibe os dados em um JOptionPane
        JOptionPane.showMessageDialog(this, dados.toString(), "Ficha Salva com Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    //limpa os campos
    private void limparFormulario() {
        codigoField.setText("");
        nomeField.setText("");
        txtCurriculo.setText("");
        sexoGroup.clearSelection();
        interesseComboBox.setSelectedIndex(0);
        atuacaoComboBox.setSelectedIndex(0);
        JOptionPane.showMessageDialog(this, "Formulário limpo. Pronto para nova entrada.", "Novo", JOptionPane.INFORMATION_MESSAGE);
    }

    //confirmação pra fechar o programa
    private void sairDoPrograma() {
        int confirmar = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente fechar o programa?",
                "Confirmação de Saída",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmar == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        Fichaava ficha = new Fichaava();
        ficha.setVisible(true);
    }
}