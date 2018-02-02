package com.altarix.dtos.security;

import com.altarix.entities.security.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserList {
    private List<User> userList;
    private long total;
}
