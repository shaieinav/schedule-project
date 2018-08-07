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

    public Teaches getTeaches(Teaches.TeachesId teachesId) throws Exception {
        return teachesRepository.findById(teachesId).orElseThrow(() -> new Exception("Can't find teaches id"));
    }

    public void addTeaches(Teaches teaches) {
        teachesRepository.save(teaches);
    }

    public void updateTeaches(Teaches.TeachesId teachesId, Teaches teaches) {
        teachesRepository.save(teaches);
    }
}