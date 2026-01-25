package com.sgt.expense_tracker.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    int id;
    String name;
    String username;
    String email;
    String password;
    String mobile;
    int active_yn;
}
