package com.saiguides.mytodo.controller;

import com.saiguides.mytodo.dto.MytodoDto;
import com.saiguides.mytodo.service.MytodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mytodos")
@AllArgsConstructor
public class MytodoController {
    private MytodoService mytodoService;
    //Build add Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MytodoDto> addTodo(@RequestBody MytodoDto mytodoDto){
        MytodoDto savedMytodo = mytodoService.addTodo(mytodoDto);
        return new ResponseEntity<>(savedMytodo, HttpStatus.CREATED);
    }
    //Build Get Todo REST API
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<MytodoDto> getTodo(@PathVariable("id") Long todoId){
         MytodoDto mytodoDto = mytodoService.getTodo(todoId);
         return new ResponseEntity<>(mytodoDto, HttpStatus.OK);
    }
    //Build Getall Todos REST API
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<MytodoDto>> getAllTodos(){
        List<MytodoDto> mytodos = mytodoService.getaAllTodos();
        return new ResponseEntity<>(mytodos,HttpStatus.OK);
    }
    //Build Update Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<MytodoDto> updateTodo(@RequestBody MytodoDto mytodoDto, @PathVariable("id") Long todoId){
        MytodoDto updatedTodo = mytodoService.updateTodo(mytodoDto, todoId);
        return new ResponseEntity<>(updatedTodo,HttpStatus.OK);
    }
    //Build Delete Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        mytodoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted Successfully!");
    }
    //Build Complete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<MytodoDto> completeTodo(@PathVariable("id") Long todoId){
        MytodoDto updatedTodo = mytodoService.completeTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }
    //Build Incomplete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<MytodoDto> inCompleteTodo(@PathVariable("id") Long todoId){
        MytodoDto updatedTodo = mytodoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }
}
