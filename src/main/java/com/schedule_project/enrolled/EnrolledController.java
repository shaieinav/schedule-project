package com.schedule_project.enrolled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EnrolledController {

    private final EnrolledService enrolledService;

    @Autowired
    public EnrolledController(EnrolledService enrolledService) {
        this.enrolledService = enrolledService;
    }

    @GetMapping("/enrolled/all")
    public List<Enrolled> getAllEnrolls() {
        return enrolledService.getAllEnrolls();
    }

    @GetMapping("/enrolled")
    public Enrolled getEnrollment(@RequestBody EnrolledId enrolledId,
                                  @RequestParam Integer studentId, @RequestParam Integer groupNum,
                                  @RequestParam String groupLocation, @RequestParam Integer courseNum,
                                  @RequestParam String semesterName) throws Exception {
        return enrolledService.getEnrollment(enrolledId);
    }

    @PostMapping("/enrolled")
    public void addEnrollment(@RequestBody Enrolled enrolled) {
        enrolledService.addEnrollment(enrolled);
    }

//    @PutMapping("/enrolled/{id}/{groupNum}/{groupLocation}/{courseNum}/{semesterName}")
//    public void updateEnrollment(@RequestBody Enrolled enrolled, @RequestBody EnrolledId enrolledId, @PathVariable Integer id,
//                                 @PathVariable Integer groupNum, @PathVariable String groupLocation, @PathVariable Integer courseNum,
//                                 @PathVariable String semesterName) {
//
//        enrolledService.updateEnrollment(enrolledId, enrolled);
//    }

//    @PutMapping("/enrolled")
//    public ResponseEntity<?> updateEnrollment(@Valid /*@RequestBody Enrolled enrolled,*/ @RequestBody EnrolledId enrolledId, BindingResult result)  throws Exception {
//
//        return enrolledService.updateEnrollment(enrolledId/*, enrolled*/, result);
//    }

    @DeleteMapping("/enrolled")
    public void deleteEnrollment(@RequestBody EnrolledId enrolledId) {
        enrolledService.deleteEnrollment(enrolledId);
    }
}