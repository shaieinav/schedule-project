package com.schedule_project.teaches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachesService {

    @Autowired
    private TeachesRepository teachesRepository;

    public List<Teaches> getAllTeaches() {
        return teachesRepository.findAll();
    }

//    The following methods are not implemented yet, need to see if they are even needed
//
//    public Teaches getTeaches(TeachesId teachesId) throws Exception {
//        return teachesRepository.findById(teachesId).orElseThrow(() -> new Exception("Can't find teaches id"));
//    }
//
//    public void addTeaches(Teaches teaches) {
//        teachesRepository.save(teaches);
//    }
//
//    public void updateTeaches(TeachesId teachesId, Teaches teaches) {
//        teachesRepository.save(teaches);
//    }
}