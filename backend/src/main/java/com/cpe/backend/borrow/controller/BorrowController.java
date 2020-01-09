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

import com.cpe.backend.Employee.entity.Employee;
import com.cpe.backend.borrow.entity.Borrow;
import com.cpe.backend.Sportequipment.entity.Category;
import com.cpe.backend.Members.entity.Members;
import com.cpe.backend.Sportequipment.entity.Sportequipment;
import com.cpe.backend.borrow.repository.BorrowRepository;
import com.cpe.backend.Members.repository.MembersRepository;
import com.cpe.backend.Sportequipment.repository.CategoryRepository;
import com.cpe.backend.Employee.repository.EmployeeRepository;
import com.cpe.backend.Sportequipment.repository.SportequipmentRepository;
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
    private SportequipmentRepository sportequipmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    BorrowController(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    @GetMapping("/borrow")
    public Collection<Borrow> Borrows() {
        return borrowRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/borrow/Members/{member_id}")
    public Borrow checkMembers(@PathVariable long member_id) {
        Members tmp = membersRepository.findById(member_id);
        return borrowRepository.findByMembers(tmp);
    }


    @PostMapping("/borrow/{M}/{C}/{SE}/{E}")
    public Borrow newBorrow(Borrow newBorrow, @PathVariable long M, @PathVariable long C, @PathVariable long SE,
            @PathVariable long E)

    {
        Members members = membersRepository.findById(M);
        Category category = categoryRepository.findById(C);
        Sportequipment sportequipment = sportequipmentRepository.findById(SE);
        Employee employee = employeeRepository.findById(E);

        newBorrow.setSportequipment(sportequipment);
        newBorrow.setCategory(category);
        newBorrow.setMembers(members);
        newBorrow.setEmployee(employee);
        newBorrow.setBorrow_date(new Date());

        return borrowRepository.save(newBorrow);
    }
}
