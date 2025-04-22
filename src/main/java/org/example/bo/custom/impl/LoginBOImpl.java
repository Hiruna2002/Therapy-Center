package org.example.bo.custom.impl;

import org.example.bo.custom.LoginBO;
import org.example.dao.DAOFactory;
import org.example.dao.SuperDAO;
import org.example.dao.custom.UserDAO;
import org.example.dto.UsersDTO;
import org.example.entity.Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginBOImpl implements LoginBO {

    UserDAO Userdao = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public List<UsersDTO> checkUser(UsersDTO usersDTO) throws IOException {
        List<Users> users = Userdao.checkUser(new Users(usersDTO.getName(), usersDTO.getPassword(), usersDTO.getJobRole()));
        List<UsersDTO> usersDTOS = new ArrayList<>();

        for (Users users1:users){
            usersDTOS.add(new UsersDTO(users1.getName(),users1.getPassword(),users1.getJobRole()));
        }
        return usersDTOS;
    }
}
