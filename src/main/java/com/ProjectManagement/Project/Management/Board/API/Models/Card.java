package com.ProjectManagement.Project.Management.Board.API.Models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class Card {

   private Long id;
    public String title;
    public String description;
    public int section;

}
