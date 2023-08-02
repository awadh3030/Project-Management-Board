package com.ProjectManagement.Project.Management.Board.API.ResponseObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetBoardResponse {
    public int id;
    public String title;
    private String[] columns;
}
