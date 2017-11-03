package com.ksfe.service;

import com.ksfe.dao.UnitDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RespondentService {
    @Autowired
    private UnitDAO unitDAO;
    List<Integer> tempUnitIDList = new ArrayList<Integer>();
    List<Integer> unitIDList = new ArrayList<Integer>();
    private Set<Integer> tempUnitIDSet;
    private String mode;
    private Set<Integer> unitIDSet=new HashSet<Integer>();

    public List<Integer> getUnitIDList(List<Integer> respondentCodeList) {
        unitIDSet.clear();
        System.out.println("Inside respondentservice" + respondentCodeList);

        for (Integer respondentCode : respondentCodeList) {
            System.out.println("calling UNIT DAO");
            if (respondentCode == 0) {
                mode = "PUBLIC";
            } else if (respondentCode < 11) {
                mode = "DEPARTMENTS";
            } else if (respondentCode > 11 && respondentCode < 111) {
                mode = "REGIONS";
            } else if (respondentCode >= 111) mode = "BRANCHES";

            switch (mode) {
                case "PUBLIC":
                    tempUnitIDSet = unitDAO.getUnitIDSet();
                    unitIDSet.addAll(tempUnitIDSet);
                    tempUnitIDSet.clear();
                    break;
                case "DEPARTMENTS":
                    unitIDSet.add(respondentCode);
                    break;
                case "REGIONS":
                    unitIDSet.add(respondentCode);
                    break;
                case "BRANCHES":
                    //Pre-set code to establish regionID from assigned targetRespondentID in Questionnaire forum;
                    int regionID = respondentCode - 100;
                    tempUnitIDSet = unitDAO.getUnitIDSet(regionID);
                    unitIDSet.addAll(tempUnitIDSet);
                    tempUnitIDSet.clear();
                    break;

                default: return null;
            }}
            unitIDList=new ArrayList<>(unitIDSet);
        System.out.println("Completed respondentservice; going back to QuestionnaireDOA" + unitIDSet);
        return unitIDList;

    }
}
