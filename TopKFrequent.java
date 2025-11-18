import java.util.*;

public class TopKFrequent {
    
    /**
     * Mengembalikan k kata paling sering muncul dari array words
     * Urutan: berdasarkan frekuensi (tinggi ke rendah), jika sama urut alfabetis
     *
     * Time Complexity: O(n log k) dimana n adalah jumlah kata dalam array words
     * - O(n) untuk menghitung frekuensi dengan HashMap
     * - O(n log k) untuk mempertahankan priority queue dengan ukuran maksimum k
     *
     * Space Complexity: O(n + k) = O(n)
     * - O(n) untuk menyimpan frequency map
     * - O(k) untuk priority queue
     *
     * @param words array of string words
     * @param k jumlah kata yang ingin dikembalikan
     * @return list of k kata paling frequent, diurutkan sesuai aturan
     */
    public static List<String> topKFrequent(String[] words, int k) {
        // Input validation
        if (words == null || words.length == 0 || k <= 0) {
            return new ArrayList<>();
        }

        // 1. Hitung frekuensi setiap kata menggunakan HashMap - O(n) time, O(n) space
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        
        // 2. Buat Priority Queue dengan custom comparator - O(1) setup
        // Menggunakan min-heap untuk mempertahankan k elemen terbesar
        // Comparator: urutkan berdasarkan frekuensi (ascending untuk min-heap)
        // Jika frekuensi sama, urutkan descending (Z ke A) karena min-heap akan menghapus yang lebih besar
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a.getValue().equals(b.getValue())) {
                    // Jika frekuensi sama, urutkan descending (Z ke A)
                    // Dalam min-heap, elemen yang "lebih besar" (secara alfabetis) akan dihapus pertama
                    // Sehingga yang tersisa adalah elemen dengan urutan alfabetis lebih awal
                    return b.getKey().compareTo(a.getKey());
                }
                // Urutkan ascending berdasarkan frekuensi
                return a.getValue() - b.getValue();
            }
        );
        
        // 3. Tambahkan semua entries ke priority queue - O(n log k) time, O(k) space
        // Setiap offer operation: O(log k), dan kita melakukan n kali
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            pq.offer(entry);
            
            // Jika size melebihi k, hapus yang paling kecil
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        // 4. Ekstrak hasil dari priority queue ke list - O(k log k) time
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll().getKey());
        }
        
        // 5. Reverse list karena kita mengambil dari min-heap - O(k) time
        // Hasil dari min-heap adalah ascending, kita butuh descending
        Collections.reverse(result);
        
        return result;
    }
    
    // Method untuk testing
    public static void main(String[] args) {
        // Test case - contoh dari soal
        String[] words = {"java", "python", "java", "golang", "java", "python"};
        int k = 2;
        List<String> result = topKFrequent(words, k);
        System.out.println("Test: " + result); // Expected: ["java", "python"]
    }
}