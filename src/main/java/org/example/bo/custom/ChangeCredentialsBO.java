package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.UsersDTO;

import java.io.IOException;

public interface ChangeCredentialsBO extends SuperBO {
    boolean saveCredentials(UsersDTO usersDTO) throws IOException;
}
