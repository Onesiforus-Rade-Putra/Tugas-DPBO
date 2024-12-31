/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tubes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ideapad
 */
public class Kerusakan extends Actions implements Identifiable {
    private int kerusakan_id;
    private int user_id;
    private int booking_id;
    private String deskripsi;
    private String foto;
    private String alat;
    private int biaya;
    private String status_pembayaran;

    public Kerusakan(int kerusakan_id, int user_id, int booking_id, String deskripsi, String foto, String alat, int biaya, String status_pembayaran) {
        this.kerusakan_id = kerusakan_id;
        this.user_id = user_id;
        this.booking_id = booking_id;
        this.deskripsi = deskripsi;
        this.foto = foto;
        this.alat = alat;
        this.biaya = biaya;
        this.status_pembayaran = status_pembayaran;
    }

    @Override
    public int getId() {
        return kerusakan_id;
    }

    @Override
    public void performAction() {
        System.out.println("Kerusakan ID: " + kerusakan_id + ", User ID: " + user_id + ", Booking ID: " + booking_id + ", Deskripsi: " + deskripsi + ", Foto: " + foto + ", Alat: " + alat + ", Biaya: " + biaya + ", Status Pembayaran: " + status_pembayaran);
    }

    public void addLaporKerusakan(ArrayList<Kerusakan> kerusakanList) {
        kerusakanList.add(this);
        System.out.println("Kerusakan ID " + kerusakan_id + " telah ditambahkan ke dalam daftar kerusakan.");
    }

    public void confirmPembayaran(ArrayList<Deposit> depositsList) {
        if (this.status_pembayaran.equals("Belum Dibayar")) {
            for (Deposit deposit : depositsList) {
                if (deposit.getUserId() == this.user_id) {
                    double saldo = deposit.getSaldo();
                    deposit.setSaldo(saldo + this.biaya);
                    this.status_pembayaran = "Dibayar";
                    System.out.println("Kerusakan ID " + kerusakan_id + " telah dikonfirmasi pembayarannya. Saldo baru: " + deposit.getSaldo());
                    return;
                }
            }
            System.out.println("Deposit tidak ditemukan untuk user ID " + this.user_id);
        } else {
            System.out.println("Kerusakan ID " + kerusakan_id + " sudah dibayar.");
        }
    }

    public static void listKerusakan(ArrayList<Kerusakan> kerusakanList) {
        for (Kerusakan kerusakan : kerusakanList) {
            kerusakan.performAction();
        }
    }

    public int getKerusakanId() {
        return kerusakan_id;
    }

    public void setKerusakanId(int kerusakan_id) {
        this.kerusakan_id = kerusakan_id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getBookingId() {
        return booking_id;
    }

    public void setBookingId(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAlat() {
        return alat;
    }

    public void setAlat(String alat) {
        this.alat = alat;
    }

    public int getBiaya() {
        return biaya;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public String getStatusPembayaran() {
        return status_pembayaran;
    }

    public void setStatusPembayaran(String status_pembayaran) {
        this.status_pembayaran = status_pembayaran;
    }

    public static Kerusakan pilihKerusakan(ArrayList<Kerusakan> kerusakanList, Scanner scanner) {
        System.out.print("Masukkan ID Kerusakan yang ingin dipilih: ");
        int kerusakanId = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer

        for (Kerusakan kerusakan : kerusakanList) {
            if (kerusakan.getKerusakanId() == kerusakanId) {
                return kerusakan;
            }
        }
        System.out.println("Kerusakan dengan ID " + kerusakanId + " tidak ditemukan.");
        return null;
    }

    public static void reportDamage(Scanner scanner, ArrayList<Kerusakan> kerusakanList, User currentUser, ArrayList<Actions> actionsList) {
        System.out.println("=== Lapor Kerusakan ===");
        try {
            System.out.print("Booking ID: ");
            int booking_id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Alat: ");
            String alat = scanner.nextLine();
            System.out.print("Deskripsi Kerusakan: ");
            String deskripsi = scanner.nextLine();
            System.out.print("Foto Kerusakan: ");
            String foto = scanner.nextLine();
            System.out.print("Biaya: ");
            int biaya = scanner.nextInt();
            scanner.nextLine();
            Kerusakan kerusakan = new Kerusakan(kerusakanList.size() + 1, currentUser.getId(), booking_id, deskripsi, foto, alat, biaya, "Belum Dibayar");
            kerusakan.addLaporKerusakan(kerusakanList);
            actionsList.add(kerusakan);
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Harap masukkan data yang benar.");
            scanner.next();
        }
    }

    public static void manageDamages(ArrayList<Kerusakan> kerusakanList, Scanner scanner, ArrayList<Deposit> depositsList, ArrayList<Actions> actionsList) {
        boolean kerusakanRunning = true;
        while (kerusakanRunning) {
            System.out.println("=== Kelola Kerusakan ===");
            System.out.println("1. Konfirmasi Pembayaran Kerusakan");
            System.out.println("2. Lihat Semua Kerusakan");
            System.out.println("3. Kembali ke Menu Utama");
            int pilihanKerusakan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihanKerusakan) {
                case 1:
                    System.out.print("Masukkan ID Kerusakan yang ingin dikonfirmasi: ");
                    int kerusakanId = scanner.nextInt();
                    scanner.nextLine();
                    Kerusakan kerusakan = pilihKerusakan(kerusakanList, scanner);
                    if (kerusakan != null) {
                        kerusakan.confirmPembayaran(depositsList);
                        actionsList.add(kerusakan);
                    } else {
                        System.out.println("ID kerusakan tidak ditemukan.");
                    }
                    break;
                case 2:
                    listKerusakan(kerusakanList);
                    break;
                case 3:
                    kerusakanRunning = false;
                    break;
                default:
                    System.out.println("Opsi tidak valid. Silakan pilih 1-3.");
                    break;
            }
        }
    }
}