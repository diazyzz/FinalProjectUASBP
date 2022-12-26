import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class FinalProjectUAS {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("FINAL PROJECT MATAKULIAH BAHASA PEMROGRAMAN\nNama : Dias Norman\nNPM : 22082010134\nKelas : Paralel 1D\n\nDosen pengampu : Pak Doddy Ridwandono S.Kom, M.Kom");


        //pilih operasi
        System.out.println();
        System.out.println(RED + "===============");
        System.out.println(RED + "SELAMAT DATANG!");
        System.out.println(RED + "===============");

        do {
            System.out.println(" ");
            System.out.println(PURPLE + "Pilih operasi anda");
            for (int i = 0; i < operasi.length; i++) {
                System.out.println(CYAN + (i + 1) + ". " + operasi[i]);
            }
            System.out.println(CYAN + "4. Selesai");
            System.out.print(PURPLE + "Pilih operasi : ");
            pilihOperasi = s.nextInt();

            switch (pilihOperasi) {
                case 1 -> Kasir();
                case 2 -> Admin();
                case 3 -> Owner();
                case 4 ->
                        System.out.println(RED + "TERIMAKASIH!");
            }
        } while (pilihOperasi != 4);
    }

    //-----------------------------------------------------------------------------------------------------
    public static final String YELLOW = "\033[0;33m";
    public static final String CYAN = "\033[0;36m";
    public static final String BLUE = "\033[0;34m";
    public static final String RED = "\033[0;31m";
    public static final String PURPLE = "\033[0;35m";

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    static LocalDateTime waktuSekarang = LocalDateTime.now();
    static String tanggaldanWaktu = waktuSekarang.format(formatter);
    static String[] operasi = {"Kasir", "Admin", "Owner"};
    static String[] produkjual = {"LAPTOP", "TV", "HP"};
    static int[] hargaProduk = {699, 59, 159};
    static int[] palingLaris = {0, 0, 0};
    static String[] laporanPenjualan = {};
    static int pilihOperasi;
    static int pilihKasir;
    static int pilihAdmin;
    static int pilihOwner;


    static void Kasir() {
        Scanner s = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println(RED + "Anda memilih operasi kasir!");
            System.out.println(RED + "===========================");
            System.out.println();
            System.out.println(CYAN + "1. Cari barang\n2. Layani transaksi\n3. Kembali ke Operasi");
            System.out.print(PURPLE + "Pilih : ");
            pilihKasir = s.nextInt();


            //Cari barang
            if (pilihKasir == 1) {
                int pilihKasir1;
                do {
                    System.out.println();
                    System.out.print(CYAN + "Barang yang dicari : ");
                    String caribarang = s.next();
                    System.out.println();
                    mCari(produkjual, hargaProduk, caribarang);

                    System.out.println();
                    System.out.print(CYAN + "Apakah anda ingin mencari barang lagi? ");
                    System.out.println(PURPLE + "1. Ya | 2. Tidak");
                    pilihKasir1 = s.nextInt();
                } while (pilihKasir1 == 1);
            }

            // Layani Transaksi
            else if (pilihKasir == 2) {
                int pilihKasir2, totalBayar;
                totalBayar = 0;
                do {
                    System.out.println();

                    System.out.println(CYAN + "Barang yang tersedia dan daftar harga : ");
                    for (int i = 0; i < produkjual.length; i++) {
                        System.out.println(BLUE + produkjual[i] + " $" + hargaProduk[i]);
                    }
                    System.out.print(PURPLE + "Masukkan barang yang ingin dibeli : ");
                    String barangDibeli = s.next();
                    int ind = mCari(produkjual, hargaProduk, barangDibeli);
                    if (ind == -1) {
                        System.out.print(CYAN + "Barang tidak ditemukan");
                    } else {
                        System.out.println(CYAN + "Nama barang : " + produkjual[ind]);
                        System.out.println(CYAN + "Harga barang : $" + hargaProduk[ind]);
                        System.out.print(PURPLE + "Total beli : ");
                        int jumlah = s.nextInt();
                        int hargatotal = hargaProduk[ind] * jumlah;
                        totalBayar += hargatotal;
                        palingLaris[ind] = jumlah;
                        System.out.println(BLUE + "Harga total : $" + hargatotal);
                    }
                    System.out.print(CYAN + "Apakah anda ingin membeli barang lagi? ");
                    System.out.println(PURPLE + "1. Ya | 2. Checkout");
                    pilihKasir2 = s.nextInt();
                } while (pilihKasir2 != 2);
                System.out.println(BLUE + "Total harga barang belian : $" + totalBayar);
                System.out.print(PURPLE + "Masukkan uang pembayaran : ");
                int pembayaran = s.nextInt();
                while (pembayaran < totalBayar) {
                    System.out.println(RED + "Uang anda kurang!");
                    System.out.print(PURPLE + "Masukkan uang pembayaran : ");
                    pembayaran = s.nextInt();
                }
                int kembalian = pembayaran - totalBayar;

                if (kembalian > 0) {
                    System.out.println(BLUE + "Kembalian anda : $" + kembalian);
                    laporanPenjualan = inputProduk(laporanPenjualan, "Total bayar $ " + totalBayar + " Uang pelanggan : $ " + pembayaran);
                } else {
                    System.out.println(YELLOW + "Uang anda pas!");
                }
                System.out.println(YELLOW + "TERIMAKASIH SUDAH BERBELANJA!");
            }
        } while (pilihKasir != 3);
    }

    static void Admin() {
        Scanner s = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println(RED + "Anda memilih operasi admin!");
            System.out.println(RED + "===========================");
            System.out.println();
            System.out.println(CYAN + "1. Tampilkan produk\n2. Tambah produk\n3. Ubah produk\n4. Hapus produk\n5. Kembali ke Operasi");
            System.out.print(PURPLE + "Pilih : ");
            pilihAdmin = s.nextInt();

            //tampilkan produk
            if (pilihAdmin == 1) {
                System.out.println();
                System.out.println(CYAN + "Produk yang tersedia : ");
                for (int i = 0; i < produkjual.length; i++) {
                    System.out.println(BLUE + produkjual[i] + " $" + hargaProduk[i]);
                }
            }
            // tambahkan produk
            else if (pilihAdmin == 2) {
                System.out.print(PURPLE + "Masukkan nama produk : ");
                String inputProduk = s.next();
                System.out.print(PURPLE + "Masukkan harga produk : ");
                int inputHarga = s.nextInt();
                produkjual = inputProduk(produkjual, inputProduk);
                hargaProduk = inputHarga(hargaProduk, inputHarga);
                System.out.println(YELLOW + "Produk berhasil tertambahkan!");
            }

            // ubah produk
            else if (pilihAdmin == 3) {
                for (int i = 0; i < produkjual.length; i++) {
                    System.out.println(BLUE + produkjual[i] + " $" + hargaProduk[i]);
                }
                System.out.println();
                System.out.print(PURPLE + "Masukkan produk yang ingin diubah : ");
                String caribarang = s.next();
                int ind = mCari(produkjual, hargaProduk, caribarang);
                if (ind < produkjual.length) {
                    System.out.print(PURPLE + "Masukkan nama produk : ");
                    produkjual[ind] = s.next();
                    System.out.print(PURPLE + "Masukkan harga produk : ");
                    hargaProduk[ind] = s.nextInt();
                    System.out.println();
                    System.out.println(YELLOW + "Produk telah berhasil diubah!");
                }
            }
            // hapus produk
            else if (pilihAdmin == 4) {
                System.out.println(PURPLE + "Masukkan produk yang ingin dihapus : ");
                String caribarang = s.next();
                System.out.println();
                int ind = mCari(produkjual, hargaProduk, caribarang);

                produkjual = hapusProduk(produkjual, ind);
                hargaProduk = hapusHarga(hargaProduk, ind);
                System.out.println();
                System.out.println(YELLOW + "Produk berhasil dihapus!");

            }
        } while (pilihAdmin != 5);
    }

    static void Owner(){
        Scanner s = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println(CYAN + "1. Laporan penjualan\n2. Data produk terlaris\n3. Kembali ke operasi");
            System.out.print(PURPLE + "Pilih : ");
            pilihOwner = s.nextInt();
            if (pilihOwner == 1) {
                if (laporanPenjualan.length > 0) {
                    for (int i = 0; i < laporanPenjualan.length; i++) {
                        System.out.println(BLUE + "Pelanggan ke - " + (i + 1) + " " + laporanPenjualan[i]);
                        System.out.println(BLUE + "tercatat : " + tanggaldanWaktu);
                    }
                } else {
                    System.out.println(RED + "Laporan masih kosong");
                }
            } else if (pilihOwner == 2) {
                produkTerlaris(produkjual, hargaProduk, palingLaris);
            }
        } while (pilihOwner != 3);

    }


    static int mCari (String[]produkjual,int[] hargaProduk, String cari){
        int ind = produkjual.length;
        boolean ketemu = false;
        for (int i = 0; i < produkjual.length; i++) {
            if (produkjual[i].equals(cari)) {
                ketemu = true;
                System.out.println(CYAN + "Stok barang masih tersedia!");
                System.out.println(YELLOW + produkjual[i] + " $" + hargaProduk[i]);
                ind = i;
            }
        }
        if (!ketemu) {
            System.out.println(CYAN + "Stok barang tidak tersedia!");
        }
        return ind;
    }

    static void produkTerlaris (String[]produkjual,int[] hargaProduk, int[] palingLaris){
        int x;
        String y;
        for (int i = 0; i < produkjual.length - 1; i++) {
            if (palingLaris[i] < palingLaris[i + 1]) {
                x = palingLaris[i];
                palingLaris[i] = palingLaris[i + 1];
                palingLaris[i + 1] = x;
                x = hargaProduk[i];
                hargaProduk[i] = hargaProduk[i + 1];
                hargaProduk[i + 1] = x;
                y = produkjual[i];
                produkjual[i] = produkjual[i + 1];
                produkjual[i + 1] = y;
            }
        }
        for (int i = 0; i < produkjual.length; i++) {
            System.out.println(PURPLE + (i + 1) + ". " + produkjual[i] + " $" + hargaProduk[i] + " terjual " + palingLaris[i] + " produk");
        }
    }

    static String[] inputProduk (String[]produk, String inputProduk){
        String[] hasil = new String[produk.length + 1];

        System.arraycopy(produk, 0, hasil, 0, produk.length);

        hasil[produk.length] = inputProduk;

        return hasil;
    }

    static int[] inputHarga ( int[] harga, int inputHarga){
        int[] hasil = new int[harga.length + 1];

        System.arraycopy(harga, 0, hasil, 0, harga.length);

        hasil[harga.length] = inputHarga;

        return hasil;
    }

    static String[] hapusProduk (String[]produkJual,int indexHapus){
        String[] hasil = new String[produkJual.length - 1];

        for (int i = 0, indexBaru = 0; i < produkJual.length; i++) {
            if (indexHapus != i) {
                hasil[indexBaru++] = produkJual[i];
            }
        }

        return hasil;
    }

    static int[] hapusHarga ( int[] harga, int indexHapus){
        int[] hasil = new int[harga.length - 1];

        for (int i = 0, indexBaru = 0; i < harga.length; i++) {
            if (indexHapus != i) {
                hasil[indexBaru++] = harga[i];
            }
        }

        return hasil;
    }

}

