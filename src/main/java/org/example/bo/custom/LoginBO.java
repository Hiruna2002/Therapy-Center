package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.UsersDTO;

import java.io.IOException;
import java.util.List;

public interface LoginBO extends SuperBO {
    List<UsersDTO> checkUser(UsersDTO usersDTO) throws IOException;
}
