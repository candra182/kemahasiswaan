/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frm_nilai.java
 *
 * Created on Jun 17, 2015, 6:10:30 PM
 */
package kemahasiswaan_10113112_10113135;
import javax.swing.*;
//Fungsi Import yang digunakan untuk SQL
import java.sql.*;
/**
 *
 * @author Eggys
 */
public class frm_nilai extends javax.swing.JFrame {
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;
    String get_nim = null; //untuk combobox nama
    String get_kd_mk = null; //untuk combobox matkul
    
    String batal_nim = null; //variabel untuk menampung nim ketika membatalkan penyimpanan data
    String batal_kode_mk = null; //variabel untuk menampung kode matkul ketika membatalkan penyimpanan data
    String batal_angkatan = null;
    /** Creates new form frm_nilai */
    public frm_nilai() {
        initComponents();
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        tabel_nilai.setModel(tableModel);
        nonaktif_teks();
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
                new String [] {"Nama",
                               "Nama M.K",
                               "Absensi",
                               "Tgs 1",
                               "Tgs 2",
                               "Tgs 3",
                               "UTS",
                               "UAS",
                               "Nilai Absen",
                               "Nilai Tugas",
                               "Nilai UTS",
                               "Nilai UAS",
                               "Nilai Akhir",
                               "Index",
                               "Keterangan",
                               "Angkatan",
                              }
                )
                //disable perubahan pada grid
        {
            boolean[] canEdit = new boolean[]
            {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            }; 
            
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
        };
    }
    String data[]= new String[16];
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
                    String SQL = "SELECT mhs.nama,"
                                        +"matkul.nama_mk,"
                                        +"nilai.absensi,"
                                        +"nilai.tgs1,"
                                        +"nilai.tgs2,"
                                        +"nilai.tgs3,"
                                        +"nilai.uts,"
                                        +"nilai.uas,"
                                        +"nilai.nilai_absen,"
                                        +"nilai.nilai_tugas,"
                                        +"nilai.nilai_uts,"
                                        +"nilai.nilai_uas,"
                                        +"nilai.nilai_akhir,"
                                        +"nilai.indeks,"
                                        +"nilai.ket,"
                                        +"nilai.angkatan"
                                +" FROM t_mahasiswa mhs JOIN t_nilai nilai"
                                +" ON mhs.`nim` = nilai.`nim`"
                                +" JOIN t_mata_kuliah matkul"
                                +" ON nilai.`kd_mk` = matkul.`kd_mk`";
                    ResultSet res = stt.executeQuery(SQL);
                    while (res.next()){
                        data[0] = res.getString(1);
                        data[1] = res.getString(2);
                        data[2] = res.getString(3);
                        data[3] = res.getString(4);
                        data[4] = res.getString(5);
                        data[5] = res.getString(6);
                        data[6] = res.getString(7);
                        data[7] = res.getString(8);
                        data[8] = res.getString(9);
                        data[9] = res.getString(10);
                        data[10] = res.getString(11);
                        data[11] = res.getString(12);
                        data[12] = res.getString(13);
                        data[13] = res.getString(14);
                        data[14] = res.getString(15);
                        data[15] = res.getString(16);
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
        txt_kehadiran.setText("");
        txt_kode_mk.setText("");
        txt_nim.setText("");
        txt_tugas1.setText("");
        txt_tugas2.setText("");
        txt_tugas3.setText("");
        txt_uas.setText("");
        txt_uts.setText("");
        combo_matkul.setSelectedItem("~Silahkan Pilih~");
        combo_nama.setSelectedItem("~Silahkan Pilih~");
        jYearChooser1.setYear(2015);
        
    }
    public void nonaktif_teks()
    {
        txt_kehadiran.setEnabled(false);
        txt_kode_mk.setEnabled(false);
        txt_nim.setEnabled(false);
        txt_tugas1.setEnabled(false);
        txt_tugas2.setEnabled(false);
        txt_tugas3.setEnabled(false);
        txt_uas.setEnabled(false);
        txt_uts.setEnabled(false);
        combo_matkul.setEnabled(false);
        combo_nama.setEnabled(false);
        jYearChooser1.setEnabled(false);
    }
    public void aktif_teks()
    {
        txt_kehadiran.setEnabled(true);
        txt_kode_mk.setEnabled(true);
        txt_nim.setEnabled(true);
        txt_tugas1.setEnabled(true);
        txt_tugas2.setEnabled(true);
        txt_tugas3.setEnabled(true);
        txt_uas.setEnabled(true);
        txt_uts.setEnabled(true);
        combo_matkul.setEnabled(true);
        combo_nama.setEnabled(true);
        jYearChooser1.setEnabled(true);
    }
    
    int row = 0;
    public void tampil_field()
    {
        row = tabel_nilai.getSelectedRow();
        combo_nama.setSelectedItem(tableModel.getValueAt(row, 0).toString());
        combo_matkul.setSelectedItem(tableModel.getValueAt(row, 1).toString());
        txt_kehadiran.setText(tableModel.getValueAt(row, 2).toString());
        txt_tugas1.setText(tableModel.getValueAt(row, 3).toString());
        txt_tugas2.setText(tableModel.getValueAt(row, 4).toString());
        txt_tugas3.setText(tableModel.getValueAt(row, 5).toString());
        txt_uts.setText(tableModel.getValueAt(row, 6).toString());
        txt_uas.setText(tableModel.getValueAt(row, 7).toString());
        String year = (tableModel.getValueAt(row, 15).toString());
        int tampil_year = Integer.valueOf(year);
        jYearChooser1.setYear(tampil_year);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txt_tugas3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_tugas2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_tugas1 = new javax.swing.JTextField();
        txt_kehadiran = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_nim = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        combo_nama = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        combo_matkul = new javax.swing.JComboBox();
        txt_kode_mk = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_uas = new javax.swing.JTextField();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        txt_uts = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        btn_tampil = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_nilai = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Vijaya", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM NILAI MAHASISWA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(691, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Pencarian Data"));

        jLabel2.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel2.setText("Masukkan Data");

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
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_cari, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addGap(405, 405, 405))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Input"));

        txt_tugas3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tugas3KeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel9.setText("Tugas 3      :");

        jLabel8.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel8.setText("Tugas 2      :");

        txt_tugas2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tugas2KeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel7.setText("Tugas 1      :");

        txt_tugas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tugas1KeyReleased(evt);
            }
        });

        txt_kehadiran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_kehadiranKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel5.setText("Kehadiran  :");

        jLabel6.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel6.setText("Pertemuan");

        txt_nim.setEditable(false);
        txt_nim.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel4.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel4.setText("NIM          :");

        jLabel3.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel3.setText("Nama        :");

        combo_nama.setFont(new java.awt.Font("Vijaya", 0, 18));
        combo_nama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "~Silahkan Pilih~" }));
        combo_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_namaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel10.setText("Nama Mata Kuliah :");

        combo_matkul.setFont(new java.awt.Font("Vijaya", 0, 18));
        combo_matkul.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "~Silahkan Pilih~" }));
        combo_matkul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_matkulActionPerformed(evt);
            }
        });

        txt_kode_mk.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel11.setText("Kode MK               :");

        jLabel12.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel12.setText("UTS                       :");

        jLabel13.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel13.setText("UAS                       :");

        jLabel14.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel14.setText("Angkatan                :");

        txt_uas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_uasKeyReleased(evt);
            }
        });

        txt_uts.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_utsKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nim, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txt_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6))
                            .addComponent(combo_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_uas, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_uts, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_kode_mk)
                                .addComponent(combo_matkul, 0, 150, Short.MAX_VALUE))
                            .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(combo_matkul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(txt_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txt_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(combo_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_nim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txt_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))))))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Proses")));

        btn_tambah.setFont(new java.awt.Font("Vijaya", 0, 18));
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_ubah.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        btn_ubah.setText("Ubah");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_hapus.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_simpan.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_batal.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btn_tambah)
                .addGap(18, 18, 18)
                .addComponent(btn_ubah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_hapus)
                .addGap(18, 18, 18)
                .addComponent(btn_simpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_batal)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_ubah)
                    .addComponent(btn_hapus)
                    .addComponent(btn_simpan)
                    .addComponent(btn_batal))
                .addGap(34, 34, 34))
        );

        btn_keluar.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        btn_keluar.setText("Kembali");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });

        btn_tampil.setFont(new java.awt.Font("Vijaya", 0, 18));
        btn_tampil.setText("Tampilkan Keseluruhan Data");
        btn_tampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampilActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Output"));

        tabel_nilai.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_nilai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_nilaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_nilai);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btn_tampil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_keluar))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tampil)
                    .addComponent(btn_keluar))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txt_cariActionPerformed

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
// TODO add your handling code here:
    try
    {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "select * from t_mahasiswa";
        ResultSet res = stt.executeQuery(SQL);
        while(res.next())
        {
            combo_nama.addItem(res.getString("nama"));//Menampilkan Data dalam Table kedalam ComboBox
        }
        String SQL2 = "select * from t_mata_kuliah";
        ResultSet res2 = stt.executeQuery(SQL2);
        while(res2.next())
        {
            combo_matkul.addItem(res2.getString("nama_mk"));//Menampilkan Data dalam Table kedalam ComboBox
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
}//GEN-LAST:event_formWindowOpened

private void combo_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_namaActionPerformed
// TODO add your handling code here:
    if(combo_nama.getSelectedItem()=="~Silahkan Pilih~")
    {
        txt_nim.setText("");
    }
    else
    {
        String nama = (String) combo_nama.getSelectedItem();
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "select * from t_mahasiswa where nama='"+nama+"'";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next())
            {
                txt_nim.setText(res.getString("nim"));//Menampilkan Data dalam Table kedalam ComboBox
                get_nim = res.getString("nim");
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
    }
}//GEN-LAST:event_combo_namaActionPerformed

private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
// TODO add your handling code here:
    frm_utama utama = new frm_utama();
    utama.setVisible(true);
}//GEN-LAST:event_formWindowClosed

private void combo_matkulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_matkulActionPerformed
// TODO add your handling code here:
if(combo_matkul.getSelectedItem()=="~Silahkan Pilih~")
{
    txt_kode_mk.setText("");
}
else
{
    String nama_mk = (String) combo_matkul.getSelectedItem();
    try
    {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "select * from t_mata_kuliah where nama_mk='"+nama_mk+"'";
        ResultSet res = stt.executeQuery(SQL);
        while(res.next())
        {
            txt_kode_mk.setText(res.getString("kd_mk"));//Menampilkan Data dalam Table kedalam ComboBox
            get_kd_mk = res.getString("kd_mk");
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
}
}//GEN-LAST:event_combo_matkulActionPerformed

private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
// TODO add your handling code here:
membersihkan_teks();
combo_nama.requestFocus();
btn_simpan.setEnabled(true);
btn_ubah.setEnabled(false);
btn_hapus.setEnabled(false);
btn_keluar.setEnabled(false);
aktif_teks();   
}//GEN-LAST:event_btn_tambahActionPerformed

private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
// TODO add your handling code here:
    String data_sama=null;
    //cek apakah nama dan mata kuliah dan angkatan sama persis dengan yang ada di database...
    try
    {
        Class.forName(driver);
        Connection kon2 = DriverManager.getConnection(
                database,
                user,
                pass);   
        Statement stt2  = kon2.createStatement();
        String SQL2     = "SELECT * FROM `t_nilai`";
        ResultSet res2 = stt2.executeQuery(SQL2);

        //selama data masih ada/elum habis
        while(res2.next())
        {
            //jika nim di database dan nim yang user pilih tidak ada yang sama maka..
            if( !res2.getString(2).equals(txt_nim.getText()))
            {
                data_sama="TIDAK";
            }
            //jika nim di database dan nim yang user pilih ada yang sama maka..
            else
            {
                //jika kode mk di database dan kode mk yang user pilih tidak ada yang sama maka..
                if( !res2.getString(3).equals(txt_kode_mk.getText()))
                {
                    data_sama="TIDAK";
                }
                //jika kode mk di database dan kode mk yang user pilih ada yang sama maka..
                else
                {
                    //ambil tahun dari database
                    int db1 = res2.getInt(17);
                    String db_tgl = String.valueOf(db1);
                    
                    //ambil tahun dari jYearChooser
                    int tanggals = jYearChooser1.getYear();
                    String tanggals_angkatan = String.valueOf(tanggals);

                    //jika angkatan di database dan angkatan yang user pilih tidak ada yang sama maka..
                    if( !db_tgl.equals(tanggals_angkatan))
                    {
                        data_sama="TIDAK";
                    }
                    //jika angkatan di database dan angkatan yang user pilih ada yang sama maka..
                    else
                    {
                        data_sama="YA";
                        break;
                    }
                }
            }
        }
        res2.close();
        stt2.close();
        kon2.close();
    }
    catch(Exception ex)
    {
        System.err.println(ex.getMessage());
        JOptionPane.showMessageDialog(null, 
                "Kesalahan saat update jumlah error menjadi di tambcccah 1","Error",
                JOptionPane.INFORMATION_MESSAGE);
    }
    if(data_sama=="YA")
    {
        JOptionPane.showMessageDialog(null, "Nama atau Nama Mata Kuliah atau Angkatan sudah terdaftar","Pesan",JOptionPane.INFORMATION_MESSAGE);
    }
    else
    {
        //sebelum ubah nim/nama ambil dahulu nim yang belum di ubah untuk sql where nim='sblm_ubah_nim'
        String sblm_ubah_nama = tableModel.getValueAt(row, 0).toString();
        String sblm_ubah_nim = null;
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT nim from t_mahasiswa where nama='"+sblm_ubah_nama+"'";
            ResultSet res = stt.executeQuery(SQL);
            if(res.next())
            {
                sblm_ubah_nim=res.getString(1);
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

        //sebelum ubah kode/nama_matkul ambil dahulu kd_mk yang belum di ubah untuk sql where kd_mk='sblm_ubah_kode_mk'
        String sblm_ubah_nama_matkul = tableModel.getValueAt(row, 1).toString();
        String sblm_ubah_kode_matkul = null;
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT kd_mk from t_mata_kuliah where nama_mk='"+sblm_ubah_nama_matkul+"'";
            ResultSet res = stt.executeQuery(SQL);
            if(res.next())
            {
                sblm_ubah_kode_matkul=res.getString(1);
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
        
        //sebelum ubah angkatan ambil dahulu angkatan yang belum di ubah untuk sql where angkatan='sblm_ubah_angkatan'
        String sblm_ubah_angkatan = tableModel.getValueAt(row, 15).toString();

        //ambil semua data yang akan di ubah datanya
        String ubah_nama = (String) combo_nama.getSelectedItem();
        String ubah_nim=txt_nim.getText();
        String ubah_nama_matkul = (String) combo_matkul.getSelectedItem();
        String ubah_kode_matkul = txt_kode_mk.getText();
        String ubah_tugas1 = txt_tugas1.getText();
        String ubah_tugas2 = txt_tugas2.getText();
        String ubah_tugas3 = txt_tugas3.getText();
        String ubah_uts = txt_uts.getText();
        String ubah_uas = txt_uas.getText();

        //variabel untuk indeks dan ket yang di pakai untuk menentukan indeksnya apa dan lulus atau tidak lulus
        String ubah_indeks="T";
        String ubah_ket="TIDAK LULUS";

        //jika absensi <11 maka tidak lulus
        String ubah_kehadiran2 = txt_kehadiran.getText();
        // masih ada lanjutan di bawah

        //perhitungan semua nilai ketika data di ubah
        Double ubah_nilai_absen = Double.parseDouble(ubah_kehadiran2);
        ubah_nilai_absen = (((ubah_nilai_absen/14)*100*5)/100);

        Double ubah_nilai_tugas1 = Double.parseDouble(ubah_tugas1);
        Double ubah_nilai_tugas2 = Double.parseDouble(ubah_tugas2);
        Double ubah_nilai_tugas3 = Double.parseDouble(ubah_tugas3);
        Double ubah_nilai_tugas = (((ubah_nilai_tugas1+ubah_nilai_tugas2+ubah_nilai_tugas3)/3)*(25)/(100));
        Double ubah_nilai_uts = Double.parseDouble(ubah_uts);
        ubah_nilai_uts = ubah_nilai_uts*30/100;

        Double ubah_nilai_uas = Double.parseDouble(ubah_uas);
        ubah_nilai_uas = ubah_nilai_uas*40/100;

        Double ubah_nilai_akhir = ubah_nilai_absen + ubah_nilai_tugas + ubah_nilai_uts + ubah_nilai_uas;


        if(ubah_nilai_akhir>=80 && ubah_nilai_akhir<=100)
        {
            ubah_indeks = "A";
            ubah_ket="LULUS";
        }
        else if(ubah_nilai_akhir>=68 && ubah_nilai_akhir<=79)
        {
            ubah_indeks = "B";
            ubah_ket="LULUS";
        }
        else if(ubah_nilai_akhir>=56 && ubah_nilai_akhir<=67)
        {
            ubah_indeks = "C";
            ubah_ket="LULUS";
        }
        else if(ubah_nilai_akhir>=45 && ubah_nilai_akhir<=55)
        {
            ubah_indeks = "D";
        }
        else if(ubah_nilai_akhir>=0 && ubah_nilai_akhir<=44)
        {
            ubah_indeks = "E";
        }

        //jika absensi <11 maka tidak lulus
        //convert string to int
        int ubah_kehadiran = Integer.parseInt(ubah_kehadiran2);
        if(ubah_kehadiran<11)
        {
            ubah_ket="TIDAK LULUS";
        }
        // masih ada lanjutan di bawah.

        int ubah_tanggal = jYearChooser1.getYear();
        String ubah_tanggal_angkatan = String.valueOf(ubah_tanggal);
        //

        if((txt_nim.getText().isEmpty()) || (txt_kode_mk.getText().isEmpty()) || (txt_kehadiran.getText().isEmpty()) || (txt_uas.getText().isEmpty()) || (txt_uts.getText().isEmpty()) || (txt_tugas1.getText().isEmpty()) || (txt_tugas2.getText().isEmpty()) || (txt_tugas3.getText().isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, Silahkan dilengkapi");
            combo_nama.requestFocus();
        }
        else
        {
            try
            {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database,user,pass);
                Statement stt = kon.createStatement();
                //JOptionPane.showMessageDialog(null, sblm_ubah_nim);
                String SQL = "UPDATE `t_nilai` "
                             + "SET `nim`='"+ubah_nim+"',"
                             + " `kd_mk`='"+ubah_kode_matkul+"',"
                             + " `absensi`='"+ubah_kehadiran2+"',"
                             + " `tgs1`='"+ubah_tugas1+"',"
                             + " `tgs2`='"+ubah_tugas2+"',"
                             + " `tgs3`='"+ubah_tugas3+"',"
                             + " `uts`='"+ubah_uts+"',"
                             + " `uas`='"+ubah_uas+"',"
                             + " `nilai_absen`='"+(String.format("%.2f", ubah_nilai_absen))+"',"
                             + " `nilai_tugas`='"+(String.format("%.2f", ubah_nilai_tugas))+"',"
                             + " `nilai_uts`='"+(String.format("%.2f", ubah_nilai_uts))+"',"
                             + " `nilai_uas`='"+(String.format("%.2f", ubah_nilai_uas))+"',"
                             + " `nilai_akhir`='"+(String.format("%.2f", ubah_nilai_akhir))+"',"
                             + " `indeks`='"+ubah_indeks+"',"
                             + " `ket`='"+ubah_ket+"',"
                             + " `angkatan`='"+ubah_tanggal_angkatan+"' "
                        + "WHERE "
                        + " `nim`='"+sblm_ubah_nim+"'"
                        + " AND `kd_mk`='"+sblm_ubah_kode_matkul+"'"
                        + " AND `angkatan`='"+sblm_ubah_angkatan+"';";            

                stt.executeUpdate(SQL);
                data[0] = ubah_nama;
                data[1] = ubah_nama_matkul;
                data[2] = ubah_kehadiran2;
                data[3] = ubah_tugas1;
                data[4] = ubah_tugas2;
                data[5] = ubah_tugas3;
                data[6] = ubah_uts;
                data[7] = ubah_uas;
                data[8] = String.valueOf((String.format("%.2f", ubah_nilai_absen)));
                data[9] = String.valueOf((String.format("%.2f", ubah_nilai_tugas)));
                data[10] = String.valueOf((String.format("%.2f", ubah_nilai_uts)));
                data[11] = String.valueOf((String.format("%.2f", ubah_nilai_uas)));
                data[12] = String.valueOf((String.format("%.2f", ubah_nilai_akhir)));
                data[13] = ubah_indeks;
                data[14] = ubah_ket;
                data[15] = ubah_tanggal_angkatan;
                tableModel.removeRow(row);
                tableModel.insertRow(row, data);
                stt.close();
                kon.close();
                membersihkan_teks();
                btn_keluar.setEnabled(true);
                btn_simpan.setEnabled(false);
                btn_ubah.setEnabled(false);
                btn_hapus.setEnabled(false);
                nonaktif_teks();
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            }
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
            //ambil nim yang data nilainya akan di hapus
            String nama_del =tableModel.getValueAt(row, 0).toString(); //ambil nama dari value tabel yang di pilih
            String SQL2 = "select nim from t_mahasiswa where nama='"+nama_del+"'";
            ResultSet res = stt.executeQuery(SQL2);
            String nim_del = null;
            if(res.next())
            {
                nim_del = res.getString(1);
            }

            //
            //ambil kode mk yang data nilainya akan di hapus
            String nama_mk_del =tableModel.getValueAt(row, 1).toString(); //ambil nama dari value tabel yang di pilih
            String SQL3 = "select kd_mk from t_mata_kuliah where nama_mk='"+nama_mk_del+"'";
            ResultSet res3 = stt.executeQuery(SQL3);
            String kd_mk_del = null;
            if(res3.next())
            {
                kd_mk_del = res3.getString(1);
            }

            
            String angkatan_del =tableModel.getValueAt(row, 15).toString(); //ambil nama dari value tabel yang di pilih
            String SQL = "Delete from t_nilai "
                            +"where "
                                +"nim='"+nim_del+"'"
                                +"AND kd_mk='"+kd_mk_del+"'"
                                +"AND angkatan='"+angkatan_del+"'";
            stt.executeUpdate(SQL);
            tableModel.removeRow(row);
            stt.close();
            kon.close();
            membersihkan_teks();
            btn_hapus.setEnabled(false);
            btn_ubah.setEnabled(false);
            nonaktif_teks();
            jYearChooser1.setEnabled(false);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
        }
   }
}//GEN-LAST:event_btn_hapusActionPerformed

private void txt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyReleased
// TODO add your handling code here:
    //menghapus seluruh isi data di dalam jtable
    tableModel.setRowCount(0);
    
    //gunakan query untk mencari
    try
    {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "SELECT mhs.nama,"
                            +"matkul.nama_mk,"
                            +"nilai.absensi,"
                            +"nilai.tgs1,"
                            +"nilai.tgs2,"
                            +"nilai.tgs3,"
                            +"nilai.uts,"
                            +"nilai.uas,"
                            +"nilai.nilai_absen,"
                            +"nilai.nilai_tugas,"
                            +"nilai.nilai_uts,"
                            +"nilai.nilai_uas,"
                            +"nilai.nilai_akhir,"
                            +"nilai.indeks,"
                            +"nilai.ket"
                            +"nilai.angkatan"
                    +" FROM t_mahasiswa mhs JOIN t_nilai nilai"
                    +" ON mhs.`nim` = nilai.`nim`"
                    +" JOIN t_mata_kuliah matkul"
                    +" ON nilai.`kd_mk` = matkul.`kd_mk`"
                    +" WHERE mhs.nama LIKE '"+txt_cari.getText()+"%'"
                    +" OR matkul.nama_mk LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.absensi LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.tgs1 LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.tgs2 LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.tgs3 LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.uts LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.uas LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.nilai_absen LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.nilai_tugas LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.nilai_uts LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.nilai_uas LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.nilai_akhir LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.indeks LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.ket LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.angkatan LIKE '"+txt_cari.getText()+"%'";
        ResultSet res = stt.executeQuery(SQL);
        while(res.next())
        {
            data[0] = res.getString(1);
            data[1] = res.getString(2);
            data[2] = res.getString(3);
            data[3] = res.getString(4);
            data[4] = res.getString(5);
            data[5] = res.getString(6);
            data[6] = res.getString(7);
            data[7] = res.getString(8);
            data[8] = res.getString(9);
            data[9] = res.getString(10);
            data[10] = res.getString(11);
            data[11] = res.getString(12);
            data[12] = res.getString(13);
            data[13] = res.getString(14);
            data[14] = res.getString(15);
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

private void tabel_nilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_nilaiMouseClicked
// TODO add your handling code here:
    if(evt.getClickCount()==1)
    {
        tampil_field();
    }
}//GEN-LAST:event_tabel_nilaiMouseClicked

private void btn_tampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tampilActionPerformed
// TODO add your handling code here:
    tableModel.setRowCount(0);
    txt_cari.setText(null);
    txt_cari.requestFocus();
    settableload();
}//GEN-LAST:event_btn_tampilActionPerformed

private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
// TODO add your handling code here:
    String data2[]=new String[16];
        //jika absensi >14 atau <0 maka muncul pesan error
        //convert string to int
        String absn2 = txt_kehadiran.getText();
        int absn = Integer.parseInt(absn2);
        //
        
        //kumpulan variabel
        Double tugas1 = Double.parseDouble(txt_tugas1.getText());
        Double tugas2 = Double.parseDouble(txt_tugas2.getText());
        Double tugas3 = Double.parseDouble(txt_tugas3.getText());
        Double nilai_uts = Double.parseDouble(txt_uts.getText());
        Double nilai_uas = Double.parseDouble(txt_uas.getText());
        Double nilai_absen = Double.parseDouble(txt_kehadiran.getText());
        //
        
    if((txt_nim.getText().isEmpty()) || (txt_kode_mk.getText().isEmpty()) || (txt_kehadiran.getText().isEmpty()) || (txt_uas.getText().isEmpty()) || (txt_uts.getText().isEmpty()) || (txt_tugas1.getText().isEmpty()) || (txt_tugas2.getText().isEmpty()) || (txt_tugas3.getText().isEmpty()))
    {
        JOptionPane.showMessageDialog(null, "Data tidak boleh ada yang kosong, Silahkan dilengkapi");
        combo_nama.requestFocus();
    }
    else
    {
        String data_sama=null;
        //cek apakah nama dan mata kuliah dan angkatan sama persis dengan yang ada di database...
        try
        {
            Class.forName(driver);
            Connection kon2 = DriverManager.getConnection(
                    database,
                    user,
                    pass);   
            Statement stt2  = kon2.createStatement();
            String SQL2     = "SELECT * FROM `t_nilai`";
            ResultSet res2 = stt2.executeQuery(SQL2);
            
            //selama data masih ada/elum habis
            while(res2.next())
            {
                //jika nim di database dan nim yang user pilih tidak ada yang sama maka..
                if( !res2.getString(2).equals(txt_nim.getText()))
                {
                    data_sama="TIDAK";
                }
                //jika nim di database dan nim yang user pilih ada yang sama maka..
                else
                {
                    //jika kode mk di database dan kode mk yang user pilih tidak ada yang sama maka..
                    if( !res2.getString(3).equals(txt_kode_mk.getText()))
                    {
                        data_sama="TIDAK";
                    }
                    //jika kode mk di database dan kode mk yang user pilih ada yang sama maka..
                    else
                    {
                        //ambil tahun angkatan dari database
                        int db1 = res2.getInt(17);
                        String db_tgl = String.valueOf(db1);
                        
                        //ambil tahun dari jYearChooser
                        int tanggals = jYearChooser1.getYear();
                        String tanggals_angkatan = String.valueOf(tanggals);
                        
                        //jika angkatan di database dan angkatan yang user pilih tidak ada yang sama maka..
                        if( !db_tgl.equals(tanggals_angkatan))
                        {
                            data_sama="TIDAK";
                        }
                        //jika angkatan di database dan angkatan yang user pilih ada yang sama maka..
                        else
                        {
                            data_sama="YA";
                            break;
                        }
                    }
                }
            }
            res2.close();
            stt2.close();
            kon2.close();
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, 
                    "Kesalahan saat update jumlah error menjadi di tambcccah 1","Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if(data_sama=="YA")
        {
            JOptionPane.showMessageDialog(null, "Nama atau Nama Mata Kuliah atau Angkatan sudah terdaftar","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            nilai_absen = (((nilai_absen/14)*100*5)/100);
            Double nilai_tugas = (((tugas1+tugas2+tugas3)/3)*(25)/(100));
            nilai_uts = nilai_uts*30/100;
            nilai_uas = nilai_uas*40/100;
            Double nilai_akhir = nilai_absen + nilai_tugas + nilai_uts + nilai_uas;

            String indeks="T";
            String ket="TIDAK LULUS";
            if(nilai_akhir>=80 && nilai_akhir<=100)
            {
                indeks = "A";
                ket="LULUS";
            }
            else if(nilai_akhir>=68 && nilai_akhir<=79)
            {
                indeks = "B";
                ket="LULUS";
            }
            else if(nilai_akhir>=56 && nilai_akhir<=67)
            {
                indeks = "C";
                ket="LULUS";
            }
            else if(nilai_akhir>=45 && nilai_akhir<=55)
            {
                indeks = "D";
            }
            else if(nilai_akhir>=0 && nilai_akhir<=44)
            {
                indeks = "E";
            }

            //jika absensi <11 maka tidak lulus
            //convert string to int
            String kehadiran2 = txt_kehadiran.getText();
            int kehadiran = Integer.parseInt(kehadiran2);
            if(kehadiran<11)
            {
                ket="TIDAK LULUS";
            }

            //
            //ambil tahun dari jYearChooser
            int tanggal = jYearChooser1.getYear();
            String tanggal_angkatan = String.valueOf(tanggal);

            //memasukkan nilai ke variabel global
            get_nim = txt_nim.getText();
            try
            {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database,user,pass);
                Statement stt = kon.createStatement();
                String SQL = "insert into t_nilai(nim,"
                                                    + "kd_mk,"
                                                    + "absensi,"
                                                    + "tgs1,"
                                                    + "tgs2,"
                                                    + "tgs3,"
                                                    + "uts,"
                                                    + "uas,"
                                                    + "nilai_absen,"
                                                    + "nilai_tugas,"
                                                    + "nilai_uts,"
                                                    + "nilai_uas,"
                                                    + "nilai_akhir,"
                                                    + "indeks,"
                                                    + "ket,"
                                                    + "angkatan)"
                                                        + " VALUES "
                                                    + "('"+get_nim+"',"
                                                    + " '"+get_kd_mk+"',"
                                                    + " '"+txt_kehadiran.getText()+" ',"
                                                    + " '"+txt_tugas1.getText()+"',"
                                                    + " '"+txt_tugas2.getText()+"',"
                                                    + " '"+txt_tugas3.getText()+"',"
                                                    + " '"+txt_uts.getText()+"',"
                                                    + " '"+txt_uas.getText()+"',"
                                                    + " '"+(String.format("%.2f", nilai_absen))+"',"
                                                    + " '"+(String.format("%.2f", nilai_tugas))+"',"
                                                    + " '"+(String.format("%.2f", nilai_uts))+"',"
                                                    + " '"+(String.format("%.2f", nilai_uas))+"',"
                                                    + " '"+(String.format("%.2f", nilai_akhir))+"',"
                                                    + " '"+indeks+"',"
                                                    + " '"+ket+"',"
                                                    + " '"+tanggal_angkatan+"')";

                stt.executeUpdate(SQL);
                batal_nim = txt_nim.getText();
                batal_kode_mk = txt_kode_mk.getText();
                int batal_angkatan2 = jYearChooser1.getYear();
                batal_angkatan = String.valueOf(batal_angkatan2);
                data2[0] = (String )combo_nama.getSelectedItem();
                data2[1] = (String )combo_matkul.getSelectedItem();
                data2[2] = txt_kehadiran.getText();
                data2[3] = txt_tugas1.getText();
                data2[4] = txt_tugas2.getText();
                data2[5] = txt_tugas3.getText();
                data2[6] = txt_uts.getText();
                data2[7] = txt_uas.getText();
                data2[8] = String.valueOf((String.format("%.2f", nilai_absen)));
                data2[9] = String.valueOf((String.format("%.2f", nilai_tugas)));
                data2[10] = String.valueOf((String.format("%.2f", nilai_uts)));
                data2[11] = String.valueOf((String.format("%.2f", nilai_uas)));
                data2[12] = String.valueOf((String.format("%.2f", nilai_akhir)));
                data2[13] = indeks;
                data2[14] = ket;
                data2[15] = tanggal_angkatan;
                tableModel.insertRow(0, data2);

                stt.close();
                kon.close();
                membersihkan_teks();
                btn_simpan.setEnabled(false);
                btn_keluar.setEnabled(true);
                btn_batal.setEnabled(true);
                nonaktif_teks();

            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}//GEN-LAST:event_btn_simpanActionPerformed

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
        String SQL = "Delete from t_nilai where nim='"+batal_nim+"' and kd_mk='"+batal_kode_mk+"' and angkatan='"+batal_angkatan+"'";
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

    private void txt_kehadiranKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kehadiranKeyReleased
        // TODO add your handling code here:
        String absn2 = txt_kehadiran.getText();
        int absn = Integer.parseInt(absn2);
        if(absn>14)
        {
            JOptionPane.showMessageDialog(null, "Total Kehadiran tidak boleh lebih dari 14","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (absn<0)
        {
            JOptionPane.showMessageDialog(null, "Total Kehadiran tidak boleh kurang dari 0","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txt_kehadiranKeyReleased

    private void txt_tugas1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tugas1KeyReleased
        // TODO add your handling code here:
        Double tugas1 = Double.parseDouble(txt_tugas1.getText());
        if(tugas1>100)
        {
            JOptionPane.showMessageDialog(null, "Nilai Tugas 1 tidak boleh lebih dari 100","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (tugas1<0)
        {
            JOptionPane.showMessageDialog(null, "Nilai Tugas 1 tidak boleh kurang dari 0","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txt_tugas1KeyReleased

    private void txt_tugas2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tugas2KeyReleased
        // TODO add your handling code here:
        Double tugas2 = Double.parseDouble(txt_tugas2.getText());
        if(tugas2>100)
        {
            JOptionPane.showMessageDialog(null, "Nilai Tugas 2 tidak boleh lebih dari 100","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (tugas2<0)
        {
            JOptionPane.showMessageDialog(null, "Nilai Tugas 2 tidak boleh kurang dari 0","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txt_tugas2KeyReleased

    private void txt_tugas3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tugas3KeyReleased
        // TODO add your handling code here:
        Double tugas3 = Double.parseDouble(txt_tugas3.getText());
        if(tugas3>100)
        {
            JOptionPane.showMessageDialog(null, "Nilai Tugas 3 tidak boleh lebih dari 100","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (tugas3<0)
        {
            JOptionPane.showMessageDialog(null, "Nilai Tugas 3 tidak boleh kurang dari 0","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txt_tugas3KeyReleased

    private void txt_utsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_utsKeyReleased
        // TODO add your handling code here:
        Double nilai_uts = Double.parseDouble(txt_uts.getText());
        if(nilai_uts>100)
        {
            JOptionPane.showMessageDialog(null, "Nilai UTS tidak boleh lebih dari 100","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (nilai_uts<0)
        {
            JOptionPane.showMessageDialog(null, "Nilai UTS tidak boleh kurang dari 0","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txt_utsKeyReleased

    private void txt_uasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_uasKeyReleased
        // TODO add your handling code here:
        Double nilai_uas = Double.parseDouble(txt_uas.getText());
        if(nilai_uas>100)
        {
            JOptionPane.showMessageDialog(null, "Nilai UAS tidak boleh lebih dari 100","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (nilai_uas<0)
        {
            JOptionPane.showMessageDialog(null, "Nilai UAS tidak boleh kurang dari 0","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txt_uasKeyReleased

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
            java.util.logging.Logger.getLogger(frm_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frm_nilai().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_tampil;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox combo_matkul;
    private javax.swing.JComboBox combo_nama;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JTable tabel_nilai;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_kehadiran;
    private javax.swing.JTextField txt_kode_mk;
    private javax.swing.JTextField txt_nim;
    private javax.swing.JTextField txt_tugas1;
    private javax.swing.JTextField txt_tugas2;
    private javax.swing.JTextField txt_tugas3;
    private javax.swing.JTextField txt_uas;
    private javax.swing.JTextField txt_uts;
    // End of variables declaration//GEN-END:variables
}
