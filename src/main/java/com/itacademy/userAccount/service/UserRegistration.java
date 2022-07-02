package com.itacademy.userAccount.service;

import com.itacademy.userAccount.model.User;

import java.util.Scanner;

public class UserRegistration {

    public static User createUser() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        boolean valid = true;
        do {
                try {
                    System.out.println("Enter your User Name: ");
                    String userName = scanner.nextLine();
                    user.setUserName(userName);
                    System.out.println("Enter your Address: ");
                    user.setUserAddress(scanner.nextLine());
                    System.out.println("Enter your User Id: ");
                    int userId = scanner.nextInt();
                    user.setUserId(userId);
                } catch (Exception e) {
                    System.out.println("Invalid data, Try again");
                }
        }
        while (!valid);
        System.out.println("Success! User with name " + user.getUserName() +
                ", id " + user.getUserId() +
                ", address " + user.getUserAddress() +
                " was created!");
        return user;
    }

    public static int enterUserId() {
        Scanner scanner = new Scanner(System.in);
        int userId = 0;
        boolean valid = true;
        do {
            try {
                System.out.println("Enter your User Id: ");
                userId = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid data, Try again");
            }
        }
        while (!valid);
        return userId;
    }
}
