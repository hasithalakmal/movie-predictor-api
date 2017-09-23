package com.smile24es.ts_project.dao;

import com.smile24es.ts_project.beans.RowData;

import java.util.List;

/**
 * Created by hasithagamage on 5/15/17.
 */
public interface RowDataDao {

    List<RowData> findAllRowData();

    void saveRowData(RowData rowdata);
    
    void deleteAllRowData();

}
