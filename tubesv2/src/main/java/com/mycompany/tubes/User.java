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
public class User extends Actions implements Identifiable {

    private int userId;
    private String username;
    private String email;
    private String password;

    public User(int userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public int getId() {
        return userId;
    }

    @Override
    public void performAction() {
        System.out.println("User ID: " + getId() + ", Username: " + getUsername());
    }

    // Getter dan setter lainnya...
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User login(HashMap<Integer, User> userMap, Scanner scanner) {
        System.out.println("=== Login ===");
        System.out.print("Username: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Password: ");
        String loginPassword = scanner.nextLine();
        for (User user : userMap.values()) {
            if (user.getUsername().equals(loginUsername) && user.getPassword().equals(loginPassword)) {
                return user;
            }
        }
        System.out.println("Username atau password salah. Silakan coba lagi.");
        return null;
    }

    public boolean signUp(HashMap<Integer, User> userMap) {
        for (User user : userMap.values()) {
            if (user.getUsername().equals(this.username)) {
                System.out.println("Username sudah digunakan.");
                return false;
            }
        }
        userMap.put(this.userId, this);
        System.out.println("Registrasi berhasil.");
        return true;
    }

    public Integer getUserId() {
        return userId;
    }
}
