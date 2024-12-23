package models;

import org.h2.engine.User;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Users extends  Model{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public  String name;
    public String phoneNumber;
    public  String email;
    public static Model.Finder<Integer, Users> finder = new Model.Finder<>(Integer.class, Users.class);
    public static List<Users> userList(){
        return  finder.findList();
    }
    public  Users(){

    }
 public Users(Users user){
       if(user.name.equals(""))throw new RuntimeException("Name is required") ;
      this.name=user.name;
      if(user.phoneNumber.equals(""))throw new RuntimeException("Phone number is required");
      this.phoneNumber=user.phoneNumber;
      if(user.email.equals(""))throw new RuntimeException("Email is required");
       this.email=user.email;
       this.save();
    }
    public  static String deleteById(int i){
        try {
            finder.byId(i).delete();
            return  "User deleted succesful";
        }catch (Exception e){
            return  e.getMessage();
        }
    }
}

