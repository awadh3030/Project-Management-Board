package com.ProjectManagement.Project.Management.Board.API.RequestObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetBoardRequestObjects {

    public int id;
    public String title;
    public String columns;
}
