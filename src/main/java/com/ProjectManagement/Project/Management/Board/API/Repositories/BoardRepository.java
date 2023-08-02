package com.ProjectManagement.Project.Management.Board.API.Repositories;

import com.ProjectManagement.Project.Management.Board.API.Models.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
