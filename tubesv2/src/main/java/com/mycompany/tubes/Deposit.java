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
public class Deposit extends Actions implements Identifiable {

    private int depositId;
    private int userId;
    private double saldo;

    public Deposit(int depositId, int userId, double saldo) {
        this.depositId = depositId;
        this.userId = userId;
        this.saldo = saldo;
    }

    @Override
    public int getId() {
        return depositId;
    }

    @Override
    public void performAction() {
        System.out.println("Deposit ID: " + getId() + ", User ID: " + getUserId() + ", Saldo: " + getSaldo());
    }

    public int getUserId() {
        return userId;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void infoDeposit() {
        System.out.println("Deposit ID: " + depositId + ", User ID: " + userId + ", Saldo: " + saldo);
    }

    public void tambahDeposit(double jumlah) {
        this.saldo += jumlah;
        System.out.println("Saldo bertambah " + jumlah + ". Saldo baru: " + this.saldo);
    }

    public void tarikDeposit(double jumlah) {
        if (this.saldo >= jumlah) {
            this.saldo -= jumlah;
            System.out.println("Saldo berkurang " + jumlah + ". Saldo baru: " + this.saldo);
        } else {
            System.out.println("Saldo tidak mencukupi.");
        }
    }

    public static void manageDeposits(ArrayList<Deposit> depositsList, User currentUser, Scanner scanner, ArrayList<Actions> actionsList) {
        boolean depositRunning = true;
        while (depositRunning) {
            System.out.println("=== Deposit ===");
            Deposit userDeposit = null;
            for (Deposit deposit : depositsList) {
                if (deposit.getUserId() == currentUser.getId()) {
                    userDeposit = deposit;
                    break;
                }
            }
            if (userDeposit != null) {
                userDeposit.infoDeposit();
                System.out.println("Pilih tindakan: ");
                System.out.println("1. Tambah Deposit");
                System.out.println("2. Tarik Deposit");
                System.out.println("3. Kembali ke Menu Utama");
                int pilihanDeposit = scanner.nextInt();
                scanner.nextLine();

                switch (pilihanDeposit) {
                    case 1:
                        System.out.print("Jumlah Deposit: ");
                        double jumlah_deposit = scanner.nextDouble();
                        userDeposit.tambahDeposit(jumlah_deposit);
                        userDeposit.infoDeposit();
                        actionsList.add(userDeposit);
                        break;
                    case 2:
                        System.out.print("Jumlah Penarikan: ");
                        double jumlah_tarik = scanner.nextDouble();
                        userDeposit.tarikDeposit(jumlah_tarik);
                        userDeposit.infoDeposit();
                        actionsList.add(userDeposit);
                        break;
                    case 3:
                        depositRunning = false;
                        break;
                    default:
                        System.out.println("Opsi tidak valid.");
                        break;
                }
            } else {
                System.out.println("Deposit untuk pengguna tidak ditemukan.");
            }
        }
    }
}

