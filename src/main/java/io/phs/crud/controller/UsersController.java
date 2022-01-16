package io.phs.crud.controller;

import io.phs.crud.entity.Users;
import io.phs.crud.model.ResponseModel;
import io.phs.crud.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UsersController {


    private final UsersRepository usersRepository;
    @Autowired
    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping(value = "/create",headers = "Accept=application/json")
    public ResponseEntity<ResponseModel> create(@RequestBody() Map<String , Users> payload) {
        Users user = payload.get("user");
        usersRepository.save(user);
        return new ResponseEntity<>(new ResponseModel("Created Success", 200, user), HttpStatus.OK);
    }

    @PutMapping(value = "/update",headers = "Accept=application/json")
    public ResponseEntity<ResponseModel> update(@RequestBody() Map<String,Users> payload,@RequestParam("id") Long id){
        Users user = payload.get("user");
        Users users =  usersRepository.findById(id).orElse(null);
        if(users ==null){
            return new ResponseEntity<>(new ResponseModel("Data Not Found", 404,null ), HttpStatus.NOT_FOUND);
        }
        users.setPassword(user.getUsername());
        users.setUsername(user.getPassword());
        usersRepository.save(users);
        return new ResponseEntity<>(new ResponseModel("Update Success", 200,users ), HttpStatus.OK);
    }

    @GetMapping(value = "/all",headers = "Accept=application/json")
    public  ResponseEntity<ResponseModel> get(){
        List<Users> user =  usersRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        return new ResponseEntity<>(new ResponseModel("Read All Success", 200, user), HttpStatus.OK);
    }

    @GetMapping(value = "/detail",headers = "Accept=application/json")
    public  ResponseEntity<ResponseModel> detail(@RequestParam("id") Long id){
        Users users =  usersRepository.findById(id).orElse(null);
        if(users ==null){
            return new ResponseEntity<>(new ResponseModel("Data Not Found", 404,null ), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseModel("Delete Success", 200,users ), HttpStatus.OK);
    }


    @GetMapping(value = "/delete",headers = "Accept=application/json")
    public  ResponseEntity<ResponseModel> delete( @RequestParam("id") Long id){
        Users users = usersRepository.findById(id).orElse(null);
        if(users ==null){
            return new ResponseEntity<>(new ResponseModel("Data Not Found", 404,null ), HttpStatus.NOT_FOUND);
        }
        usersRepository.deleteById(id);
        return new ResponseEntity<>(new ResponseModel("Delete Success", 200,users ), HttpStatus.OK);
    }
}
