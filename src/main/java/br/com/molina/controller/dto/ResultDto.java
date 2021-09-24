package br.com.molina.controller.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResultDto {
	public List<IntervaDto> min;
	public List<IntervaDto> max;
}