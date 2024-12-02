package com.saiguides.mytodo.service;


import com.saiguides.mytodo.dto.MytodoDto;

import java.util.List;

public interface MytodoService {
    MytodoDto addTodo(MytodoDto mytodoDto);
    MytodoDto getTodo(Long id);
    List<MytodoDto> getaAllTodos();
    MytodoDto updateTodo(MytodoDto mytodoDto, Long id);
    void deleteTodo(Long id);
    MytodoDto completeTodo(Long id);
    MytodoDto inCompleteTodo(Long id);
}
