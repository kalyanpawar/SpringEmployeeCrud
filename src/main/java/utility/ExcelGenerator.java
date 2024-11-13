package utility;

import com.example.employee_crud.entity.Employee;
import com.example.employee_crud.entity.Email;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelGenerator {

    public static ByteArrayInputStream employeesToExcel(List<Employee> employees) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Employees");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "First Name", "Last Name", "Age", "Salary",
                    "Date of Joining", "Date of Birth", "Email Type", "Email Address"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            int rowIdx = 1;

            for (Employee employee : employees) {
                List<Email> emails = employee.getEmails();
                int emailRows = emails.size();

                // Create a new row for each email type or merge cells for other fields
                for (int i = 0; i < emailRows; i++) {
                    Row row = sheet.createRow(rowIdx++);
                    if (i == 0) { // Populate employee details only once per employee
                        row.createCell(0).setCellValue(employee.getId());
                        row.createCell(1).setCellValue(employee.getFirstName());
                        row.createCell(2).setCellValue(employee.getLastName());
                        row.createCell(3).setCellValue(employee.getAge());
                        row.createCell(4).setCellValue(employee.getSalary());
                        row.createCell(5).setCellValue(employee.getDateOfJoining().toString());
                        row.createCell(6).setCellValue(employee.getDob().toString());

                        // Merge cells for columns other than emails
                        for (int col = 0; col < 7; col++) {
                            sheet.addMergedRegion(new CellRangeAddress(rowIdx - 1, rowIdx + emailRows - 2, col, col));
                        }
                    }

                    // Populate email fields
                    row.createCell(7).setCellValue(emails.get(i).getType());
                    row.createCell(8).setCellValue(emails.get(i).getEmail());
                }
            }

            // Adjust column widths for better readability
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate Excel file", e);
        }
    }
}

