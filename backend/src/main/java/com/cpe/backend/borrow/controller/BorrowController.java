package com.cpe.backend.borrow.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.cpe.backend.borrow.entity.Employee;
import com.cpe.backend.borrow.entity.Borrow;
import com.cpe.backend.borrow.entity.Category;
import com.cpe.backend.borrow.entity.Members;
import com.cpe.backend.borrow.entity.SportEquipment;
import com.cpe.backend.borrow.repository.BorrowRepository;
import com.cpe.backend.borrow.repository.MembersRepository;
import com.cpe.backend.borrow.repository.CategoryRepository;
import com.cpe.backend.borrow.repository.EmployeeRepository;
import com.cpe.backend.borrow.repository.SportEquipmentRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BorrowController {
    @Autowired
    private final BorrowRepository borrowRepository;
    @Autowired 
    private MembersRepository membersRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SportEquipmentRepository sportEquipmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    BorrowController(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    @GetMapping("/borrow")
    public Collection<Borrow> Borrows() {
        return borrowRepository.findAll().stream().collect(Collectors.toList());
    }


    @PostMapping("/borrow/{M}/{C}/{SE}/{E}")
    public Borrow newBorrow(Borrow newBorrow, @PathVariable long M, @PathVariable long C, @PathVariable long SE,
            @PathVariable long E)

    {
        Members members = membersRepository.findById(M);
        Category category = categoryRepository.findById(C);
        SportEquipment sportEquipment = sportEquipmentRepository.findById(SE);
        Employee employee = employeeRepository.findById(E);

        newBorrow.setSportEquipment(sportEquipment);
        newBorrow.setCategory(category);
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);

        newBorrow.setBorrow_date(new Date());

        return borrowRepository.save(newBorrow);
    }
}
