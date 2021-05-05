package com.codingdojo.FundraisingProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

@SpringBootApplication
public class FundraisingProjectApplication {

	public static final String SAMPLE_XLSX_FILE_PATH = "D:/PROJECT UK/JOHN/02 Forecasts by market.xlsx";
	
	public static void main(String[] args) {
		SpringApplication.run(FundraisingProjectApplication.class, args);
		
	}

}
