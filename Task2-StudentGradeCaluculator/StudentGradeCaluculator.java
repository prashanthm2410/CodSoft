import java.util.InputMismatchException;
import java.util.Scanner;
class StudentGradeCaluculator {
    static int total=0;
    public static void main(String[] args) {
        Grading gr = new Grading();
        System.out.print("Enter the Total Number of Subjects : ");
        Scanner read = new Scanner(System.in);
        int Subjects = read.nextInt();
        int marks[] = new int[Subjects];
        System.out.println("Enter the Respective Subjects Marks out of 100");
        for(int i=0;i<Subjects;i++) {
            System.out.print("Subject-"+(i+1)+"--->");
            try{
                marks[i] = read.nextInt();
        }catch(InputMismatchException e) {
            System.out.println("Invalid Type\nRe-Enter the Marks\n");
            read.nextLine();
            i--;
        }
    }
        read.close();
        gr.gradeAssign(averagePercentage(marks,Subjects),total);
    }
    private static float averagePercentage(int marks[],int Subjects) {
        for(int i = 0;i<Subjects;i++) {
            total+=marks[i];
        }
        return(total/Subjects);
    }
}
class Grading {
    Grading() {
        System.out.println("Grading System is as Follows");
        System.out.println("Average Percentage\tGrade");
        System.out.println("90-100\t\t\tS(Outstanding)");
        System.out.println("75-89\t\t\tA(Excellent)");
        System.out.println("66-74\t\t\tB(Very Good)");
        System.out.println("56-65\t\t\tC(Good)");
        System.out.println("50-55\t\t\tD(Average)");
        System.out.println("45-49\t\t\tE(Poor)");
        System.out.println("<45\t\t\tF(Fail)");
    }
    protected void gradeAssign(float averagePercentage,float total) {
        char Grade;
    if (averagePercentage>=90 && averagePercentage<=100) {
        Grade='S';
    }else if(averagePercentage>=75 && averagePercentage<=89) {
        Grade='A';
    }else if(averagePercentage>=66 && averagePercentage<=74) {
        Grade='B';
    }else if(averagePercentage>=56 && averagePercentage<=65) {
        Grade='C';
    }else if(averagePercentage>=50 && averagePercentage<=55) {
        Grade='D';
    }else if(averagePercentage>=45 && averagePercentage<=49) {
        Grade='E';
    }else if(averagePercentage<45) {
        Grade='F';
    }else{
        Grade='I';
    }
    display(total,averagePercentage,Grade);
    }
    protected void display(float total,float averagePercentage,char Grade) {
        System.out.println("Your Result as Follows");
        System.out.println("Total Marks--->"+total+"\nAverage Percentage--->"+averagePercentage+"\nGrade--->"+Grade);
    }
}
