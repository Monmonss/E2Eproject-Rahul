package academy_rahul.utilities;


import academy_rahul.base.Base;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviderClass extends Base {

    ExcelReader excelReader;

    public DataProviderClass() {
        this.excelReader = new ExcelReader(System.getProperty("user.dir") + "\\src\\main\\resources\\excel\\details.xlsx");
    }

    @DataProvider(name = "Details")
    //data provider wspólny dla wszystkich. w ecelu sheet musi mieć taka samą nazwe jak metoda
    public Object[][] getCustomerDetails(Method method) {
        String sheetName = method.getName();
        int row = excelReader.getRowCount(sheetName);
        int column = excelReader.getColumnCount(sheetName);
        Object[][] data = new Object[row - 1][column];

        for (int rowNumber = 2; rowNumber <= row; rowNumber++) {
            for (int columnN = 0; columnN < column; columnN++) {
                data[rowNumber - 2][columnN] = excelReader.getCellData(sheetName, columnN, rowNumber);

            }

        }
        return data;
    }
}
