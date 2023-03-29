package com.demo.reactive.api;

import com.demo.reactive.model.Employee;
import com.demo.reactive.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("v1/api/employee")
public class EmployeeApi {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public void create(@RequestBody Employee employee) {
        employeeService.create(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") Integer id) {
        Mono<Employee> employeeMono = employeeService.findById(id);
        return new ResponseEntity<Mono<Employee>>(employeeMono, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/name/{name}")
    public Flux<Employee> findByName(@PathVariable("name") String name) {
        return employeeService.findByName(name);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> update(@RequestBody Employee employee) {
        return employeeService.update(employee);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById (@PathVariable("id") Integer id) {
        employeeService.deleteById(id).subscribe();
    }
}
