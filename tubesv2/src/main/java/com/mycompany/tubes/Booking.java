/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tubes;

import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ideapad
 */
public class Booking extends Actions {

    private int booking_id;
    private int userId;
    private String username;
    private String email;
    private String nik;
    private String destinasi;
    private Date booking_date;
    private double harga;
    private String status_pembayaran;
    private String alat;

    public Booking(int booking_id, String username, String email, String nik, String destinasi, Date booking_date, double harga, String status_pembayaran, String alat) {
        this.booking_id = booking_id;
        this.username = username;
        this.email = email;
        this.nik = nik;
        this.destinasi = destinasi;
        this.booking_date = booking_date;
        this.harga = harga;
        this.status_pembayaran = status_pembayaran;
        this.alat = alat;
    }

    @Override
    public void performAction() {
        System.out.println("Booking ID: " + booking_id + ", Username: " + username + ", Email: " + email + ", NIK: " + nik + ", Destinasi: " + destinasi + ", Booking Date: " + booking_date + ", Harga: " + harga + ", Status Pembayaran: " + status_pembayaran + ", Alat: " + alat);
    }

    public void confirmBooking(ArrayList<Deposit> depositsList) {
        if (this.status_pembayaran.equals("Belum Dibayar")) {
            for (Deposit deposit : depositsList) {
                if (deposit.getUserId() == this.userId) {
                    double saldo = deposit.getSaldo();
                    if (saldo >= this.harga) {
                        deposit.setSaldo(saldo + this.harga);
                        this.status_pembayaran = "Dibayar";
                        System.out.println("Booking ID " + booking_id + " telah dikonfirmasi pembayarannya. Saldo baru: " + deposit.getSaldo());
                    } else {
                        System.out.println("Saldo tidak mencukupi untuk pembayaran booking ID " + booking_id);
                    }
                    return;
                }
            }
            System.out.println("Deposit tidak ditemukan untuk user ID " + this.userId);
        } else {
            System.out.println("Booking ID " + booking_id + " sudah dibayar.");
        }
    }

    public void cancelBooking() {
        if (this.status_pembayaran.equals("Belum Dibayar")) {
            System.out.println("Booking ID " + booking_id + " telah dibatalkan.");
        } else {
            System.out.println("Booking ID " + booking_id + " tidak dapat dibatalkan karena sudah dibayar.");
        }
    }

    public int getBookingId() {
        return booking_id;
    }

    public void setBookingId(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getDestinasi() {
        return destinasi;
    }

    public void setDestinasi(String destinasi) {
        this.destinasi = destinasi;
    }

    public Date getBookingDate() {
        return booking_date;
    }

    public void setBookingDate(Date booking_date) {
        this.booking_date = booking_date;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getStatusPembayaran() {
        return status_pembayaran;
    }

    public void setStatusPembayaran(String status_pembayaran) {
        this.status_pembayaran = status_pembayaran;
    }

    public String getAlat() {
        return alat;
    }

    public void setAlat(String alat) {
        this.alat = alat;
    }

    public static Booking pilihBooking(ArrayList<Booking> bookingsList, Scanner scanner) {
        System.out.print("Masukkan ID Booking yang ingin dipilih: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        for (Booking booking : bookingsList) {
            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }
        System.out.println("Booking dengan ID " + bookingId + " tidak ditemukan.");
        return null;
    }

    public static void infoBooking(ArrayList<Booking> bookingsList) {
        for (Booking booking : bookingsList) {
            booking.performAction();
        }
    }

    public static void manageBookings(ArrayList<Booking> bookingsList, Scanner scanner, ArrayList<Deposit> depositsList, ArrayList<Actions> actionsList) {
        boolean bookingRunning = true;
        while (bookingRunning) {
            System.out.println("=== Booking ===");
            infoBooking(bookingsList);
            Booking selectedBooking = pilihBooking(bookingsList, scanner);
            if (selectedBooking != null) {
                System.out.println("Pilih tindakan untuk booking ID " + selectedBooking.getBookingId() + ": ");
                System.out.println("1. Konfirmasi Booking");
                System.out.println("2. Batalkan Booking");
                System.out.println("3. Kembali ke Menu Utama");
                int pilihanBooking = scanner.nextInt();
                scanner.nextLine();

                switch (pilihanBooking) {
                    case 1:
                        selectedBooking.confirmBooking(depositsList);
                        actionsList.add(selectedBooking);
                        break;
                    case 2:
                        selectedBooking.cancelBooking();
                        actionsList.add(selectedBooking);
                        break;
                    case 3:
                        bookingRunning = false;
                        break;
                    default:
                        System.out.println("Opsi tidak valid.");
                        break;
                }
            } else {
                System.out.println("ID booking tidak ditemukan.");
            }
        }
    }
}
