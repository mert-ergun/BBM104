public class Member extends Person {

    private double weight;
    private double height;

    Member(int id, String name, String surname, double weight, double height) {
         this.id = id;
         this.name = name;
         this.surname = surname;
         this.weight = weight;
         this.height = height;
     }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    private double bmi() {
         return weight / (height * height);
    }

    public String weightStatus() {
         double control = bmi();
         String status;
         if (control < 18.5)
             status = "Thin";
         else if (control < 25)
             status = "Normal";
         else if (control < 30)
             status = "Fat";
         else
             status = "Obese";

         return status;
    }
}
