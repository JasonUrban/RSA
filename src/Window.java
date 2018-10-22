import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.util.Random;

class Window extends JFrame {
    private JTextArea sourceText;
    private JTextArea outputText;
    private JCheckBox keyByDefault;
    private JSpinner p;
    private JSpinner q;
    private JSpinner e;
    private JButton encryptButton;
    private JButton decryptButton;

    Window(String name) {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel panel = new MyPanel();
        panel.setLayout(null);
        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");
        JButton clearButton = new JButton("Clear fields");
        JLabel sourceLabel = new JLabel("Input text for encryption here:");
        JLabel outLabel = new JLabel("Your encrypted data:");
        JLabel pLabel = new JLabel("Input prime number p:");
        JLabel qLabel = new JLabel("Input prime number q:");
        JLabel eLabel = new JLabel("Choose e parameter:");
        sourceText = new JTextArea();
        outputText = new JTextArea();
        SpinnerModel model = new SpinnerNumberModel(1009, 1009, 9973, 1);
        p = new JSpinner(model);
        model = new SpinnerNumberModel(1009, 1009, 9973, 1);
        q = new JSpinner(model);
        model = new SpinnerNumberModel(1009, 1000, 1000000000, 1);
        e = new JSpinner(model);
        p.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e1) {
                e.setModel(new SpinnerNumberModel((int) e.getValue(), 1009, ((int) p.getValue() - 1) * ((int) q.getValue() - 1), 1));
            }
        });
        q.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e1) {
                e.setModel(new SpinnerNumberModel((int) e.getValue(), 1009, ((int) p.getValue() - 1) * ((int) q.getValue() - 1), 1));
            }
        });
        JRadioButton encrypt = new JRadioButton("Encrypt");
        JRadioButton decrypt = new JRadioButton("Decrypt");
        keyByDefault = new JCheckBox("Key by default");
        ButtonGroup group = new ButtonGroup();
        JScrollPane sourceTextScrollPane = new JScrollPane(sourceText);
        JScrollPane outputTextScrollPane = new JScrollPane(outputText);
        JLabel image = new JLabel(new ImageIcon(System.getProperty("user.dir") + "\\src\\key.png"));
        image.setSize(175, 200);
        image.setLocation(50, 150);
        encrypt.setSize(150, 50);
        encrypt.setLocation(150, 50);
        decrypt.setSize(150, 50);
        decrypt.setLocation(150, 100);
        keyByDefault.setSize(150, 50);
        keyByDefault.setLocation(50, 75);
        encryptButton.setSize(150, 50);
        encryptButton.setLocation(110, 390);
        encryptButton.setBackground(new Color(100, 200, 225));
        decryptButton.setSize(150, 50);
        decryptButton.setLocation(110, 390);
        decryptButton.setBackground(new Color(100, 200, 225));
        clearButton.setSize(150, 50);
        clearButton.setBackground(new Color(225, 100, 100));
        clearButton.setLocation(890, 390);
        sourceTextScrollPane.setSize(400, 300);
        sourceTextScrollPane.setLocation(250, 50);
        outputTextScrollPane.setSize(400, 300);
        outputTextScrollPane.setLocation(700, 50);
        pLabel.setSize(175, 25);
        pLabel.setLocation(50, 125);
        p.setSize(175, 50);
        p.setLocation(50, 150);
        qLabel.setSize(175, 25);
        qLabel.setLocation(50, 200);
        q.setSize(175, 50);
        q.setLocation(50, 225);
        eLabel.setSize(175, 25);
        eLabel.setLocation(50, 275);
        e.setSize(175, 50);
        e.setLocation(50, 300);
        sourceLabel.setSize(200, 25);
        sourceLabel.setLocation(250, 25);
        outLabel.setSize(200, 25);
        outLabel.setLocation(700, 25);
        encryptButton.setContentAreaFilled(false);
        decryptButton.setContentAreaFilled(false);
        clearButton.setContentAreaFilled(false);
        encryptButton.setOpaque(true);
        decryptButton.setOpaque(true);
        clearButton.setOpaque(true);
        encrypt.setOpaque(false);
        decrypt.setOpaque(false);
        keyByDefault.setOpaque(false);
        outputText.setLineWrap(true);
        sourceText.setLineWrap(true);
        keyByDefault.setSelected(true);
        encryptButton.setVisible(true);
        decryptButton.setVisible(false);
        encrypt.setSelected(true);
        encryptButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        decryptButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sourceText.setFont(new Font("System Regular", Font.PLAIN, 16));
        outputText.setFont(new Font("System Regular", Font.PLAIN, 16));
        p.setFont(new Font("System Regular", Font.PLAIN, 16));
        q.setFont(new Font("System Regular", Font.PLAIN, 16));
        e.setFont(new Font("System Regular", Font.PLAIN, 16));
        panel.add(sourceLabel);
        panel.add(outLabel);
        panel.add(pLabel);
        panel.add(qLabel);
        panel.add(eLabel);
        panel.add(clearButton);
        panel.add(sourceTextScrollPane);
        panel.add(outputTextScrollPane);
        panel.add(p);
        panel.add(q);
        panel.add(e);
        panel.add(image);
        image.setVisible(true);
        group.add(encrypt);
        group.add(decrypt);
        panel.add(encrypt);
        panel.add(decrypt);
        panel.add(keyByDefault);
        panel.add(encryptButton);
        panel.add(decryptButton);
        outputText.setWrapStyleWord(true);
        sourceText.setWrapStyleWord(true);
        outputText.setEditable(true);
        p.setVisible(false);
        q.setVisible(false);
        e.setVisible(false);
        pLabel.setVisible(false);
        qLabel.setVisible(false);
        eLabel.setVisible(false);
        setContentPane(panel);
        setSize(1150, 500);
        keyByDefault.addActionListener(e1 -> {
            if (p.isVisible()) {
                p.setValue(1009);
                q.setValue(1009);
                e.setValue(0);
            }
            pLabel.setVisible(!pLabel.isVisible());
            qLabel.setVisible(!qLabel.isVisible());
            eLabel.setVisible(!eLabel.isVisible());
            p.setVisible(!p.isVisible());
            q.setVisible(!q.isVisible());
            e.setVisible(!e.isVisible());
        });
        encrypt.addActionListener(e -> {
            encryptButton.setVisible(true);
            decryptButton.setVisible(false);
            sourceLabel.setText("Input text for encryption here:");
            outLabel.setText("Your encrypted data:");
            if (!sourceText.getText().equals("") && !outputText.getText().equals("")) {
                String temp = sourceText.getText();
                sourceText.setText(outputText.getText());
                outputText.setText(temp);
            }
        });
        decrypt.addActionListener(e -> {
            encryptButton.setVisible(false);
            decryptButton.setVisible(true);
            sourceLabel.setText("Input text for decryption here:");
            outLabel.setText("Your decrypted data:");
            if (!sourceText.getText().equals("") && !outputText.getText().equals("")) {
                String temp = sourceText.getText();
                sourceText.setText(outputText.getText());
                outputText.setText(temp);
            }
        });
        clearButton.addActionListener(e1 -> {
            sourceText.setText("");
            outputText.setText("");
            p.setValue(1009);
            q.setValue(1009);
            e.setValue(0);
        });
        encryptButton.addActionListener(e -> translate());
        decryptButton.addActionListener(e -> translate());
    }

    public class MyPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(255, 242, 130);
            Color color2 = new Color(255, 114, 0);
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }

    private void translate() {
        BigInteger[] key = new BigInteger[2];
        if (keyByDefault.isSelected()) {
            long p = 0, q = 0;
            Random rand = new Random();
            while (!Algorithm.isPrime(new BigInteger(Long.toString(p))) || p < 1000) {
                p = rand.nextInt();
            }
            while (!Algorithm.isPrime(new BigInteger(Long.toString(q))) || q < 1000) {
                q = rand.nextInt();
            }
            key[0] = new BigInteger(Long.toString(p));
            key[1] = new BigInteger(Long.toString(q));
        } else {
            key[0] = new BigInteger(p.getValue().toString());
            key[1] = new BigInteger(q.getValue().toString());
            if (!Algorithm.isPrime(key[0]) || !Algorithm.isPrime(key[1])) {
                JOptionPane.showMessageDialog(Window.this, "Numbers are not prime integers!\n" +
                                "Try once again...",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            BigInteger eValue = new BigInteger(e.getValue().toString());
            if (!Algorithm.relativelyPrime(key[0].subtract(BigInteger.ONE).multiply(key[1].subtract(BigInteger.ONE)), eValue)) {
                JOptionPane.showMessageDialog(Window.this, "E is not relatively prime to φ!\n" +
                                "Try once again...",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        outputText.setText(Algorithm.RSAAlgorithm(sourceText.getText(), key));
    }
}
