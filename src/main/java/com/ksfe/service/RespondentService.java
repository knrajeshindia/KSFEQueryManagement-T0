package com.ksfe.service;

import com.ksfe.dao.UnitDAO;
import com.ksfe.dao.UnitDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RespondentService {
    @Autowired
    private UnitDAO unitDAO;
    List<Integer> tempUnitIDList = new ArrayList<Integer>();
    List<Integer> unitIDList = new ArrayList<Integer>();

    public List<Integer> getUnitIDList(List<Integer> unitTypeIDList) {
        unitIDList.clear();
        System.out.println("Inside respondentservice" +unitTypeIDList);

        for (Integer unitTypeID : unitTypeIDList) {
            System.out.println("calling UNIT DAO");
            tempUnitIDList = unitDAO.getUnitIDList(unitTypeID);
            System.out.println("UNITIDs:" +tempUnitIDList);
            unitIDList.addAll(tempUnitIDList);
            tempUnitIDList=null;
        }
        System.out.println("Completed respondentservice; going back to QuestionnaireDOA" + unitIDList);
        return unitIDList;

    }
}
