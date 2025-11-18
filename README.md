# Koleksi Program Java

Repository ini berisi kumpulan program Java yang mendemonstrasikan berbagai konsep dan algoritma pemrograman. Setiap program didokumentasikan dengan baik menggunakan komentar dan menyertakan kasus uji untuk validasi.

## Daftar Isi

1. [Mahasiswa.java](#mahasiswajava) - Sistem Manajemen Mahasiswa
2. [TopKFrequent.java](#topkfrequentjava) - Algoritma K Elemen Terbanyak
3. [ValidateNestedJSON.java](#validatenestedjsonjava) - Validator Struktur JSON
4. [WordCounter.java](#wordcounterjava) - Penghitung Frekuensi Kata

---

## Mahasiswa.java

### Ikhtisar
Kelas manajemen mahasiswa yang komprehensif yang mendemonstrasikan prinsip-prinsip pemrograman berorientasi objek termasuk enkapsulasi, konstruktor, getter/setter, dan implementasi logika bisnis.

### Fitur
- **Enkapsulasi**: Atribut privat dengan metode getter/setter publik
- **Beberapa Konstruktor**: Konstruktor default dan berparameter
- **Logika Bisnis**: Penentuan status kelulusan berdasarkan threshold nilai
- **Validasi Data**: Penanganan kasus batas untuk nilai batas
- **Pengujian Komprehensif**: Kasus uji bawaan dengan berbagai skenario

### Struktur Kelas
```java
public class Mahasiswa {
    // Atribut privat
    private String nama;    // Nama mahasiswa
    private String nim;     // NIM mahasiswa
    private double nilai;   // Nilai mahasiswa
    
    // Konstruktor
    public Mahasiswa()                           // Konstruktor default
    public Mahasiswa(String nama, String nim, double nilai)  // Berparameter
    
    // Metode inti
    public void setData(String nama, String nim, double nilai)
    public void cetakStatusKelulusan()
    public boolean isLulus()
    
    // Metode Getter/Setter
    // ... (getter dan setter individual untuk setiap atribut)
}
```

### Aturan Bisnis
- **Kriteria Kelulusan**: Nilai > 70.0
- **Rentang Nilai**: Menerima nilai double apa pun
- **Kasus Batas**: 
  - Nilai = 70.0 → TIDAK LULUS
  - Nilai = 70.1 → LULUS

### Contoh Penggunaan
```java
// Buat mahasiswa dengan konstruktor
Mahasiswa mhs1 = new Mahasiswa("Ahmad Rizki", "2021001234", 85.5);
mhs1.cetakStatusKelulusan();

// Buat mahasiswa kosong lalu set data
Mahasiswa mhs2 = new Mahasiswa();
mhs2.setData("Siti Nurhaliza", "2021001235", 65.0);
mhs2.cetakStatusKelulusan();

// Periksa status kelulusan
if (mhs1.isLulus()) {
    System.out.println("Mahasiswa lulus!");
}
```

### Contoh Output
```
=== Data Mahasiswa ===
Nama    : Ahmad Rizki
NIM     : 2021001234
Nilai   : 85.5
Status  : LULUS
========================
```

---

## TopKFrequent.java

### Ikhtisar
Implementasi efisien dari algoritma K Elemen Terbanyak menggunakan min-heap (Priority Queue) dengan logika pengurutan kustom. Solusi ini mengoptimalkan kompleksitas waktu dan ruang.

### Detail Algoritma

#### Kompleksitas Waktu: **O(n log k)**
- **O(n)**: Membangun peta frekuensi dengan HashMap
- **O(n log k)**: Memelihara antrian prioritas ukuran k
- **O(k log k)**: Mengekstrak dan mengurutkan hasil akhir

#### Kompleksitas Ruang: **O(n)**
- **O(n)**: Penyimpanan peta frekuensi
- **O(k)**: Penyimpanan antrian prioritas

### Fitur Utama
- **Komparator Kustom**: Menangani pengurutan frekuensi dan alfabetis
- **Strategi Min-Heap**: Memelihara hanya k elemen yang paling sering
- **Validasi Input**: Menangani kasus batas dan input tidak valid
- **Performa Optimal**: Efisien untuk dataset besar

### Logika Algoritma
1. **Penghitungan Frekuensi**: Hitung kemunculan setiap kata menggunakan HashMap
2. **Antrian Prioritas**: Gunakan min-heap dengan komparator kustom:
   - Pengurutan primer: Frekuensi (naik untuk min-heap)
   - Pengurutan sekunder: Alfabetis (turun saat frekuensi sama)
3. **Pemeliharaan Ukuran**: Pertahankan hanya k elemen dalam heap
4. **Ekstraksi Hasil**: Ekstrak dan balikkan untuk urutan yang benar

### Tanda Tangan Metode
```java
public static List<String> topKFrequent(String[] words, int k)
```

### Contoh Penggunaan
```java
String[] words = {"java", "python", "java", "golang", "java", "python"};
int k = 2;
List<String> result = TopKFrequent.topKFrequent(words, k);
// Output: ["java", "python"]
```

### Aturan Pengurutan
1. **Primer**: Berdasarkan frekuensi (tertinggi pertama)
2. **Sekunder**: Urutan alfabetis (A-Z) saat frekuensi sama

### Kasus Batas yang Ditangani
- Array input kosong
- k ≤ 0 atau k > jumlah kata unik
- Kata dengan frekuensi sama
- Array kata tunggal

---

## ValidateNestedJSON.java

### Ikhtisar
Validator struktur JSON yang kuat yang secara rekursif memeriksa apakah objek Java sesuai dengan format JSON yang valid. Mendukung objek bersarang, array, dan tipe primitif JSON.

### Aturan Validasi
Struktur JSON yang valid dapat berisi:
- **Objek**: Map dengan kunci String dan nilai JSON yang valid
- **Array**: List atau array dengan elemen JSON yang valid
- **Primitif**: String, Number, Boolean, null

### Detail Algoritma

#### Kompleksitas Waktu: **O(n)**
- n = jumlah total elemen dalam struktur
- Setiap elemen dikunjungi tepat sekali

#### Kompleksitas Ruang: **O(d)**
- d = kedalaman rekursi maksimum
- Ruang tumpukan panggilan untuk validasi rekursif

### Metode Inti
```java
public static boolean isValidJson(Object input)           // Validator utama
private static boolean isValidJsonObject(Map<?, ?> map)   // Validator objek
private static boolean isValidJsonArray(Object array)     // Validator array
private static boolean isPrimitiveJsonType(Object obj)    // Pemeriksa primitif
```

### Logika Validasi
1. **Pemeriksaan Null**: null adalah nilai JSON yang valid
2. **Pemeriksaan Primitif**: String, Number, Boolean adalah valid
3. **Validasi Objek**: 
   - Harus Map dengan kunci String
   - Semua nilai harus JSON yang valid (rekursif)
4. **Validasi Array**:
   - Mendukung List dan array native
   - Semua elemen harus JSON yang valid (rekursif)

### Contoh Penggunaan

#### Struktur JSON Valid
```java
// Objek sederhana
Map<String, Object> valid = new HashMap<>();
valid.put("name", "Bejo");
valid.put("age", 30);
valid.put("active", true);

// Struktur bersarang
Map<String, Object> nested = new HashMap<>();
nested.put("person", Map.of("name", "John"));
nested.put("tags", Arrays.asList("java", "programming"));
nested.put("scores", new int[]{90, 85, 95});
```

#### Struktur JSON Tidak Valid
```java
// Kunci non-String
Map<Object, Object> invalid1 = new HashMap<>();
invalid1.put(123, "number as key");

// Tipe nilai tidak valid
Map<String, Object> invalid2 = new HashMap<>();
invalid2.put("key", new Thread());  // Thread bukan tipe JSON
```

### Kasus Uji yang Disertakan
- Objek sederhana yang valid
- Objek dan array bersarang yang valid
- Kunci non-String tidak valid
- Tipe nilai tidak valid
- Validasi tipe primitif
- Validasi array (List dan array native)
- Struktur bersarang kompleks

---

## WordCounter.java

### Ikhtisar
Utilitas analisis teks yang menghitung frekuensi kata dalam teks input dengan normalisasi untuk penghitungan yang tidak peka huruf besar/kecil dan penghapusan tanda baca.

### Fitur
- **Tidak Peka Huruf Besar/Kecil**: Memperlakukan "Java" dan "java" sebagai kata yang sama
- **Penghapusan Tanda Baca**: Menghapus karakter non-alfabetis
- **Pengurutan Alfabetis**: Menampilkan hasil dalam urutan A-Z
- **Validasi Input**: Menangani input kosong dan null
- **Mode Interaktif**: Antarmuka baris perintah untuk input pengguna

### Detail Algoritma

#### Kompleksitas Waktu: **O(n)**
- n = panjang teks input
- Satu pass melalui teks untuk pemisahan kata
- Operasi HashMap adalah O(1) rata-rata

#### Kompleksitas Ruang: **O(w)**
- w = jumlah kata unik
- Penyimpanan untuk peta frekuensi kata

### Metode Inti
```java
public static Map<String, Integer> countWords(String text)    // Logika penghitungan utama
public static void displayWordCount(String text)              // Tampilkan hasil
```

### Alur Pemrosesan
1. **Validasi Input**: Periksa input null atau kosong
2. **Pemisahan Kata**: Pisah berdasarkan whitespace menggunakan regex `\\s+`
3. **Normalisasi**: 
   - Hapus karakter non-alfabetis: `[^a-zA-Z]`
   - Konversi ke huruf kecil
4. **Penghitungan**: Perbarui frekuensi dalam HashMap
5. **Pengurutan**: Gunakan TreeMap untuk output alfabetis
6. **Tampilan**: Format dan cetak hasil

### Contoh Penggunaan
```java
// Panggilan metode langsung
String text = "Java programming is fun! Java is powerful.";
Map<String, Integer> counts = WordCounter.countWords(text);
// Hasil: {java=2, programming=1, is=2, fun=1, powerful=1}

// Mode interaktif
WordCounter.main(args);
// Meminta input pengguna dan menampilkan hasil
```

### Contoh Input/Output
```
=== Word Counter Program ===
Masukkan kalimat atau teks:
> Java programming is fun! Java is powerful.

Input: "Java programming is fun! Java is powerful."
Output:
fun → 1
is → 2
java → 2
programming → 1
powerful → 1
```

### Aturan Normalisasi
- **Huruf**: Semua kata dikonversi ke huruf kecil
- **Tanda Baca**: Semua karakter non-alfabetis dihapus
- **Whitespace**: Spasi/tab/baris baru berurutan diperlakukan sebagai pemisah tunggal
- **Kata Kosong**: Dilewati setelah normalisasi

---

## Cara Menjalankan

### Prasyarat
- Java Development Kit (JDK) 8 atau lebih tinggi
- IDE Java atau kompiler baris perintah

### Metode 1: Kompilasi Lokal
```bash
# Kompilasi file individual
javac Mahasiswa.java
javac TopKFrequent.java
javac ValidateNestedJSON.java
javac WordCounter.java

# Atau kompilasi semua sekaligus
javac *.java
```

### Eksekusi Lokal
```bash
# Jalankan setiap program
java Mahasiswa
java TopKFrequent
java ValidateNestedJSON
java WordCounter
```

### Metode 2: Running via JDoodle Online Compiler

JDoodle adalah compiler online yang memungkinkan Anda menjalankan kode Java langsung di browser tanpa instalasi lokal.

#### Langkah-langkah Menggunakan JDoodle:

1. **Buka JDoodle**: Kunjungi [https://www.jdoodle.com/online-java-compiler](https://www.jdoodle.com/online-java-compiler)

2. **Pilih Program**: Pilih salah satu file Java yang ingin dijalankan:

   #### Mahasiswa.java
   - Copy seluruh kode dari [`Mahasiswa.java`](Mahasiswa.java:1)
   - Paste di editor JDoodle
   - Klik "Execute"
   - **Output yang diharapkan**: Test case untuk 4 mahasiswa dengan status kelulusan

   #### TopKFrequent.java
   - Copy seluruh kode dari [`TopKFrequent.java`](TopKFrequent.java:1)
   - Paste di editor JDoodle
   - Klik "Execute"
   - **Output yang diharapkan**: `[java, python]`

   #### ValidateNestedJSON.java
   - Copy seluruh kode dari [`ValidateNestedJSON.java`](ValidateNestedJSON.java:1)
   - Paste di editor JDoodle
   - Klik "Execute"
   - **Output yang diharapkan**: 13 test case dengan hasil true/false

   #### WordCounter.java
   - Copy seluruh kode dari [`WordCounter.java`](WordCounter.java:1)
   - Paste di editor JDoodle
   - Klik "Execute"
   - **Catatan**: Program ini memerlukan input interaktif. Di JDoodle, input dapat dimasukkan melalui tab "STDIN"

3. **Tips untuk JDoodle**:
   - **Input Interaktif** (untuk WordCounter):
     - Setelah menjalankan program, gunakan tab "STDIN" untuk input
     - Masukkan teks contoh: `Java programming is fun! Java is powerful.`
     - Klik "Execute" lagi untuk melihat hasil
   
   - **Multiple Classes**:
     - JDoodle otomatis mengompilasi semua class yang diperlukan
     - Tidak perlu mengompilasi secara manual
   
   - **Output Format**:
     - Output akan muncul di tab "STDOUT"
     - Error akan muncul di tab "STDERR"

4. **Keuntungan Menggunakan JDoodle**:
   - **Tidak perlu instalasi**: Cukup browser dan koneksi internet
   - **Instant execution**: Kompilasi dan jalankan dengan satu klik
   - **Multi-version support**: Dapat memilih versi Java yang berbeda
   - **Share code**: Dapat berbagi kode melalui URL
   - **No setup required**: Langsung bisa coding

5. **Keterbatasan JDoodle**:
   - **Time limit**: Maksimal 10 detik untuk program yang berjalan
   - **Memory limit**: Batas memori tertentu
   - **Network access**: Tidak dapat mengakses jaringan eksternal
   - **File system**: Akses file terbatas

#### Contoh Running di JDoodle:

**Untuk Mahasiswa.java:**
1. Buka JDoodle
2. Copy dan paste kode Mahasiswa.java
3. Klik "Execute"
4. Lihat output test case mahasiswa

**Untuk WordCounter.java:**
1. Buka JDoodle
2. Copy dan paste kode WordCounter.java
3. Di tab "STDIN", masukkan: `Java programming is fun! Java is powerful.`
4. Klik "Execute"
5. Lihat hasil penghitungan kata

### Metode 3: IDE Instructions
1. Impor file Java ke IDE Anda
2. Jalankan metode `main()` dari setiap kelas
3. Tinjau output konsol untuk hasil uji


---

## Spesifikasi Teknis

### Kompatibilitas Versi Java
- **Minimum**: Java 8
- **Direkomendasikan**: Java 11 atau lebih tinggi
- **Diuji**: Java 17
- **JDoodle Support**: Java 8, 11, 17, dan versi terbaru

### Dependensi
- **Hanya Pustaka Standar**: Tidak ada dependensi eksternal yang diperlukan
- **Kelas Bawaan**: HashMap, TreeMap, PriorityQueue, ArrayList, dll.
- **JDoodle Compatible**: Semua kelas standar Java tersedia di JDoodle

### Fitur Kualitas Kode
- **Dokumentasi Komprehensif**: Komentar JavaDoc untuk semua metode
- **Penanganan Kesalahan**: Validasi input dan manajemen kasus batas
- **Cakupan Uji**: Kasus uji bawaan dengan output yang diharapkan
- **Analisis Performa**: Dokumentasi kompleksitas waktu dan ruang

### Platform Support
- **Local Development**: Windows, macOS, Linux
- **Online Compilers**: JDoodle, Replit, OnlineGDB
- **IDE Support**: IntelliJ IDEA, Eclipse, VS Code, NetBeans

---

## Berkontribusi

Saat berkontribusi ke basis kode ini:
1. Ikuti konvensi penamaan Java
2. Tambahkan komentar JavaDoc yang komprehensif
3. Sertakan kasus uji untuk fungsionalitas baru
4. Dokumentasikan kompleksitas waktu dan ruang
5. Pertahankan gaya kode yang konsisten
6. Pastikan kode berjalan baik di lokal maupun di JDoodle

---

## Lisensi

Proyek ini disediakan untuk tujuan pendidikan. Jangan ragu untuk menggunakan, memodifikasi, dan mendistribusikan kode untuk pembelajaran dan pengembangan.

---

## Quick Start dengan JDoodle

Ingin mencoba kode ini tanpa instalasi? Kunjungi [JDoodle Online Java Compiler](https://www.jdoodle.com/online-java-compiler) dan:

1. Pilih file yang ingin dijalankan
2. Copy kode dari repository ini
3. Paste di JDoodle editor
4. Klik Execute untuk menjalankan

**Tidak perlu setup, langsung coding!**
