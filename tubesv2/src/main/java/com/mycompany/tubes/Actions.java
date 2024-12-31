/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tubes;

import java.util.ArrayList;

/**
 *
 * @author ideapad
 */
public abstract class Actions {
    public abstract void performAction();

    public static void viewActions(ArrayList<Actions> actionsList) {
        for (Actions action : actionsList) {
            action.performAction();
        }
    }
}
