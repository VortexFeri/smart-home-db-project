package com.iec3.smarthome.service;

import com.iec3.smarthome.dao.DeviceDAO;
import com.iec3.smarthome.dao.LoginDAO;
import com.iec3.smarthome.dto.DeviceDTO;
import com.iec3.smarthome.entity.Device;
import com.iec3.smarthome.entity.Login;
import com.iec3.smarthome.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginService {
    private final LoginDAO loginDao;

    public LoginService(LoginDAO loginDao) {
        this.loginDao = loginDao;
    }
    public void addNewUser(Login user) {
        // TODO: check if device exists
        int result = loginDao.insert(new Login( user.user(), user.password()));
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }
    public Login getLogin(String user) throws Throwable {
        return (Login) loginDao.getById(user)
                .orElseThrow(() -> new NotFoundException(String.format("Error logging in %1", user)));
    }
    public boolean verifyLogin(Login a) throws Throwable {
        return Objects.equals(getLogin(a.user()).password(), a.password());
    }
}
