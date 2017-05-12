/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frm_simulasi_nilai.java
 *
 * Created on Jun 28, 2015, 1:41:56 PM
 */
package kemahasiswaan_10113112_10113135;
import javax.swing.*;
//Fungsi Import yang digunakan untuk SQL
import java.sql.*;
/**
 *
 * @author BankZeck182
 */
public class frm_simulasi_nilai extends javax.swing.JFrame {
    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;
   
    String get_kd_mk = null; //untuk combobox matkul
    
    String batal_kode_mk = null; //variabel untuk menampung nim ketika membatalkan penyimpanan data
    String batal_p_kehadiran = null; //variabel untuk menampung kode matkul ketika membatalkan penyimpanan data
    String batal_p_tugas = null;
    String batal_p_uts = null;
    String batal_p_uas = null;
    String batal_absensi = null;
    String batal_tgs1 = null;
    String batal_tgs2 = null;
    String batal_tgs3 = null;
    String batal_uts = null;
    String batal_uas = null;
    /** Creates new form frm_simulasi_nilai */
    public frm_simulasi_nilai() {
        initComponents();
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        tabel_simulasi_nilai.setModel(tableModel);
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
                new String [] {"Nama M.K",
                               "P_Absensi",
                               "P_Tgs",
                               "P_UTS",
                               "P_UAS",
                               "Absensi",
                               "Tugas 1",
                               "Tugas 2",
                               "Tugas 3",
                               "UTS",
                               "UAS",
                               "Nilai Absen",
                               "Nilai Tugas",
                               "Nilai UTS",
                               "Nilai UAS",
                               "Nilai Akhir",
                               "Index",
                               "Keterangan",
                              }
                )
                //disable perubahan pada grid
        {
            boolean[] canEdit = new boolean[]
            {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            }; 
            
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
        };
    }
    String data[]= new String[18];
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
                    String SQL = "SELECT matkul.nama_mk,"
                                        +"nilai.p_kehadiran,"
                                        +"nilai.p_tugas,"
                                        +"nilai.p_uts,"
                                        +"nilai.p_uas,"
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
                                        +"nilai.n_akhir,"
                                        +"nilai.indeks,"
                                        +"nilai.keterangan"
                                +" FROM t_mata_kuliah matkul JOIN t_simulasi_nilai nilai"
                                +" ON matkul.`kd_mk` = nilai.`kd_mk`";
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
                        data[16] = res.getString(17);
                        data[17] = res.getString(18);
                        tableModel.addRow(data);
                    }
                    res.close();
                    stt.close();
                    kon.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            
            System.exit(0);
        }
    }
    public void membersihkan_teks()
    {
        txt_kehadiran.setText("");
        txt_kode_mk.setText("");
        txt_p_absen.setText("");
        txt_p_tgs.setText("");
        txt_p_uas.setText("");
        txt_p_uts.setText("");
        txt_tugas1.setText("");
        txt_tugas2.setText("");
        txt_tugas3.setText("");
        txt_uts.setText("");
        txt_uas.setText("");
        combo_matkul.setSelectedItem("~Silahkan Pilih~");
        
        
    }
    public void nonaktif_teks()
    {
        txt_kehadiran.setEnabled(false);
        txt_kode_mk.setEnabled(false);
        txt_p_absen.setEditable(false);
        txt_p_tgs.setEditable(false);
        txt_p_uas.setEditable(false);
        txt_p_uts.setEditable(false);
        txt_tugas1.setEnabled(false);
        txt_tugas2.setEnabled(false);
        txt_tugas3.setEnabled(false);
        txt_uas.setEnabled(false);
        txt_uts.setEnabled(false);
        combo_matkul.setEnabled(false);
       
    }
    public void aktif_teks()
    {
        txt_kehadiran.setEnabled(true);
        txt_kode_mk.setEnabled(true);
        txt_p_absen.setEditable(true);
        txt_p_tgs.setEditable(true);
        txt_p_uas.setEditable(true);
        txt_p_uts.setEditable(true);
        txt_tugas1.setEnabled(true);
        txt_tugas2.setEnabled(true);
        txt_tugas3.setEnabled(true);
        txt_uts.setEnabled(true);
        txt_uas.setEnabled(true);
        combo_matkul.setEnabled(true);
        
    }
    
    int row = 0;
    public void tampil_field()
    {
        row = tabel_simulasi_nilai.getSelectedRow();
        combo_matkul.setSelectedItem(tableModel.getValueAt(row, 0).toString());
        txt_p_absen.setText(tableModel.getValueAt(row, 1).toString());
        txt_p_tgs.setText(tableModel.getValueAt(row, 2).toString());
        txt_p_uts.setText(tableModel.getValueAt(row, 3).toString());
        txt_p_uas.setText(tableModel.getValueAt(row, 4).toString());
        txt_kehadiran.setText(tableModel.getValueAt(row, 5).toString());
        txt_tugas1.setText(tableModel.getValueAt(row, 6).toString());
        txt_tugas2.setText(tableModel.getValueAt(row, 7).toString());
        txt_tugas3.setText(tableModel.getValueAt(row, 8).toString());
        txt_uts.setText(tableModel.getValueAt(row, 9).toString());
        txt_uas.setText(tableModel.getValueAt(row, 10).toString()); 
        
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
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_p_absen = new javax.swing.JTextField();
        txt_p_tgs = new javax.swing.JTextField();
        txt_p_uts = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_p_uas = new javax.swing.JTextField();
        txt_kode_mk = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_kehadiran = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_tugas3 = new javax.swing.JTextField();
        txt_tugas2 = new javax.swing.JTextField();
        txt_tugas1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_uts = new javax.swing.JTextField();
        txt_uas = new javax.swing.JTextField();
        combo_matkul = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_simulasi_nilai = new javax.swing.JTable();
        btn_tampil = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Vijaya", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM SIMULASI NILAI MAHASISWA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(420, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Input"));

        jLabel3.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel3.setText("Kode Mata Kuliah  :");

        jLabel4.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel4.setText("Nama Mata Kuliah :");

        jLabel5.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel5.setText("Persentase Absen   :");

        jLabel6.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel6.setText("Persentase Tugas   :");

        jLabel7.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel7.setText("Persentase UTS     :");

        txt_p_absen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_p_absenKeyReleased(evt);
            }
        });

        txt_p_tgs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_p_tgsKeyReleased(evt);
            }
        });

        txt_p_uts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_p_utsActionPerformed(evt);
            }
        });
        txt_p_uts.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_p_utsKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel8.setText("Persentase UAS     :");

        txt_p_uas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_p_uasKeyReleased(evt);
            }
        });

        txt_kode_mk.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel9.setText("Kehadiran :");

        jLabel10.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel10.setText("Pertemuan");

        txt_kehadiran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_kehadiranKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel11.setText("Tugas 1    :");

        jLabel12.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel12.setText("Tugas 2    :");

        jLabel13.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel13.setText("Tugas 3    :");

        txt_tugas3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tugas3KeyReleased(evt);
            }
        });

        txt_tugas2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tugas2KeyReleased(evt);
            }
        });

        txt_tugas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tugas1KeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel15.setText("UAS         :");

        jLabel16.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel16.setText("UTS         :");

        txt_uts.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_utsKeyReleased(evt);
            }
        });

        txt_uas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_uasKeyReleased(evt);
            }
        });

        combo_matkul.setFont(new java.awt.Font("Vijaya", 0, 18));
        combo_matkul.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "~Silahkan Pilih~" }));
        combo_matkul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_matkulActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_p_absen, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_p_uas, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                    .addComponent(txt_p_uts, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                    .addComponent(txt_p_tgs, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))))
                        .addGap(120, 120, 120)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel15)
                                .addComponent(jLabel16)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_kehadiran)
                                    .addComponent(txt_tugas1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tugas2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10))
                            .addComponent(txt_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(combo_matkul, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txt_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(txt_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txt_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txt_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(combo_matkul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_p_absen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_p_tgs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_p_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_p_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(57, 57, 57)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Proses")));

        btn_tambah.setFont(new java.awt.Font("Vijaya", 0, 18));
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_ubah.setFont(new java.awt.Font("Vijaya", 0, 18));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_ubah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_hapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_simpan)
                .addGap(18, 18, 18)
                .addComponent(btn_batal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ubah)
                    .addComponent(btn_tambah)
                    .addComponent(btn_hapus)
                    .addComponent(btn_simpan)
                    .addComponent(btn_batal))
                .addGap(34, 34, 34))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Output"));

        tabel_simulasi_nilai.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_simulasi_nilai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_simulasi_nilaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_simulasi_nilai);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        btn_tampil.setFont(new java.awt.Font("Vijaya", 0, 18));
        btn_tampil.setText("Tampilkan Keseluruhan Data");
        btn_tampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampilActionPerformed(evt);
            }
        });

        btn_keluar.setFont(new java.awt.Font("Vijaya", 0, 18)); // NOI18N
        btn_keluar.setText("Kembali");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Pencarian Data"));

        jLabel17.setFont(new java.awt.Font("Vijaya", 0, 18));
        jLabel17.setText("Masukkan Data");

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_cari, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addGap(406, 406, 406))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(198, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(331, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_tampil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 479, Short.MAX_VALUE)
                .addComponent(btn_keluar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tampil)
                    .addComponent(btn_keluar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
// TODO add your handling code here:
membersihkan_teks();
btn_simpan.setEnabled(true);
btn_ubah.setEnabled(false);
btn_hapus.setEnabled(false);
btn_keluar.setEnabled(false);
aktif_teks();   
}//GEN-LAST:event_btn_tambahActionPerformed

private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
// TODO add your handling code here:
    
    //sebelum ubah kode/nama_matkul ambil dahulu kd_mk yang belum di ubah untuk sql where kd_mk='sblm_ubah_kode_mk'
    String sblm_ubah_nama_matkul = tableModel.getValueAt(row, 0).toString();
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
    
    //ambil semua data yang akan di ubah datanya
    
    String ubah_nama_matkul = (String) combo_matkul.getSelectedItem();
    String ubah_kode_matkul = txt_kode_mk.getText();
    String ubah_p_absen = txt_p_absen.getText();
    String ubah_p_tgs = txt_p_tgs.getText();
    String ubah_p_uts = txt_p_uts.getText();
    String ubah_p_uas = txt_p_uas.getText();
    String ubah_tugas1 = txt_tugas1.getText();
    String ubah_tugas2 = txt_tugas2.getText();
    String ubah_tugas3 = txt_tugas3.getText();
    String ubah_uts = txt_uts.getText();
    String ubah_uas = txt_uas.getText();
    
    //variabel untuk indeks dan ket yang di pakai untuk menentukan indeksnya apa dan lulus atau tidak lulus
    String ubah_indeks="T";
    String ubah_ket="TIDAK LULUS";
    //
    
        //jika absensi <11 maka tidak lulus
        String ubah_kehadiran2 = txt_kehadiran.getText();
        // masih ada lanjutan di bawah
    
        //perhitungan semua nilai ketika data di ubah
        Double ubah_nilai_absen = Double.parseDouble(ubah_kehadiran2);
        Double ubah_nilai_p_absen = Double.parseDouble(ubah_p_absen);
        ubah_nilai_absen = (((ubah_nilai_absen/14)*100*ubah_nilai_p_absen)/100);
        
        Double ubah_nilai_p_tgs   = Double.parseDouble(ubah_p_tgs);
        Double ubah_nilai_p_uts   = Double.parseDouble(ubah_p_uts);
        Double ubah_nilai_p_uas   = Double.parseDouble(ubah_p_uas);
        Double ubah_nilai_tugas1 = Double.parseDouble(ubah_tugas1);
        Double ubah_nilai_tugas2 = Double.parseDouble(ubah_tugas2);
        Double ubah_nilai_tugas3 = Double.parseDouble(ubah_tugas3);
        Double ubah_nilai_tugas = (((ubah_nilai_tugas1+ubah_nilai_tugas2+ubah_nilai_tugas3)/3)*(ubah_nilai_p_tgs)/(100));
        Double ubah_nilai_uts = Double.parseDouble(ubah_uts);
        ubah_nilai_uts = ubah_nilai_uts*ubah_nilai_p_uts/100;
        
        Double ubah_nilai_uas = Double.parseDouble(ubah_uas);
        ubah_nilai_uas = ubah_nilai_uas*ubah_nilai_p_uas/100;
        
        Double ubah_nilai_akhir = ubah_nilai_absen + ubah_nilai_tugas + ubah_nilai_uts + ubah_nilai_uas;
        
        Double ubah_nilai_p_jumlah = ubah_nilai_p_absen + ubah_nilai_p_tgs + ubah_nilai_p_uts + ubah_nilai_p_uas;
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
        // masih ada lanjutan di bawah
    
        //convert jdatechooser ke string
    
        //
    
    if((txt_kode_mk.getText().isEmpty()) || 
       (txt_kehadiran.getText().isEmpty()) || 
       (txt_uas.getText().isEmpty()) || 
       (txt_uts.getText().isEmpty()) || 
       (txt_tugas1.getText().isEmpty()) || 
       (txt_tugas2.getText().isEmpty()) || 
       (txt_tugas3.getText().isEmpty()))
    {
        JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, silahkan dilengkapi");
        combo_matkul.requestFocus();
    }
    else
    {
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            //JOptionPane.showMessageDialog(null, sblm_ubah_nim);
            String SQL = "UPDATE `t_simulasi_nilai` "
                         + "SET `kd_mk`='"+ubah_kode_matkul+"',"
                         + " `p_kehadiran`='"+ubah_p_absen+"',"
                         + " `p_tugas`='"+ubah_p_tgs+"',"
                         + " `p_uts`='"+ubah_p_uts+"',"
                         + " `p_uas`='"+ubah_p_uts+"',"
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
                         + " `n_akhir`='"+(String.format("%.2f", ubah_nilai_akhir))+"',"
                         + " `indeks`='"+ubah_indeks+"',"
                         + " `keterangan`='"+ubah_ket+"'"
                         
                    + "WHERE "
                    + "`kd_mk`='"+sblm_ubah_kode_matkul+"';";            
          
            stt.executeUpdate(SQL);
            data[0] = ubah_nama_matkul;
            data[1] = ubah_p_absen;
            data[2] = ubah_p_tgs;
            data[3] = ubah_p_uts;
            data[4] = ubah_p_uas;
            data[5] = ubah_kehadiran2;
            data[6] = ubah_tugas1;
            data[7] = ubah_tugas2;
            data[8] = ubah_tugas3;
            data[9] = ubah_uts;
            data[10] = ubah_uas;
            data[11] = String.valueOf((String.format("%.2f", ubah_nilai_absen)));
            data[12] = String.valueOf((String.format("%.2f", ubah_nilai_tugas)));
            data[13] = String.valueOf((String.format("%.2f", ubah_nilai_uts)));
            data[14] = String.valueOf((String.format("%.2f", ubah_nilai_uas)));
            data[15] = String.valueOf((String.format("%.2f", ubah_nilai_akhir)));
            data[16] = ubah_indeks;
            data[17] = ubah_ket;
            tableModel.removeRow(row);
            tableModel.insertRow(row, data);
            stt.close();
            kon.close();
            membersihkan_teks();
            btn_keluar.setEnabled(true);
            btn_simpan.setEnabled(false);
            nonaktif_teks();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
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

            //ambil kode mk yang data nilainya akan di hapus
            String nama_mk_del =tableModel.getValueAt(row, 0).toString(); //ambil nama dari value tabel yang di pilih
            String SQL3 = "select kd_mk from t_mata_kuliah where nama_mk='"+nama_mk_del+"'";
            ResultSet res3 = stt.executeQuery(SQL3);
            String kd_mk_del = null;
            if(res3.next())
            {
                kd_mk_del = res3.getString(1);
            }
            
            String p_absensi_del =tableModel.getValueAt(row, 1).toString();
            String p_tgs_del =tableModel.getValueAt(row, 2).toString();
            String p_uts_del =tableModel.getValueAt(row, 3).toString();
            String p_uas_del =tableModel.getValueAt(row, 4).toString();
            String absensi_del =tableModel.getValueAt(row, 5).toString();
            String tugas1_del =tableModel.getValueAt(row, 6).toString();
            String tugas2_del =tableModel.getValueAt(row, 7).toString();
            String tugas3_del =tableModel.getValueAt(row, 8).toString();
            String uts_del =tableModel.getValueAt(row, 9).toString();
            String uas_del =tableModel.getValueAt(row, 10).toString();
            String nilai_absen_del =tableModel.getValueAt(row, 11).toString();
            String nilai_tugas_del =tableModel.getValueAt(row, 12).toString();
            String nilai_uts_del =tableModel.getValueAt(row, 13).toString();
            String nilai_uas_del =tableModel.getValueAt(row, 14).toString();
            String nilai_akhir_del =tableModel.getValueAt(row, 15).toString();
            String indeks_del =tableModel.getValueAt(row, 16).toString();
            String keterangan_del =tableModel.getValueAt(row, 17).toString();
            //
            //delete data nilai
            String SQL = "Delete from t_simulasi_nilai "
                            +"where "
                                +"kd_mk='"+kd_mk_del+"'"
                                +"AND p_kehadiran='"+p_absensi_del+"'"
                                +"AND p_tugas='"+p_tgs_del+"'"
                                +"AND p_uts='"+p_uts_del+"'"
                                +"AND p_uas='"+p_uas_del+"'"
                                +"AND absensi='"+absensi_del+"'"
                                +"AND tgs1='"+tugas1_del+"'"
                                +"AND tgs2='"+tugas2_del+"'"
                                +"AND tgs3='"+tugas3_del+"'"
                                +"AND uts='"+uts_del+"'"
                                +"AND uas='"+uas_del+"'"
                                +"AND nilai_absen='"+nilai_absen_del+"'"
                                +"AND nilai_tugas='"+nilai_tugas_del+"'"
                                +"AND nilai_uts='"+nilai_uts_del+"'"
                                +"AND nilai_uas='"+nilai_uas_del+"'"
                                +"AND n_akhir='"+nilai_akhir_del+"'"
                                +"AND indeks='"+indeks_del+"'"
                                +"AND keterangan='"+keterangan_del+"'";
            stt.executeUpdate(SQL);
            tableModel.removeRow(row);
            stt.close();
            kon.close();
            membersihkan_teks();
            nonaktif_teks();
            btn_hapus.setEnabled(false);
            btn_ubah.setEnabled(false);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
        } 
   }
}//GEN-LAST:event_btn_hapusActionPerformed

private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
// TODO add your handling code here:
    String data2[]=new String[18];
        //jika absensi >14 atau <0 maka muncul pesan error
        //convert string to int
        String absn2 = txt_kehadiran.getText();
        int absn = Integer.parseInt(absn2);
        //
        
        //kumpulan variabel
        Double p_absen = Double.parseDouble(txt_p_absen.getText());
        Double p_tgs   = Double.parseDouble(txt_p_tgs.getText());
        Double p_uas   = Double.parseDouble(txt_p_uas.getText());
        Double p_uts   = Double.parseDouble(txt_p_uts.getText());
        Double tugas1 = Double.parseDouble(txt_tugas1.getText());
        Double tugas2 = Double.parseDouble(txt_tugas2.getText());
        Double tugas3 = Double.parseDouble(txt_tugas3.getText());
        Double nilai_uts = Double.parseDouble(txt_uts.getText());
        Double nilai_uas = Double.parseDouble(txt_uas.getText());
        Double nilai_absen = Double.parseDouble(txt_kehadiran.getText());
        
        //
        Double p_jumlah = p_absen + p_tgs + p_uts + p_uas;
        
    if((combo_matkul.getSelectedItem()=="~Silahkan Pilih~") ||
      (txt_kode_mk.getText().isEmpty()) || 
      (txt_p_absen.getText().isEmpty()) || 
      (txt_p_tgs.getText().isEmpty()) || 
      (txt_p_uas.getText().isEmpty()) ||
      (txt_p_uts.getText().isEmpty()) || 
      (txt_kehadiran.getText().isEmpty()) || 
      (txt_uas.getText().isEmpty()) || 
      (txt_uts.getText().isEmpty()) ||
      (txt_tugas1.getText().isEmpty()) || 
      (txt_tugas2.getText().isEmpty()) ||
      (txt_tugas3.getText().isEmpty()))
    {
        JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, Silahkan dilengkapi");
        combo_matkul.requestFocus();
    }
    else if(p_jumlah>100)
    {
        JOptionPane.showMessageDialog(null, "Jumlah persentase tidak boleh melebihi 100%","Pesan",JOptionPane.INFORMATION_MESSAGE);
    }
    else if(p_jumlah!=100)
    {
        JOptionPane.showMessageDialog(null, "Jumlah persentase harus 100%","Pesan",JOptionPane.INFORMATION_MESSAGE);
    }
    else
    {
        nilai_absen = (((nilai_absen/14)*100*p_absen)/100);
        Double nilai_tugas = (((tugas1+tugas2+tugas3)/3)*(p_tgs)/(100));
        nilai_uts = nilai_uts*p_uts/100;
        nilai_uas = nilai_uas*p_uas/100;
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
        
        //memasukkan nilai ke variabel global
        get_kd_mk = txt_kode_mk.getText();
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "insert into t_simulasi_nilai(kd_mk,"
                                                + "p_kehadiran,"
                                                + "p_tugas,"
                                                + "p_uts,"
                                                + "p_uas,"
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
                                                + "n_akhir,"
                                                + "indeks,"
                                                + "keterangan)"
                                                    + " VALUES "
                                                + "('"+get_kd_mk+"',"
                                                + " '"+txt_p_absen.getText()+" ',"
                                                + " '"+txt_p_tgs.getText()+"',"
                                                + " '"+txt_p_uts.getText()+"',"
                                                + " '"+txt_p_uas.getText()+"',"
                                                + " '"+txt_kehadiran.getText()+"',"
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
                                                + " '"+ket+"')";
            
            stt.executeUpdate(SQL);
            //memasukkan data ke variabel global yang akan di pakai ketika batal
            batal_kode_mk = txt_kode_mk.getText();
            batal_p_kehadiran = txt_p_absen.getText();
            batal_p_tugas = txt_p_tgs.getText();
            batal_p_uts = txt_p_uts.getText();
            batal_p_uas = txt_p_uas.getText();
            batal_absensi = txt_kehadiran.getText();
            batal_tgs1 = txt_tugas1.getText();
            batal_tgs2 = txt_tugas2.getText();
            batal_tgs3 = txt_tugas3.getText();
            batal_uts = txt_uts.getText();
            batal_uas = txt_uas.getText();
            data2[0] = (String )combo_matkul.getSelectedItem();
            data2[1] = txt_p_absen.getText();
            data2[2] = txt_p_tgs.getText();
            data2[3] = txt_p_uts.getText();
            data2[4] = txt_p_uas.getText();
            data2[5] = txt_kehadiran.getText();
            data2[6] = txt_tugas1.getText();
            data2[7] = txt_tugas2.getText();
            data2[8] = txt_tugas3.getText();
            data2[9] = txt_uts.getText();
            data2[10] = txt_uas.getText();
            data2[11] = String.valueOf((String.format("%.2f", nilai_absen)));
            data2[12] = String.valueOf((String.format("%.2f", nilai_tugas)));
            data2[13] = String.valueOf((String.format("%.2f", nilai_uts)));
            data2[14] = String.valueOf((String.format("%.2f", nilai_uas)));
            data2[15] = String.valueOf((String.format("%.2f", nilai_akhir)));
            data2[16] = indeks;
            data2[17] = ket;
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

}//GEN-LAST:event_btn_simpanActionPerformed

private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
// TODO add your handling code here:
    try
    {
        membersihkan_teks();
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        String SQL = "Delete from t_simulasi_nilai "
                            +"where "
                                +"kd_mk='"+batal_kode_mk+"'"
                                +"AND p_kehadiran='"+batal_p_kehadiran+"'"
                                +"AND p_tugas='"+batal_p_tugas+"'"
                                +"AND p_uts='"+batal_p_uts+"'"
                                +"AND p_uas='"+batal_p_uas+"'"
                                +"AND absensi='"+batal_absensi+"'"
                                +"AND tgs1='"+batal_tgs1+"'"
                                +"AND tgs2='"+batal_tgs2+"'"
                                +"AND tgs3='"+batal_tgs3+"'"
                                +"AND uts='"+batal_uts+"'"
                                +"AND uas='"+batal_uas+"'";
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

private void tabel_simulasi_nilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_simulasi_nilaiMouseClicked
// TODO add your handling code here:
    if(evt.getClickCount()==1)
    {
        tampil_field();
    }
}//GEN-LAST:event_tabel_simulasi_nilaiMouseClicked

private void btn_tampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tampilActionPerformed
// TODO add your handling code here:
    tableModel.setRowCount(0);
    txt_cari.setText(null);
    txt_cari.requestFocus();
    settableload();
}//GEN-LAST:event_btn_tampilActionPerformed

private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
// TODO add your handling code here:
    frm_utama utama = new frm_utama();
    //ga tau salahnya apa, aneh jadi beda dari form yang lain
    //utama.setDefaultCloseOperation(frm_utama.DISPOSE_ON_CLOSE);
    utama.setVisible(true);
    dispose();
}//GEN-LAST:event_btn_keluarActionPerformed

private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txt_cariActionPerformed

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
        String SQL = "SELECT matkul.nama_mk,"
                                        +"nilai.p_kehadiran,"
                                        +"nilai.p_tugas,"
                                        +"nilai.p_uts,"
                                        +"nilai.p_uas,"
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
                                        +"nilai.n_akhir,"
                                        +"nilai.indeks,"
                                        +"nilai.keterangan"
                                +" FROM t_mata_kuliah matkul JOIN t_simulasi_nilai nilai"
                                +" ON matkul.`kd_mk` = nilai.`kd_mk`"
                    +" WHERE matkul.nama_mk LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.p_kehadiran LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.p_tugas LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.p_uts LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.p_uas LIKE '"+txt_cari.getText()+"%'"
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
                    +" OR nilai.n_akhir LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.indeks LIKE '"+txt_cari.getText()+"%'"
                    +" OR nilai.keterangan LIKE '"+txt_cari.getText()+"%'";
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
            data[15] = res.getString(16);
            data[16] = res.getString(17);
            data[17] = res.getString(18);
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
        String SQL = "select * from `t_mata_kuliah` where `nama_mk`='"+nama_mk+"'";
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

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
// TODO add your handling code here:
     try
    {
        Class.forName(driver);
        Connection kon = DriverManager.getConnection(database,user,pass);
        Statement stt = kon.createStatement();
        
        String SQL2 = "select * from t_mata_kuliah";
        ResultSet res = stt.executeQuery(SQL2);
        while(res.next())
        {
            combo_matkul.addItem(res.getString("nama_mk"));//Menampilkan Data dalam Table kedalam ComboBox
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

    private void txt_p_absenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_p_absenKeyReleased
        // TODO add your handling code here:
        Double p_absen = Double.parseDouble(txt_p_absen.getText());
        if(p_absen>100)
        {
            JOptionPane.showMessageDialog(null, "Persentase Absen tidak boleh lebih dari 100","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (p_absen<0)
        {
            JOptionPane.showMessageDialog(null, "Persentase Absen tidak boleh kurang dari 0","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txt_p_absenKeyReleased

    private void txt_p_tgsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_p_tgsKeyReleased
        // TODO add your handling code here:
        Double p_tugas = Double.parseDouble(txt_p_tgs.getText());
        if(p_tugas>100)
        {
            JOptionPane.showMessageDialog(null, "Persentase Tugas tidak boleh lebih dari 100","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (p_tugas<0)
        {
            JOptionPane.showMessageDialog(null, "Persentase Tugas tidak boleh kurang dari 0","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txt_p_tgsKeyReleased

    private void txt_p_utsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_p_utsKeyReleased
        // TODO add your handling code here:
        Double p_uts = Double.parseDouble(txt_p_uts.getText());
        if(p_uts>100)
        {
            JOptionPane.showMessageDialog(null, "Persentase UTS tidak boleh lebih dari 100","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (p_uts<0)
        {
            JOptionPane.showMessageDialog(null, "Persentase UTS tidak boleh kurang dari 0","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txt_p_utsKeyReleased

    private void txt_p_utsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_p_utsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_p_utsActionPerformed

    private void txt_p_uasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_p_uasKeyReleased
        // TODO add your handling code here:
        Double p_uas = Double.parseDouble(txt_p_uas.getText());
        if(p_uas>100)
        {
            JOptionPane.showMessageDialog(null, "Persentase UAS tidak boleh lebih dari 100","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
        else if (p_uas<0)
        {
            JOptionPane.showMessageDialog(null, "Persentase UAS tidak boleh kurang dari 0","Pesan",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txt_p_uasKeyReleased

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
            java.util.logging.Logger.getLogger(frm_simulasi_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_simulasi_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_simulasi_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_simulasi_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new frm_simulasi_nilai().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel_simulasi_nilai;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_kehadiran;
    private javax.swing.JTextField txt_kode_mk;
    private javax.swing.JTextField txt_p_absen;
    private javax.swing.JTextField txt_p_tgs;
    private javax.swing.JTextField txt_p_uas;
    private javax.swing.JTextField txt_p_uts;
    private javax.swing.JTextField txt_tugas1;
    private javax.swing.JTextField txt_tugas2;
    private javax.swing.JTextField txt_tugas3;
    private javax.swing.JTextField txt_uas;
    private javax.swing.JTextField txt_uts;
    // End of variables declaration//GEN-END:variables
}
