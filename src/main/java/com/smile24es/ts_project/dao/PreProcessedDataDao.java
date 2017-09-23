package com.smile24es.ts_project.dao;

import com.smile24es.ts_project.beans.Actor;
import com.smile24es.ts_project.beans.PreProcessedData;
import java.util.List;

/**
 * Created by hasithagamage on 5/15/17.
 */
public interface PreProcessedDataDao {

    List<PreProcessedData> findAllPreProcessedData();

    void savePreProcessedData(PreProcessedData preProcessedData);
    
    PreProcessedData findPreProcessedDataByName(String moveName);

    void deleteAllPreProcessedData();

}
