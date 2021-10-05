package br.com.molina.controller.dto;

import lombok.Data;

@Data
public class IntervaDto{
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;
}

