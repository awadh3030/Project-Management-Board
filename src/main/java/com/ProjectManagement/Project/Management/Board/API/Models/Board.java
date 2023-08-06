package com.ProjectManagement.Project.Management.Board.API.Models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long id;
    public String title;
    public String columns;

    @OneToMany(mappedBy="board")
    public List<Card> cards;
    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

}
