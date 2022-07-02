package com.itacademy.userAccount.util;

import java.util.Scanner;

public class OptionSelector {
    public static int selectOption() {
        Scanner scanner = new Scanner(System.in);
        boolean valid = true;
        int option = 0;
        do {
            System.out.println("Select 1 or 2");
            try {
                if (scanner.hasNextLine()) {
                    option = scanner.nextInt();
                }
            } catch (Exception e) {
                System.out.println("Invalid data, Try again");
            }
        } while (!valid);
        System.out.println(option + " option was selected!");
        return option;
    }

}
