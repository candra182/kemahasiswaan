/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frm_mhs.java
 *
 * Created on Jun 1, 2015, 1:20:59 PM
 */
package kemahasiswaan_10113112_10113135;
import javax.swing.*;
//Fungsi Import yang digunakan untuk SQL
import java.sql.*;
//Fungsi Import yang digunakan untuk Tanggal
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Eggys
 */
public class frm_mhs extends javax.swing.JFrame {
    //deklarasi Variabel 
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;
    String batal_nim = null;
    /** Creates new form frm_mhs */
    public frm_mhs() {
        initComponents();
        nonaktif_teks();
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        tabel_mahasiswa.setModel(tableModel);
        settableload();
        btn_ubah.setEnabled(false);
        btn_hapus.setEnabled(false);
        btn_simpan.setEnabled(false);
        btn_batal.setEnabled(false);
    }
    
    private javax.swing.table.DefaultTableModel tableModel= getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel() {
        //membuat Judul header 
        return new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String [] {"NIM",
                               "Nama Mahasiswa",
                               "Tempat Lahir",
                               "Tanggal Lahir",
                               "Alamat"
                              }
                )
                //disable perubahan pada grid
        {
            boolean[] canEdit = new boolean[]
            {
                false, false, false, false, false
            }; 
            
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
        };
    }
    String data[]= new String[5];
    private void settableload()
    {
        String stat ="";
        try{
           Class.forName(driver);
                    Connection kon = DriverManager.getConnection(
                            database,
                            user,
                            pass); 
                    
                    Statement stt=kon.createStatement();
                    String SQL = "select * from t_mahasiswa";
                    ResultSet res = stt.executeQuery(SQL);
                    while (res.next()){
                        data[0] = res.getString(1);
                        data[1] = res.getString(2);
                        data[2] = res.getString(3);
                        data[3] = res.getString(4);
                        data[4] = res.getString(5);
                        tableModel.addRow(data);
                    }
                    res.close();
                    stt.close();
                    kon.close();
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            
            System.exit(0);
        }
    }
    public void membersihkan_teks()
    {
        txt_nim.setText("");
        txt_nama.setText("");
        jDateChooser1.setDate(null);
        txt_tmpt_lahir.setText("");
        txt_alamat.setText("");
    }
    public void nonaktif_teks()
    {
        txt_nim.setEnabled(false);
        txt_nama.setEnabled(false);
        jDateChooser1.setEnabled(false);
        txt_tmpt_lahir.setEnabled(false);
        txt_alamat.setEnabled(false);
    }
    public void aktif_teks()
    {
        txt_nim.setEnabled(true);
        txt_nama.setEnabled(true);
        jDateChooser1.setEnabled(true);
        txt_tmpt_lahir.setEnabled(true);
        txt_alamat.setEnabled(true);
    }
    
    int row = 0;
    public void tampil_field()
    {
        row = tabel_mahasiswa.getSelectedRow();
        txt_nim.setText(tableModel.getValueAt(row, 0).toString());
        txt_nama.setText(tableModel.getValueAt(row, 1).toString());
        txt_tmpt_lahir.setText(tableModel.getValueAt(row, 2).toString());
        txt_alamat.setText(tableModel.getValueAt(row, 4).toString());
        
        try
        {
            String data1[]= new String[1];
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "select tgl_lahir from t_mahasiswa WHERE nim = \'"+txt_nim.getText()+"\'";
            ResultSet res = stt.executeQuery(SQL);
            if(res.next())
            {
                Date tgl_lahir = res.getDate(1);
                jDateChooser1.setDate(tgl_lahir);
            }            
            res.close();
            stt.close();
            kon.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        
        btn_simpan.setEnabled(false);
        btn_ubah.setEnabled(true);
        btn_hapus.setEnabled(true);
        btn_batal.setEnabled(false);
        aktif_teks();
        
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_keluar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_tmpt_lahir = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_nim = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_mahasiswa = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btn__tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_tampil = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_keluar.setFont(new java.awt.Font("Vijaya", 0, 18));
        btn_keluar.setText("Kembali");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 550, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 0, 0), null, null), "Pencarian Data", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel2.setFont(new java.awt.Font("Vijaya", 0, 18));

        jLabel8.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel8.setText("Masukkan Data");

        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(8, 8, 8)
                .addComponent(txt_cari, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 340, 70));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Input"));

        txt_alamat.setColumns(20);
        txt_alamat.setRows(5);
        jScrollPane1.setViewportView(txt_alamat);

        jLabel5.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel5.setText("Alamat               :");

        jLabel6.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel6.setText("  ");

        txt_tmpt_lahir.setName(""); // NOI18N

        txt_nama.setName(""); // NOI18N

        jLabel4.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel4.setText("Tempat Lahir      :");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel3.setText("Nama                 :");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel2.setText("NIM                   :");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txt_nim.setName(""); // NOI18N

        jLabel7.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel7.setText("Tanggal Lahir     :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nim, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(txt_nama, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(txt_tmpt_lahir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_tmpt_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, 260));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Output"));

        tabel_mahasiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_mahasiswa.setToolTipText("");
        tabel_mahasiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_mahasiswaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabel_mahasiswa);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 460, 260));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Proses"));

        btn__tambah.setFont(new java.awt.Font("Vijaya", 0, 18));
        btn__tambah.setText("Tambah");
        btn__tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn__tambahActionPerformed(evt);
            }
        });

        btn_ubah.setFont(new java.awt.Font("Vijaya", 0, 18));
        btn_ubah.setText("Ubah");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_hapus.setFont(new java.awt.Font("Vijaya", 0, 18));
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_simpan.setFont(new java.awt.Font("Vijaya", 0, 18));
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_batal.setFont(new java.awt.Font("Vijaya", 0, 18));
        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn__tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(873, 873, 873)
                .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_hapus)
                    .addComponent(btn_ubah)
                    .addComponent(btn__tambah)
                    .addComponent(btn_simpan)
                    .addComponent(btn_batal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, 480, 70));

        btn_tampil.setFont(new java.awt.Font("Vijaya", 0, 18));
        btn_tampil.setText("Tampilkan Keseluruhan Data");
        btn_tampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampilActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 550, -1, -1));

        jLabel1.setBackground(new java.awt.Color(51, 204, 255));
        jLabel1.setFont(new java.awt.Font("Vijaya", 1, 24));
        jLabel1.setForeground(new java.awt.Color(51, 204, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM MAHASISWA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 244, 52));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kemahasiswaan_10113112_10113135/bg3.jpg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 640));

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

private void btn__tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn__tambahActionPerformed
// TODO add your handling code here:
    membersihkan_teks();
    txt_nim.requestFocus();
    btn_simpan.setEnabled(true);
    btn_ubah.setEnabled(false);
    btn_hapus.setEnabled(false);
    btn_keluar.setEnabled(false);
    aktif_teks();
}//GEN-LAST:event_btn__tambahActionPerformed

private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
// TODO add your handling code here:
    String data[]=new String[5];
    
    if((txt_nim.getText().isEmpty()) || (txt_nama.getText().isEmpty()))
    {
        JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, Silahkan dilengkapi");
        txt_nim.requestFocus();
    }
    else
    {
        //convert jdatechooser ke string
        Date tanggal = new Date();
        tanggal = jDateChooser1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal2 = sdf.format(tanggal);
        //
        
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "insert into t_mahasiswa(nim,"
                                                + "nama,"
                                                + "ttl,"
                                                + "tgl_lahir,"
                                                + "alamat)"
                                                    + "VALUES "
                                                + "( '"+txt_nim.getText()+"',"
                                                + " '"+txt_nama.getText()+"' ,"
                                                + " '"+txt_tmpt_lahir.getText()+"',"
                                                + " '"+tanggal2+"',"
                                                + " '"+txt_alamat.getText()+"')";
            
            stt.executeUpdate(SQL);
            batal_nim = txt_nim.getText();
            data[0] = txt_nim.getText();
            data[1] = txt_nama.getText();
            data[2] = txt_tmpt_lahir.getText();
            data[3] = tanggal2;
            data[4] = txt_alamat.getText();
            tableModel.insertRow(0, data);
            stt.close();
            kon.close();
            membersihkan_teks();
            btn_simpan.setEnabled(false);
            nonaktif_teks();
            btn_batal.setEnabled(true);
            btn_keluar.setEnabled(true);
            txt_cari.requestFocus();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}//GEN-LAST:event_btn_simpanActionPerformed

private void tabel_mahasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_mahasiswaMouseClicked
// TODO add your handling code here:
    if(evt.getClickCount()==1)
    {
        tampil_field();
    }
}//GEN-LAST:event_tabel_mahasiswaMouseClicked

private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
// TODO add your handling code here:
    String nim=txt_nim.getText();
    String nama=txt_nama.getText();
    String tempat_lahir = txt_tmpt_lahir.getText();
    String alamat = txt_alamat.getText();
    
    //convert jdatechooser ke string
    Date tanggal = new Date();
    tanggal = jDateChooser1.getDate();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String tanggal2 = sdf.format(tanggal);
    //
    
    if((nim.isEmpty()) | (nama.isEmpty()))
    {
        JOptionPane.showMessageDialog(null, "Sata tidak boleh kosong, Silahkan dilengkapi");
        txt_nim.requestFocus();
    }
    else
    {
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "UPDATE `t_mahasiswa` "
                         + "SET `nim`='"+nim+"',"
                         + " `nama`='"+nama+"',"
                         + " `ttl`='"+tempat_lahir+"',"
                         + " `tgl_lahir`='"+tanggal2+"',"
                         + " `alamat`='"+alamat+"' "
                    + "WHERE "
                    + " `nim`='"+tableModel.getValueAt(row, 0).toString()+"';";            
          
            stt.executeUpdate(SQL);
            data[0] = nim;
            data[1] = nama;
            data[2] = tempat_lahir;
            data[3] = tanggal2;
            data[4] = alamat;
            tableModel.removeRow(row);
            tableModel.insertRow(row, data);
            stt.close();
            kon.close();
            membersihkan_teks();
            btn_simpan.setEnabled(false);
            btn_hapus.setEnabled(false);
            btn_ubah.setEnabled(false);
            nonaktif_teks();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Pesan",JOptionPane.ERROR_MESSAGE);
        }
    }
}//GEN-LAST:event_btn_ubahActionPerformed

private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
// TODO add your handling code here:
   int info = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ini ?", "Pesan", JOptionPane.YES_NO_OPTION);
   if (info == JOptionPane.YES_OPTION)
   {
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "Delete from t_mahasiswa "
                            +"where "
                         +"nim='"+tableModel.getValueAt(row, 0).toString()+"'";
            stt.executeUpdate(SQL);
            tableModel.removeRow(row);
            stt.close();
            kon.close();
            membersihkan_teks();
            nonaktif_teks();
            txt_cari.requestFocus();
            btn_hapus.setEnabled(false);
            btn_ubah.setEnabled(false);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
        }
   }
}//GEN-LAST:event_btn_hapusActionPerformed

private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txt_cariActionPerformed

private void btn_tampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tampilActionPerformed
// TODO add your handling code here:
    tableModel.setRowCount(0);
    txt_cari.setText(null);
    txt_cari.requestFocus();
    settableload();
}//GEN-LAST:event_btn_tampilActionPerformed

private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
// TODO add your handling code here:
    frm_utama utama = new frm_utama();
    utama.setVisible(true);
}//GEN-LAST:event_formWindowClosed

private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
// TODO add your handling code here:
    tableModel.setRowCount(0);
    
    //gunakan query untk mencari
    try
    {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "SELECT * FROM t_mahasiswa"
                        +" WHERE nim LIKE '"+txt_cari.getText()+"%'"
                        +" OR nama LIKE'"+txt_cari.getText()+"%'"
                        +" OR ttl LIKE'"+txt_cari.getText()+"%'"
                        +" OR tgl_lahir LIKE'"+txt_cari.getText()+"%'"
                        +" OR alamat LIKE'"+txt_cari.getText()+"%'";
        ResultSet res = stt.executeQuery(SQL);
        while(res.next())
        {
            data[0] = res.getString(1);
            data[1] = res.getString(2);
            data[2] = res.getString(3);
            data[3] = res.getString(4);
            data[4] = res.getString(5);
            tableModel.addRow(data);
        }
        res.close();
        stt.close();
        kon.close();
    }
    catch(Exception ex)
    {
        JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}//GEN-LAST:event_txt_cariKeyReleased

private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
// TODO add your handling code here:
    frm_utama utama = new frm_utama();
    utama.setDefaultCloseOperation(frm_utama.DISPOSE_ON_CLOSE);
    dispose();
    
}//GEN-LAST:event_btn_keluarActionPerformed

private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
// TODO add your handling code here:
    try
    {
        membersihkan_teks();
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "Delete from t_mahasiswa where nim='"+batal_nim+"'";
        stt.executeUpdate(SQL);
        tableModel.setRowCount(0);
        settableload();
        stt.close();
        kon.close();
        membersihkan_teks();
        btn_simpan.setEnabled(false);
        btn_batal.setEnabled(false);
        btn_keluar.setEnabled(true);
        nonaktif_teks();
        
    }
    catch (Exception ex)
    {
        JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
    }
}//GEN-LAST:event_btn_batalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_mhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_mhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_mhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_mhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frm_mhs().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn__tambah;
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tampil;
    private javax.swing.JButton btn_ubah;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabel_mahasiswa;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nim;
    private javax.swing.JTextField txt_tmpt_lahir;
    // End of variables declaration//GEN-END:variables
}
