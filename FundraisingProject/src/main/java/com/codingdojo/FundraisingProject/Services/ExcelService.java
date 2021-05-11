package com.codingdojo.FundraisingProject.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codingdojo.FundraisingProject.Util.ExcelUtil;

@Service
public class ExcelService {

	@Autowired
	ExcelUtil excelUtil;

	public String excelUrl = "D:\\test excel\\";

	public void readData(MultipartFile multipartFile) throws IOException, EncryptedDocumentException, InvalidFormatException, ParseException {

			String filepath = excelUrl + multipartFile.getOriginalFilename();

			byte[] bytes = multipartFile.getBytes();
			java.nio.file.Path path = Paths.get(excelUrl + multipartFile.getOriginalFilename());
			Files.write(path, bytes);


			excelUtil.getSheetDetails(filepath);
			System.out.println("made it past get sheet details");
		 /*response=*/	excelUtil.readExcelSheet(filepath);
		 System.out.println("made it through read excel!!!");

	}

}
