package com.jd.gobrs.async.example.service;

import com.jd.gobrs.async.example.DataContext;
import com.jd.gobrs.async.example.executor.SerExector;
import com.jd.gobrs.async.gobrs.GobrsAsyncSupport;
import com.jd.gobrs.async.task.AsyncTask;
import com.jd.gobrs.async.task.TaskResult;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: gobrs-async-example
 * @ClassName DService
 * @description:
 * @author: sizegang
 * @create: 2022-01-29 21:04
 * @Version 1.0
 **/
@Service
public class DService implements AsyncTask<DataContext, Map>, SerExector {

    @Override
    public void result(boolean success, DataContext param, TaskResult<Map> workResult) {
        if (success) {
//            System.out.println("DService 成功");
        } else {
            System.out.println("DService 失败");
        }
    }

    @Override
    public Map task(DataContext params, GobrsAsyncSupport support) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean nessary(DataContext params, GobrsAsyncSupport support) {
        return true;
    }
}
