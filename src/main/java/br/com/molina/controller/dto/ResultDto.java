package br.com.molina.controller.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResultDto {
	private List<IntervaDto> min;
	private List<IntervaDto> max;
}