package com.ProjectManagement.Project.Management.Board.API.RequestObjects;

import lombok.Data;
@Data
public class GetCardRequestObjects {

        private String title;
        private String description;
        private int section;


}