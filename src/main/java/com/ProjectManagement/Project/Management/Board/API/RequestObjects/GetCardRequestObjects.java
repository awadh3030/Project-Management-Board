package com.ProjectManagement.Project.Management.Board.API.RequestObjects;

import lombok.Data;

public class GetCardRequestObjects {

    @Data
    public class CardRequest {
        private String title;
        private String description;
        private int section;
    }

}