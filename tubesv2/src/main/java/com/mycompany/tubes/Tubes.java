/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.tubes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ideapad
 */
public class Tubes {

    private static Scanner scanner = new Scanner(System.in);
    private static HashMap<Integer, User> userMap = new HashMap<>();
    private static ArrayList<Booking> bookingsList = new ArrayList<>();
    private static ArrayList<Actions> actionsList = new ArrayList<>();
    private static ArrayList<Deposit> depositsList = new ArrayList<>();
    private static ArrayList<Kerusakan> kerusakanList = new ArrayList<>();
    private static ArrayList<Alat> alatList = new ArrayList<>();
    private static User currentUser = null;
    private static boolean loggedIn = false;

    public static void main(String[] args) {
        initializeData();

        while (!loggedIn) {
            showLoginMenu();
        }

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = getInput(1, 10);
            running = handleMainMenuChoice(choice);
        }

        scanner.close();
    }

    private static void initializeData() {
        Profile radeUser = new Profile(0, "rade", "rade@example.com", "rade12", "123456789", "08123456789");
        userMap.put(radeUser.getUserId(), radeUser);
        depositsList.add(new Deposit(1, radeUser.getUserId(), 1000000));

        bookingsList.add(new Booking(1, "user1", "user1@gmail.com", "1234567890", "Bali", new Date(), 500000.0, "Belum Dibayar", "Alat kemah"));
        bookingsList.add(new Booking(2, "user2", "user2@gmail.com", "0987654321", "Lombok", new Date(), 750000.0, "Belum Dibayar", "Tenda"));
        bookingsList.add(new Booking(3, "user3", "user3@gmail.com", "1122334455", "Jakarta", new Date(), 1000000.0, "Belum Dibayar", "set grill"));

        alatList.add(new Alat("Alat Selam", 10, "Tersedia"));
        alatList.add(new Alat("Kamera", 5, "Tersedia"));
    }

    private static void showLoginMenu() {
        System.out.println("=== Selamat Datang ===");
        System.out.println("1. Login");
        System.out.println("2. Registrasi");
        System.out.print("Pilih opsi (1-2): ");

        int choice = getInput(1, 2);

        switch (choice) {
            case 1:
                currentUser = User.login(userMap, scanner);
                loggedIn = currentUser != null;
                break;
            case 2:
                currentUser = Profile.register(userMap, scanner);
                if (currentUser != null) {
                    loggedIn = true;
                    actionsList.add(currentUser);
                    depositsList.add(new Deposit(depositsList.size() + 1, currentUser.getId(), 1000000));
                }
                break;
        }
    }

    private static void showMainMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Lihat Semua Profile");
        System.out.println("2. Update Profile");
        System.out.println("3. Lihat Semua Booking");
        System.out.println("4. List Booking");
        System.out.println("5. Deposit");
        System.out.println("6. Lapor Kerusakan");
        System.out.println("7. Kelola Kerusakan");
        System.out.println("8. Kelola Alat");
        System.out.println("9. Aksi Logbook");
        System.out.println("10. Logout");
        System.out.print("Pilih opsi (1-10): ");
    }

    private static int getInput(int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Harap masukkan angka.");
                scanner.next();
            }
        }
        return choice;
    }

    private static boolean handleMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                Profile.tampilkanSemuaProfile(userMap);
                break;
            case 2:
                Profile.updateProfile(userMap, scanner);
                break;
            case 3:
                Booking.infoBooking(bookingsList);
                break;
            case 4:
                Booking.manageBookings(bookingsList, scanner, depositsList, actionsList);
                break;
            case 5:
                Deposit.manageDeposits(depositsList, currentUser, scanner, actionsList);
                break;
            case 6:
                Kerusakan.reportDamage(scanner, kerusakanList, currentUser, actionsList);
                break;
            case 7:
                Kerusakan.manageDamages(kerusakanList, scanner, depositsList, actionsList);
                break;
            case 8:
                Alat.manageEquipment(alatList, scanner, actionsList);
                break;
            case 9:
                Actions.viewActions(actionsList);
                break;
            case 10:
                logout();
                return false;
        }
        return true;
    }

    private static void logout() {
        loggedIn = false;
        currentUser = null;
        System.out.println("Anda telah logout.");
    }
}
