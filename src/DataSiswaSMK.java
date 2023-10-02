import org.w3c.dom.CDATASection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSiswaSMK extends JDialog {
    private JPanel contentPane;
    private JTextField text_NISN;
    private JTextField text_NAMA;
    private JComboBox comboBox_KELAS;
    private JTextField text_ALAMAT;
    private JTextField text_EMAIL;
    private JTextField text_NOMORHP;
    private JTable table1;
    private JButton TAMBAHButton;
    private JButton SIMPANButton;
    private JButton UBAHButton;
    private JButton HAPUSButton;
    private JButton BATALButton;
    private JButton KELUARButton;
    private JScrollPane FormTablle;
    private JButton buttonOK;
    private JButton buttonCancel;

    public void TampilData() throws SQLException {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nisn");
        model.addColumn("Nama");
        model.addColumn("kelas");
        model.addColumn("Alamat");
        model.addColumn("Email");
        model.addColumn("Nomor HP");

        try {
            int no = 1;
            String sql = "SELECT * from siswa" ;
            java.sql.Connection koneksi = KoneksiDB.ConnDB();
            java.sql.Statement pernyataan = koneksi.createStatement();
            java.sql.ResultSet tampil = pernyataan.executeQuery(sql);
            while (tampil.next()) {
                model.addRow(new Object[]{no++, tampil.getString(1),
                        tampil.getString(2), tampil.getString(3),
                        tampil.getString(4), tampil.getString(5),
                        tampil.getString(6),});
            }
            table1.setModel(model);
        } catch (SQLException e) {
            System.out.println("error : " + e.getMessage());
        }
    }

    private void kosongkanForm() {
        text_NISN.setText(null);
        text_NISN.setEditable(true);
        text_NAMA.setText(null);
        text_ALAMAT.setText(null);
        text_EMAIL.setText(null);
        text_NOMORHP.setText(null);
        comboBox_KELAS.getSelectedItem();
    }

    public DataSiswaSMK() throws SQLException, RuntimeException {
        setContentPane(contentPane);
        try {
            TampilData();
            kosongkanForm();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);



        TAMBAHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Mengosongkan data!", "error", JOptionPane.ERROR_MESSAGE);
                kosongkanForm();
            }
        });
        SIMPANButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (text_ALAMAT.getText().isEmpty()||
                            text_EMAIL.getText().isEmpty()||
                            text_NAMA.getText().isEmpty()||
                            text_NOMORHP.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Isi lah dulu bang!");
                        return;
                    }
                    int  jawaban = JOptionPane.showConfirmDialog(null,"Sudah yakin dengan data anda?");
                    if (jawaban == JOptionPane.YES_NO_OPTION) {
                        String sql = "INSERT INTO siswa VALUES ('" + text_NISN.getText() +
                                "','" + text_NAMA.getText() +
                                "','" + comboBox_KELAS.getSelectedItem() +
                                "','" + text_ALAMAT.getText() +
                                "','" + text_EMAIL.getText() +
                                "','" + text_NOMORHP.getText() + "')";
                        Connection conn = KoneksiDB.ConnDB();
                        java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
                        pstm.execute();
                        JOptionPane.showMessageDialog(null, "Berhasil di simpan");
                        TampilData();
                        kosongkanForm();
                    }

                }catch (HeadlessException | SQLException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }

            }
        });


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int baris = table1.rowAtPoint(e.getPoint());
                String nisn = table1.getValueAt(baris,1).toString();
                text_NISN.setText(nisn);
                String nama = table1.getValueAt(baris, 2).toString();
                text_NAMA.setText(nama);
                String kelas = table1.getValueAt(baris, 3).toString();
                comboBox_KELAS.setSelectedItem(kelas);
                String alamat = table1.getValueAt(baris, 4).toString();
                text_ALAMAT.setText(alamat);
                String email = table1.getValueAt(baris, 5).toString();
                text_EMAIL.setText(email);
                String nomorhp = table1.getValueAt(baris, 6).toString();
                text_NOMORHP.setText(nomorhp);
            }
        });
        UBAHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        try {
                            if (text_ALAMAT.getText().isEmpty()||
                                    text_EMAIL.getText().isEmpty()||
                                    text_NAMA.getText().isEmpty()||
                                    text_NOMORHP.getText().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Pilih lah dulu bang!");
                                return;
                            }
                            int  jawaban = JOptionPane.showConfirmDialog(null,"Sudah yakin dengan data anda?");
                            if (jawaban == JOptionPane.YES_NO_OPTION) {
                                String sql = "UPDATE siswa set nisn = '" + text_NISN.getText() + "',nama = '" +text_NAMA.getText() + "',kelas='" + comboBox_KELAS.getSelectedItem() + "', alamat = '" + text_ALAMAT.getText() + "', email = '" + text_EMAIL.getText() + "', nomor_hp = '" + text_NOMORHP.getText() + "'where nisn = '" + text_NISN.getText() + "'";
                                Connection conn = KoneksiDB.ConnDB();
                                java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
                                pstm.execute();
                                JOptionPane.showMessageDialog(null, "Berhasil di simpan");
                                TampilData();
                                kosongkanForm();
                            }

                        }catch (HeadlessException | SQLException exception){
                            JOptionPane.showMessageDialog(null,exception.getMessage());
                        }

            }
        });
        HAPUSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (text_ALAMAT.getText().isEmpty()||
                                        text_EMAIL.getText().isEmpty()||
                                        text_NAMA.getText().isEmpty()||
                                        text_NOMORHP.getText().isEmpty()){
                                    JOptionPane.showMessageDialog(null,"Pilih dulu lah bang!");
                                    return;
                                }
                                int  jawaban = JOptionPane.showConfirmDialog(null,"Apakah anda sudah yakin ingin menghapus data? ");
                                if (jawaban == JOptionPane.YES_NO_OPTION) {
                                    String sql = "DELETE FROM siswa where nisn = '" + text_NISN.getText() + "'";
                                    Connection conn = KoneksiDB.ConnDB();
                                    java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
                                    pstm.execute();
                                    JOptionPane.showMessageDialog(null, "Proses hapus data sudah dilakukan,terimaksih)");
                                    TampilData();
                                    kosongkanForm();
                                }

                            }catch (HeadlessException | SQLException exception){
                                JOptionPane.showMessageDialog(null,exception.getMessage());
                            }

                        }
                    });
        BATALButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int  jawaban = JOptionPane.showConfirmDialog(null,"Batal?");
                    if (jawaban == JOptionPane.YES_NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "Batal berhasil dilakukan");
                        TampilData();
                        kosongkanForm();
                    }

                }catch (HeadlessException | SQLException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }

            }
        });
        KELUARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int  jawaban = JOptionPane.showConfirmDialog(null,"Apakah yakin anda ingin keluar?");
                    if (jawaban == JOptionPane.YES_NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "Terimakasih sudah berkunjung:)");
                        TampilData();
                        kosongkanForm();
                        System.exit(1);
                    }
                }catch (HeadlessException | SQLException exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }

            }
        });
            }
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            System.out.println(info.getClassName());
        }
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        DataSiswaSMK dialog = new DataSiswaSMK();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
