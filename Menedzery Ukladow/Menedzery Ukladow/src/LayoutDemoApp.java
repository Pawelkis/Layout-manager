
import javax.swing.*;
import java.awt.*;

public class LayoutDemoApp extends JFrame {

    public LayoutDemoApp() {
        setTitle("LayoutDemoApp");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 5, 5));

        JButton b1 = new JButton("FlowLayout");
        JButton b2 = new JButton("BorderLayout");
        JButton b3 = new JButton("GridLayout");
        JButton b4 = new JButton("BoxLayout");
        JButton b5 = new JButton("CardLayout");
        JButton b6 = new JButton("GridBagLayout");

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);

        b1.addActionListener(e -> flow());
        b2.addActionListener(e -> border());
        b3.addActionListener(e -> grid());
        b4.addActionListener(e -> box());
        b5.addActionListener(e -> card());
        b6.addActionListener(e -> gridBag());
    }

    void flow() {
        JFrame f = new JFrame("FlowLayout");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(400, 200);
        f.setLayout(new FlowLayout());

        f.add(new JLabel("Elementy obok siebie:"));
        f.add(new JButton("Przycisk 1"));
        f.add(new JButton("Przycisk 2"));
        f.add(new JButton("Przycisk 3"));

        f.setLocationRelativeTo(this);
        f.setVisible(true);
    }

    void border() {
        JFrame f = new JFrame("BorderLayout");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(400, 300);
        f.setLayout(new BorderLayout());

        f.add(new JButton("GORA"), BorderLayout.NORTH);
        f.add(new JButton("DOL"), BorderLayout.SOUTH);
        f.add(new JButton("LEWO"), BorderLayout.WEST);
        f.add(new JButton("PRAWO"), BorderLayout.EAST);
        f.add(new JButton("SRODEK"), BorderLayout.CENTER);

        f.setLocationRelativeTo(this);
        f.setVisible(true);
    }

    void grid() {
        JFrame f = new JFrame("GridLayout");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(400, 300);
        f.setLayout(new GridLayout(3, 3, 5, 5));

        for (int i = 1; i <= 9; i++) {
            f.add(new JButton("Przycisk " + i));
        }

        f.setLocationRelativeTo(this);
        f.setVisible(true);
    }

    void box() {
        JFrame f = new JFrame("BoxLayout");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(300, 300);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        p.add(new JLabel("Elementy jeden pod drugim"));
        p.add(Box.createVerticalStrut(10));
        p.add(new JButton("Przycisk 1"));
        p.add(Box.createVerticalStrut(10));
        p.add(new JButton("Przycisk 2"));
        p.add(Box.createVerticalStrut(10));
        p.add(new JButton("Przycisk 3"));

        f.add(p);
        f.setLocationRelativeTo(this);
        f.setVisible(true);
    }

    void card() {
        JFrame f = new JFrame("CardLayout");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(300, 200);

        CardLayout cl = new CardLayout();
        JPanel p = new JPanel(cl);

        p.add(new JLabel("Karta 1", JLabel.CENTER), "1");
        p.add(new JLabel("Karta 2", JLabel.CENTER), "2");
        p.add(new JLabel("Karta 3", JLabel.CENTER), "3");

        JButton b = new JButton("Nastepna karta");
        b.addActionListener(e -> cl.next(p));

        f.add(p, BorderLayout.CENTER);
        f.add(b, BorderLayout.SOUTH);

        f.setLocationRelativeTo(this);
        f.setVisible(true);
    }

    void gridBag() {
        JFrame f = new JFrame("GridBagLayout");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(400, 250);

        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.gridy = 0;
        p.add(new JLabel("Login:"), c);

        c.gridx = 1;
        p.add(new JTextField(12), c);

        c.gridx = 0;
        c.gridy = 1;
        p.add(new JLabel("Haslo:"), c);

        c.gridx = 1;
        p.add(new JPasswordField(12), c);

        c.gridx = 0;
        c.gridy = 2;
        p.add(new JButton("Zaloguj"), c);

        c.gridx = 1;
        p.add(new JButton("Anuluj"), c);

        f.add(p);
        f.setLocationRelativeTo(this);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LayoutDemoApp().setVisible(true);
        });
    }
}
