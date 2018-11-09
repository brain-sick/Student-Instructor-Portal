import javafx.application.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.io.*;
import java.lang.*;
import javafx.scene.control.cell.*;
import java.util.*;

class Process{

    private ObservableList<Student> students;
    public Process()
    {
        students= FXCollections.observableArrayList();
    }
    public ObservableList<Student> getStudents()
    {
        return this.students;
    }
    public String getMean1()
    {
        int mean=0;
        for(int i=0;i<students.size();i++)
        {
            mean+=students.get(i).getMarks1();
        }
        mean=mean/(students.size());
        return mean+"";
    }
    public String getMean2()
    {
        int mean=0;
        for(int i=0;i<students.size();i++)
        {
            mean+=students.get(i).getMarks2();
        }
        mean=mean/(students.size());
        return mean+"";
    }
    public String getMedian1()
    {
        ArrayList <Integer> nums= new ArrayList<Integer>();
        for(int i=0;i<students.size();i++)
        {
            nums.add(students.get(i).getMarks1());
        }
        Collections.sort(nums);
        int s=nums.size();
        int res=(nums.get((s/2)-1)+nums.get(((s+1)/2)-1))/2;
        return res+"";
    }
    public String getMedian2()
    {
        ArrayList <Integer> nums= new ArrayList<Integer>();
        for(int i=0;i<students.size();i++)
        {
            nums.add(students.get(i).getMarks2());
        }
        Collections.sort(nums);
        int s=nums.size();
        int res=(nums.get((s/2)-1)+nums.get(((s+1)/2)-1))/2;
        return res+"";
    }
    public String getMax1()
    {
        int maxv=0;
        for(int i=0;i<students.size();i++)
        {
            if(students.get(i).getMarks1()>maxv)
                maxv=students.get(i).getMarks1();
        }
        return maxv+"";
    }
    public String getMin1()
    {
        int minv=20;
        for(int i=0;i<students.size();i++)
        {
            if(students.get(i).getMarks1()<minv)
                minv=students.get(i).getMarks1();
        }
        return minv+"";
    }
    public String getMax2()
    {
        int maxv=0;
        for(int i=0;i<students.size();i++)
        {
            if(students.get(i).getMarks2()>maxv)
                maxv=students.get(i).getMarks2();
        }
        return maxv+"";
    }
    public String getMin2()
    {
        int minv=20;
        for(int i=0;i<students.size();i++)
        {
            if(students.get(i).getMarks2()<minv)
                minv=students.get(i).getMarks2();
        }
        return minv+"";
    }
    public void readFromCSV(String csvFile){
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(",");
                students.add(createStudent(tempArr));
            }
            br.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void addStudent(String name,String roll,String sm1,String sm2)
    {
        int m1=Integer.parseInt(sm1);
        int m2=Integer.parseInt(sm2);
        students.add(new Student(roll,name,m1,m2,m1+m2));
    }
    public void delStudents(String roll)
    {
        for(int i=0;i<students.size();i++)
        {
            if(roll.equalsIgnoreCase(students.get(i).getRoll()))
            {
                students.remove(i);
                break;
            }

        }
    }
    public Student createStudent(String[] info)
    {
        String roll=info[1];
        String name=info[2];
        int marks1=Integer.parseInt(info[3]);
        int marks2=Integer.parseInt(info[4]);
        int total=marks1+marks2;
        Student newSt=new Student(roll,name,marks1,marks2,total);
        return newSt;
    }
    public void display()
    {
        System.out.println(students.size());
        for(int i=1;i<students.size();i++)
        {
            System.out.println(students.get(i));
        }
    }
    public Student find(String roll)
    {
        for(Student st:students)
        {
            if(roll.equalsIgnoreCase(st.getRoll()))
               return st;
        }
        Student temp=new Student("NIL","NIL",0,0,0);
        return temp;
    }
    public void editMarks(String roll,String smarks1,String smarks2)
    {
        System.out.println(roll);
        for(int i=0;i<students.size();i++)
        {
            if(roll.equalsIgnoreCase(students.get(i).getRoll()))
            {
                int marks1,marks2;
                if(smarks1.compareTo("")==0)
                    marks1=students.get(i).getMarks1();
                else
                    marks1=Integer.parseInt(smarks1);
                if(smarks2.compareTo("")==0)
                    marks2=students.get(i).getMarks2();
                else
                    marks2=Integer.parseInt(smarks2);

                Student temp= new Student(roll.toUpperCase(),students.get(i).getName(),marks1,marks2,marks1+marks2);
                students.set(i,temp);
            }
        }
    }
    public void write(String path){
        try{
            FileWriter file1 = new FileWriter(path);
            for(int j=0; j< students.size(); j++){
                String str1 = (j+1)+","+students.get(j).getRoll()+","+students.get(j).getName()+","+students.get(j).getMarks1()+","+students.get(j).getMarks2();
                for (int i = 0; i < str1.length(); i++) {
                    file1.write(str1.charAt(i));
                }
                file1.write('\n');
            }

            file1.close();
        }
        catch(IOException fileException){
            fileException.printStackTrace();
        }
    }
}
public class StudentInstructor extends Application  {


    static Process op;
    public static void main(String[] args) {
        op=new Process();
        launch(args);

    }

    @Override
    public void start(Stage window) throws Exception {
        Scene scene,studentScene,iScene;
        window.setTitle("Studnet-Instructor Portal");
        Label l1 = new Label("Welcome to the Portal");
        Button b1 = new Button("Student");
        Button b2 = new Button("Intructor");
        Label l2=new Label("Specifiy the file path");
        Label l3=new Label();
        TextField filePath=new TextField();
        filePath.setMaxWidth(300);
        Button b3=new Button("Read CSV");
        VBox layout = new VBox(20);
        VBox studentlayout= new VBox(20);
        Button b4=new Button("Display entire list");
        Label l4=new Label("Enter Roll to display marks");
        TextField rollField=new TextField();
        rollField.setMaxWidth(150);
        Button b5=new Button("Display");
        Button save = new Button("Edit changes to CSV");
        TableView<Student> result;
        layout.getChildren().addAll(l1, b1, b2,l2,filePath,b3,l3,save);
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout, 700, 700);
        window.setScene(scene);
        //table for student window
        TableColumn<Student, String> rollColumn = new TableColumn<>("Roll");
        rollColumn.setCellValueFactory(new PropertyValueFactory<>("roll"));
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Student, Integer> marks1Column = new TableColumn<>("Question1");
        marks1Column.setCellValueFactory(new PropertyValueFactory<>("marks1"));
        TableColumn<Student, Integer> marks2Column = new TableColumn<>("Question2");
        marks2Column.setCellValueFactory(new PropertyValueFactory<>("marks2"));
        TableColumn<Student, Integer> totalColumn = new TableColumn<>("Grand Total");
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        save.setOnAction(e->{
            op.write(filePath.getText());
        });
        b4.setOnAction(e->{
            TableView<Student> table = new TableView<>();
            table.setItems(op.getStudents());
            table.getColumns().addAll(rollColumn, nameColumn, marks1Column, marks2Column,totalColumn);

            VBox entries=new VBox(20);
            entries.getChildren().addAll(table);
            entries.setAlignment(Pos.CENTER);
            Scene sc=new Scene(entries,500,500);
            Stage window2 = new Stage();
            window2.setTitle("Students data");
            window2.setScene(sc);
            window2.show();
        });
        Button b6=new Button("back to main window");
        b6.setOnAction(e->{
            window.setScene(scene);
        });
        Button summary = new Button("click for summary");
        Button isummary = new Button("click for summary");
        summary.setOnAction(e->{
            VBox data= new VBox(5);
            Label qn1 = new Label("Question 1");
            Label t1=new Label("Mean");
            Label t2=new Label("Median");
            Label t3=new Label("Max");
            Label t4=new Label("Min");
            Label d1 = new Label();
            Label d2 = new Label();
            Label d3 = new Label();
            Label d4 = new Label();
            d1.setText(op.getMean1());
            d2.setText(op.getMedian1());
            d3.setText(op.getMax1());
            d4.setText(op.getMin1());
            Label qn2 = new Label("Question 2");
            Label t12=new Label("Mean");
            Label t22=new Label("Median");
            Label t32=new Label("Max");
            Label t42=new Label("Min");
            Label d12 = new Label();
            Label d22 = new Label();
            Label d32 = new Label();
            Label d42 = new Label();
            d12.setText(op.getMean2());
            d22.setText(op.getMedian2());
            d32.setText(op.getMax2());
            d42.setText(op.getMin2());
            data.getChildren().addAll(qn1,t1,d1,t2,d2,t3,d3,t4,d4,qn2,t12,d12,t22,d22,t32,d32,t42,d42);
            data.setAlignment(Pos.CENTER);
            Scene wow=new Scene(data,700,700);
            Stage window3 = new Stage();
            window3.setTitle("Summary");
            window3.setScene(wow);
            window3.show();
        });
        isummary.setOnAction(e->{
            VBox data= new VBox(5);
            Label qn1 = new Label("Question 1");
            Label t1=new Label("Mean");
            Label t2=new Label("Median");
            Label t3=new Label("Max");
            Label t4=new Label("Min");
            Label d1 = new Label();
            Label d2 = new Label();
            Label d3 = new Label();
            Label d4 = new Label();
            d1.setText(op.getMean1());
            d2.setText(op.getMedian1());
            d3.setText(op.getMax1());
            d4.setText(op.getMin1());
            Label qn2 = new Label("Question 2");
            Label t12=new Label("Mean");
            Label t22=new Label("Median");
            Label t32=new Label("Max");
            Label t42=new Label("Min");
            Label d12 = new Label();
            Label d22 = new Label();
            Label d32 = new Label();
            Label d42 = new Label();
            d12.setText(op.getMean2());
            d22.setText(op.getMedian2());
            d32.setText(op.getMax2());
            d42.setText(op.getMin2());
            data.getChildren().addAll(qn1,t1,d1,t2,d2,t3,d3,t4,d4,qn2,t12,d12,t22,d22,t32,d32,t42,d42);
            data.setAlignment(Pos.CENTER);
            Scene wow=new Scene(data,500,500);
            Stage window3 = new Stage();
            window3.setTitle("Summary");
            window3.setScene(wow);
            window3.show();
        });
        studentlayout.getChildren().addAll(b4,l4,rollField,b5,b6,summary);
        studentlayout.setAlignment(Pos.CENTER);
        studentScene=new Scene(studentlayout,700,700);
        b1.setOnAction(e->{
                window.setScene(studentScene);
        });
        //iScene
        VBox iLayout = new VBox(20);
        Button ib4=new Button("Display entire list");
        ib4.setOnAction(e->{
            TableView<Student> table = new TableView<>();
            table.setItems(op.getStudents());
            table.getColumns().addAll(rollColumn, nameColumn, marks1Column, marks2Column,totalColumn);
            VBox entries=new VBox(20);
            entries.getChildren().addAll(table);
            entries.setAlignment(Pos.CENTER);
            Scene sc=new Scene(entries,500,500);
            Stage window2 = new Stage();
            window2.setTitle("Students data");
            window2.setScene(sc);
            window2.show();
        });
        Label il4=new Label("Enter Roll to display marks");
        TextField irollField=new TextField();
        irollField.setMaxWidth(150);
        Button ib5=new Button("Display");
        Button ib6=new Button("back to main window");
        Button ib7 = new Button("Edit / Leave blank for not editing");
        Label il1 = new Label("Enter marks of Question 1");
        TextField marks1Field = new TextField();
        marks1Field.setMaxWidth(200);
        Label il2 = new Label("Enter marks of Question 2");
        TextField marks2Field = new TextField();
        marks2Field.setMaxWidth(200);
        Button ib8 = new Button("save");
        il1.setVisible(false);
        marks1Field.setVisible(false);
        il2.setVisible(false);
        marks2Field.setVisible(false);
        ib8.setVisible(false);
        ib6.setOnAction(e->{
            window.setScene(scene);
        });
        ib7.setOnAction(e->{
            il1.setVisible(true);
            marks1Field.setVisible(true);
            il2.setVisible(true);
            marks2Field.setVisible(true);
            ib8.setVisible(true);
        });
        Label confirm = new Label("");
        ib8.setOnAction(e->{
            op.editMarks(irollField.getText(),marks1Field.getText(),marks2Field.getText());
            confirm.setText("Modified");
            op.display();
            op.write(filePath.getText());
        });
        Button addSt = new Button("Add student");
        addSt.setOnAction(e->{

            Stage stg;
            Label name = new Label("Name");
            Label roll= new Label("Roll");
            Label m1 = new Label("Question 1");
            Label m2 = new Label("Question 2");
            TextField gname = new TextField();
            TextField groll = new TextField();
            TextField gm1 = new TextField();
            TextField gm2 = new TextField();
            Button submit = new Button("submit");
            Label done = new Label();
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setVgap(5);
            grid.setHgap(5);
            grid.setAlignment(Pos.CENTER);
            grid.add(name,0,0);
            grid.add(gname,1,0);
            grid.add(roll,0,1);
            grid.add(groll,1,1);
            grid.add(m1,0,2);
            grid.add(gm1,1,2);
            grid.add(m2,0,3);
            grid.add(gm2,1,3);
            grid.add(submit,0,4);
            grid.add(done,1,4);
            submit.setOnAction(e1->{
                op.addStudent(gname.getText(),groll.getText(),gm1.getText(),gm2.getText());
                done.setText("Done");
                op.write(filePath.getText());
            });
            Scene wah = new Scene(grid,400,400);
            stg = new Stage();
            stg.setScene(wah);
            stg.setTitle("enter new Student details");
            stg.show();

        });
        Button delSt = new Button("Delete Student");
        delSt.setOnAction(e2->{
           Label roll = new Label("enter roll to delete");
           TextField groll = new TextField();
           groll.setMaxWidth(150);
           Button submit = new Button("Delete");
           Label dial = new Label();
           submit.setOnAction(e3->{
               op.delStudents(groll.getText());
               dial.setText("deleted");
               op.write(filePath.getText());
           });
           VBox wo = new VBox(20);
           wo.setAlignment(Pos.CENTER);
           wo.getChildren().addAll(roll,groll,submit,dial);
           Scene sz = new Scene(wo,300,300);
           Stage royal = new Stage();
           royal.setTitle("Delete Students");
           royal.setScene(sz);
           royal.show();

        });
        iLayout.getChildren().addAll(ib4,il4,irollField,ib5,ib6,ib7,il1,marks1Field,il2,marks2Field,ib8,confirm,addSt,delSt,isummary);
        iLayout.setAlignment(Pos.CENTER);
        iScene = new Scene(iLayout,700,700);
        b2.setOnAction(e->{
            window.setScene(iScene);
        });
        b3.setOnAction(e->{
            File file=new File(filePath.getText());
            if(file.exists())
            {
               l3.setText("file found");
               op.readFromCSV(filePath.getText());
            }
            else
            {
                l3.setText("not found");
            }
        });
        HBox single=new HBox(10);
        Label pname=new Label("name:");
        Label tname=new Label();
        Label proll=new Label("roll:");
        Label troll=new Label();
        Label pq1=new Label("question1:");
        Label tq1=new Label();
        Label pq2=new Label("question2:");
        Label tq2=new Label();
        pname.setVisible(false);
        tname.setVisible(false);
        proll.setVisible(false);
        troll.setVisible(false);
        pq1.setVisible(false);
        tq1.setVisible(false);
        pq2.setVisible(false);
        tq2.setVisible(false);
        single.setAlignment(Pos.CENTER);
        pname.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : black;"
        );
        proll.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : black;"
        );
        pq1.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : black;"
        );
        pq2.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : black;"
        );
        tname.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : blue;"
        );
        troll.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : blue;"
        );
        tq1.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : blue;"
        );
        tq2.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : blue;"
        );
        //for student
        HBox ssingle=new HBox(10);
        Label spname=new Label("name:");
        Label stname=new Label();
        Label sproll=new Label("roll:");
        Label stroll=new Label();
        Label spq1=new Label("question1:");
        Label stq1=new Label();
        Label spq2=new Label("question2:");
        Label stq2=new Label();
        spname.setVisible(false);
        stname.setVisible(false);
        sproll.setVisible(false);
        stroll.setVisible(false);
        spq1.setVisible(false);
        stq1.setVisible(false);
        spq2.setVisible(false);
        stq2.setVisible(false);
        single.setAlignment(Pos.CENTER);
        spname.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : black;"
        );
        sproll.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : black;"
        );
        spq1.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : black;"
        );
        spq2.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : black;"
        );
       stname.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : blue;"
        );
        stroll.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : blue;"
        );
        stq1.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : blue;"
        );
        stq2.setStyle("-fx-border-style : solid inside;"+
                "-fx-border-width : 2;"+
                "-fx-border-color : blue;"
        );
        ssingle.setAlignment(Pos.CENTER);
        ssingle.getChildren().addAll(spname,stname,sproll,stroll,spq1,stq1,spq2,stq2);
        studentlayout.getChildren().add(ssingle);
        single.getChildren().addAll(pname,tname,proll,troll,pq1,tq1,pq2,tq2);
        iLayout.getChildren().add(single);
        b5.setOnAction(e->{
            Student found=op.find(rollField.getText());
            if(found.getRoll().compareTo("NIL")==0) {
                Label errorMessage = new Label("roll number not found");
                studentlayout.getChildren().add(errorMessage);
                e.consume();
            }
            spname.setVisible(true);
            stname.setVisible(true);
            sproll.setVisible(false);
            stroll.setVisible(true);
            spq1.setVisible(true);
            stq1.setVisible(true);
            spq2.setVisible(true);
            stq2.setVisible(true);
            stname.setText(found.getName());
            stroll.setText(found.getRoll());
            stq1.setText(found.getMarks1()+"");
            stq2.setText(found.getMarks2()+"");
        });
        ib5.setOnAction(e->{

            Student found=op.find(irollField.getText());
            if(found.getRoll().compareTo("NIL")==0) {
                Label errorMessage = new Label("roll number not found");
                iLayout.getChildren().add(errorMessage);
                e.consume();
            }

            pname.setVisible(true);
            tname.setVisible(true);
            proll.setVisible(false);
            troll.setVisible(true);
            pq1.setVisible(true);
            tq1.setVisible(true);
            pq2.setVisible(true);
            tq2.setVisible(true);
            tname.setText(found.getName());
            troll.setText(found.getRoll());
            tq1.setText(found.getMarks1()+"");
            tq2.setText(found.getMarks2()+"");


        });


        window.show();
    }

}