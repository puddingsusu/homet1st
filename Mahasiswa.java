public class Mahasiswa {
    // Atribut private untuk enkapsulasi
    private String nama;
    private String nim;
    private double nilai;

    /**
     * Constructor default
     */
    public Mahasiswa() {
        this.nama = "";
        this.nim = "";
        this.nilai = 0.0;
    }

    /**
     * Constructor dengan parameter
     */
    public Mahasiswa(String nama, String nim, double nilai) {
        this.nama = nama;
        this.nim = nim;
        this.nilai = nilai;
    }

    /**
     * Method untuk set data mahasiswa
     */
    public void setData(String nama, String nim, double nilai) {
        this.nama = nama;
        this.nim = nim;
        this.nilai = nilai;
    }

    /**
     * Method untuk mencetak status kelulusan
     */
    public void cetakStatusKelulusan() {
        System.out.println("=== Data Mahasiswa ===");
        System.out.println("Nama    : " + nama);
        System.out.println("NIM     : " + nim);
        System.out.println("Nilai   : " + nilai);
        System.out.println("Status  : " + (nilai > 70 ? "LULUS" : "TIDAK LULUS"));
        System.out.println("========================");
    }

    /**
     * Method untuk mengecek status kelulusan (return boolean)
     */
    public boolean isLulus() {
        return nilai > 70;
    }

    // Getter methods
    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public double getNilai() {
        return nilai;
    }

    // Setter methods individual
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    /**
     * Method main untuk testing
     */
    public static void main(String[] args) {
        System.out.println("=== Test Class Mahasiswa ===\n");

        // Test 1: Membuat objek dengan constructor
        Mahasiswa mhs1 = new Mahasiswa("Ahmad Rizki", "2021001234", 85.5);
        mhs1.cetakStatusKelulusan();

        // Test 2: Membuat objek kosong lalu set data
        Mahasiswa mhs2 = new Mahasiswa();
        mhs2.setData("Siti Nurhaliza", "2021001235", 65.0);
        mhs2.cetakStatusKelulusan();

        // Test 3: Edge case - nilai tepat 70
        Mahasiswa mhs3 = new Mahasiswa();
        mhs3.setNama("Budi Santoso");
        mhs3.setNim("2021001236");
        mhs3.setNilai(70.0);
        mhs3.cetakStatusKelulusan();

        // Test 4: Edge case - nilai 70.1
        Mahasiswa mhs4 = new Mahasiswa("Dewi Lestari", "2021001237", 70.1);
        mhs4.cetakStatusKelulusan();

      
    }
}