/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tubes;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author ideapad
 */
public class Profile extends User implements Identifiable {

    private String nik;
    private String nomorHp;

    public Profile(int userId, String username, String email, String password, String nik, String nomorHp) {
        super(userId, username, email, password);
        this.nik = nik;
        this.nomorHp = nomorHp;
    }

    @Override
    public void performAction() {
        System.out.println("Profile ID: " + getId() + ", Username: " + getUsername() + ", Email: " + getEmail() + ", NIK: " + nik + ", Nomor HP: " + nomorHp);
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public void updateProfile() {
        System.out.println("Profile dengan ID " + getId() + " berhasil diperbarui.");
    }

    public static void tampilkanSemuaProfile(HashMap<Integer, User> userMap) {
        for (User user : userMap.values()) {
            if (user instanceof Profile) {
                ((Profile) user).performAction();
            } else {
                System.out.println("User ID: " + user.getId() + ", Username: " + user.getUsername() + ", Email: " + user.getEmail());
            }
        }
    }

    public static Profile pilihProfile(HashMap<Integer, User> userMap, Scanner scanner) {
        System.out.print("Masukkan ID Profile yang ingin dipilih: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User user = userMap.get(userId);
        if (user instanceof Profile) {
            return (Profile) user;
        }
        System.out.println("Profile dengan ID " + userId + " tidak ditemukan.");
        return null;
    }

    public static User register(HashMap<Integer, User> userMap, Scanner scanner) {
        System.out.println("=== Registrasi ===");
        System.out.print("Username: ");
        String regUsername = scanner.nextLine();
        System.out.print("Email: ");
        String regEmail = scanner.nextLine();
        System.out.print("Password: ");
        String regPassword = scanner.nextLine();
        System.out.print("NIK: ");
        String regNik = scanner.nextLine();

        User newUser = new Profile(userMap.size() + 1, regUsername, regEmail, regPassword, regNik, "Nomor HP Default");
        if (newUser.signUp(userMap)) {
            return newUser;
        }
        return null;
    }

    public static void updateProfile(HashMap<Integer, User> userMap, Scanner scanner) {
        boolean updateProfileRunning = true;
        while (updateProfileRunning) {
            System.out.println("=== Update Profile ===");
            tampilkanSemuaProfile(userMap);
            Profile profile = pilihProfile(userMap, scanner);
            if (profile != null) {
                System.out.println("Pilih bagian yang ingin diubah: ");
                System.out.println("1. Username");
                System.out.println("2. Email");
                System.out.println("3. Password");
                System.out.println("4. NIK");
                System.out.println("5. Nomor HP");
                System.out.println("6. Kembali ke Menu Utama");
                int pilihanUpdate = scanner.nextInt();
                scanner.nextLine();

                switch (pilihanUpdate) {
                    case 1:
                        System.out.print("Username baru: ");
                        profile.setUsername(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Email baru: ");
                        profile.setEmail(scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("Password baru: ");
                        profile.setPassword(scanner.nextLine());
                        break;
                    case 4:
                        System.out.print("NIK baru: ");
                        profile.setNik(scanner.nextLine());
                        break;
                    case 5:
                        System.out.print("Nomor HP baru: ");
                        profile.setNomorHp(scanner.nextLine());
                        break;
                    case 6:
                        updateProfileRunning = false;
                        break;
                    default:
                        System.out.println("Opsi tidak valid.");
                        break;
                }
                profile.updateProfile();
            } else {
                System.out.println("ID profil tidak ditemukan.");
            }
        }
    }

}
