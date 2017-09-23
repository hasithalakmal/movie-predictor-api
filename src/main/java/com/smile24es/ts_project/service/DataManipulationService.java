package com.smile24es.ts_project.service;

import com.smile24es.ts_project.beans.Actor;
import com.smile24es.ts_project.beans.Director;
import com.smile24es.ts_project.beans.PreProcessedData;
import com.smile24es.ts_project.beans.PredictionResults;
import com.smile24es.ts_project.beans.Recode;
import com.smile24es.ts_project.beans.RowData;
import com.smile24es.ts_project.beans.TestResults;
import com.smile24es.ts_project.beans.TrainingResponce;

import java.util.List;

public interface DataManipulationService {

    List<PreProcessedData> findAllPreProcessedData();
    
    List<RowData> findAllRowData();
    
    List<Actor> findAllActors();
    
     List<Director> findAlldirectors();

    void saveDataSet(List<RowData> listOfRowdata);
    
    TestResults testAccurecy(List<RowData> listOfRowdata);
    
    TrainingResponce trainingData();
    
    PredictionResults predict(RowData rowdata);

}
