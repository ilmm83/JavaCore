package edu.mvc;

public record MyModel() {

    public Student getStudent() {
        var student = new Student();
        student.setName("Andrey");

        return student;
    }
}
