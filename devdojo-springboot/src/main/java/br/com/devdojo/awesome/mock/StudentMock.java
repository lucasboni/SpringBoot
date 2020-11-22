package br.com.devdojo.awesome.mock;

import br.com.devdojo.awesome.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentMock {
    public static List<Student> studentList;
    static {
        criarLista();
    }

    private static void criarLista(){
        studentList = new ArrayList<>(Arrays.asList(new Student(1,"Deku"),new Student(2,"Todoroki")));
    }

}
