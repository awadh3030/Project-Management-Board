package com.ProjectManagement.Project.Management.Board.API.Services;


import com.ProjectManagement.Project.Management.Board.API.Models.Board;
import com.ProjectManagement.Project.Management.Board.API.Repositories.BoardRepository;
import com.ProjectManagement.Project.Management.Board.API.ResponseObjects.GetBoardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServices {

    @Autowired
    BoardRepository  boardRepository;

    public  void saveBoard(Board board) {
        boardRepository.save(board);
    }

    public List<Board> getAllBoard() {
        return boardRepository.findAll();
    }


    public Board getBoardById(Long id){
        return boardRepository.findById(id).get();
    }

    public Board updateBoard(Board existingBoard) {
        return boardRepository.save(existingBoard);
    }

    public void deleteBoard(Board board) {
        boardRepository.delete(board);
    }


}
