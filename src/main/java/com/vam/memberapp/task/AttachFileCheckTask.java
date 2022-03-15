package com.vam.memberapp.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vam.memberapp.model.dao.AdminDaoImpl;
import com.vam.memberapp.model.dto.AttachImageVO;
import java.io.File;
@Component
public class AttachFileCheckTask {
	@Autowired
	private AdminDaoImpl adminDaoImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(AttachFileCheckTask.class);
	
	private String getFolderYesterDay() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DATE, -1);
		
		String str = sdf.format(cal.getTime());
		
		return str.replace("-", File.separator);
	}	
	
	@Scheduled(cron="0 0 1 * * *")
	public void checkFiles() throws Exception{	
		
		logger.warn("File Check Task Run..........");
		logger.warn("date :::>>>>>" ,new Date());
		logger.warn("========================================");		
		
		// DB�� ����� ���� ����Ʈ
		List<AttachImageVO> fileList = adminDaoImpl.checkFileList();		
		
		
		// �� ���� ���� ����Ʈ(Path��ü)
		List<Path> checkFilePath = new ArrayList<Path>();
			//���� �̹���
		fileList.forEach(vo -> {
			Path path = Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
			checkFilePath.add(path);
		});		
			//����� �̹���
		fileList.forEach(vo -> {
			Path path = Paths.get("C:\\upload", vo.getUploadPath(), "s_" +  vo.getUuid() + "_" + vo.getFileName());
			checkFilePath.add(path);
		});
		
		
		// ���丮 ���� ����Ʈ
		File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile();
		File[] targetFile = targetDir.listFiles();
		
		
		// ���� ��� ���� ����Ʈ(�з�)
		List<File> removeFileList = new ArrayList<File>(Arrays.asList(targetFile));		
		for(File file : targetFile){
			checkFilePath.forEach(checkFile ->{
				if(file.toPath().equals(checkFile)) 
					removeFileList.remove(file);	
			});
		}
		
		
		// ���� ��� ���� ����
		logger.warn("file Delete : ");
		for(File file : removeFileList) {
			logger.warn("file"+ file);
			file.delete();
		}		
		
		logger.warn("========================================");
		
	}
	
}
