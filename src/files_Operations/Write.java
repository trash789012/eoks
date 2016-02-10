package files_Operations;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ������� on 23.11.2015.
 */
public class Write {
    public static void write(String fileName, String text) throws IOException {
        //���������� ����
        File file = new File(fileName);

        try {
            //���������, ��� ���� ���� �� ���������� �� ������� ���
            if(!file.exists()){
                file.createNewFile();
            }

            //PrintWriter ��������� ����������� ������ � ����
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                //���������� ����� � ����
                out.print(text);
            } finally {
                //����� ���� �� ������ ������� ����
                //����� ���� �� ���������
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
