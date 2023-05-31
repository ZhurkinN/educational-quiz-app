package ru.lobakina.educationalquizapp.support.helper;

import ru.lobakina.educationalquizapp.model.user.User;

import java.util.Arrays;
import java.util.List;

public class BubbleSorter {

    public static List<User> sortUsers(User[] users) {
        User temp;
        for (int j = 0; j < users.length; j++) {
            for (int i = j + 1; i < users.length; i++) {
                if (users[i].getFirstName().compareTo(users[j].getFirstName()) < 0) {
                    temp = users[j];
                    users[j] = users[i];
                    users[i] = temp;
                } else if (users[i].getFirstName().compareTo(users[j].getFirstName()) == 0) {
                    if (users[i].getLastName().compareTo(users[j].getLastName()) < 0) {
                        temp = users[j];
                        users[j] = users[i];
                        users[i] = temp;
                    } else if (users[i].getLastName().compareTo(users[j].getLastName()) == 0) {
                        if (users[i].getMiddleName().compareTo(users[j].getMiddleName()) < 0) {
                            temp = users[j];
                            users[j] = users[i];
                            users[i] = temp;
                        }
                    }
                }
            }
        }
        return Arrays.asList(users);
    }
}
