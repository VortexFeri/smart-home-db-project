package com.iec3.smarthome.service;

import com.iec3.smarthome.dao.PvPDAO;
import com.iec3.smarthome.entity.PvP;
import com.iec3.smarthome.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class PvPService {
    private final PvPDAO pvPDAO;

    public PvPService(PvPDAO pvPDAO) {
        this.pvPDAO = pvPDAO;
    }
    public PvP getPvP(int id) {
        return pvPDAO.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Month nr %d was not found", id)));
    }
    public void addNewUsage(float pvp,int month) {
        PvP production = getPvP(month);
        int result = pvPDAO.insert(new PvP(month, production.production(), pvp));
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }
    public List<PvP> getPvP() {
        return pvPDAO.getAll();
    }
}
