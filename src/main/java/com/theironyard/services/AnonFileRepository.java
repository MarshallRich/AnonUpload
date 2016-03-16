package com.theironyard.services;

import com.theironyard.entities.AnonFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by MacLap on 3/16/16.
 */
public interface AnonFileRepository extends CrudRepository<AnonFile, Integer>{
    List<AnonFile> findByIsKept(boolean isKept);

}
