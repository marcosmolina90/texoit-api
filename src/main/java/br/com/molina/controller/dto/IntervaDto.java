package br.com.molina.controller.dto;

import lombok.Data;

@Data
public class IntervaDto{
    public String producer;
    public int interval;
    public int previousWin;
    public int followingWin;
}

