package database;

public class Add_fileDto {
    int file_Id;
    String file_Name;
    int file_Size;
    String file_Dir;
    String file_Content_Type;
    int f_Board_Id;
    String file_Reg_Date;
    String file_Edit_Date;
    int f_Member_Id;

    public int getFile_id() {
        return file_Id;
    }

    public void setFile_id(int file_id) {
        file_Id = file_id;
    }

    public String getFile_name() {
        return file_Name;
    }

    public void setFile_name(String file_name) {
        file_Name = file_name;
    }

    public int getFile_size() {
        return file_Size;
    }

    public void setFile_size(int file_size) {
        file_Size = file_size;
    }

    public String getFile_dir() {
        return file_Dir;
    }

    public void setFile_dir(String file_dir) {
        file_Dir = file_dir;
    }

    public String getFile_contenttype() {
        return file_Content_Type;
    }

    public void setFile_contenttype(String file_Content_Type) {
        this.file_Content_Type = file_Content_Type;
    }

    public int getF_Board_id() {
        return f_Board_Id;
    }

    public void setF_Board_id(int f_Board_id) {
        f_Board_Id = f_Board_id;
    }

    public String getFile_regdate() {
        return file_Reg_Date;
    }

    public void setFile_regdate(String file_regdate) {
        file_Reg_Date = file_regdate;
    }

    public String getFile_editdate() {
        return file_Edit_Date;
    }

    public void setFile_editdate(String file_editdate) {
        file_Edit_Date = file_editdate;
    }

    public int getF_Member_id() {
        return f_Member_Id;
    }

    public void setF_Member_id(int f_Member_id) {
        f_Member_Id = f_Member_id;
    }

}
