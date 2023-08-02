package com.ProjectManagement.Project.Management.Board.API.Repositories;

import com.ProjectManagement.Project.Management.Board.API.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByBoardId(Long boardId);
}
