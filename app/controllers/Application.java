package controllers;

import models.Users;
import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.util.List;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }
    public static Result display()
    {
        List<Users> users=Users.userList();
        String responseResult=flash("responseResult");

        return ok(views.html.users.display.render(users,responseResult));
    }
    public static Result createUser(){
        Form<Users> form=Form.form(Users.class).bindFromRequest();
        Users user=new Users();
        user.name=form.field("name").value();
        user.email=form.field("email").value();
        user.phoneNumber=form.field("phoneNumber").value();
        user.save();
        flash("responseResult","User Saved successful");
        String responseResult=flash("responseResult");
        return ok(Json.toJson(responseResult));
    }
    public static  Result deleteById(int id){
        Users user=new Users();
        try {
        return ok(Json.toJson(user.deleteById(id)));
        }
        catch (Exception e){
            return  badRequest(Json.toJson(e.getMessage()));
        }
    }
    public static  Result findUserById(int id){
        Users user=Users.finder.byId(id);
       return ok(views.html.users.edit.render(user));
    }
    public static Result updateUser(){
        Form<Users> form=Form.form(Users.class).bindFromRequest();
        Users user=new Users();
        user.id=Integer.parseInt(form.field("id").value());
        user.name=form.field("name").value();
        user.email=form.field("email").value();
        user.phoneNumber=form.field("phoneNumber").value();
        user.update();
        flash("responseResult","User updated successful");
//        return  ok(views.html.users.display.render(users,""));
        return redirect("/display");
    }
}
