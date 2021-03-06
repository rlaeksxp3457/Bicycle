package database;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Add_fileDao {
    DBDriver dbDriver = new DBDriver();
    private PreparedStatement pstmt;
    private ResultSet rs;

    //Add_fileDto 를 F_Board_id 값으로 객체 리턴 처리
    public Add_fileDto getDto(int id) {
        Connection connection = dbDriver.connDB();
        String sql = "select * from add_file where F_Board_id= ? ";
        Add_fileDto add_FileDto = new Add_fileDto();
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int file_Id = rs.getInt("File_Id");
                String file_Name = rs.getString("File_name");
                int file_Size = rs.getInt("File_size");
                String file_Dir = rs.getString("File_dir");
                String file_Contenttype = rs.getString("File_contenttype");
                int f_Board_Id = rs.getInt("F_Board_id");
                String file_Regdate = rs.getString("File_regdate");
                String file_Editdate = rs.getString("File_editdate");
                int F_Member_id = rs.getInt("F_Member_id");

                add_FileDto.setFile_id(file_Id);
                add_FileDto.setFile_name(file_Name);
                add_FileDto.setFile_size(file_Size);
                add_FileDto.setFile_dir(file_Dir);
                add_FileDto.setFile_contenttype(file_Contenttype);
                add_FileDto.setF_Board_id(f_Board_Id);
                add_FileDto.setFile_regdate(file_Regdate);
                add_FileDto.setFile_editdate(file_Editdate);
                add_FileDto.setF_Member_id(F_Member_id);
                dbDriver.closeAll(rs, pstmt, connection);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return add_fileDto;
    }

    //add_file 테이블 insert 처리
    public boolean insert(String file_name, int file_size, String file_dir, String file_Contenttype, int f_Board_id,
                          int Member_id) {
        String sql = "insert into add_file (File_name, File_size, File_dir, File_contenttype, F_Board_id, F_Member_id) values(?,?,?,?,?,?)";
        Connection connection = dbDriver.connDB();
        boolean check;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, file_name);
            pstmt.setInt(2, file_size);
            pstmt.setString(3, file_dir);
            pstmt.setString(4, file_Contenttype);
            pstmt.setInt(5, f_Board_id);
            pstmt.setInt(6, Member_id);
            pstmt.executeUpdate();
            check = true;
            dbDriver.closeAll(pstmt, connection);
        } catch (SQLException e) {
            check = false;
            e.printStackTrace();
        }
        return check;

    }

    //add_file 테이블 update 처리
    public boolean update(String file_name, int file_size, String file_dir, String file_Contenttype, int f_Board_id) {
        String sql = "update add_file set File_name= ?, File_size = ?,File_dir = ?, File_contenttype = ?  WHERE F_Board_id = ?";
        Connection connection = dbDriver.connDB();
        boolean check;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, file_name);
            pstmt.setInt(2, file_size);
            pstmt.setString(3, file_dir);
            pstmt.setString(4, file_Contenttype);
            pstmt.setInt(5, f_Board_id);
            pstmt.executeUpdate();
            check = true;
            dbDriver.closeAll(pstmt, connection);
        } catch (SQLException e) {
            check = false;
            e.printStackTrace();
        }

        return check;

    }

    //글 수정,삭제 이미지파일 제거
    public void deleteFile(String file, HttpServletRequest request) {
        ServletContext context = request.getSession().getServletContext();
        String saveDir = context.getRealPath("web/upload");
        String filePath = saveDir + file;

        File deleteFile = new File(filePath);

        if (deleteFile.exists()) {
            deleteFile.delete();
        }
    }

    //회원 챌린지 등록시 아이디값으로 폴더 생성
    public void createFolder(String Dir) {

        File Folder = new File(Dir);


        if (!Folder.exists()) {
            try {
                Folder.mkdir();

            } catch (Exception e) {
                e.getStackTrace();
            }
        } else {

        }
    }


}
