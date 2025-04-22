package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dao.SuperDAO;
import org.example.entity.Users;

import java.io.IOException;
import java.util.List;

public interface UserDAO extends CrudDAO<Users> {
    List<Users> checkUser(Users users) throws IOException;

}
