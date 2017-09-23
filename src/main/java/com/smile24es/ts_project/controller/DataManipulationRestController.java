package com.smile24es.ts_project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile24es.ts_project.beans.Actor;
import com.smile24es.ts_project.beans.Director;
import com.smile24es.ts_project.beans.PreProcessedData;
import com.smile24es.ts_project.beans.PredictionResults;
import com.smile24es.ts_project.beans.Recode;
import com.smile24es.ts_project.beans.RowData;
import com.smile24es.ts_project.beans.TestResults;
import com.smile24es.ts_project.beans.TrainingResponce;
import com.smile24es.ts_project.service.DataManipulationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import org.springframework.http.MediaType;

/**
 * Created by hasithagamage on 5/15/17.
 */
@RestController
public class DataManipulationRestController extends BaseRestController {

    private static final Logger SL4J_LOGGER = LoggerFactory.getLogger(DataManipulationRestController.class);

    @Autowired
    DataManipulationService dataManipulationService;

    @RequestMapping(value = "row-data", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity findAllRowData() {
        SL4J_LOGGER.info("Starting find all row data");
        List<RowData> rowDataSet = dataManipulationService.findAllRowData();
        SL4J_LOGGER.info("All row data are set to response object");
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(rowDataSet);
        } catch (JsonProcessingException ex) {
            java.util.logging.Logger.getLogger(DataManipulationRestController.class.getName()).log(Level.SEVERE, null, ex);
            SL4J_LOGGER.error("String conversion error found in row data");
        }
        return new ResponseEntity<>(jsonInString, HttpStatus.OK);
    }
    
    @RequestMapping(value = "actors", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity findAllActors() {
        SL4J_LOGGER.info("Starting find all actors");
        List<Actor> actors = dataManipulationService.findAllActors();
        SL4J_LOGGER.info("All row data are set to response object");
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(actors);
        } catch (JsonProcessingException ex) {
            java.util.logging.Logger.getLogger(DataManipulationRestController.class.getName()).log(Level.SEVERE, null, ex);
            SL4J_LOGGER.error("String conversion error found in row data");
        }
        return new ResponseEntity<>(jsonInString, HttpStatus.OK);
    }
    
    @RequestMapping(value = "directors", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity findAllDirectors() {
        SL4J_LOGGER.info("Starting find all directors");
        List<Director> directors = dataManipulationService.findAlldirectors();
        SL4J_LOGGER.info("All row data are set to response object");
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(directors);
        } catch (JsonProcessingException ex) {
            java.util.logging.Logger.getLogger(DataManipulationRestController.class.getName()).log(Level.SEVERE, null, ex);
            SL4J_LOGGER.error("String conversion error found in row data");
        }
        return new ResponseEntity<>(jsonInString, HttpStatus.OK);
    }

    @RequestMapping(value = "pre-processed-data", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity findAllPreProcessedData() {
        SL4J_LOGGER.info("Starting find all row data");
        List<PreProcessedData> preProcessedDataSet = dataManipulationService.findAllPreProcessedData();
        SL4J_LOGGER.info("All row data are set to response object - {}", preProcessedDataSet);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(preProcessedDataSet);
        } catch (JsonProcessingException ex) {
            java.util.logging.Logger.getLogger(DataManipulationRestController.class.getName()).log(Level.SEVERE, null, ex);
            SL4J_LOGGER.error("String conversion error found in row data");
        }
        return new ResponseEntity<>(jsonInString, HttpStatus.OK);
    }

    @RequestMapping(value = "row-data-set", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity saveRowData(@RequestBody List<RowData> listOfRowdata) {
        SL4J_LOGGER.info("Starting to save list of row data [{0}]", listOfRowdata);
        dataManipulationService.saveDataSet(listOfRowdata);
        return new ResponseEntity<>(listOfRowdata, HttpStatus.CREATED);
    }
        
    @RequestMapping(value = "training-system", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity trainingAlgo() {
        SL4J_LOGGER.info("Starting training");
        TrainingResponce trainingResponce = dataManipulationService.trainingData();
        SL4J_LOGGER.info("All training responce are set to response object - {}",trainingResponce);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(trainingResponce);
        } catch (JsonProcessingException ex) {
            java.util.logging.Logger.getLogger(DataManipulationRestController.class.getName()).log(Level.SEVERE, null, ex);
            SL4J_LOGGER.error("String conversion error found in row data");
        }
        return new ResponseEntity<>(jsonInString, HttpStatus.OK);
    }
    
    @RequestMapping(value = "test-accuracy", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity testAccuracy(@RequestBody List<RowData> listOfRowdata) {
        SL4J_LOGGER.info("Starting to save list of row data [{0}]", listOfRowdata);
        TestResults testResults =dataManipulationService.testAccurecy(listOfRowdata);
        return new ResponseEntity<>(testResults, HttpStatus.OK);
    }
    
    @RequestMapping(value = "predict", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity predict(@RequestBody RowData rowdata) {
        SL4J_LOGGER.info("Starting to predict of rowdata [{0}]", rowdata);
        PredictionResults predictionResults =dataManipulationService.predict(rowdata);
        return new ResponseEntity<>(predictionResults, HttpStatus.OK);
    }
    
}
