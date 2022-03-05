package com.jd.gobrs.async.example.service;

import com.alibaba.fastjson.JSONObject;
import com.jd.gobrs.async.example.DataContext;
import com.jd.gobrs.async.example.executor.ParaExector;
import com.jd.gobrs.async.gobrs.GobrsAsyncSupport;
import com.jd.gobrs.async.task.AsyncTask;
import com.jd.gobrs.async.task.TaskResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: gobrs-async-example
 * @ClassName AService
 * @description:
 * @author: sizegang
 * @create: 2022-01-29 21:01
 * @Version 1.0
 **/
@Service
public class AService implements AsyncTask<DataContext, DataContext>, ParaExector {
    @Override
    public void result(boolean success, DataContext param, TaskResult<DataContext> workResult) {
        if (success) {
//            System.out.println("AService 成功");
        } else {
            System.out.println("AService 失败");
        }
    }

    @Override
    public DataContext task(DataContext params, GobrsAsyncSupport support) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        byte[] result  = new byte[1024*1024*10];
        Map h = new HashMap();
        h.put("result", result);
        params.setResult(h);
        return params;
    }

    @Override
    public boolean nessary(DataContext params, GobrsAsyncSupport support) {
        return true;
    }
}
