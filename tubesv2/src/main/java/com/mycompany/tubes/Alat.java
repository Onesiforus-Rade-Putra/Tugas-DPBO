/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tubes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ideapad
 */
public class Alat extends Actions {

    private String nama;
    private int jumlah;
    private String status;

    public Alat(String nama, int jumlah, String status) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.status = status;
    }

    @Override
    public void performAction() {
        System.out.println("Nama Alat: " + getNama() + ", Jumlah: " + getJumlah() + ", Status: " + getStatus());
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static void addAlat(ArrayList<Alat> alatList, Alat alat) {
        alatList.add(alat);
        System.out.println("Alat " + alat.getNama() + " berhasil ditambahkan.");
    }

    public static void deleteAlat(ArrayList<Alat> alatList, String namaAlat) {
        Alat toRemove = null;
        for (Alat alat : alatList) {
            if (alat.getNama().equals(namaAlat)) {
                toRemove = alat;
                break;
            }
        }
        if (toRemove != null) {
            alatList.remove(toRemove);
            System.out.println("Alat " + namaAlat + " berhasil dihapus.");
        } else {
            System.out.println("Alat " + namaAlat + " tidak ditemukan.");
        }
    }

    public static void updateAlat(ArrayList<Alat> alatList, String namaAlat, int jumlah, String status) {
        for (Alat alat : alatList) {
            if (alat.getNama().equals(namaAlat)) {
                alat.setJumlah(jumlah);
                alat.setStatus(status);
                System.out.println("Alat " + namaAlat + " berhasil diperbarui.");
                return;
            }
        }
        System.out.println("Alat " + namaAlat + " tidak ditemukan.");
    }

    public static void listAlat(ArrayList<Alat> alatList) {
        for (Alat alat : alatList) {
            alat.performAction();
        }
    }

    public static void manageEquipment(ArrayList<Alat> alatList, Scanner scanner, ArrayList<Actions> actionsList) {
        boolean alatRunning = true;
        while (alatRunning) {
            System.out.println("=== Kelola Alat ===");
            System.out.println("1. Tambah Alat");
            System.out.println("2. Hapus Alat");
            System.out.println("3. Update Alat");
            System.out.println("4. Lihat Semua Alat");
            System.out.println("5. Kembali ke Menu Utama");
            int pilihanAlat = scanner.nextInt();
            scanner.nextLine();

            switch (pilihanAlat) {
                case 1:
                    System.out.print("Nama Alat: ");
                    String nama = scanner.nextLine();
                    System.out.print("Jumlah: ");
                    int jumlah = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Status: ");
                    String status = scanner.nextLine();
                    Alat newAlat = new Alat(nama, jumlah, status);
                    Alat.addAlat(alatList, newAlat);
                    actionsList.add(newAlat);
                    break;
                case 2:
                    System.out.print("Nama Alat yang akan dihapus: ");
                    String hapusNamaAlat = scanner.nextLine();
                    Alat.deleteAlat(alatList, hapusNamaAlat);
                    break;
                case 3:
                    System.out.print("Nama Alat yang akan diupdate: ");
                    String updateNamaAlat = scanner.nextLine();
                    System.out.print("Jumlah baru: ");
                    int updateJumlah = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Status baru: ");
                    String updateStatus = scanner.nextLine();
                    Alat.updateAlat(alatList, updateNamaAlat, updateJumlah, updateStatus);
                    break;
                case 4:
                    Alat.listAlat(alatList);
                    break;
                case 5:
                    alatRunning = false;
                    break;
                default:
                    System.out.println("Opsi tidak valid. Silakan pilih 1-5.");
                    break;
            }
        }
    }
}
