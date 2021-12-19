package com.group5.finalproject;

/*
*   Created by: Jerome T. Pasag
*   December 19,2021
*
 */
public class Quiview {
    /*
    *   Our get and set method for our data here
    *   Here we have the following (this is temporary)
    *
    *   Students (student id[PK], surname, firstname, gender, course) in database -> (login_time, created_at)
    *   Teachers (teacher_id[FK], surname, firstname, gender, subject) in database -> (login_time, created_at)
    *   Subject (subject_id[FK], subject name) in database -> (date_created)
    *   Question (question_id[FK], question) in database -> (
    *   Question_Choices (
    *   Answers (answer_id[FK], answer)
     */

    /*
        For Students
     */
    private String student_id;
    private String student_surname;
    private String student_firstname;
    private String student_gender;
    private String student_course;
    private String student_email; // In case
    private String student_pass; //In case

    //Student ID
    public String getStudentId(){
        return student_id;
    }
    public void setStudent_id(String student_id){
        this.student_id = student_id;
    }

    //Student Surname
    public String getStudentSurname(){
        return student_surname;
    }
    public void setStudent_surname(String student_surname){
        this.student_surname = student_surname;
    }

    //Student Firstname
    public String getStudentFirstname(){
        return student_firstname;
    }
    public void setStudentFirstname(String student_firstname){
        this.student_firstname = student_firstname;
    }

    //Student Gender
    public String getStudentGender(){
        return student_gender;
    }
    public void setStudentGender(String student_gender){
        this.student_gender = student_gender;
    }

    //Student Course
    public String getStudentCourse(){
        return student_course;
    }
    public void setStudentCourse(String student_course){
        this.student_course = student_course;
    }

    //Student Email
    public String getStudentEmail(){
        return student_email;
    }
    public void setStudentEmail(String student_email){
        this.student_email = student_email;
    }

    //Student password
    public String getStudentPassword(){
        return student_pass;
    }
    public void setStudentPassword(String student_pass){
        this.student_pass = student_pass;
    }

    /*
        For Teachers
     */

    private String teacher_id;
    private String teacher_surname;
    private String teacher_firstname;
    private String teacher_gender;
    private String teacher_subject;
    private String teacher_email;
    private String teacher_pass;

    //Teacher ID
    public String getTeacherId(){
        return teacher_id;
    }
    public void setTeacherId(String teacher_id){
        this.teacher_id = teacher_id;
    }

    //Teacher Surname
    public String getTeacherSurname(){
        return teacher_surname;
    }
    public void setTeacherSurname(String teacher_surname){
        this.teacher_surname = teacher_surname;
    }

    //Teacher Firstname
    public String getTeacherFirstname(){
        return teacher_firstname;
    }
    public void setTeacherFirstname(String teacher_firstname){
        this.teacher_firstname = teacher_firstname;
    }

    //Teacher Gender
    public String getTeacherGender(){
        return teacher_gender;
    }
    public void setTeacherGender(String teacher_gender){
        this.teacher_gender = teacher_gender;
    }

    //Teacher Subject
    public String getTeacherSubject(){
        return teacher_subject;
    }
    public void setTeacherSubject(String teacher_subject){
        this.teacher_subject = teacher_subject;
    }

    //Teacher Email
    public String getTeacherEmail(){
        return teacher_email;
    }
    public void setTeacherEmail(String teacher_email){
        this.teacher_email = teacher_email;
    }

    //Teacher Password
    public String getTeacherPassword(){
        return teacher_pass;
    }
    public void setTeacherPassword(String teacher_pass){
        this.teacher_pass = teacher_pass;
    }
}
