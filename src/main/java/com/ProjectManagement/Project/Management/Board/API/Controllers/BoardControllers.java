package com.ProjectManagement.Project.Management.Board.API.Controllers;


import com.ProjectManagement.Project.Management.Board.API.Models.Board;
import com.ProjectManagement.Project.Management.Board.API.Repositories.BoardRepository;
import com.ProjectManagement.Project.Management.Board.API.RequestObjects.GetBoardRequestObjects;
import com.ProjectManagement.Project.Management.Board.API.Services.BoardServices;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/boards")
@CrossOrigin("*")
public class BoardControllers {
    @Autowired
    public BoardServices boardServices;
    @Autowired
    public BoardRepository boardRepository;

    @PostMapping("/createBoard")

    public Board createBoard(@RequestBody Board newBoard) {
        boardRepository.save(newBoard);
        return newBoard;
    }

    public void createBoardFromRequest(@RequestBody GetBoardRequestObjects boardRequestObjects) {
        Board board = new Board();
        board.setTitle(boardRequestObjects.getTitle());
        board.setId(1L);
        board.setColumns(Arrays.toString(new String[]{1+"To do", 2+"In progress", 3+"Done"}));
        boardServices.saveBoard(board);
    }


    @GetMapping
    public List<Board> getAllBoard() {
        return boardRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Board> getSpecificBoard(@PathVariable Long id) {
        return boardRepository.findById(id);
    }

    @PutMapping(path = "/{id}")
    public Board updateSpecificBoard(@PathVariable Long id, @RequestBody Board newBoard) {
        Board existingBoard = getSpecificBoard(id).get();
        existingBoard.setTitle(newBoard.getTitle());
        boardRepository.save(existingBoard);
        return existingBoard;
    }
    @DeleteMapping(path = "/{id}")
    public Board removeBoard(@PathVariable Long id) {
        Board existingBoard = getSpecificBoard(id).get();
        boardRepository.delete(existingBoard);
        return existingBoard;
    }






}
