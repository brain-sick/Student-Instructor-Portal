public class Student{
    private String roll;
    private String name;
    private int marks1;
    private int marks2;
    private int total;
    public Student(String roll,String name,int marks1,int marks2,int total) {
        this.roll = roll;
        this.name = name;
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.total=marks1+marks2;
    }
    public int getMarks1()
    {
        return this.marks1;
    }
    public int getTotal()
    {
        return this.total;
    }
    public void setTotal(int total)
    {
        this.total=total;
    }
    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public int getMarks2()
    {
        return this.marks2;
    }
    public String getRoll(){
        return this.roll;
    }
    public void setRoll(String roll)
    {
        this.roll=roll;
    }
    public void setMarks1(int marks1)
    {
        this.marks1=marks1;
    }
    public void setMarks2(int marks2)
    {
        this.marks2=marks2;
    }
    public String toString()
    {
        return roll+" "+name+" "+marks1+" "+marks2;
    }
}