package com.hitsz.pae.sevice.Impl;/*
 *@Author:Simon
 *@Date: 2025-03-04 - 2025 03 04 20:50
 *@Description:practiceAndExam
 *@version:1.0
 */

import com.hitsz.pae.exceptions.BaseExceptionInterface;
import com.hitsz.pae.exceptions.BizException;
import com.hitsz.pae.mapper.StudentMapper;
import com.hitsz.pae.pojo.stu.Student;
import com.hitsz.pae.sevice.FileService;
import com.hitsz.pae.util.FileTypeChecker;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private final StudentMapper studentMapper;

    public FileServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Transactional
    public void uploadFile(MultipartFile file){
        List<Student> students = List.of();
        try(InputStream inputStream = file.getInputStream()){
            if(FileTypeChecker.isCsv(file)){
                processCsvFile(file);
            }else if(FileTypeChecker.isXLSX(file)){
                Workbook workbook = new XSSFWorkbook(inputStream);
                students = processXLSXFile(workbook);
            }else if (FileTypeChecker.isXLS(file)) {
                Workbook workbook = new HSSFWorkbook(inputStream);
                students = processXLSXFile(workbook);
            }else{
                throw new BizException(new BaseExceptionInterface() {
                    @Override
                    public String getErrorCode() {
                        return "401";
                    }
                    @Override
                    public String getErrorMessage() {
                        return "File Type Wrong";
                    }
                });
            }
        }catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }

        for(Student student:students){
            student.setId(studentMapper.selectByIdCard(student.getIdCard()));
            if(student.getId()!=null){
                studentMapper.updateStudent(student);
                studentMapper.delProfession(student.getId());
            }else{
                studentMapper.insertStudent(student);
            }
            studentMapper.insertProfession(student.getId(), student.getProfession());
        }
    }


    private List<Student> processXLSXFile(Workbook workbook){
        try {
            Sheet sheet = workbook.getSheetAt(0);
            List<Student> students = new LinkedList<>();
            boolean i = false;

            for (Row row : sheet) {
                if(!i){
                    i = true;
                    continue;
                }
                Student student = new Student();
                Cell nameCell = row.getCell(0);
                if(nameCell!=null && nameCell.getCellType()==CellType.STRING){
                    student.setName(nameCell.getStringCellValue());
                }

                Cell idCell = row.getCell(1);
                DataFormatter formatter = new DataFormatter();
                String idCard = formatter.formatCellValue(idCell);
                student.setIdCard(idCard);

                Cell professionsCell = row.getCell(2);
                if(professionsCell!=null && professionsCell.getCellType()==CellType.STRING){
                    String profession = professionsCell.getStringCellValue();
                    List<String> professions = List.of(profession.split("[,，\\s;]"));
                    List<Integer> professionInt = professions.stream().map(s -> switch (s) {
                        case "焊工" -> 3;
                        case "高处" -> 2;
                        case "高压" -> 1;
                        default -> 0;
                    }).toList();
                    student.setProfession(professionInt);
                }
                Cell dateCell = row.getCell(3);
                if(dateCell != null && dateCell.getCellType()==CellType.NUMERIC){
                    Date date = dateCell.getDateCellValue(); // 获取日期值
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                    String formattedDate = dateFormat.format(date); // 格式化日期输出
                    student.setDate(YearMonth.parse(formattedDate));
                }
                Cell adminCell = row.getCell(4);
                if(adminCell != null && adminCell.getCellType()==CellType.NUMERIC){
                    student.setAdminId((int)adminCell.getNumericCellValue());
                }
                students.add(student);
            }
            workbook.close();
            return students;
        } catch (IOException e) {
            log.error("处理文件出错{}", e.getMessage());
            throw new BizException(new BaseExceptionInterface() {
                @Override
                public String getErrorCode() {
                    return "401";
                }
                @Override
                public String getErrorMessage() {
                    return "处理文件时出错" + e.getMessage();
                }
            });
        }
    }

    private void processCsvFile(MultipartFile file){
        try(InputStreamReader reader = new InputStreamReader(file.getInputStream());
            BufferedReader br = new BufferedReader(reader)){

            List<Student> list = new CsvToBeanBuilder<Student>(br)
                    .withType(Student.class)
                    .build()
                    .parse();

            for(Student student : list){
                System.out.println(student);
            }
        }catch (IOException e){
            log.error(e.getMessage());
        }
    }
}
