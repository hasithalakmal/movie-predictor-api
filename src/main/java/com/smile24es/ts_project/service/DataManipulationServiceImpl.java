package com.smile24es.ts_project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile24es.ts_project.beans.Actor;
import com.smile24es.ts_project.beans.ActorCategory;
import com.smile24es.ts_project.beans.BudgetCategory;
import com.smile24es.ts_project.beans.CartResult;
import com.smile24es.ts_project.beans.CartTestResult;
import com.smile24es.ts_project.beans.DataSet;
import com.smile24es.ts_project.beans.Director;
import com.smile24es.ts_project.beans.DirectorCategory;
import com.smile24es.ts_project.beans.DuratonCategory;
import com.smile24es.ts_project.beans.ListOfLikelihoodTables;
import com.smile24es.ts_project.beans.NiveBaseResult;
import com.smile24es.ts_project.beans.NiveBayearsTestResult;
import com.smile24es.ts_project.beans.PreProcessedData;
import com.smile24es.ts_project.beans.PredictionResults;
import com.smile24es.ts_project.beans.Recode;
import com.smile24es.ts_project.beans.RowData;
import com.smile24es.ts_project.beans.TestResults;
import com.smile24es.ts_project.beans.TrainingResponce;
import com.smile24es.ts_project.controller.DataManipulationRestController;
import com.smile24es.ts_project.dao.ActorDao;
import com.smile24es.ts_project.dao.BudgetCategoryDao;
import com.smile24es.ts_project.dao.DirectorDao;
import com.smile24es.ts_project.dao.DuratonCategoryDao;
import com.smile24es.ts_project.dao.PreProcessedDataDao;
import com.smile24es.ts_project.dao.RowDataDao;
import static com.smile24es.ts_project.utill.ErrorCode.ERROR_IN_CONNECTING_NAIVE;
import com.smile24es.ts_project.utill.MovieProjectApiException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

@Service("dataManipulationService")
@Transactional
public class DataManipulationServiceImpl implements DataManipulationService {

    private static final Logger SL4J_LOGGER = LoggerFactory.getLogger(DataManipulationServiceImpl.class);

    @Autowired
    private RowDataDao rowDataDao;

    @Autowired
    private PreProcessedDataDao preProcessedDataDao;

    @Autowired
    private DuratonCategoryDao duratonCategoryDao;

    @Autowired
    private BudgetCategoryDao budgetCategoryDao;

    @Autowired
    private DirectorDao directorDao;

    @Autowired
    private ActorDao actorDao;

    //CONSTANTS for duration
    private static final String LONG_MOVIE = "Long";
    private static final String MEDIUM_MOVIE = "Medium";
    private static final String SHORT_MOVIE = "Short";

    //CONSTANTS for budget
    private static final String EXPENCIVE_MOVIE = "Expencive";
    private static final String AVARAGE_MOVIE = "Avarage";
    private static final String CHEEP_MOVIE = "Cheep";

    //CONSTANTS for actor
    private static final String MOST__POPULER__ACTOR = "Most Populer Actor";
    private static final String POPULER__ACTOR = "Populer Actor";
    private static final String KNOWN__ACTOR = "Known Actor";
    private static final String UNKNOWN__ACTOR = "Unknown Actor";

    @Override
    public List<PreProcessedData> findAllPreProcessedData() {
        return preProcessedDataDao.findAllPreProcessedData();
    }

    @Override
    public List<RowData> findAllRowData() {
        return rowDataDao.findAllRowData();
    }

    @Override
    public void saveDataSet(List<RowData> listOfRowdata) {
        cleanDatabase();

        for (RowData rowData : listOfRowdata) {

            if (StringUtils.isNotBlank(rowData.getActorName())
                    && StringUtils.isNotBlank(rowData.getActorName())
                    && StringUtils.isNotBlank(rowData.getCountry())
                    && StringUtils.isNotBlank(rowData.getDiretorName())
                    && StringUtils.isNotBlank(rowData.getGenre())
                    && StringUtils.isNotBlank(rowData.getLanauge())
                    && StringUtils.isNotBlank(rowData.getMoveName())
                    && StringUtils.isNotBlank(rowData.getDuration().toString())
                    && StringUtils.isNotBlank(rowData.getBudget().toString())
                    && StringUtils.isNotBlank(rowData.getIncome().toString())) {
                if ((rowData.getIncome() - rowData.getBudget()) >= 0) {
                    rowData.setIsProfitable("true");
                } else {
                    rowData.setIsProfitable("false");
                }
                rowDataDao.saveRowData(rowData);
            }

        }

        List<RowData> listOfAllRowData = rowDataDao.findAllRowData();

        Map<String, Integer> directorMap = new HashMap<>();
        Map<String, Integer> actorMap = new HashMap<>();
        List<Integer> durationList = new ArrayList<>();
        List<Integer> budgetList = new ArrayList<>();

        for (RowData rowData : listOfAllRowData) {
            populateMapsAndList(rowData, directorMap, actorMap, durationList, budgetList);
        }
        directorCatogarization(directorMap);
        actorCatogarization(actorMap);
        Map<String, DuratonCategory> mapOfDuratonCategories = durationCategorize(durationList);
        Map<String, BudgetCategory> mapOfBudgetCategories = budgetCategorize(budgetList, durationList);

        //create pre-processed data set
        for (RowData rowData : listOfAllRowData) {
            PreProcessedData preProcessedData = createPreProcessedDataObject(rowData, mapOfDuratonCategories, mapOfBudgetCategories);
            preProcessedDataDao.savePreProcessedData(preProcessedData);
        }

    }

    private PreProcessedData createPreProcessedDataObject(RowData rowData, Map<String, DuratonCategory> mapOfDuratonCategories, Map<String, BudgetCategory> mapOfBudgetCategories) {
        PreProcessedData preProcessedData = new PreProcessedData();
        preProcessedData.setMoveName(rowData.getMoveName());
        preProcessedData.setCountry(rowData.getCountry());
        preProcessedData.setGenre(rowData.getGenre());
        preProcessedData.setLanauge(rowData.getLanauge());
        preProcessedData.setIsProfitable(rowData.getIsProfitable());
        Actor actor = actorDao.findActorByName(rowData.getActorName());
        preProcessedData.setActorCategory(actor.getActorCategory());
        Director director = directorDao.findDirectorByName(rowData.getDiretorName());
        preProcessedData.setDiretorCategory(director.getDirectorCategory());
        Integer duration = rowData.getDuration();
        if (mapOfDuratonCategories.get(SHORT_MOVIE).getDuratonCategoryMax() > duration) {
            preProcessedData.setDurationCategory(duratonCategoryDao.findDuratonCategoryByName(SHORT_MOVIE));
        } else if (mapOfDuratonCategories.get(MEDIUM_MOVIE).getDuratonCategoryMax() > duration) {
            preProcessedData.setDurationCategory(duratonCategoryDao.findDuratonCategoryByName(MEDIUM_MOVIE));
        } else {
            preProcessedData.setDurationCategory(duratonCategoryDao.findDuratonCategoryByName(LONG_MOVIE));
        }
        Integer budget = rowData.getBudget();
        if (mapOfBudgetCategories.get(CHEEP_MOVIE).getBudgetCategoryMax() > budget) {
            preProcessedData.setBudgetCategory(budgetCategoryDao.findBudgetCategoryByName(CHEEP_MOVIE));
        } else if (mapOfBudgetCategories.get(AVARAGE_MOVIE).getBudgetCategoryMax() > budget) {
            preProcessedData.setBudgetCategory(budgetCategoryDao.findBudgetCategoryByName(AVARAGE_MOVIE));
        } else {
            preProcessedData.setBudgetCategory(budgetCategoryDao.findBudgetCategoryByName(EXPENCIVE_MOVIE));
        }
        return preProcessedData;
    }

    private void cleanDatabase() {
        preProcessedDataDao.deleteAllPreProcessedData();
        directorDao.deleteAllDirectors();
        actorDao.deleteAllActors();
        duratonCategoryDao.deleteAllDuratonCategories();
        budgetCategoryDao.deleteAllBudgetCategories();
        rowDataDao.deleteAllRowData();
    }

    private void actorCatogarization(Map<String, Integer> actorMap) {
        int actorMaxValue = 0;
        int actorMinValue = 0;
        List<Integer> actorList = new ArrayList<Integer>(actorMap.values());
        Collections.sort(actorList);
        actorMinValue = actorList.get(0);
        actorMaxValue = actorList.get(actorList.size() - 1);
        int actorClassRange = (actorMaxValue - actorMinValue) / 3;

        for (Map.Entry<String, Integer> entry : actorMap.entrySet()) {
            Actor actor = new Actor();
            actor.setActorName(entry.getKey());
            ActorCategory actorCategory = new ActorCategory();

            if (entry.getValue() < (actorMinValue + actorClassRange)) {
                actorCategory.setActorCategoryId(1);
                actorCategory.setActorCategoryName(UNKNOWN__ACTOR);
            } else if (entry.getValue() < (actorMinValue + actorClassRange * 2)) {
                actorCategory.setActorCategoryId(2);
                actorCategory.setActorCategoryName(KNOWN__ACTOR);
            } else if (entry.getValue() < (actorMinValue + actorClassRange * 3)) {
                actorCategory.setActorCategoryId(3);
                actorCategory.setActorCategoryName(POPULER__ACTOR);
            } else if (entry.getValue() >= (actorMinValue + actorClassRange * 3)) {
                actorCategory.setActorCategoryId(4);
                actorCategory.setActorCategoryName(MOST__POPULER__ACTOR);
            }
            actor.setActorCategory(actorCategory);

            System.out.println("**************************************");
            System.out.println(actor.toString());
            System.out.println("**************************************");

            actorDao.saveActor(actor);
        }
    }

    private void directorCatogarization(Map<String, Integer> directorMap) {
        int directotMaxValue = 0;
        int directotMinValue = 0;
        List<Integer> directorList = new ArrayList<Integer>(directorMap.values());
        Collections.sort(directorList);
        directotMinValue = directorList.get(0);
        directotMaxValue = directorList.get(directorList.size() - 1);
        int directotClassRange = (directotMaxValue - directotMinValue) / 3;

        for (Map.Entry<String, Integer> entry : directorMap.entrySet()) {
            Director director = new Director();
            director.setDirectorName(entry.getKey());
            DirectorCategory directorCategory = new DirectorCategory();

            if (entry.getValue() < (directotMinValue + directotClassRange)) {
                directorCategory.setDirectorCategoryId(1);
                //directorCategory.setDirectorCategoryName(UNKNOWN__DIRECTOR);
            } else if (entry.getValue() < (directotMinValue + directotClassRange * 2)) {
                directorCategory.setDirectorCategoryId(2);
                //directorCategory.setDirectorCategoryName(KNOWN__DIRECTOR);
            } else if (entry.getValue() < (directotMinValue + directotClassRange * 3)) {
                directorCategory.setDirectorCategoryId(3);
                // directorCategory.setDirectorCategoryName(POPULER__DIRECTOR);
            } else if (entry.getValue() >= (directotMinValue + directotClassRange * 3)) {
                directorCategory.setDirectorCategoryId(4);
                // directorCategory.setDirectorCategoryName(MOST__POPULER__DIRECTOR);
            }
            director.setDirectorCategory(directorCategory);
            directorDao.saveDirector(director);
        }
    }
    private static final String MOST__POPULER__DIRECTOR = "Most Populer Director";
    private static final String POPULER__DIRECTOR = "Populer Director";
    private static final String KNOWN__DIRECTOR = "Known Director";
    private static final String UNKNOWN__DIRECTOR = "Unknown Director";

    private Map<String, BudgetCategory> budgetCategorize(List<Integer> budgetList, List<Integer> durationList) {
        Collections.sort(budgetList);
        int numberOfElements = durationList.size();
        int sizeOfRange = numberOfElements / 4;
        BudgetCategory budgetCategory1 = new BudgetCategory();
        budgetCategory1.setBudgetCategoryMin(0);
        budgetCategory1.setBudgetCategoryMax(budgetList.get(sizeOfRange));
        budgetCategory1.setBudgetCategoryName(CHEEP_MOVIE);
        budgetCategoryDao.saveBudgetCategory(budgetCategory1);

        BudgetCategory budgetCategory2 = new BudgetCategory();
        budgetCategory2.setBudgetCategoryMin(budgetList.get(sizeOfRange));
        budgetCategory2.setBudgetCategoryMax(budgetList.get(sizeOfRange * 2));
        budgetCategory2.setBudgetCategoryName(AVARAGE_MOVIE);
        budgetCategoryDao.saveBudgetCategory(budgetCategory2);

        BudgetCategory budgetCategory3 = new BudgetCategory();
        budgetCategory3.setBudgetCategoryMin(budgetList.get(sizeOfRange) * 2);
        budgetCategory3.setBudgetCategoryName(EXPENCIVE_MOVIE);
        budgetCategoryDao.saveBudgetCategory(budgetCategory3);

        Map<String, BudgetCategory> mapOfBudgetCategories = new HashMap<>();
        mapOfBudgetCategories.put(budgetCategory1.getBudgetCategoryName(), budgetCategory1);
        mapOfBudgetCategories.put(budgetCategory2.getBudgetCategoryName(), budgetCategory2);
        mapOfBudgetCategories.put(budgetCategory3.getBudgetCategoryName(), budgetCategory3);
        return mapOfBudgetCategories;
    }

    private Map<String, DuratonCategory> durationCategorize(List<Integer> durationList) {
        Collections.sort(durationList);
        int numberOfElements = durationList.size();
        int sizeOfRange = numberOfElements / 3;
        DuratonCategory durationCategory1 = new DuratonCategory();
        durationCategory1.setDuratonCategoryMin(0);
        durationCategory1.setDuratonCategoryMax(durationList.get(sizeOfRange));
        durationCategory1.setDuratonCategoryName(SHORT_MOVIE);
        duratonCategoryDao.saveDuratonCategory(durationCategory1);

        DuratonCategory durationCategory2 = new DuratonCategory();
        durationCategory2.setDuratonCategoryMin(durationList.get(sizeOfRange));
        durationCategory2.setDuratonCategoryMax(durationList.get(sizeOfRange * 2));
        durationCategory2.setDuratonCategoryName(MEDIUM_MOVIE);
        duratonCategoryDao.saveDuratonCategory(durationCategory2);

        DuratonCategory durationCategory3 = new DuratonCategory();
        durationCategory3.setDuratonCategoryMin(durationList.get(sizeOfRange * 2));
        durationCategory3.setDuratonCategoryName(LONG_MOVIE);
        duratonCategoryDao.saveDuratonCategory(durationCategory3);

        Map<String, DuratonCategory> mapOfDuratonCategories = new HashMap<>();
        mapOfDuratonCategories.put(durationCategory1.getDuratonCategoryName(), durationCategory1);
        mapOfDuratonCategories.put(durationCategory2.getDuratonCategoryName(), durationCategory2);
        mapOfDuratonCategories.put(durationCategory3.getDuratonCategoryName(), durationCategory3);
        return mapOfDuratonCategories;

    }

    private void populateMapsAndList(RowData rowData, Map<String, Integer> directorMap, Map<String, Integer> actorMap, List<Integer> durationList, List<Integer> budgetList) {
        //Director map populate
        String directorName = rowData.getDiretorName();
        int directorCount = 0;
        if (directorMap.containsKey(directorName)) {
            directorCount = directorMap.get(directorName);
        }
        directorCount++;
        directorMap.put(directorName, directorCount);

        //Actor map populate
        String actorName = rowData.getActorName();
        int actorCount = 0;
        if (actorMap.containsKey(actorName)) {
            actorCount = actorMap.get(actorName);
        }
        actorCount++;
        actorMap.put(actorName, actorCount);

        //Duration list pupulate
        Integer duration = rowData.getDuration();
        durationList.add(duration);

        //Budget list populate
        Integer budget = rowData.getBudget();
        budgetList.add(budget);
    }

    @Override
    public TrainingResponce trainingData() {
        List<PreProcessedData> preProcessedDataList = preProcessedDataDao.findAllPreProcessedData();
        DataSet dataSet = new DataSet();
        List<Recode> listOfRecodes = new ArrayList<>();
        for (PreProcessedData preProcessedData : preProcessedDataList) {
            Recode recode = new Recode();
            recode.setActorOne(preProcessedData.getActorCategory().getActorCategoryName());
            recode.setBudgetID(preProcessedData.getBudgetCategory().getBudgetCategoryName());
            recode.setCountry(preProcessedData.getCountry());
            recode.setDirector(preProcessedData.getDiretorCategory().getDirectorCategoryName());
            recode.setDuration(preProcessedData.getDiretorCategory().getDirectorCategoryName());
            recode.setGenres(preProcessedData.getGenre());
            recode.setLanguage(preProcessedData.getLanauge());
            if(preProcessedData.getIsProfitable().equals("true")){
                recode.setProfitable(true);
            }else{
                recode.setProfitable(false);
            }
            
            listOfRecodes.add(recode);
        }
        dataSet.setListOfRecodes(listOfRecodes);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(dataSet);
        } catch (JsonProcessingException ex) {
            java.util.logging.Logger.getLogger(DataManipulationRestController.class.getName()).log(Level.SEVERE, null, ex);
            SL4J_LOGGER.error("String conversion error found in data set");
        }

        //send it to Nive Byaers
        ListOfLikelihoodTables likelihoodTables;
        SL4J_LOGGER.info("Start Nive Training...");
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://localhost:8084/naive-bayes-api/traing-nive-bayers-algo");

            StringEntity input = new StringEntity(jsonInString);
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            String responcePayload = "";
            while ((output = br.readLine()) != null) {
                responcePayload += output;
            }
            httpClient.getConnectionManager().shutdown();

            SL4J_LOGGER.info("Found responce [{}]", responcePayload);
            likelihoodTables = mapper.readValue(responcePayload, ListOfLikelihoodTables.class);
        } catch (MalformedURLException e) {
            SL4J_LOGGER.error("Cannot connect to Nive.", e);
            throw new MovieProjectApiException(HttpStatus.GATEWAY_TIMEOUT, ERROR_IN_CONNECTING_NAIVE, "URL is invalid for nive.");
        } catch (IOException e) {
            SL4J_LOGGER.error("IO exception on read responce.", e);
            throw new MovieProjectApiException(HttpStatus.GATEWAY_TIMEOUT, ERROR_IN_CONNECTING_NAIVE, "Cannot read nive responce.");
        }
        SL4J_LOGGER.info("End Nive Training...");
        
        //send it to CART
        SL4J_LOGGER.info("Start CART Training...");
        String cartURL="";
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://localhost:8084/cart-api/traing-cart-algo");

            StringEntity input = new StringEntity(jsonInString);
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            String responcePayload = "";
            while ((output = br.readLine()) != null) {
                responcePayload += output;
            }
            httpClient.getConnectionManager().shutdown();

            SL4J_LOGGER.info("Found responce [{}]", responcePayload);
            cartURL = responcePayload;
        } catch (MalformedURLException e) {
            SL4J_LOGGER.error("Cannot connect to CART.", e);
            throw new MovieProjectApiException(HttpStatus.GATEWAY_TIMEOUT, ERROR_IN_CONNECTING_NAIVE, "URL is invalid for nive.");
        } catch (IOException e) {
            SL4J_LOGGER.error("IO exception on read responce.", e);
            throw new MovieProjectApiException(HttpStatus.GATEWAY_TIMEOUT, ERROR_IN_CONNECTING_NAIVE, "Cannot read nive responce.");
        }
        SL4J_LOGGER.info("End CART Training...");
        
        TrainingResponce trainingResponce = new TrainingResponce();
        trainingResponce.setLikelihoodTables(likelihoodTables);
        trainingResponce.setCartURL(cartURL);
        return trainingResponce;
    }

    @Override
    public TestResults testAccurecy(List<RowData> listOfRowdata) {

        DataSet dataSet = new DataSet();
        List<Recode> listOfRecodes = new ArrayList<>();
        for (RowData rowData : listOfRowdata) {
            Recode recode = getRecodeFromRowData(rowData);
            listOfRecodes.add(recode);
        }
        dataSet.setListOfRecodes(listOfRecodes);


        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(dataSet);
        } catch (JsonProcessingException ex) {
            java.util.logging.Logger.getLogger(DataManipulationRestController.class.getName()).log(Level.SEVERE, null, ex);
            SL4J_LOGGER.error("String conversion error found in data set");
        }

        //send it to Nive Byaers - test
        NiveBayearsTestResult niveTestResults;
        SL4J_LOGGER.info("Start Nive Testing...");
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://localhost:8084/naive-bayes-api/test-nive-bayers");

            StringEntity input = new StringEntity(jsonInString);
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            String responcePayload = "";
            while ((output = br.readLine()) != null) {
                responcePayload += output;
            }
            httpClient.getConnectionManager().shutdown();

            SL4J_LOGGER.info("Found responce [{}]", responcePayload);
            niveTestResults = mapper.readValue(responcePayload, NiveBayearsTestResult.class);
        } catch (MalformedURLException e) {
            SL4J_LOGGER.error("Cannot connect to Nive.", e);
            throw new MovieProjectApiException(HttpStatus.GATEWAY_TIMEOUT, ERROR_IN_CONNECTING_NAIVE, "URL is invalid for nive.");
        } catch (IOException e) {
            SL4J_LOGGER.error("IO exception on read responce.", e);
            throw new MovieProjectApiException(HttpStatus.GATEWAY_TIMEOUT, ERROR_IN_CONNECTING_NAIVE, "Cannot read nive responce.");
        }
        SL4J_LOGGER.info("End Nive Testing...");

        //send it to Cart- test
        SL4J_LOGGER.info("Start Cart Testing...");
        CartTestResult cartTestResult;
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://localhost:8084/cart-api/test-cart");

            StringEntity input = new StringEntity(jsonInString);
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            String responcePayload = "";
            while ((output = br.readLine()) != null) {
                responcePayload += output;
            }
            httpClient.getConnectionManager().shutdown();

            SL4J_LOGGER.info("Found responce [{}]", responcePayload);
            cartTestResult = mapper.readValue(responcePayload, CartTestResult.class);
        } catch (MalformedURLException e) {
            SL4J_LOGGER.error("Cannot connect to Nive.", e);
            throw new MovieProjectApiException(HttpStatus.GATEWAY_TIMEOUT, ERROR_IN_CONNECTING_NAIVE, "URL is invalid for nive.");
        } catch (IOException e) {
            SL4J_LOGGER.error("IO exception on read responce.", e);
            throw new MovieProjectApiException(HttpStatus.GATEWAY_TIMEOUT, ERROR_IN_CONNECTING_NAIVE, "Cannot read cart responce.");
        }
        SL4J_LOGGER.info("End Cart Testing...");

        int totalTestDataset =niveTestResults.getTotalTestDataset();
        int cartTotalSuccessCount = cartTestResult.getTotalSuccessCount();
        double cartSuccessProbability = (double)cartTotalSuccessCount/(double)totalTestDataset;
        double cartTotalUnsuccessCount =1-cartSuccessProbability;
        cartTestResult.setSuccessProbability(cartSuccessProbability);
        cartTestResult.setUnsuccessProbability(cartTotalUnsuccessCount);

        TestResults agregatedTestResults = new TestResults();
        agregatedTestResults.setNiveBayearsTestResult(niveTestResults);
        agregatedTestResults.setCartTestResult(cartTestResult);

        return agregatedTestResults;
    }

    private Recode getRecodeFromRowData(RowData rowData) {
        Recode recode = new Recode();
        recode.setCountry(rowData.getCountry());
        recode.setGenres(rowData.getGenre());
        recode.setLanguage(rowData.getLanauge());
        recode.setProfitable(Boolean.valueOf(rowData.getIsProfitable()));
        Actor actor = actorDao.findActorByName(rowData.getActorName());
        if (actor == null) {
            recode.setActorOne(UNKNOWN__ACTOR);
        } else {
            recode.setActorOne(actor.getActorCategory().getActorCategoryName());
        }
        Director director = directorDao.findDirectorByName(rowData.getDiretorName());
        if (actor == null) {
            recode.setDirector(MOST__POPULER__DIRECTOR);
        } else {
            recode.setDirector(director.getDirectorCategory().getDirectorCategoryName());
        }
        Integer duration = rowData.getDuration();
        if (duratonCategoryDao.findDuratonCategoryByName(LONG_MOVIE).getDuratonCategoryMin() < duration) {
            recode.setDuration(duratonCategoryDao.findDuratonCategoryByName(LONG_MOVIE).getDuratonCategoryName());
        } else if (duratonCategoryDao.findDuratonCategoryByName(MEDIUM_MOVIE).getDuratonCategoryMin() < duration) {
            recode.setDuration(duratonCategoryDao.findDuratonCategoryByName(MEDIUM_MOVIE).getDuratonCategoryName());
        } else {
            recode.setDuration(duratonCategoryDao.findDuratonCategoryByName(SHORT_MOVIE).getDuratonCategoryName());
        }
        Integer budget = rowData.getBudget();
        if (budgetCategoryDao.findBudgetCategoryByName(EXPENCIVE_MOVIE).getBudgetCategoryMin() < budget) {
            recode.setBudgetID(budgetCategoryDao.findBudgetCategoryByName(EXPENCIVE_MOVIE).toString());
        } else if (budgetCategoryDao.findBudgetCategoryByName(AVARAGE_MOVIE).getBudgetCategoryMin() < budget) {
            recode.setBudgetID(budgetCategoryDao.findBudgetCategoryByName(AVARAGE_MOVIE).getBudgetCategoryName());
        } else {
            recode.setBudgetID(budgetCategoryDao.findBudgetCategoryByName(CHEEP_MOVIE).getBudgetCategoryName());
        }
        return recode;
    }

    @Override
    public PredictionResults predict(RowData rowData) {
        Recode recode = getRecodeFromRowData(rowData);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(recode);
        } catch (JsonProcessingException ex) {
            java.util.logging.Logger.getLogger(DataManipulationRestController.class.getName()).log(Level.SEVERE, null, ex);
            SL4J_LOGGER.error("String conversion error found in data set");
        }
        
        //nive pridiction
        SL4J_LOGGER.info("Nive pridiction is started");
        NiveBaseResult niveByarseResult;
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://localhost:8084/naive-bayes-api/nive-bayers-predict");

            StringEntity input = new StringEntity(jsonInString);
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            String responcePayload = "";
            while ((output = br.readLine()) != null) {
                responcePayload += output;
            }
            httpClient.getConnectionManager().shutdown();

            SL4J_LOGGER.info("Found responce [{}]", responcePayload);
            niveByarseResult = mapper.readValue(responcePayload, NiveBaseResult.class);
        } catch (MalformedURLException e) {
            SL4J_LOGGER.error("Cannot connect to Nive.", e);
            throw new MovieProjectApiException(HttpStatus.GATEWAY_TIMEOUT, ERROR_IN_CONNECTING_NAIVE, "URL is invalid for nive.");
        } catch (IOException e) {
            SL4J_LOGGER.error("IO exception on read responce.", e);
            throw new MovieProjectApiException(HttpStatus.GATEWAY_TIMEOUT, ERROR_IN_CONNECTING_NAIVE, "Cannot read nive responce.");
        }
        SL4J_LOGGER.info("End Nive Prediction...");
        
        //cart pridiction
        SL4J_LOGGER.info("Cart pridiction is started");
        CartResult cartResult;
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://localhost:8084/cart-api/cart-predict");

            StringEntity input = new StringEntity(jsonInString);
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            String responcePayload = "";
            while ((output = br.readLine()) != null) {
                responcePayload += output;
            }
            httpClient.getConnectionManager().shutdown();

            SL4J_LOGGER.info("Found responce [{}]", responcePayload);
            cartResult = mapper.readValue(responcePayload, CartResult.class);
        } catch (MalformedURLException e) {
            SL4J_LOGGER.error("Cannot connect to Nive.", e);
            throw new MovieProjectApiException(HttpStatus.GATEWAY_TIMEOUT, ERROR_IN_CONNECTING_NAIVE, "URL is invalid for nive.");
        } catch (IOException e) {
            SL4J_LOGGER.error("IO exception on read responce.", e);
            throw new MovieProjectApiException(HttpStatus.GATEWAY_TIMEOUT, ERROR_IN_CONNECTING_NAIVE, "Cannot read nive responce.");
        }
        SL4J_LOGGER.info("End Nive Prediction...");
        
        PredictionResults predictionResults = new PredictionResults();
        predictionResults.setNiveByarseResult(niveByarseResult);
        predictionResults.setCartResult(cartResult);
        return predictionResults;
    }

    @Override
    public List<Actor> findAllActors() {
        return actorDao.findAllActors();
    }

    @Override
    public List<Director> findAlldirectors() {
        return directorDao.findAllDirectors();
    }

}
