package org.iaroslaveremeev.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.iaroslaveremeev.model.TV;
import org.iaroslaveremeev.util.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TvRepository {
    private ArrayList<TV> tvs = new ArrayList<>();

    public TvRepository() {
        ObjectMapper objectMapper = new ObjectMapper();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.FILE_NAME))){
            this.tvs = objectMapper.readValue(bufferedReader, new TypeReference<>() {});
        } catch (IOException ignored) {}
    }

    public int getMaxId(ArrayList<TV> tvs){
        if (tvs.size() > 0){
            return tvs.stream().max(Comparator.comparing(TV::getId)).get().getId();
        }
        else return 0;
    }

    public void addTv(TV tv){
        int maxId = getMaxId(this.tvs) + 1;
        tv.setId(maxId);
        this.tvs.add(tv);
        ObjectMapper objectMapper = new ObjectMapper();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constants.FILE_NAME))){
            objectMapper.writeValue(bufferedWriter, this.tvs);
        } catch (IOException ignored) {}
    }

    public TV getTvById(int id){
        for (TV tv : tvs) {
            if (tv.getId() == id) return tv;
        }
        return null;
    }

    public ArrayList<TV> getTvs() {
        return tvs;
    }

    public void deleteById(int id){
        this.tvs.removeIf(item -> item.getId() == id);
        ObjectMapper objectMapper = new ObjectMapper();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constants.FILE_NAME))){
            objectMapper.writeValue(bufferedWriter, this.tvs);
        } catch (IOException ignored) {}
    }

    public void substitute(TV newTv){
        this.tvs = (ArrayList<TV>) this.tvs.stream().map(oldTv -> oldTv.getId() == newTv.getId() ? newTv : oldTv)
                .collect(Collectors.toList());
        ObjectMapper objectMapper = new ObjectMapper();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constants.FILE_NAME))){
            objectMapper.writeValue(bufferedWriter, this.tvs);
        } catch (IOException ignored) {}
    }
}
