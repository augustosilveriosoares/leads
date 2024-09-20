package com.leads.leads.controllers;

import com.leads.leads.client.UsersClient;
import com.leads.leads.dto.LeadDto;
import com.leads.leads.repositories.LeadRepository;
import com.leads.leads.models.Lead;
import com.netflix.discovery.converters.Auto;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class LeadController {

    @Autowired
    private LeadRepository r;

    private LeadDto userDto;

    @Autowired
    private UsersClient usersClient;


    @PostMapping("/leads")
    public ResponseEntity<Lead> saveLead(@RequestBody @Valid LeadDto leadDto){
        var lead = new Lead();
        BeanUtils.copyProperties(leadDto,lead);
        return  ResponseEntity.status(HttpStatus.CREATED).body(r.save(lead));
    }

    @GetMapping("/leads")
    public ResponseEntity<List<Lead>> getAllLeads (){
        List<Lead> leadList = r.findAll();
        if(!leadList.isEmpty()){
            for(Lead lead: leadList){
                UUID id = lead.getId();
                lead.add(linkTo(methodOn(LeadController.class).getOneLead(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(leadList);
    }

    @GetMapping("/users")
    public List<Object> getUsers(){
        return usersClient.getAllUsers();
    }

    @GetMapping("/leads/{id}")
    public ResponseEntity<Object>getOneLead(@PathVariable(value="id") UUID id){
        Optional<Lead> lead0 = r.findById(id);
        if(lead0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lead não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(lead0.get());
    }

    @PutMapping("/leads/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value="id")UUID id, @RequestBody @Valid LeadDto leadDto){
        Optional<Lead> lead0 = r.findById(id);
        if(lead0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lead não encontrado");
        }
        var lead = lead0.get();
        BeanUtils.copyProperties(leadDto,lead);
        return  ResponseEntity.status(HttpStatus.OK).body(r.save(lead));

    }

    @DeleteMapping("/leads/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value="id")UUID id){
        Optional<Lead> lead0 = r.findById(id);
        if(lead0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lead não encontrado");
        }
        r.delete(lead0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Lead Removido com sucesso!");

    }
}
