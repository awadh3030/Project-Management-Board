package com.ProjectManagement.Project.Management.Board.API.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    public String description;
    public int section;

    @ManyToOne
    @JoinColumn(name="board_id", nullable=false)
    @JsonIgnore
    public Board board;
}
