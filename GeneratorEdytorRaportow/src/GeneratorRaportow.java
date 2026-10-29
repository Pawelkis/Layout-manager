import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeneratorRaportow extends JFrame {

    private JTextArea textArea;
    private JButton generateButton;
    private JProgressBar progressBar;

    public GeneratorRaportow() {
        setTitle("INF.04 - Generator Raportów");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Plik");

        JMenuItem saveItem = new JMenuItem("Zapisz Raport");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveItem.addActionListener(e -> zapiszRaport());

        JMenuItem exitItem = new JMenuItem("Wyjście");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        generateButton = new JButton("Generuj Raport w Tle");
        generateButton.addActionListener(e -> uruchomGenerowanie());

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        bottomPanel.add(generateButton);
        bottomPanel.add(progressBar);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void uruchomGenerowanie() {
        generateButton.setEnabled(false);
        progressBar.setValue(0);
        textArea.setText("");

        SwingWorker<Void, String> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(300);
                    publish("Krok " + i + ": Wygenerowano sekcję danych raportu nr " + i + "\n");
                    setProgress(i * 10);
                }
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                for (String wiersz : chunks) {
                    textArea.append(wiersz);
                }
            }

            @Override
            protected void done() {
                textArea.append("--- Zakończono generowanie raportu ---\n");
                generateButton.setEnabled(true);
            }
        };

        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((Integer) evt.getNewValue());
            }
        });

        worker.execute();
    }

    private void zapiszRaport() {
        String zawartosc = textArea.getText();

        if (zawartosc.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pole raportu jest puste! Wygeneruj lub wpisz tekst przed zapisem.",
                    "Ostrzeżenie",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Zapisz Raport do pliku");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".txt");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(zawartosc);
                JOptionPane.showMessageDialog(
                        this,
                        "Raport został pomyślnie zapisany do pliku:\n" + fileToSave.getAbsolutePath(),
                        "Sukces",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Wystąpił błąd podczas zapisu pliku: " + ex.getMessage(),
                        "Błąd",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GeneratorRaportow app = new GeneratorRaportow();
            app.setVisible(true);
        });
    }
}