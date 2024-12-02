package com.saiguides.mytodo.service;

import com.saiguides.mytodo.dto.MytodoDto;
import com.saiguides.mytodo.entity.Mytodo;
import com.saiguides.mytodo.exception.ResourceNotFoundException;
import com.saiguides.mytodo.repository.MytodoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MytodoServiceImpl implements MytodoService{
    private MytodoRepository mytodoRepository;
    private ModelMapper modelMapper;
    @Override
    public MytodoDto addTodo(MytodoDto mytodoDto) {
        Mytodo mytodo = modelMapper.map(mytodoDto, Mytodo.class);
        Mytodo savedMytodo = mytodoRepository.save(mytodo);
        MytodoDto savedMytodoDto = modelMapper.map(savedMytodo, MytodoDto.class);
        return savedMytodoDto;
    }

    @Override
    public MytodoDto getTodo(Long id) {
        Mytodo mytodo = mytodoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo not found with id"+id));
        return modelMapper.map(mytodo, MytodoDto.class);
    }

    @Override
    public List<MytodoDto> getaAllTodos() {
        List<Mytodo> mytodos = mytodoRepository.findAll();
        return mytodos.stream().map((mytodo)->modelMapper.map(mytodo, MytodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MytodoDto updateTodo(MytodoDto mytodoDto, Long id) {
        Mytodo mytodo = mytodoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo not found with id"+id));
        mytodo.setTitle(mytodoDto.getTitle());
        mytodo.setDescription(mytodoDto.getDescription());
        mytodo.setCompleted(mytodoDto.isCompleted());
        Mytodo updatedTodo = mytodoRepository.save(mytodo);
        return modelMapper.map(updatedTodo, MytodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Mytodo mytodo = mytodoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo not found with id"+id));
        mytodoRepository.deleteById(id);
    }

    @Override
    public MytodoDto completeTodo(Long id) {
        Mytodo mytodo = mytodoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo not found with id"+id));
        mytodo.setCompleted(Boolean.TRUE);
        Mytodo updatedTodo = mytodoRepository.save(mytodo);
        return modelMapper.map(updatedTodo, MytodoDto.class);
    }

    @Override
    public MytodoDto inCompleteTodo(Long id) {
        Mytodo mytodo = mytodoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo not found with id"+id));
        mytodo.setCompleted(Boolean.FALSE);
        Mytodo updatedTodo = mytodoRepository.save(mytodo);
        return modelMapper.map(updatedTodo, MytodoDto.class);
    }
}
