package com.ProjectManagement.Project.Management.Board.API.ResponseObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCardResponse {
    private Long id;
    public String title;
    public String description;
    public int section;

}
